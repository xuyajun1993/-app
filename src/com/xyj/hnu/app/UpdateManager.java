package com.xyj.hnu.app;

import java.io.File;

import com.xyj.hnu.R;
import com.xyj.hnu.domain.Update;
import com.xyj.hnu.http.LatestVersion;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * 版本更新
 * 
 * @author xyj
 * 
 */
public class UpdateManager extends Service{
	private static final int DOWN_NOSDCARD = 0;
	private static final int DOWN_UPDATE = 1;
	private static final int DOWN_OVER = 2;
	private static final int DIALOG_TYPE_LATEST = 0;
	private static final int DIALOG_TYPE_FAIL = 1;
	private static UpdateManager updateManager;
	private Context context;
	private ProgressDialog dialog;
	// 通知对话框
	private Dialog noticeDialog;
	// '已经是最新' 或者 '无法获取最新版本' 的对话框
	private Dialog latestOrFailDialog;
	private Thread mthread;
	private Update update;
	private String savePath;
	private String apkFilePath;
	private String tmpFilePath;
	AppContext appContext;
	private Intent updateIntent;
	private PendingIntent pendingIntent;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case DOWN_NOSDCARD:
				Toast.makeText(context, "无法下载安装文件，请检查SD卡是否挂载", 3000).show();
				break;

			}
		};
	};

	// 单实例化
	public static UpdateManager getUpdateManager() {
		if (updateManager == null) {
			updateManager = new UpdateManager();
		}
		return updateManager;
	}

	// 检测新版本
	public void checkVersion(Context context) {
		this.context = context;
		// 显示正在检测对话框
		showCheckingDialog();
		// 得到本地版本号
		appContext = ((AppContext) context.getApplicationContext());
		int localVersion = appContext.getAppVersion();
		// 从网络上得到最新版本号
		update = LatestVersion.getUpdate();
		dialog.dismiss();
		if (localVersion < update.getVersionCode()) {
			// 弹出对话框，是否下载新版本
			showNoticeDialog();
		} else if (localVersion == update.getVersionCode()) {
			showLatestOrFailDialog(DIALOG_TYPE_LATEST);
		} else {
			showLatestOrFailDialog(DIALOG_TYPE_FAIL);
		}
	}

	//正在检测对话框
	private void showCheckingDialog() {
		dialog = ProgressDialog.show(context, null, "正在检测，请稍后...", true, true);
	}

	/**
	 * 显示'已经是最新'或者'无法获取版本信息'对话框
	 */
	private void showLatestOrFailDialog(int dialogType) {
		if (latestOrFailDialog != null) {
			// 关闭并释放之前的对话框
			latestOrFailDialog.dismiss();
			latestOrFailDialog = null;
		}
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle("系统提示");
		if (dialogType == DIALOG_TYPE_LATEST) {
			builder.setMessage("您当前已经是最新版本");
		} else if (dialogType == DIALOG_TYPE_FAIL) {
			builder.setMessage("无法获取版本更新信息");
		}
		builder.setPositiveButton("确定", null);
		latestOrFailDialog = builder.create();
		latestOrFailDialog.show();
	}

	/**
	 * 显示版本更新通知对话框
	 */
	private void showNoticeDialog() {
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle("软件版本更新");
		// builder.setMessage(updateMsg);
		builder.setPositiveButton("立即更新", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				createNotification();
			}
		});
		builder.setNegativeButton("以后再说", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		noticeDialog = builder.create();
		noticeDialog.show();
	}

	/**
	 * 创建通知栏
	 */
	protected void createNotification() {
		NotificationManager manager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification=new Notification();
		notification.icon=com.xyj.hnu.R.drawable.ic_launcher;
		notification.tickerText="开始下载";
		//自定义视图
		RemoteViews views=new RemoteViews(appContext.getPackageName(), com.xyj.hnu.R.layout.notification_item);
        views.setTextViewText(com.xyj.hnu.R.id.notificationTitle,"正在下载");
        views.setTextViewText(R.id.notificationPercent, "0%");
        views.setProgressBar(R.id.notificationProgress, 100, 0, false);
        notification.contentView=views;
        
        updateIntent=new Intent();
	}

	/**
	 * 下载apk
	 */
	public void downApk() {
		mthread = new Thread(mdownApkRunnable);
	}

	/**
	 * 下载apk的线程
	 */
	private Runnable mdownApkRunnable = new Runnable() {
		@Override
		public void run() {
			String apkName = "HNU" + update.getVersionName() + ".apk";
			String tmpApk = "HNU" + update.getVersionName() + ".tmp";
			// 判断是否挂载了sdcard
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				savePath = Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/HNU/Update/";
				File file = new File(savePath);
				if (!file.exists()) {
					file.mkdirs();
				}
				apkFilePath = savePath + apkName;
				tmpFilePath = savePath + tmpApk;
			} else {
				handler.sendEmptyMessage(DOWN_NOSDCARD);
			}
			File file = new File(apkFilePath);
			if (file.exists()) {
				installApk();
				return;
			}

		}

	};

	// 安装
	private void installApk() {
		File apkFile = new File(apkFilePath);
		if (!apkFile.exists())
			return;
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse("file://" + apkFile.toString()),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}
}

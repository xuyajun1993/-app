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
 * �汾����
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
	// ֪ͨ�Ի���
	private Dialog noticeDialog;
	// '�Ѿ�������' ���� '�޷���ȡ���°汾' �ĶԻ���
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
				Toast.makeText(context, "�޷����ذ�װ�ļ�������SD���Ƿ����", 3000).show();
				break;

			}
		};
	};

	// ��ʵ����
	public static UpdateManager getUpdateManager() {
		if (updateManager == null) {
			updateManager = new UpdateManager();
		}
		return updateManager;
	}

	// ����°汾
	public void checkVersion(Context context) {
		this.context = context;
		// ��ʾ���ڼ��Ի���
		showCheckingDialog();
		// �õ����ذ汾��
		appContext = ((AppContext) context.getApplicationContext());
		int localVersion = appContext.getAppVersion();
		// �������ϵõ����°汾��
		update = LatestVersion.getUpdate();
		dialog.dismiss();
		if (localVersion < update.getVersionCode()) {
			// �����Ի����Ƿ������°汾
			showNoticeDialog();
		} else if (localVersion == update.getVersionCode()) {
			showLatestOrFailDialog(DIALOG_TYPE_LATEST);
		} else {
			showLatestOrFailDialog(DIALOG_TYPE_FAIL);
		}
	}

	//���ڼ��Ի���
	private void showCheckingDialog() {
		dialog = ProgressDialog.show(context, null, "���ڼ�⣬���Ժ�...", true, true);
	}

	/**
	 * ��ʾ'�Ѿ�������'����'�޷���ȡ�汾��Ϣ'�Ի���
	 */
	private void showLatestOrFailDialog(int dialogType) {
		if (latestOrFailDialog != null) {
			// �رղ��ͷ�֮ǰ�ĶԻ���
			latestOrFailDialog.dismiss();
			latestOrFailDialog = null;
		}
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle("ϵͳ��ʾ");
		if (dialogType == DIALOG_TYPE_LATEST) {
			builder.setMessage("����ǰ�Ѿ������°汾");
		} else if (dialogType == DIALOG_TYPE_FAIL) {
			builder.setMessage("�޷���ȡ�汾������Ϣ");
		}
		builder.setPositiveButton("ȷ��", null);
		latestOrFailDialog = builder.create();
		latestOrFailDialog.show();
	}

	/**
	 * ��ʾ�汾����֪ͨ�Ի���
	 */
	private void showNoticeDialog() {
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle("����汾����");
		// builder.setMessage(updateMsg);
		builder.setPositiveButton("��������", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				createNotification();
			}
		});
		builder.setNegativeButton("�Ժ���˵", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		noticeDialog = builder.create();
		noticeDialog.show();
	}

	/**
	 * ����֪ͨ��
	 */
	protected void createNotification() {
		NotificationManager manager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification=new Notification();
		notification.icon=com.xyj.hnu.R.drawable.ic_launcher;
		notification.tickerText="��ʼ����";
		//�Զ�����ͼ
		RemoteViews views=new RemoteViews(appContext.getPackageName(), com.xyj.hnu.R.layout.notification_item);
        views.setTextViewText(com.xyj.hnu.R.id.notificationTitle,"��������");
        views.setTextViewText(R.id.notificationPercent, "0%");
        views.setProgressBar(R.id.notificationProgress, 100, 0, false);
        notification.contentView=views;
        
        updateIntent=new Intent();
	}

	/**
	 * ����apk
	 */
	public void downApk() {
		mthread = new Thread(mdownApkRunnable);
	}

	/**
	 * ����apk���߳�
	 */
	private Runnable mdownApkRunnable = new Runnable() {
		@Override
		public void run() {
			String apkName = "HNU" + update.getVersionName() + ".apk";
			String tmpApk = "HNU" + update.getVersionName() + ".tmp";
			// �ж��Ƿ������sdcard
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

	// ��װ
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

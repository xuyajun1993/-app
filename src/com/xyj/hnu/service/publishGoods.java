package com.xyj.hnu.service;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.xyj.hnu.R;

public class publishGoods extends Activity implements OnClickListener {

	private ImageView iv_select;
	private EditText et_barprice;
	private EditText et_oriprice;
	private EditText et_describe;
	private EditText et_phone;
	private Button btn_publish;
	private TextView tv_photo;
	private TextView tv_gallery;
	private TextView tv;
	private PopupWindow popw;
	private File file;
	private Uri uri;
	private ContentResolver resolver;
	private String path = "";
	private Bitmap bitmap;
	private Spinner type_spinner = null;
	private static final String[] type_datas = { "手机数码", "图书音像", "鞋帽衣饰",
			"学习工具", "其他" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.postsecondgoods);

		iv_select = (ImageView) findViewById(R.id.iv_selectpic);
		et_barprice = (EditText) findViewById(R.id.et_barprice);
		et_oriprice = (EditText) findViewById(R.id.et_oriprice);
		et_describe = (EditText) findViewById(R.id.et_describe);
		et_phone = (EditText) findViewById(R.id.et_phone);
		btn_publish = (Button) findViewById(R.id.btn_publish);
		type_spinner = (Spinner) findViewById(R.id.type_spinner);
		tv = (TextView) findViewById(R.id.textView2);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, type_datas);
		// 声明当控件打开时的外观：为系统提供的simple_spinner_dropdown_item
		arrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		type_spinner.setAdapter(arrayAdapter);
		// 为spinner添加选择事件监听器
		type_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("spinner" + position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		type_spinner.setVisibility(View.VISIBLE);
		iv_select.setOnClickListener(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1 && resultCode == RESULT_OK) {
			iv_select.setImageBitmap(getBitmap(uri.getPath()));
			path = uri.getPath();
			System.out.println(path);
		} else if (requestCode == 2 && resultCode == RESULT_OK) {
			Uri uri = data.getData();
			try {
				resolver = this.getContentResolver();
				// 打开输入流创建位图
//				bitmap = BitmapFactory.decodeStream(resolver
//						.openInputStream(uri));
//				iv_select.setImageBitmap(bitmap);
				iv_select.setImageBitmap(getBitmapfromStream(resolver
						.openInputStream(uri)));
				// 从手机内存中取出的照片
				path = getAbsoluteImagePath(uri);
				System.out.println(path);
			} catch (Exception e) {
				path = uri.getPath();
				System.out.println("erroe:" + path);
			}
		}
	}

	private Bitmap getBitmapfromStream(InputStream openInputStream) {
		BitmapFactory.Options options = new Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(openInputStream, null, options);
		int iv_width = iv_select.getWidth();
		int iv_height = iv_select.getHeight();
		int width = options.outWidth;
		int height = options.outHeight;
		// 计算缩放比例
		int scaleX = width / iv_width;
		int scaleY = height / iv_height;
		int scale = 1;
		if (scaleX > scaleY & scaleY > 1)
			scale = scaleX;
		if (scaleY > scaleX & scaleX > 1)
			scale = scaleY;
System.out.println("scale"+scale);
		options.inJustDecodeBounds = false;
		options.inSampleSize = scale;
		return BitmapFactory.decodeStream(openInputStream, null, options);
	}

	private Bitmap getBitmap(String url) {
		BitmapFactory.Options options = new Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(url, options);
		int iv_width = iv_select.getWidth();
		int iv_height = iv_select.getHeight();
		int width = options.outWidth;
		int height = options.outHeight;
		// 计算缩放比例
		int scaleX = width / iv_width;
		int scaleY = height / iv_height;
		int scale = 1;
		if (scaleX > scaleY & scaleY > 1)
			scale = scaleX;
		if (scaleY > scaleX & scaleX > 1)
			scale = scaleY;
		System.out.println("scale"+scale);
		options.inJustDecodeBounds = false;
		options.inSampleSize = scale;
		return BitmapFactory.decodeFile(url, options);
	}

	protected String getAbsoluteImagePath(Uri uri) {
		// can post image
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = resolver.query(uri, proj, // Which columns to return
				null, // WHERE clause; which rows to return (all rows)
				null, // WHERE clause selection arguments (none)
				null); // Order-by clause (ascending by name)

		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();

		return cursor.getString(column_index);
	}

	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"'IMG'_yyyyMMdd_HHmmss");
		return dateFormat.format(date) + ".jpg";
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_selectpic:
			View view = getLayoutInflater().inflate(R.layout.selectpop, null);
			tv_photo = (TextView) view.findViewById(R.id.tv_photo);
			tv_gallery = (TextView) view.findViewById(R.id.tv_gallery);
			tv_photo.setOnClickListener(this);
			tv_gallery.setOnClickListener(this);
			popw = new PopupWindow(view, iv_select.getWidth() * 2,
					LayoutParams.WRAP_CONTENT, true);
			popw.setBackgroundDrawable(new BitmapDrawable());
			popw.showAsDropDown(tv, (tv.getWidth() - popw.getWidth()) / 2, 30);
			break;

		case R.id.tv_photo:
			// 跳转到拍照,将照片存放在HNU文件夹下，文件名为当前时间
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			// 首先得考虑用户有没有sdcard，如果有就在sdcard上创建一个指定的文件夹，如果没有则在你的工程所在的目录“/data/data/你的包名”下创建文件夹
			String dir = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/HNU/Camera/";
			file = new File(dir);
			if (!file.exists()) {
				// 如果文件不存在，会创建相应文件夹
				file.mkdirs();
			}
			uri = Uri.fromFile(new File(file, getPhotoFileName()));
			intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
			startActivityForResult(intent, 1);
			break;

		case R.id.tv_gallery:
			// 转到图库
			Intent intent1 = new Intent();
			intent1.setType("image/*");
			intent1.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(intent1, 2);
			break;
		}
	}
}

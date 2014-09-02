package com.xyj.hnu.tools;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;

/**
 * 保存图片，json数据
 * 
 * @author xyj
 * 
 */
public class FileUtils {

	// 把信息写入到文件(覆盖)
	public static void write(Context context, String fileName, String content) {
		if (content == null)
			content = "";

		try {
			FileOutputStream outputStream = context.openFileOutput(fileName,
					Context.MODE_PRIVATE);
			outputStream.write(content.getBytes());
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 把信息写入到文件(追加)
	public static void write_append(Context context, String fileName,
			String content) {
		if (content == null)
			content = "";

		try {
			FileOutputStream outputStream = context.openFileOutput(fileName,
					Context.MODE_APPEND);
			outputStream.write(content.getBytes());
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 读取信息
	public static String read(Context context, String fileName) {

		try {
			FileInputStream fis = context.openFileInput(fileName);
			return readInStream(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}

	// 把流转化成字符串
	private static String readInStream(FileInputStream fis) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length = -1;
		try {
			while ((length = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, length);
			}
			fis.close();
			baos.close();
			return baos.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	// 把图片存到缓存
	public void saveImagetoCache(Context context, String filename) {

	}
}

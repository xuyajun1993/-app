package com.xyj.hnu.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * ͼƬ����
 * 
 * @author xyj
 * 
 */
public class bitmapCache implements ImageCache {
	private LruCache<String, Bitmap> cache;

	public bitmapCache() {
		int maxSize = 10 * 1024 * 1024;
		cache = new LruCache<String, Bitmap>(maxSize) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// ����ͼƬ��С
				return value.getRowBytes() * value.getHeight();
			}
		};
	}

	@Override
	public Bitmap getBitmap(String url) {
		return cache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		cache.put(url, bitmap);
	}

}

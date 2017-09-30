package com.hr.ent.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtil {

	public BitmapUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 对给定图片进行位图解码
	 * 
	 * @param resources
	 *            资源文件
	 * @param ResId
	 *            解码位图的ID
	 * @param reqWidth
	 *            指定输出位图的宽度
	 * @param reqHeight
	 *            指定输出位图的高度
	 * @return
	 */
	public static Bitmap decodeBitmap(byte[] data, int reqWidth, int reqHeight) {
		// 对位图进行解码的参数设置
		BitmapFactory.Options options = new BitmapFactory.Options();
		// 对位图进行解码的过程中，避免申请内存空间
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeByteArray(data, 0, data.length, options);
		// 对位图进行一定比例的压缩处理
		options.inSampleSize = calculateInSimpleSize(options, reqWidth,
				reqHeight);
		// 真正输出位图
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeByteArray(data, 0, data.length, options);
	}

	/**
	 * 对给定图片进行位图解码
	 * 
	 * @param resources
	 *            资源文件
	 * @param ResId
	 *            解码位图的ID
	 * @param reqWidth
	 *            指定输出位图的宽度
	 * @param reqHeight
	 *            指定输出位图的高度
	 * @return
	 */
	public static Bitmap decodeResources(Resources resources, int ResId,
			int reqWidth, int reqHeight) {
		// 对位图进行解码的参数设置
		BitmapFactory.Options options = new BitmapFactory.Options();
		// 对位图进行解码的过程中，避免申请内存空间
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(resources, ResId, options);
		// 对位图进行一定比例的压缩处理
		options.inSampleSize = calculateInSimpleSize(options, reqWidth,
				reqHeight);
		// 真正输出位图
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(resources, ResId, options);
	}

	public static Bitmap decodeFile(File file, int reqWidth, int reqHeight) {
		try {
			// 对位图进行解码的参数设置
			BitmapFactory.Options options = new BitmapFactory.Options();
			// 对位图进行解码的过程中，避免申请内存空间
			options.inJustDecodeBounds = true;
			BitmapFactory
					.decodeStream(new FileInputStream(file), null, options);

			// 对位图进行一定比例的压缩处理
			options.inSampleSize = calculateInSimpleSize(options, reqWidth,
					reqHeight);
			// 真正输出位图
			options.inJustDecodeBounds = false;
			return BitmapFactory.decodeStream(new FileInputStream(file), null,
					options);
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		}
		return null;
	}

	public static int calculateInSimpleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// 获取图片的原始宽高
		int imageWidth = options.outWidth;
		int imageHeight = options.outHeight;
		// 压缩比例，假如是4就是压缩到原来大小的1/4
		int inSimpleSize = 1;
		if (imageWidth > reqWidth || imageHeight > reqHeight) {
			final int widthRatio = Math.round((float) imageWidth
					/ (float) reqWidth);
			final int heightRatio = Math.round((float) imageHeight
					/ (float) reqHeight);
			inSimpleSize = widthRatio < heightRatio ? widthRatio : heightRatio;
		}
		return inSimpleSize;
	}
}

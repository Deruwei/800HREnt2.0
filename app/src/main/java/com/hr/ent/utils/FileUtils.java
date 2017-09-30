package com.hr.ent.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Environment;

/**
 * 文件处理工具类
 * 
 * @author 800hr：zhuhui
 * 
 *         2014-4-29
 */
public class FileUtils {

	/**
	 * 获得 city 数组信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, String>> getLingyuArray(JSONArray jsonArray) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		JSONObject jsonObject = null;
		int len = jsonArray.length();
		try {
			for (int i = 0; i < len; i++) {
				jsonObject = jsonArray.getJSONObject(i);
				String key = (String) jsonObject.keys().next();
				HashMap<String, String> item = new HashMap<String, String>();
				item.put("key", key);
				item.put("value", jsonObject.getString(key));
				list.add(item);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 获得 city 数组信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, String>> getCityArray(JSONArray jsonArray,
			String id) {
		String endString = "00";
		int endIndex = 2;
		List<Map<String, String>> list = null;
		try {
			list = getArrayList(jsonArray, id, endString, endIndex);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	/**
	 * 获得 job 数组信息
	 * 
	 * @param id
	 * @return
	 */
	public static List<Map<String, String>> getJobArray(JSONArray jsonArray,
			String id) {
		String endString = "000";
		int endIndex = 3;
		List<Map<String, String>> list = null;
		try {
			list = getArrayList(jsonArray, id, endString, endIndex);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	private static List<Map<String, String>> getArrayList(JSONArray jsonArray,
			String id, String endString, int endIndex) throws JSONException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		int len = jsonArray.length();
		JSONObject jsonObject = null;

		if ("0".equals(id)) { // 一级分类
			for (int i = 0; i < len; i++) {
				jsonObject = jsonArray.getJSONObject(i);
				String key = (String) jsonObject.keys().next();
				if (key.endsWith(endString) && !key.equals("4600")) {
					HashMap<String, String> item = new HashMap<String, String>();
					item.put("key", key);
					item.put("value", jsonObject.getString(key));
					list.add(item);
				}
			}
		} else {
			String preId = id.substring(0, endIndex);
			for (int i = 0; i < len; i++) {
				jsonObject = jsonArray.getJSONObject(i);
				String key = (String) jsonObject.keys().next();
				if (key.startsWith(preId) && !key.endsWith(endString)
						&& !key.equals("4600")) {
					HashMap<String, String> item = new HashMap<String, String>();
					item.put("key", key);
					item.put("value", "    " + jsonObject.getString(key));
					list.add(item);
				}
			}
		}
		return list;
	}

	/**
	 * 获取 city 的 JSON 数组对象，由于json 数据不大，就先存在内存中，避免每次读取文件获得
	 * 
	 * @param context
	 * @param filename
	 * @return 整个 city 的 JSONArray
	 * @throws Exception
	 */
	public static JSONArray getCityAsJSONArray(Context context, String filename) {
		JSONArray array = null;
		try {
			String json = getFileAsString(context, filename);
			array = new JSONArray(json);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return array;
	}

	/**
	 * 获取job的JSON数组对象
	 * 
	 * @param context
	 * @param filename
	 * @param industryId
	 * @return
	 * @throws Exception
	 */
	public static JSONArray getJobAsJSONArray(Context context, String filename,
			String industryId) {
		JSONArray array = null;
		try {
			String json = getFileAsString(context, filename);
			array = new JSONObject(json).getJSONArray(industryId);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return array;
	}

	/**
	 * 获取领域的JSON数组对象
	 * 
	 * @param context
	 * @param filename
	 * @param industryId
	 * @return
	 * @throws Exception
	 */
	public static JSONArray getLingYuAsJSONArray(Context context,
			String filename, String industryId) {
		JSONArray jsonObject = null;
		String json;
		try {
			json = getFileAsString(context, filename);
			jsonObject = new JSONObject(json).getJSONArray(industryId);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

	private static String getFileAsString(Context context, String filename)
			throws FileNotFoundException, UnsupportedEncodingException,
			IOException {
		FileInputStream in = context.openFileInput(filename);
		return readAsString(in, Const.ENCODE);
	}

	public static String readAsString(InputStream inputStream, String encode)
			throws UnsupportedEncodingException, IOException {
		return new String(readAsByteArray(inputStream), encode);
	}

	private static byte[] readAsByteArray(InputStream inputStream)
			throws IOException {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = inputStream.read(buffer)) != -1) {
				outSteam.write(buffer, 0, len);
			}
			return outSteam.toByteArray();
		} finally {
			outSteam.close();
			inputStream.close();
		}
	}

	/**
	 * 打包文件夹
	 * 
	 * @param file
	 */
	public static void rarfile(File file) {
		String jarPath = Environment.getExternalStorageDirectory()
				+ Const.LOGPATH + file.getName() + ".zip";// 打包文件的文件名
		File[] files = file.listFiles();
		if (files == null)
			return;
		try {
			byte[] buffer = new byte[100 * 1024];
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					jarPath));
			for (int i = 0; i < files.length; i++) {
				FileInputStream fis = new FileInputStream(files[i]);
				out.putNextEntry(new ZipEntry(files[i].getName()));
				int len;
				// 读入log文件，打包到zip文件
				while ((len = fis.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
				out.closeEntry();
				fis.close();
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件或目录
	 * 
	 * @param sPath
	 *            需要删除的文件路径
	 * @return
	 */
	public static boolean DeleteFolder(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(sPath);
			} else { // 为目录时调用删除目录方法
				return deleteDirectory(sPath);
			}
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param sPath
	 *            需要删除文件的路径
	 * @return
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除目录
	 * 
	 * @param sPath
	 *            需要删除的文件的路径
	 * @return
	 */
	public static boolean deleteDirectory(String sPath) {
		boolean flag = false;

		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}

		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}

		flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile() && !files[i].getName().endsWith(".zip")) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}

		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}
}

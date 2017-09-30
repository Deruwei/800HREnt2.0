package com.hr.ent.utils;

import android.annotation.SuppressLint;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @author 800hr:zhuhui
 * 
 * 2014-3-27
 */
@SuppressLint("DefaultLocale")
public abstract class StringUtils
{
	public static boolean hasLength(String str) 
	{
		return (str != null && str.length() > 0);
	}
	
	public static boolean isEmailRight(String email){
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	/**
	 * 清除str前面的不可见字符
	 * @param str
	 * @return
	 */
	public static String trimLeadingWhitespace(String str) 
	{
		if (!hasLength(str)) 
		{
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) 
		{
			buf.deleteCharAt(0);
		}
		return buf.toString();
	}
	
	/**
	 * 使用delimiter来分割str，生成数组
	 * @param str
	 * @param delimiter
	 * @return
	 */
	public static String[] delimitedListToStringArray(String str, String delimiter) 
	{
		if (str == null) 
		{
			return new String[0];
		}
		if (delimiter == null) 
		{
			return new String[] {str};
		}
		
		List<String>result = new ArrayList<String>();
		if ("".equals(delimiter)) 
		{
			result.add(str);
		}
		else 
		{
			int pos = 0;
			int delPos = 0;
			while ((delPos = str.indexOf(delimiter, pos)) != -1) 
			{
				result.add(str.substring(pos, delPos));
				pos = delPos + delimiter.length();
			}
			if (str.length() > 0 && pos <= str.length()) 
			{
				// Add rest of String, but not in case of empty input.
				result.add(str.substring(pos));
			}
		}
		return result.toArray(new String[result.size()]);
	}
	
	/**
	 * 用delim将数组分隔,合并为一个字符串
	 * @param arr
	 * @param delim
	 * @return
	 */
	public static String arrayToDelimitedString(Object[] arr, String delim) 
	{
		if (arr == null || arr.length == 0)
		{
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) 
		{	
			if (i > 0) 
			{
				sb.append(delim);
			}
			sb.append(arr[i].toString());
		}
		
		return sb.toString();
	}
	
	/**
	 * 返回文件名，去掉路径
	 * @param path
	 * @return
	 */
	public static String getFilename(String path) 
	{
		if (path == null) 
		{
			return null;
		}
		int separatorIndex = path.lastIndexOf(File.separator);
		return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);
	}

	/**
	 * 返回文件的扩展名
	 */
	public static String getFilenameExtension(String path) 
	{
		if (path == null) 
		{
			return null;
		}
		int sepIndex = path.lastIndexOf(".");
		return (sepIndex != -1 ? path.substring(sepIndex + 1) : null);
	}

	
	/**
	 * 返回唯一性字符串
	 * @return
	 */
	public static String getUUID() 
	{
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();

		return str.replaceAll("-", "");
	}
	
	/**
	 * 将字节大小格式化为可读的格式
	 * @param size
	 * @return
	 */
	public static String formatSize(long size) 
	{
		long SIZE_KB = 1024;
		long SIZE_MB = SIZE_KB * 1024;
		long SIZE_GB = SIZE_MB * 1024;

		if (size < SIZE_KB) 
		{
			return String.format("%d B", (int) size);
		} 
		else if (size < SIZE_MB) 
		{
			return String.format("%.2f KB", (float) size / SIZE_KB);
		} 
		else if (size < SIZE_GB) 
		{
			return String.format("%.2f MB", (float) size / SIZE_MB);
		} 
		else 
		{
			return String.format("%.2f GB", (float) size / SIZE_GB);
		}
	}
	
	
	/**
	 * 判断字符串的长度是否合法返回boolean则合法，返回string则超过判断长度，并返回长度内的字符串
	 * @param str 判断的字符串
	 * @param length 判断字符串的长度
	 * @return
	 */
	public static Object validateLength(String str,int length){
		//如果输入的字符串大于长度则返回正确的字符串
		String reStr = null;
		try {
			if(str != null)
			{
				//将字符串转成gbk模式
				byte[] bytes = str.getBytes("gbk");
				//长度给l赋值
				int l = length;
				//如果字符数组大于长度表示字符大于规定长度主要是为了判断文字与字母同在的时候
				if(bytes.length>length){
					//如果长度判断小于2
					if(length<2){
						l = 1;
					}else if (bytes[length-2]>0&&bytes[length-1]<0){
						l = length-1;
					}
					
				}
				byte[] b = new byte[l];
				if(bytes.length>length){
					for(int i = 0 ; i <l ; i ++){
						b[i] = bytes[i];
					}
					reStr = new String(new String(b,"gbk").getBytes("utf-8"),"utf-8");
				}else{
					return true;
				}
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return reStr;
	}
}

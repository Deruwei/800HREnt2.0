package com.hr.ent.utils;

/**
 * 解析网站编码
 * @author 800hr：zhuhui
 *
 * 2014-11-27
 */
public class SiteUtils {
	
	public static final String getSiteNameByCode(int code){
		String siteName = "";
		switch (code) {
		case 11:
			siteName = "建筑英才网";
			break;
		case 12:
			siteName = "金融英才网";
			break;
		case 14:
			siteName = "医药英才网";
			break;
		case 26:
			siteName = "服装英才网";
			break;
		case 29:
			siteName = "化工英才网";
			break;
		case 15:
			siteName = "教培英才网";
			break;
		case 22:
			siteName = "机械英才网";
			break;
		case 19:
			siteName = "电子英才网";
			break;
		case 13:
			siteName = "传媒英才网";
			break;
		default:
			break;
		}
		return siteName;
	}

}

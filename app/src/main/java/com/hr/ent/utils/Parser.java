package com.hr.ent.utils;

import org.json.JSONException;
import org.json.JSONObject;

import com.hr.ent.R;

/**
 * json解析工具类
 * 
 * @author 800hr：zhuhui
 * 
 *         2014-3-27
 */
public class Parser {

	/**
	 * 判断json对象里是否包含某个字段
	 * 
	 * @param jo
	 * @param name
	 * @return
	 * @throws JSONException
	 */
	public static boolean isNotNull(JSONObject jo, String name)
			throws JSONException {
		if (!jo.has(name))
			return false;
		Object object = jo.get(name);
		return (object != null && !object.equals(JSONObject.NULL));
	}

	public static int parseErrorCode(String errorCode) {
		if (errorCode.equals("101")) {
			return R.string.error_101;
		} else if (errorCode.equals("102")) {
			return R.string.error_102;
		} else if (errorCode.equals("103")) {
			return R.string.error_103;
		} else if (errorCode.equals("104")) {
			return R.string.error_104;
		} else if (errorCode.equals("105")) {
			return R.string.error_105;
		} else if (errorCode.equals("106")) {
			return R.string.error_106;
		} else if (errorCode.equals("201")) {
			return R.string.error_201;
		} else if (errorCode.equals("202")) {
			return R.string.error_202;
		} else if (errorCode.equals("203")) {
			return R.string.error_203;
		} else if (errorCode.equals("204")) {
			return R.string.error_204;
		} else if (errorCode.equals("205")) {
			return R.string.error_205;
		} else if (errorCode.equals("206")) {
			return R.string.error_206;
		} else if (errorCode.equals("207")) {
			return R.string.error_207;
		} else if (errorCode.equals("208")) {
			return R.string.error_208;
		} else if (errorCode.equals("209")) {
			return R.string.error_209;
		} else if (errorCode.equals("210")) {
			return R.string.error_210;
		} else if (errorCode.equals("301")) {
			return R.string.error_301;
		} else if (errorCode.equals("302")) {
			return R.string.error_302;
		} else if (errorCode.equals("303")) {
			return R.string.error_303;
		} else if (errorCode.equals("304")) {
			return R.string.error_304;
		} else if (errorCode.equals("305")) {
			return R.string.error_305;
		} else if (errorCode.equals("306")) {
			return R.string.error_306;
		} else if (errorCode.equals("307")) {
			return R.string.error_307;
		} else if (errorCode.equals("308")) {
			return R.string.error_308;
		} else if (errorCode.equals("309")) {
			return R.string.error_309;
		} else if (errorCode.equals("310")) {
			return R.string.error_310;
		} else if (errorCode.equals("311")) {
			return R.string.error_311;
		} else if (errorCode.equals("312")) {
			return R.string.error_312;
		} else if (errorCode.equals("313")) {
			return R.string.error_313;
		} else if (errorCode.equals("314")) {
			return R.string.error_314;
		} else if (errorCode.equals("315")) {
			return R.string.error_315;
		} else if (errorCode.equals("317")) {
			return R.string.error_317;
		} else if (errorCode.equals("318")) {
			return R.string.error_318;
		} else if (errorCode.equals("319")) {
			return R.string.error_319;
		} else if (errorCode.equals("320")) {
			return R.string.error_320;
		} else if (errorCode.equals("321")) {
			return R.string.error_321;
		} else if (errorCode.equals("401")) {
			return R.string.error_401;
		} else if (errorCode.equals("402")) {
			return R.string.error_402;
		} else if (errorCode.equals("403")) {
			return R.string.error_403;
		} else if (errorCode.equals("404")) {
			return R.string.error_404;
		} else if (errorCode.equals("405")) {
			return R.string.error_405;
		} else if (errorCode.equals("406")) {
			return R.string.error_406;
		} else if (errorCode.equals("407")) {
			return R.string.error_407;
		} else if (errorCode.equals("408")) {
			return R.string.error_408;
		} else if (errorCode.equals("411")) {
			return R.string.error_411;
		} else if (errorCode.equals("412")) {
			return R.string.error_412;
		} else if (errorCode.equals("413")) {
			return R.string.error_413;
		} else if (errorCode.equals("501")) {
			return R.string.error_501;
		} else if (errorCode.equals("502")) {
			return R.string.error_502;
		} else if (errorCode.equals("503")) {
			return R.string.error_503;
		} else if (errorCode.equals("504")) {
			return R.string.error_504;
		} else if (errorCode.equals("205.1")) {
			return R.string.error_205_1;
		} else if (errorCode.equals("205.2")) {
			return R.string.error_205_2;
		} else if (errorCode.equals("205.3")) {
			return R.string.error_205_3;
		} else if (errorCode.equals("205.4")) {
			return R.string.error_205_4;
		} else if (errorCode.equals("205.5")) {
			return R.string.error_205_5;
		} else if (errorCode.equals("4004")) {
			return R.string.error_4004;
		} else if (errorCode.equals("4004")) {
			return R.string.error_4004;
		} else if (errorCode.equals("4005")) {
			return R.string.error_4005;
		} else if (errorCode.equals("4006")) {
			return R.string.error_4006;
		} else if (errorCode.equals("4007")) {
			return R.string.error_4007;
		} else if (errorCode.equals("4009")) {
			return R.string.error_4009;
		} else if (errorCode.equals("4010")) {
			return R.string.error_4010;
		} else if (errorCode.equals("2001")) {
			return R.string.error_2001;
		} else if (errorCode.equals("2002")) {
			return R.string.error_2002;
		} else if (errorCode.equals("2003")) {
			return R.string.error_2003;
		}
		else if (errorCode.equals("2004")) {
			return R.string.error_2004;
		}
		else if (errorCode.equals("2005")) {
			return R.string.error_2005;
		}
		else if (errorCode.equals("2006")) {
			return R.string.error_2006;
		}
		else if (errorCode.equals("2007")) {
			return R.string.error_2007;
		}
		else if (errorCode.equals("2008")) {
			return R.string.error_2008;
		}
		else if (errorCode.equals("2009")) {
			return R.string.error_2009;
		}
		else if (errorCode.equals("2010")) {
			return R.string.error_2010;
		}
		else if (errorCode.equals("2011")) {
			return R.string.error_2011;
		}
		else if (errorCode.equals("2012")) {
			return R.string.error_2012;
		}
		else if (errorCode.equals("2013")) {
			return R.string.error_2013;
		}
		else if (errorCode.equals("2014")) {
			return R.string.error_2014;
		}
		else if (errorCode.equals("2015")) {
			return R.string.error_2015;
		}
		else if (errorCode.equals("1001")) {
			return R.string.error_1001;
//			return R.string.error_1001;
		} else if (errorCode.equals("9005")) {
			return R.string.error_9005;
		}
		return R.string.error_101;
	}
}

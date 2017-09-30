package com.hr.ent.utils;
//package com.hr.HD.utils;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import org.json.JSONObject;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Handler;
//import android.os.Message;
//import android.view.View;
//import android.widget.Toast;
//
//import com.hr.model.MoreAccountInfo;
//import com.hr.service.HRService;
//import com.hr.ui.FindJobActivityGroup;
//import com.hr.ui.FunctionSelectActivityFindJob;
//import com.hr.ui.MainActivity;
//import com.hr.ui.MoreAccountBindActivity;
//import com.hr.ui.OpenBindActivity;
//import com.hr.ui.PersonCenterActivity;
//import com.hr.ui.PersonCenterActivityGroup;
//import com.hr.ui.R;
//import com.hr.ui.ResumeCenterActivity;
//import com.hr.ui.ResumeCenterActivityGroup;
//import com.hr.ui.SearchJobActivity;
//
///**
// * 判断用户的第三方平台是否已经使用
// * 
// * @author 123
// * 
// */
//public class IsBind {
//	private String platformString;
//	private int loginFromTag;
//	private String uid;
//	private String name;
//	private String birthday;
//	private String nickname;
//	private String tinyurl;
//	private String gender;
//	private Context context;
//	private String industry;// 登录时选择行业
//	private String suid;// 要保留绑定的用户ID
//	private boolean isFromPush = false;
//	private Handler handlerService = new Handler() {
//		public void handleMessage(Message msg) {
//			if (msg.what == 0) {
//				String json = (String) msg.obj;
//				System.out.println("第三方登录结果：" + json);
//				try {
//					JSONObject jsonObject = new JSONObject(json);
//					int error_code = jsonObject.getInt("error_code");
//					switch (error_code) {
//					case 0:
//						// 改变全局状态
//						MyUtils.isLogin = true;
//						MyUtils.isLogouted = false;
//						MyUtils.userID = jsonObject.getString("user_id");
//						MyUtils.industryId = jsonObject.getString("industry");
//						if (nickname != null && nickname.length() > 0) {
//							MyUtils.username = nickname;
//						} else {
//							MyUtils.username = name;
//						}
//						if (isFromPush) {
//
//						}
//						Toast.makeText(context, R.string.login_success,
//								Toast.LENGTH_SHORT).show();
//						switch (loginFromTag) {
//						case Constants.PERSONCENTER_LOGIN:// ---------个人中心
//							// 初始化个人中心group
//							if (MyUtils.currentGroup != null) {
//								MyUtils.currentGroup.clearViews();
//							}
//							// 跳转
//							View viewplaceselect2 = MyUtils.currentGroup
//									.getLocalActivityManager()
//									.startActivity(
//											"PersonCenterActivity",
//											new Intent(context,
//													PersonCenterActivity.class)
//													.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//									.getDecorView();
//							viewplaceselect2.setTag("PersonCenterActivity");
//							MyUtils.currentGroup.replaceView(viewplaceselect2);
//							// resetFindJobGroup();
//							break;
//						case Constants.RESUMECENTER_LOGIN:// ---------简历中心
//							// 初始化简历中心group
//							if (MyUtils.currentGroup != null) {
//								MyUtils.currentGroup.clearViews();
//							}
//							// 跳转
//							View resumeCenterActivityView = MyUtils.currentGroup
//									.getLocalActivityManager()
//									.startActivity(
//											"ResumeCenterActivity",
//											new Intent(context,
//													ResumeCenterActivity.class)
//													.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//									.getDecorView();
//							resumeCenterActivityView
//									.setTag("ResumeCenterActivity");
//							MyUtils.currentGroup
//									.replaceView(resumeCenterActivityView);
//							// resetFindJobGroup();
//							break;
//						case Constants.FINDJOB_LOGIN:// ---------找工作
//							System.out.println("找工作");
//							if (PersonCenterActivityGroup.group != null) {
//								PersonCenterActivityGroup.group.clearViews();
//							}
//							if (ResumeCenterActivityGroup.group != null) {
//								ResumeCenterActivityGroup.group.clearViews();
//							}
//							MyUtils.currentGroup.back();
//							sendBroadCastForPosition();
//							break;
//						}
//						break;
//					case 305:// 第一次绑定账号
//						Intent intent = new Intent(context,
//								OpenBindActivity.class);
//						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//						intent.putExtra("uid", uid);
//						intent.putExtra("platform", platformString);
//						intent.putExtra("name", name);
//						intent.putExtra("birthday", birthday);
//						intent.putExtra("nickname", nickname);
//						intent.putExtra("tinyurl", tinyurl);
//						intent.putExtra("gender", gender);
//						intent.putExtra("isFromPush", isFromPush);
//						if (isFromPush) {
//							context.startActivity(intent);
//						} else {
//							// 跳转
//							View view = MyUtils.currentGroup
//									.getLocalActivityManager()
//									.startActivity("OpenBindActivity", intent)
//									.getDecorView();
//							MyUtils.currentGroup.replaceView(view);
//						}
//						break;
//					case 322:// 此第三方账号曾绑定过多行业
//						// System.out.println("此第三方账号绑定过多行业");
//						ArrayList<MoreAccountInfo> moreAccountInfoList = new MoreAccountBindJsonParse()
//								.getMoAccountInfo(jsonObject
//										.getString("user_list"));
//						// 跳转
//						Intent moreAccountBindIntent = new Intent(context,
//								MoreAccountBindActivity.class);
//						moreAccountBindIntent
//								.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//						moreAccountBindIntent
//								.putExtra("isFromPush", isFromPush);
//						moreAccountBindIntent.putExtra("third_code",
//								platformString);
//						moreAccountBindIntent.putExtra("third_uid", uid);
//						moreAccountBindIntent.putExtra("industry", industry);
//						moreAccountBindIntent.putExtra("suid", suid);
//						moreAccountBindIntent.putExtra("moreAccountInfoList",
//								moreAccountInfoList);
//						moreAccountBindIntent.putExtra("nickname", nickname);
//						moreAccountBindIntent.putExtra("loginFromTag",
//								loginFromTag);
//						moreAccountBindIntent.putExtra("name", name);
//						View view = MyUtils.currentGroup
//								.getLocalActivityManager()
//								.startActivity("MoreAccountBindActivity",
//										moreAccountBindIntent).getDecorView();
//						MyUtils.currentGroup.replaceView(view);
//
//						break;
//					default:
//						Toast.makeText(context,
//								Auth800hr_Errorcode.getErrorResourceId(error_code),
//								Toast.LENGTH_LONG).show();
//						break;
//					}
//
//				} catch (Exception e) {
//					Toast.makeText(
//							context,
//							context.getResources().getString(
//									R.string.login_false), Toast.LENGTH_SHORT)
//							.show();
//					e.printStackTrace();
//				}
//			}
//		};
//
//	};
//
//	/**
//	 * 
//	 * @param platformString
//	 * @param loginFromTag
//	 * @param uid
//	 * @param name
//	 * @param birthday
//	 * @param nickname
//	 * @param tinyurl
//	 * @param gender
//	 * @param context
//	 * @param industry
//	 * @param suid
//	 * @param isFromPush
//	 */
//	public IsBind(String platformString, int loginFromTag, String uid,
//			String name, String birthday, String nickname, String tinyurl,
//			String gender, Context context, String industry, String suid,
//			boolean isFromPush) {
//		super();
//		this.isFromPush = isFromPush;
//		this.platformString = platformString;
//		this.loginFromTag = loginFromTag;
//		this.uid = uid;
//		this.name = name;
//		this.birthday = birthday;
//		this.nickname = nickname;
//		this.tinyurl = tinyurl;
//		this.gender = gender;
//		this.context = context;
//		this.industry = industry;
//		this.suid = suid;
//
//	}
//
//	public void execute() {
//		// System.out.println("isbind");
//		HashMap<String, String> requestParams = new HashMap<String, String>();
//		requestParams.put("method", "user.thirdlogin");
//		requestParams.put("third_code", platformString);
//		requestParams.put("third_uid", uid);
//		requestParams.put("industry", industry);
//		requestParams.put("suid", suid);
//		try {
//			HRService service = new HRService(context, handlerService);
//			service.execute(requestParams);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void resetFindJobGroup() {
//		// 初始化找工作页
//		FindJobActivityGroup.group.clearViews();
//		View view = FindJobActivityGroup.group
//				.getLocalActivityManager()
//				.startActivity(
//						"SearchJobActivity",
//						new Intent(context, SearchJobActivity.class)
//								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//				.getDecorView();
//		view.setTag("SearchJobActivity");// 摇一摇中用于区别其他页面
//		FindJobActivityGroup.group.replaceView(view);
//		SearchJobActivity.mySensorEventListener
//				.unregisterSensorListener(MainActivity.mainContext);
//		FunctionSelectActivityFindJob.selectMap.clear();
//	}
//	
//	/**
//	 * 登录成功后发送广播请求职位详情接口及收藏接口
//	 */
//	private void sendBroadCastForPosition() {
//		// TODO Auto-generated method stub
//		Intent intent = new Intent("android.intent.action.MAIN");
//		context.sendBroadcast(intent);
//	};
//}
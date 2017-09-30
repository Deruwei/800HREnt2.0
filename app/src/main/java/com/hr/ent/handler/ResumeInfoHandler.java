package com.hr.ent.handler;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.async.AsyncExecutant;
import com.hr.ent.fragment.ResumeInfoFragment;
import com.hr.ent.fragment.ResumeInfoParticularFragment;
import com.hr.ent.model.ResumeInfoOuterBean;
import com.hr.ent.model.ResumeTempleListBean;
import com.hr.ent.task.GetInviteTagTask;
import com.hr.ent.task.GetResumeInfoTask;
import com.hr.ent.task.ResumeDownloadTask;
import com.hr.ent.task.ResumeForwardTask;
import com.hr.ent.task.ResumeNoteTask;
import com.hr.ent.task.ResumeSetReadTask;
import com.hr.ent.task.SendInviteTask;
import com.hr.ent.task.Task;
import com.hr.ent.ui.ResumeInfoActivity;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.Parser;

import java.util.Map;

/**
 * 简历信息处理类
 *
 * @author 800hr：yelong
 *         <p/>
 *         2015-6-26
 */
public class ResumeInfoHandler extends Handler {
    public static final int wGetResumeInfoStart = 1;// 开始获取简历详情
    public static final int wGetResumeInfoSuccess = 2;// 获取详情成功
    public static final int wGetResumeInfoFailed = 3;// 获取详情失败

    public static final int wGetInviteTagStart = 4;// 获取通知信模板列表开始
    public static final int wGetInviteTagSuccess = 5;// 获取通知信模板列表成功
    public static final int wGetInviteTagFailed = 6;// 获取通知信模板列表失败

    public static final int wSendInviteStart = 7;// 开始发送通知信
    public static final int wSendInviteSuccess = 8;// 发送通知信成功
    public static final int wSendInviteFailed = 9;// 发送通知信失败

    public static final int wSetResumeNoteStart = 10;// 开始设置简历备注
    public static final int wSetResumeNoteSuccess = 11;// 设置简历备注成功
    public static final int wSetResumeNoteFailed = 12;// 设置简历备注失败

    public static final int wDeleteResumeStart = 13;// 开始删除简历
    public static final int wDeleteResumeSuccess = 14;// 删除简历成功
    public static final int wDeleteResumeFailed = 15;// 删除简历失败

    public static final int wResumeForwardStart = 16;// 转发简历开始
    public static final int wResumeForwardSuccess = 17;// 转发简历成功
    public static final int wResumeForwardFailed = 18;// 转发简历失败

    public static final int wSetResumeReadStart = 19;// 开始设置简历已读
    public static final int wSetResumeReadSuccess = 20;// 设置简历已读成功
    public static final int wSetResumeReadFailed = 21;// 设置简历已读失败

    public static final int wGetResumeInfoStart2 = 22;// 开始获取简历详情
    public static final int wGetResumeInfoSuccess2 = 23;// 获取详情成功
    public static final int wGetResumeInfoFailed2 = 24;// 获取详情失败

    public static final int wDownloadResumeStart = 25;// 开始下载简历
    public static final int wDownloadResumeSuccess = 26;// 下载简历联系方式成功
    public static final int wDownloadResumeFailed = 27;// 获取简历联系方式失败
    private Context context;
    private ResumeInfoActivity resu;
    private ResumeInfoFragment infoFragment;
    private ResumeInfoParticularFragment infoFragment2;

    public ResumeInfoHandler(Context context, ResumeInfoActivity resu) {
        this.context = context;
        this.resu = resu;
    }

    public ResumeInfoHandler(Context context, ResumeInfoFragment fragment) {
        this.context = context;
        this.infoFragment = fragment;
    }

    public ResumeInfoHandler(Context context, ResumeInfoParticularFragment fragment2) {
        this.context = context;
        this.infoFragment2 = fragment2;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void dispatchMessage(Message msg) {
        // TODO Auto-generated method stub
        super.dispatchMessage(msg);
        switch (msg.what) {
            case wGetResumeInfoStart:
                wGetResumeInfoStart((Map<String, Object>) msg.obj);
                break;
            case wGetResumeInfoSuccess:
                wGetResumeInfoSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetResumeInfoFailed:
                wGetResumeInfoFailed((Map<String, Object>) msg.obj);
                break;

            case wSetResumeNoteStart:
                wSetResumeNoteStart((Map<String, Object>) msg.obj);
                break;
            case wSetResumeNoteSuccess:
                wSetResumeNoteSuccess((Map<String, Object>) msg.obj);
                break;
            case wSetResumeNoteFailed:
                wSetResumeNoteFailed((Map<String, Object>) msg.obj);
                break;

//		case wDeleteResumeStart:
//			wDeleteResumeStart((Map<String, Object>) msg.obj);
//			break;
//		case wDeleteResumeSuccess:
//			wDeleteResumeSuccess((Map<String, Object>) msg.obj);
//			break;
//            case wDeleteResumeFailed:
//                wDeleteResumeFailed((Map<String, Object>) msg.obj);
//                break;

            case wResumeForwardStart:
                wResumeForwardStart((Map<String, Object>) msg.obj);
                break;
            case wResumeForwardSuccess:
                wResumeForwardSuccess((Map<String, Object>) msg.obj);
                break;
            case wResumeForwardFailed:
                wResumeForwardFailed((Map<String, Object>) msg.obj);
                break;

            case wGetInviteTagStart:
                wGetInviteTagStart((Map<String, Object>) msg.obj);
                break;
            case wGetInviteTagSuccess:
                wGetInviteTagSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetInviteTagFailed:
                wGetInviteTagFailed((Map<String, Object>) msg.obj);
                break;

            case wSendInviteStart:
                wSendInviteStart((Map<String, Object>) msg.obj);
                break;
            case wSendInviteSuccess:
                wSendInviteSuccess((Map<String, Object>) msg.obj);
                break;
            case wSendInviteFailed:
                wSendInviteFailed((Map<String, Object>) msg.obj);
                break;

            case wSetResumeReadStart:
                wSetResumeReadStart((Map<String, Object>) msg.obj);
                break;
            case wSetResumeReadSuccess:
                wSetResumeReadSuccess((Map<String, Object>) msg.obj);
                break;
            case wSetResumeReadFailed:
                wSetResumeReadFailed((Map<String, Object>) msg.obj);
                break;
            case wGetResumeInfoStart2:
                wGetResumeInfoStart2((Map<String, Object>) msg.obj);
                break;
            case wGetResumeInfoSuccess2:
                wGetResumeInfoSuccess2((Map<String, Object>) msg.obj);
                break;
            case wGetResumeInfoFailed2:
                wGetResumeInfoFailed2((Map<String, Object>) msg.obj);
                break;
            case wDownloadResumeStart:
                wDownloadResumeStart((Map<String, Object>) msg.obj);
                break;
            case wDownloadResumeSuccess:
                wDownloadResumeSuccess((Map<String, Object>) msg.obj);
                break;
            case wDownloadResumeFailed:
                wDownloadResumeFailed((Map<String, Object>) msg.obj);
                break;
        }
    }

    private void wGetResumeInfoFailed2(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "获取简历详情失败", Toast.LENGTH_SHORT).show();
    }

    private void wGetResumeInfoSuccess2(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            ResumeInfoOuterBean outerBean = (ResumeInfoOuterBean) obj.get("result");
            ((ResumeInfoParticularFragment) infoFragment2).refshUI(outerBean.getResume_info(), outerBean);
            ((ResumeInfoParticularFragment) infoFragment2).setContentVisiable();
        } else {
            ((ResumeInfoParticularFragment) infoFragment2).initEmptyView();
            if (null != obj.get(Const.ERROR_CODE)) {
                Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(context, R.string.nonet, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void wGetResumeInfoStart2(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new GetResumeInfoTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetResumeInfoSuccess2, wGetResumeInfoFailed2);
        executant.execute();
    }

    /**
     * 获取简历详情失败
     *
     * @param obj
     */
    private void wGetResumeInfoFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "获取简历详情失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取简历详情成功
     *
     * @param obj
     */
    private void wGetResumeInfoSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            ResumeInfoOuterBean outerBean = (ResumeInfoOuterBean) obj.get("result");
            ((ResumeInfoFragment) infoFragment).refshUI(outerBean.getResume_info(), outerBean);
            ((ResumeInfoFragment) infoFragment).setContentVisiable();
        } else {
            ((ResumeInfoFragment) infoFragment).initEmptyView();
            if (null != obj.get(Const.ERROR_CODE)) {
                if (obj.get(Const.ERROR_CODE).equals("304")) {
                } else {
                    Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, R.string.nonet, Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 开始获取简历详情
     *
     * @param obj
     */
    private void wGetResumeInfoStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new GetResumeInfoTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetResumeInfoSuccess,
                wGetResumeInfoFailed);
        executant.execute();
    }

    /**
     * 获取通知信模板列表失败
     *
     * @param obj
     */
    private void wGetInviteTagFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "获取通知信模板列表失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取通知信模板列表成功
     *
     * @param obj
     */
    private void wGetInviteTagSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            ResumeTempleListBean listBean = (ResumeTempleListBean) obj.get("result");
            ((ResumeInfoActivity) context).initPopuwindow(listBean.getReturn_data());
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * 开始获取通知信模板列表
     *
     * @param obj
     */
    private void wGetInviteTagStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new GetInviteTagTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetInviteTagSuccess,
                wGetInviteTagFailed);
        executant.execute();
    }

    /**
     * 发送通知信失败
     *
     * @param obj
     */
    private void wSendInviteFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "发送通知信失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 发送通知信成功
     *
     * @param obj
     */
    private void wSendInviteSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            ((ResumeInfoActivity) context).closeInviteDialog();
            Toast.makeText(context, "通知成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * 发送通知信开始
     *
     * @param obj
     */
    private void wSendInviteStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new SendInviteTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wSendInviteSuccess,
                wSendInviteFailed);
        executant.execute();
    }

    /**
     * 设置简历备注失败
     *
     * @param obj
     */
    private void wSetResumeNoteFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "添加简历备注失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 设置简历备注成功
     *
     * @param obj
     */
    private void wSetResumeNoteSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            ((ResumeInfoActivity) context).closeNoteDialog(obj.get("remark").toString());
            ResumeInfoFragment.resumeInfoFragment.closeNoteDialog();
            Toast.makeText(context, "备注成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * 开始设置简历备注
     *
     * @param obj
     */
    private void wSetResumeNoteStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new ResumeNoteTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wSetResumeNoteSuccess,
                wSetResumeNoteFailed);
        executant.execute();
    }

    /**
     * 简历转发失败
     *
     * @param obj
     */
    private void wResumeForwardFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "简历转发失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 简历转发成功
     *
     * @param obj
     */
    private void wResumeForwardSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            ((ResumeInfoActivity) context).closeForwardDialog();
            Toast.makeText(context, "转发成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * 简历转发开始
     *
     * @param obj
     */
    private void wResumeForwardStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new ResumeForwardTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wResumeForwardSuccess,
                wResumeForwardFailed);
        executant.execute();
    }

    /**
     * 删除简历失败
     *
     * @param obj
     */
    private void wDeleteResumeFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "简历删除失败", Toast.LENGTH_SHORT).show();
    }

//	/**
//	 * 删除简历成功
//	 *
//	 * @param obj
//	 */
//	private void wDeleteResumeSuccess(Map<String, Object> obj) {
//		// TODO Auto-generated method stub
//		if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
//			((ResumeInfoActivity) context).closeDeleteDialog();
//			Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
//		} else {
//			Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT)
//					.show();
//		}
//	}

//	/**
//	 * 开始删除简历
//	 *
//	 * @param obj
//	 */
//	private void wDeleteResumeStart(Map<String, Object> obj) {
//		// TODO Auto-generated method stub
//		Task task = new ResumeDeleteTask(context, obj);
//		AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wDeleteResumeSuccess,
//				wDeleteResumeFailed);
//		executant.execute();
//	}

    /**
     * 设置简历已读失败
     *
     * @param obj
     */
    private void wSetResumeReadFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "设置简历已读失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 设置简历已读成功
     *
     * @param obj
     */
    private void wSetResumeReadSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            if (obj.containsKey("id")) {
                // 更新简历浏览的对应简历为已读状态
                Intent intent = new Intent(Const.RESUME_READ);
                intent.putExtra("id", obj.get("id").toString());
                context.sendBroadcast(intent);
            }
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * 开始设置简历已读状态
     *
     * @param obj
     */
    private void wSetResumeReadStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new ResumeSetReadTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wSetResumeReadSuccess,
                wSetResumeReadFailed);
        executant.execute();
    }

    /**
     * 下载简历失败
     *
     * @param obj
     */
    private void wDownloadResumeFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "下载简历失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 下载简历成功
     *
     * @param obj
     */
    private void wDownloadResumeSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
//            ResumeInfoActivity.resumeInfoActivity.init();
            ResumeInfoActivity.resumeInfoActivity.goInit();
            ResumeInfoActivity.resumeInfoActivity.isPhone = true;
            ResumeInfoActivity.resumeInfoActivity.refushUI();

        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * 开始下载简历
     *
     * @param obj
     */
    private void wDownloadResumeStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new ResumeDownloadTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wDownloadResumeSuccess, wDownloadResumeFailed);
        executant.execute();
    }
}

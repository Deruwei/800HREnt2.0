package com.hr.ent.handler;

import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.async.AsyncExecutant;
import com.hr.ent.model.ContractLimitBean;
import com.hr.ent.task.GetContractInfoTask;
import com.hr.ent.task.GetContractStateTask;
import com.hr.ent.task.JobInfoTask;
import com.hr.ent.task.SetJobTask;
import com.hr.ent.task.Task;
import com.hr.ent.ui.JobInfoActivity;
import com.hr.ent.ui.ServerInfoActivity;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.Parser;

/**
 * 职位详情逻辑处理类
 *
 * @author 800hr：zhuhui
 *         <p>
 *         2014-12-30
 */
public class JobInfoHandler extends Handler {
    public static final int wGetJobInfoStart = 1;// 开始获取职位详情
    public static final int wGetJobInfoSuccess = 2;// 获取职位详情成功
    public static final int wGetJobInfoFailed = 3;// 获取职位详情失败
    public static final int wGetServiceStart=13;
    public static final int wGetServiceSuccess=14;
    public static final int wGetServiceFailed=15;

    public static final int wJobInfoDelStart = 4;// 开始获取职位详情
    public static final int wJobInfoDelSuccess = 5;// 获取职位详情成功
    public static final int wJobInfoDelFailed = 6;// 获取职位详情失败

    public static final int wJobIssueStart = 7;// 开始获取职位详情
    public static final int wJobIssueSuccess = 8;// 获取职位详情成功
    public static final int wJobIssueFailed = 9;// 获取职位详情失败

    public static final int wJobPasueStart = 10;// 开始获取职位详情
    public static final int wJobPasueSuccess = 11;// 获取职位详情成功
    public static final int wJobPasueFailed = 12;// 获取职位详情失败

    private Context context;

    public JobInfoHandler(Context context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void dispatchMessage(Message msg) {
        // TODO Auto-generated method stub
        super.dispatchMessage(msg);
        switch (msg.what) {
            case wGetJobInfoStart:
                wGetJobInfoStart((Map<String, Object>) msg.obj);
                break;
            case wGetJobInfoSuccess:
                wGetJobInfoSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetJobInfoFailed:
                wGetJobInfoFailed((Map<String, Object>) msg.obj);
                break;
            case wJobInfoDelStart:
                wJobInfoDelStart((Map<String, Object>) msg.obj);
                break;
            case wJobInfoDelSuccess:
                wJobInfoDelSuccess((Map<String, Object>) msg.obj);
                break;
            case wJobInfoDelFailed:
                wJobInfoDelFailed((Map<String, Object>) msg.obj);
                break;
            case wJobIssueStart:
                wJobIssueStart((Map<String, Object>) msg.obj);
                break;
            case wJobIssueSuccess:
                wJobIssueSuccess((Map<String, Object>) msg.obj);
                break;
            case wJobIssueFailed:
                wJobIssueFailed((Map<String, Object>) msg.obj);
                break;
            case wJobPasueStart:
                wJobPasueStart((Map<String, Object>) msg.obj);
                break;
            case wJobPasueSuccess:
                wJobPasueSuccess((Map<String, Object>) msg.obj);
                break;
            case wJobPasueFailed:
                wJobPasueFailed((Map<String, Object>) msg.obj);
                break;
            case wGetServiceStart:
                wGetServiceStart((Map<String, Object>) msg.obj);
                break;
            case wGetServiceSuccess:
                wGetServiceSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetServiceFailed:
                wGetServiceFailed((Map<String, Object>) msg.obj);
                break;
            default:
                break;
        }
    }
    private void wGetServiceStart(Map<String,Object> obj){
        Task task = new GetContractStateTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetServiceSuccess, wGetServiceFailed);
        executant.execute();
    }
    private void wGetServiceSuccess(Map<String,Object> obj){
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            ContractLimitBean contractLimitBean = (ContractLimitBean) obj.get("result");
            int num= Integer.parseInt(contractLimitBean.getBase_contract().getJob_open_limit())-Integer.parseInt(contractLimitBean.getBase_contract().getJob_open_limit_used());
            ((JobInfoActivity) context).setCurrent_limitNum(num);
            //Log.i("当前的限量值：",contractLimitBean.getBase_contract().getJob_open_limit()+"------后"+contractLimitBean.getBase_contract().getJob_open_limit_used());
        } else {
           // Log.i("当前的限量值：",obj.toString());
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }
    private void wGetServiceFailed(Map<String,Object> obj){
        Toast.makeText(context, "查询失败!", Toast.LENGTH_SHORT).show();
    }
    private void wJobPasueFailed(Map<String, Object> obj) {
        Toast.makeText(context, "暂停失败!", Toast.LENGTH_SHORT).show();

    }

    private void wJobPasueSuccess(Map<String, Object> obj) {
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            Toast.makeText(context, "暂停成功!", Toast.LENGTH_SHORT).show();
            ((JobInfoActivity)context).finish();
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }

    private void wJobPasueStart(Map<String, Object> obj) {
        Task task = new SetJobTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wJobPasueSuccess, wJobPasueFailed);
        executant.execute();
    }

    private void wJobIssueFailed(Map<String, Object> obj) {
        Toast.makeText(context, "发布失败!", Toast.LENGTH_SHORT).show();
    }

    private void wJobIssueSuccess(Map<String, Object> obj) {
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            Toast.makeText(context, "发布成功!", Toast.LENGTH_SHORT).show();
            ((JobInfoActivity)context).finish();
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }

    private void wJobIssueStart(Map<String, Object> obj) {
        Task task = new SetJobTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wJobIssueSuccess, wJobIssueFailed);
        executant.execute();
    }

    private void wJobInfoDelFailed(Map<String, Object> obj) {
        Toast.makeText(context, "删除失败!", Toast.LENGTH_SHORT).show();
    }

    private void wJobInfoDelSuccess(Map<String, Object> obj) {
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            Toast.makeText(context, "删除成功!", Toast.LENGTH_SHORT).show();
            ((JobInfoActivity)context).finish();
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }

    private void wJobInfoDelStart(Map<String, Object> obj) {
        Task task = new SetJobTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wJobInfoDelSuccess, wJobInfoDelFailed);
        executant.execute();
    }

    /**
     * 获取职位详情失败
     *
     * @param obj
     */
    private void wGetJobInfoFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "获取职位失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取职位详情成功
     *
     * @param obj
     */
    private void wGetJobInfoSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey("result")) {
            String result = obj.get("result").toString();
            ((JobInfoActivity) context).refshWebView(result);
        }
    }

    /**
     * 开始获取职位详情
     *
     * @param obj
     */
    private void wGetJobInfoStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new JobInfoTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task,
                R.string.loading, this, wGetJobInfoSuccess, wGetJobInfoFailed);
        executant.execute();
    }

}

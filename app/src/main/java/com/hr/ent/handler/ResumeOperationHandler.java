package com.hr.ent.handler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.hr.ent.R;
import com.hr.ent.async.AsyncExecutant;
import com.hr.ent.fragment.ByResumeScanFragment;
import com.hr.ent.model.JobResumeCountListBean;
import com.hr.ent.model.ResumeBoxInfoBean;
import com.hr.ent.model.ResumeDownloadBean;
import com.hr.ent.model.ResumeFilterInfoBean;
import com.hr.ent.model.ResumeNum;
import com.hr.ent.model.ResumeTypeAllBean;
import com.hr.ent.task.GetJobResumeCountTask;
import com.hr.ent.task.ResumeBoxFilterTask;
import com.hr.ent.task.ResumeOperationTask;
import com.hr.ent.task.Task;
import com.hr.ent.ui.MainActivity;
import com.hr.ent.ui.ResumeInfoActivity;
import com.hr.ent.ui.ResumeOperationActivity;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.Parser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeOperationHandler extends Handler {
    public static final int wSetResumeOperation = 1;// 设置简历到人才库
    public static final int wSetResumeOperationSuccess = 2;// 设置简历到人才库成功
    public static final int wSetResumeOperationFailed = 3;// 设置简历到人才库失败

    public static final int wSetResumeFilterStart = 4;// 获取职位列表
    public static final int wSetResumeFilterSuccess = 5;// 获取职位列表成功
    public static final int wSetResumeFilterFailed = 6;// 获取职位列表失败

    public static final int wSetResumeOperation2 = 7;// 设置简历到人才库
    public static final int wSetResumeOperationSuccess2 = 8;// 设置简历到人才库成功
    public static final int wSetResumeOperationFailed2 = 9;// 设置简历到人才库失败

    private Context context;
//	private ResumeAdapter adapter;

    public ResumeOperationHandler(Context context) {
        this.context = context;
//		this.adapter = adapter;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void dispatchMessage(Message msg) {
        // TODO Auto-generated method stub
        super.dispatchMessage(msg);
        switch (msg.what) {
            case wSetResumeOperation:
                wSetResumeOperation((Map<String, Object>) msg.obj);
                break;
            case wSetResumeOperationSuccess:
                wSetResumeOperationSuccess((Map<String, Object>) msg.obj);
                break;
            case wSetResumeOperationFailed:
                wSetResumeOperationFailed((Map<String, Object>) msg.obj);
                break;
            case wSetResumeFilterStart:
                wSetResumeFilterStart((Map<String, Object>) msg.obj);
                break;
            case wSetResumeFilterSuccess:
                wSetResumeFilterSuccess((Map<String, Object>) msg.obj);
                break;
            case wSetResumeFilterFailed:
                wSetResumeFilterFailed((Map<String, Object>) msg.obj);
                break;
            case wSetResumeOperation2:
                wSetResumeOperation2((Map<String, Object>) msg.obj);
                break;
            case wSetResumeOperationSuccess2:
                wSetResumeOperationSuccess2((Map<String, Object>) msg.obj);
                break;
            case wSetResumeOperationFailed2:
                wSetResumeOperationFailed2((Map<String, Object>) msg.obj);
                break;
        }
    }

    /**
     * 设置简历到人才库失败
     *
     * @param obj
     */
    private void wSetResumeOperationFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "设置简历到人才库失败", Toast.LENGTH_SHORT).show();

    }

    /**
     * 设置简历到人才库成功
     *
     * @param obj
     */
    @SuppressWarnings("unchecked")
    private void wSetResumeOperationSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            Toast.makeText(context, "设置简历到人才库成功", Toast.LENGTH_SHORT).show();
//            ByResumeScanFragment.byResumeScanFragment.initData();
            ResumeOperationActivity.resumeOperationActivity.finish();
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 设置简历到人才库
     *
     * @param obj
     */
    private void wSetResumeOperation(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new ResumeOperationTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wSetResumeOperationSuccess, wSetResumeOperationFailed);
        executant.execute();
    }

    /**
     * 获取职位列表失败
     *
     * @param obj
     */
    private void wSetResumeFilterFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "设置筛选状态失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * @param obj
     */
    private void wSetResumeFilterSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            Toast.makeText(context, "设置筛选状态成功", Toast.LENGTH_SHORT).show();
//            ByResumeScanFragment.byResumeScanFragment.initData();
            ResumeOperationActivity.resumeOperationActivity.finish();
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param obj
     */
    private void wSetResumeFilterStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new ResumeOperationTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wSetResumeFilterSuccess, wSetResumeFilterFailed);
        executant.execute();
    }

    /**
     * 设置简历到人才库失败
     *
     * @param obj
     */
    private void wSetResumeOperationFailed2(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "设置简历到人才库失败", Toast.LENGTH_SHORT).show();

    }

    /**
     * 设置简历到人才库成功
     *
     * @param obj
     */
    @SuppressWarnings("unchecked")
    private void wSetResumeOperationSuccess2(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            try {
                String isSuccess =new JSONObject(obj.get("result")+"").getJSONObject("return_data").getString("true");
                if (isSuccess.equals("1")){
                    Toast.makeText(context, "设置简历到人才库成功", Toast.LENGTH_SHORT).show();
                    ByResumeScanFragment.byResumeScanFragment.initData();
                    ResumeOperationActivity.resumeOperationActivity.finish();
                }else {
                    Toast.makeText(context, "已设置人才库", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 设置简历到人才库
     *
     * @param obj
     */
    private void wSetResumeOperation2(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new ResumeOperationTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wSetResumeOperationSuccess2, wSetResumeOperationFailed2);
        executant.execute();
    }

}

package com.hr.ent.handler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.async.AsyncExecutant;
import com.hr.ent.model.DepInfoBean;
import com.hr.ent.model.DepListBean;
import com.hr.ent.model.GetJobInfoBean;
import com.hr.ent.task.GetDepInfoTask;
import com.hr.ent.task.GetDepTask;
import com.hr.ent.task.GetJobInfoTask;
import com.hr.ent.task.SetJobTask;
import com.hr.ent.task.Task;
import com.hr.ent.ui.EditJobActivity;
import com.hr.ent.ui.GetDepartmentActivity;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.Parser;

import java.util.ArrayList;
import java.util.Map;

/**
 * 职位刷新逻辑控制类
 *
 * @author 800hr：zhuhui
 *         <p>
 *         2014-11-13
 */
public class PostJobHandler extends Handler {
    public static final int wGetDepStart = 1;// 开始获取发布中的职位
    public static final int wGetDepSuccess = 2;// 获取发布中的职位成功
    public static final int wGetDepFailed = 3;// 获取发布中的职位失败


    public static final int wSetJobStart = 4;// 新建职位
    public static final int wSetJobSuccess = 5;// 新建职位成功
    public static final int wSetJobFailed = 6;// 新建职位失败


    public static final int wGetJobInfoStart = 7;// 获取简历信息
    public static final int wGetJobInfoSuccess = 8;// 获取简历成功
    public static final int wGetJobInfoFailed = 9;// 获取简历失败

    public static final int wGetDep2Start = 10;// 获取简历信息
    public static final int wGetDep2Success = 11;// 获取简历成功
    public static final int wGetDep2Failed = 12;// 获取简历失败


    private Context context;
    private ListView listView;

    public PostJobHandler(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    public PostJobHandler(Context context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void dispatchMessage(Message msg) {
        // TODO Auto-generated method stub

        switch (msg.what) {
            case wGetDepStart:
                wGetDepStart((Map<String, Object>) msg.obj);
                break;
            case wGetDepSuccess:
                wGetDepSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetDepFailed:
                wGetDepFailed((Map<String, Object>) msg.obj);
                break;
            case wSetJobStart:
                wSetJobStart((Map<String, Object>) msg.obj);
                break;
            case wSetJobSuccess:
                wSetJobSuccess((Map<String, Object>) msg.obj);
                break;
            case wSetJobFailed:
                wSetJobFailed((Map<String, Object>) msg.obj);
                break;
            case wGetJobInfoStart:
                wGetJobInfoStart((Map<String, Object>) msg.obj);
                break;
            case wGetJobInfoSuccess:
                wGetJobInfoSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetJobInfoFailed:
                wGetJobInfoFailed((Map<String, Object>) msg.obj);
                break;
            case wGetDep2Start:
                wGetDep2Start((Map<String, Object>) msg.obj);
                break;
            case wGetDep2Success:
                wGetDep2Success((Map<String, Object>) msg.obj);
                break;
            case wGetDep2Failed:
                wGetDep2Failed((Map<String, Object>) msg.obj);
                break;
            default:
                break;
        }
    }

    private void wGetDep2Failed(Map<String, Object> obj) {

    }

    private void wGetDep2Success(Map<String, Object> obj) {
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            DepInfoBean depInfoBean = (DepInfoBean) obj.get("result");
            GetDepartmentActivity.getDepartmentActivity.initDepInfo(depInfoBean);
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }


    private void wGetDep2Start(Map<String, Object> obj) {
        Task task = new GetDepInfoTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetDep2Success, wGetDep2Failed);
        executant.execute();
    }

    private void wGetJobInfoFailed(Map<String, Object> obj) {
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            DepListBean depListBean = (DepListBean) obj.get("result");
            GetDepartmentActivity.getDepartmentActivity.initAdapter((ArrayList<DepListBean.ReturnDataBean>) depListBean.getReturn_data());
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }

    }

    private void wGetJobInfoSuccess(Map<String, Object> obj) {
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            GetJobInfoBean getJobInfoBean = (GetJobInfoBean) obj.get("result");
            EditJobActivity.editJobActivity.setData(getJobInfoBean);
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }

    private void wGetJobInfoStart(Map<String, Object> obj) {
        Task task = new GetJobInfoTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetJobInfoSuccess, wGetJobInfoFailed);
        executant.execute();
    }

    private void wSetJobFailed(Map<String, Object> obj) {
        Toast.makeText(context, "保存失败!", Toast.LENGTH_SHORT).show();
    }


    private void wSetJobSuccess(Map<String, Object> obj) {
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            Toast.makeText(context, "保存成功!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }

    private void wSetJobStart(Map<String, Object> obj) {
        Task task = new SetJobTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wSetJobSuccess, wSetJobFailed);
        executant.execute();
    }

    private void wGetDepFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub

    }

    private void wGetDepSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            DepListBean depListBean = (DepListBean) obj.get("result");
            GetDepartmentActivity.getDepartmentActivity.initAdapter((ArrayList<DepListBean.ReturnDataBean>) depListBean.getReturn_data());

        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }


    private void wGetDepStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new GetDepTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetDepSuccess, wGetDepFailed);
        executant.execute();
    }
}

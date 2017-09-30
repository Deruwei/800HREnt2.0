package com.hr.ent.handler;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.ResumeSearchListAdapter;
import com.hr.ent.app.App;
import com.hr.ent.async.AsyncExecutant;
import com.hr.ent.model.ResumeNavpageInfoBean;
import com.hr.ent.model.ResumeSearchListBean;
import com.hr.ent.model.ResumeSearchResultBean;
import com.hr.ent.task.GetSearchResumeTask;
import com.hr.ent.task.Task;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.Parser;
import com.hr.ent.view.LoadMoreListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 职位刷新逻辑控制类
 *
 * @author 800hr：zhuhui
 *         <p>
 *         2014-11-13
 */
public class ResumeRefshHandler extends Handler {
    public static final int wGetResumeStart = 1;// 开始获取发布中的职位
    public static final int wGetResumeSuccess = 2;// 获取发布中的职位成功
    public static final int wGetResumeFailed = 3;// 获取发布中的职位失败

    public static final int wGetResumeMoreStart = 7;// 开始获取更多发布中的职位
    public static final int wGetResumeMoreSuccess = 8;// 获取更多发布中的职位成功
    public static final int wGetResumeMoreFailed = 9;// 获取更多发布中的职位失败

    private Context context;
    private ResumeSearchListAdapter adapter;
    private App app;
    private LoadMoreListView loadMoreListView;

    public ResumeRefshHandler(Context context, ResumeSearchListAdapter adapter, LoadMoreListView loadMoreListView) {
        this.context = context;
        this.adapter = adapter;
        this.loadMoreListView = loadMoreListView;
    }

    public ResumeRefshHandler(Context context, ResumeSearchListAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
        app = (App) context.getApplicationContext();
    }

    public ResumeRefshHandler(Context context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void dispatchMessage(Message msg) {
        // TODO Auto-generated method stub

        switch (msg.what) {
            case wGetResumeStart:
                wGetResumeStart((Map<String, Object>) msg.obj);
                break;
            case wGetResumeSuccess:
                wGetResumeSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetResumeFailed:
                wGetResumeFailed((Map<String, Object>) msg.obj);
                break;
            case wGetResumeMoreStart:
                wGetResumeMoreStart((Map<String, Object>) msg.obj);
                break;
            case wGetResumeMoreSuccess:
                wGetResumeMoreSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetResumeMoreFailed:
                wGetResumeMoreFailed((Map<String, Object>) msg.obj);
                break;
            default:
                break;
        }
    }

    private void wGetResumeMoreFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
    }


    private void wGetResumeMoreStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new GetSearchResumeTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetResumeMoreSuccess, wGetResumeMoreFailed);
        executant.execute();

    }

    private void wGetResumeFailed(Map<String, Object> obj) {

    }

    private void wGetResumeSuccess(Map<String, Object> obj) {
//		// TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            ResumeSearchResultBean listBean = (ResumeSearchResultBean) obj.get("result");
            List<ResumeSearchListBean> infoBeans = listBean.getList();
            List<ResumeSearchListBean> baseResumeInfoBeans = new ArrayList<ResumeSearchListBean>();
            ResumeNavpageInfoBean resumeNavpageInfo = (ResumeNavpageInfoBean) obj.get("resumeNavpageInfo");
            baseResumeInfoBeans.addAll(infoBeans);
            adapter.setTotalPage(Integer.parseInt(resumeNavpageInfo.getTotal_pages()));
            adapter.setList(baseResumeInfoBeans);
            adapter.notifyDataSetChanged();
            loadMoreListView.onLoadMoreComplete();
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }

    }

    private void wGetResumeMoreSuccess(Map<String, Object> obj) {
//        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
//            ResumeSearchResultBean jobListBean = (ResumeSearchResultBean) obj.get("result");
//            ResumeNavpageInfoBean resumeNavpageInfo = (ResumeNavpageInfoBean) obj.get("resumeNavpageInfo");
//            adapter.setTotalPage(Integer.parseInt(resumeNavpageInfo.getTotal_pages()));
//            adapter.add(jobListBean.getList());
//            adapter.notifyDataSetChanged();
//            adapter.setTotalNums(Integer.parseInt(resumeNavpageInfo.getRecord_nums()));
////            loadMoreListView.onLoadMoreComplete();
//        } else {
//            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
//        }
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            ResumeSearchResultBean listBean = (ResumeSearchResultBean) obj.get("result");
            List<ResumeSearchListBean> infoBeans = listBean.getList();
            List<ResumeSearchListBean> baseResumeInfoBeans = new ArrayList<ResumeSearchListBean>();
//            ResumeSearchResultBean jobListBean = (ResumeSearchResultBean) obj.get("result");
            ResumeNavpageInfoBean resumeNavpageInfo = (ResumeNavpageInfoBean) obj.get("resumeNavpageInfo");
            baseResumeInfoBeans.addAll(infoBeans);
            adapter.setTotalPage(Integer.parseInt(resumeNavpageInfo.getTotal_pages()));
            adapter.add(baseResumeInfoBeans);
            adapter.notifyDataSetChanged();
            loadMoreListView.onLoadMoreComplete();
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }


    private void wGetResumeStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new GetSearchResumeTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetResumeSuccess, wGetResumeFailed);
        executant.execute();
    }


}

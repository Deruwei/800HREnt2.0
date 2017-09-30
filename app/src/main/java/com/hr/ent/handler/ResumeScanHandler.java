package com.hr.ent.handler;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.ResumeListAdapter;
import com.hr.ent.app.App;
import com.hr.ent.async.AsyncExecutant;
import com.hr.ent.model.BaseResumeInfoBean;
import com.hr.ent.model.DownLoadResumeInfoBean;
import com.hr.ent.model.DownLoadResumeListBean;
import com.hr.ent.model.ResumeInfoBean;
import com.hr.ent.model.ResumeListBean;
import com.hr.ent.task.GetDownResumeListTask;
import com.hr.ent.task.GetResumeListTask;
import com.hr.ent.task.Task;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 简历浏览处理类
 *
 * @author 800hr：yelong
 *         <p/>
 *         2015-6-26
 */
public class ResumeScanHandler extends Handler {
    public static final int wGetResumeListStart = 1;// 开始获取简历列表
    public static final int wGetResumeListSuccess = 2;// 获取简历列表成功
    public static final int wGetResumeListFailed = 3;// 获取简历列表失败

    public static final int wGetResumeListMoreStart = 4;// 开始获取更多简历列表
    public static final int wGetResumeListMoreSuccess = 5;// 获取更多简历列表成功
    public static final int wGetResumeListMoreFailed = 6;// 获取更多简历列表失败

    public static final int wGetDownResumeListStart = 7;// 开始获取已下载简历列表
    public static final int wGetDownResumeListSuccess = 8;// 获取已下载简历列表成功
    public static final int wGetDownResumeListFailed = 9;// 获取已下载简历列表失败

    public static final int wGetDownResumeListMoreStart = 10;// 开始获取更多已下载简历列表
    public static final int wGetDownResumeListMoreSuccess = 11;// 获取更多已下载简历列表成功
    public static final int wGetDownResumeListMoreFailed = 12;// 获取更多已下载简历列表失败

    private Context context;
    private ResumeListAdapter listAdapter;
    private App app;

    public ResumeScanHandler(Context context, ResumeListAdapter listAdapter) {
        this.context = context;
        this.listAdapter = listAdapter;
        app = (App) context.getApplicationContext();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void dispatchMessage(Message msg) {
        // TODO Auto-generated method stub
        super.dispatchMessage(msg);
        switch (msg.what) {
            case wGetResumeListStart:
                wGetResumeListStart((Map<String, Object>) msg.obj);
                break;
            case wGetResumeListSuccess:
                wGetResumeListSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetResumeListFailed:
                wGetResumeListFailed((Map<String, Object>) msg.obj);
                break;

            case wGetResumeListMoreStart:
                wGetResumeListMoreStart((Map<String, Object>) msg.obj);
                break;
            case wGetResumeListMoreSuccess:
                wGetResumeListMoreSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetResumeListMoreFailed:
                wGetResumeListMoreFailed((Map<String, Object>) msg.obj);
                break;

            case wGetDownResumeListStart:
                wGetDownResumeListStart((Map<String, Object>) msg.obj);
                break;
            case wGetDownResumeListSuccess:
                wGetDownResumeListSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetDownResumeListFailed:
                wGetDownResumeListFailed((Map<String, Object>) msg.obj);
                break;

            case wGetDownResumeListMoreStart:
                wGetDownResumeListMoreStart((Map<String, Object>) msg.obj);
                break;
            case wGetDownResumeListMoreSuccess:
                wGetDownResumeListMoreSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetDownResumeListMoreFailed:
                wGetDownResumeListMoreFailed((Map<String, Object>) msg.obj);
                break;
        }
    }

    /**
     * 获取简历列表失败
     *
     * @param obj
     */
    private void wGetResumeListFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "获取简历列表失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取简历列表成功
     *
     * @param obj
     */
    private void wGetResumeListSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE)
                && obj.get(Const.ERROR_CODE).toString()
                .equals(Const.ApiSuccess + "")) {
            if (obj.containsKey("result")) {
                ResumeListBean listBean = (ResumeListBean) obj.get("result");
                List<ResumeInfoBean> infoBeans = listBean.getReturn_data();
                List<BaseResumeInfoBean> baseResumeInfoBeans = new ArrayList<BaseResumeInfoBean>();
                baseResumeInfoBeans.addAll(infoBeans);
                listAdapter.setTotalPage(listBean.getNavpage_info()
                        .getTotal_pages());
                listAdapter.setTotalNums(listBean.getNavpage_info()
                        .getRecord_nums());
                listAdapter.setList(baseResumeInfoBeans);
                listAdapter.notifyDataSetChanged();
                // 初始化简历浏览的数据
                Intent mIntent = new Intent(Const.RESUME_LIST);
                context.sendBroadcast(mIntent);

                if (app.isLoadingResumeInfo()) {
                    Intent intent = new Intent();
                    intent.setAction(Const.INIT_PAGE);

                    // intent.putExtra("infobeans",
                    // (Serializable) baseResumeInfoBeans);

                    context.sendBroadcast(intent);
                }
            }

        } else {
            Toast.makeText(
                    context,
                    Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取简历列表开始
     *
     * @param obj
     */
    private void wGetResumeListStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new GetResumeListTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task,
                R.string.loading, this, wGetResumeListSuccess,
                wGetResumeListFailed);
        executant.execute();
    }

    /**
     * 获取更多简历列表失败
     *
     * @param obj
     */
    private void wGetResumeListMoreFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "获取更多简历失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取更多简历列表成功
     *
     * @param obj
     */
    private void wGetResumeListMoreSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE)
                && obj.get(Const.ERROR_CODE).toString()
                .equals(Const.ApiSuccess + "")) {
            ResumeListBean listBean = (ResumeListBean) obj.get("result");
            List<ResumeInfoBean> infoBeans = listBean.getReturn_data();
            List<BaseResumeInfoBean> baseResumeInfoBeans = new ArrayList<BaseResumeInfoBean>();
            baseResumeInfoBeans.addAll(infoBeans);
            listAdapter.setTotalPage(listBean.getNavpage_info()
                    .getTotal_pages());
            listAdapter.add(baseResumeInfoBeans);
            listAdapter.notifyDataSetChanged();
            // 初始化简历浏览的数据
            Intent mIntent = new Intent(Const.RESUME_LIST);
            context.sendBroadcast(mIntent);

            if (app.isLoadingResumeInfo()) {
                Intent intent = new Intent();
                intent.setAction(Const.REFSH_PAGE);
                context.sendBroadcast(intent);
            }
        } else {
            Toast.makeText(
                    context,
                    Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 开始获取更多简历列表
     *
     * @param obj
     */
    private void wGetResumeListMoreStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new GetResumeListTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task,
                R.string.loading, this, wGetResumeListMoreSuccess,
                wGetResumeListMoreFailed);
        executant.execute();
    }

    /**
     * 获取已下载列表失败
     *
     * @param obj
     */
    private void wGetDownResumeListFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "获取已下载简历列表失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取已下载简历列表成功
     *
     * @param obj
     */
    private void wGetDownResumeListSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE)
                && obj.get(Const.ERROR_CODE).toString()
                .equals(Const.ApiSuccess + "")) {

            DownLoadResumeListBean listBean = (DownLoadResumeListBean) obj
                    .get("result");
            List<DownLoadResumeInfoBean> downLoadResumeInfoBeans = listBean
                    .getReturn_data().getList();
            List<BaseResumeInfoBean> resumeInfoBeans = new ArrayList<BaseResumeInfoBean>();
            resumeInfoBeans.addAll(downLoadResumeInfoBeans);
            listAdapter.setTotalPage(listBean.getNavpage_info()
                    .getTotal_pages());
            listAdapter.setTotalNums(listBean.getNavpage_info()
                    .getRecord_nums());
            listAdapter.setList(resumeInfoBeans);
            listAdapter.notifyDataSetChanged();
            // 初始化简历浏览的数据
            Intent mIntent = new Intent(Const.RESUME_LIST);
            context.sendBroadcast(mIntent);

            if (app.isLoadingResumeInfo()) {
                Intent intent = new Intent();
                intent.setAction(Const.INIT_PAGE);
                context.sendBroadcast(intent);
            }
        } else {
            Toast.makeText(
                    context,
                    Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 开始获取已下载简历列表
     *
     * @param obj
     */
    private void wGetDownResumeListStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new GetDownResumeListTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task,
                R.string.loading, this, wGetDownResumeListSuccess,
                wGetDownResumeListFailed);
        executant.execute();
    }

    /**
     * 获取更多已下载简历列表失败
     *
     * @param obj
     */
    private void wGetDownResumeListMoreFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "获取更多已下载简历列表失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取更多已下载简历列表成功
     *
     * @param obj
     */
    private void wGetDownResumeListMoreSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE)
                && obj.get(Const.ERROR_CODE).toString()
                .equals(Const.ApiSuccess + "")) {

            DownLoadResumeListBean listBean = (DownLoadResumeListBean) obj
                    .get("result");
            List<DownLoadResumeInfoBean> downLoadResumeInfoBeans = listBean
                    .getReturn_data().getList();
            List<BaseResumeInfoBean> resumeInfoBeans = new ArrayList<BaseResumeInfoBean>();
            resumeInfoBeans.addAll(downLoadResumeInfoBeans);
            listAdapter.setTotalPage(listBean.getNavpage_info()
                    .getTotal_pages());
            listAdapter.add(resumeInfoBeans);
            listAdapter.notifyDataSetChanged();
            // 初始化简历浏览的数据
            Intent mIntent = new Intent(Const.RESUME_LIST);
            context.sendBroadcast(mIntent);

            if (app.isLoadingResumeInfo()) {
                Intent intent = new Intent();
                intent.setAction(Const.REFSH_PAGE);
                context.sendBroadcast(intent);
            }
        } else {
            Toast.makeText(
                    context,
                    Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 开始获取更多已下载简历列表
     *
     * @param obj
     */
    private void wGetDownResumeListMoreStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new GetDownResumeListTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task,
                R.string.loading, this, wGetDownResumeListMoreSuccess,
                wGetDownResumeListMoreFailed);
        executant.execute();
    }
}

package com.hr.ent.handler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.ChooseJobAdapter;
import com.hr.ent.async.AsyncExecutant;
import com.hr.ent.model.JobListBean;
import com.hr.ent.task.GetJobTask;
import com.hr.ent.task.JobRefshAllTask;
import com.hr.ent.task.JobRefshTask;
import com.hr.ent.task.Task;
import com.hr.ent.ui.GetJobActivity;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.DateUtils;
import com.hr.ent.utils.Parser;
import com.hr.ent.view.LoadMoreListView;

import java.util.Map;

/**
 * 职位刷新逻辑控制类
 *
 * @author 800hr：zhuhui
 *         <p/>
 *         2014-11-13
 */
public class ChooseJobRefshHandler extends Handler {
    public static final int wGetJobStart = 1;// 开始获取发布中的职位
    public static final int wGetJobSuccess = 2;// 获取发布中的职位成功
    public static final int wGetJobFailed = 3;// 获取发布中的职位失败

    public static final int wGetJobMoreStart = 7;// 开始获取更多发布中的职位
    public static final int wGetJobMoreSuccess = 8;// 获取更多发布中的职位成功
    public static final int wGetJobMoreFailed = 9;// 获取更多发布中的职位失败

    public static final int wRefshStart = 4;// 开始刷新职位
    public static final int wRefshSuccess = 5;// 刷新职位成功
    public static final int wRefshFailed = 6;// 刷新职位失败

    public static final int wRefshAllStart = 10;// 开始刷新全部职位
    public static final int wRefshAllSuccess = 11;// 刷新全部职位成功
    public static final int wRefshAllFailed = 12;// 刷新全部职位失败

    private Context context;
    private ChooseJobAdapter adapter;
    private LoadMoreListView loadMoreListView;

    public ChooseJobRefshHandler(Context context, ChooseJobAdapter adapter,
                                 LoadMoreListView loadMoreListView) {
        this.context = context;
        this.adapter = adapter;
        this.loadMoreListView = loadMoreListView;
    }

    public ChooseJobRefshHandler(Context context, ChooseJobAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void dispatchMessage(Message msg) {
        // TODO Auto-generated method stub

        switch (msg.what) {
            case wGetJobStart:
                wGetJobStart((Map<String, Object>) msg.obj);
                break;
            case wGetJobSuccess:
                wGetJobSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetJobFailed:
                wGetJobFailed((Map<String, Object>) msg.obj);
                break;

            case wGetJobMoreStart:
                wGetJobMoreStart((Map<String, Object>) msg.obj);
                break;
            case wGetJobMoreSuccess:
                wGetJobMoreSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetJobMoreFailed:
                wGetJobMoreFailed((Map<String, Object>) msg.obj);
                break;
            case wRefshStart:
                wRefshStart((Map<String, Object>) msg.obj);
                break;
            case wRefshSuccess:
                wRefshSuccess((Map<String, Object>) msg.obj);
                break;
            case wRefshFailed:
                wRefshFailed((Map<String, Object>) msg.obj);
                break;
            case wRefshAllStart:
                wRefshAllStart((Map<String, Object>) msg.obj);
                break;
            case wRefshAllSuccess:
                wRefshAllSuccess((Map<String, Object>) msg.obj);
                break;
            case wRefshAllFailed:
                wRefshAllFailed((Map<String, Object>) msg.obj);
                break;
            default:
                break;
        }
    }

    /**
     * 刷新全部职位失败
     *
     * @param obj
     */
    private void wRefshAllFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
    }

    /**
     * 刷新全部职位成功
     *
     * @param obj
     */
    private void wRefshAllSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE)
                && obj.get(Const.ERROR_CODE).toString()
                .equals(Const.ApiSuccess + "")) {
            GetJobActivity.getJobActivity.initDataByNet();
            Toast.makeText(context, DateUtils.GetNowDateChinesne() + "职位刷新成功",
                    Toast.LENGTH_LONG).show();
        } else {
            if (obj.containsKey(Const.ERROR_CODE)
                    && !obj.get(Const.ERROR_CODE).toString().equals("401")) {
                Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE)
                        .toString()), Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 开始刷新全部职位
     *
     * @param obj
     */
    private void wRefshAllStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new JobRefshAllTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task,
                R.string.loading, this, wRefshAllSuccess, wRefshAllFailed);
        executant.execute();
    }

    private void wGetJobMoreFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
    }


    private void wGetJobMoreStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new GetJobTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task,
                R.string.loading, this, wGetJobMoreSuccess, wGetJobMoreFailed);
        executant.execute();

    }

    private void wRefshFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "刷新失败", Toast.LENGTH_SHORT).show();
    }

    private void wRefshSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE)
                && obj.get(Const.ERROR_CODE).toString()
                .equals(Const.ApiSuccess + "")) {
            if (obj.containsKey("job_id")) {
                adapter.updateJobDate(obj.get("job_id").toString());
            }
            Toast.makeText(context, "刷新成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(
                    context,
                    Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void wRefshStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new JobRefshTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task,
                R.string.loading, this, wRefshSuccess, wRefshFailed);
        executant.execute();
    }

    private void wGetJobFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub

    }

    private void wGetJobSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            JobListBean jobListBean = (JobListBean) obj.get("result");
            adapter.setTotalPage(jobListBean.getNavpage_info().getTotal_pages());
            adapter.setJobInfoBeans(jobListBean.getList());
            adapter.setTotalNums(jobListBean.getNavpage_info().getRecord_nums());
            adapter.notifyDataSetChanged();
            loadMoreListView.onLoadMoreComplete();
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }

    private void wGetJobMoreSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            JobListBean jobListBean = (JobListBean) obj.get("result");
            adapter.setTotalPage(jobListBean.getNavpage_info().getTotal_pages());
            adapter.addJobInfoBeans(jobListBean.getList());
            adapter.notifyDataSetChanged();
            adapter.setTotalNums(jobListBean.getNavpage_info().getPage_nums());
            loadMoreListView.onLoadMoreComplete();
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }

    private void wGetJobStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new GetJobTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task,
                R.string.loading, this, wGetJobSuccess, wGetJobFailed);
        executant.execute();
    }
}

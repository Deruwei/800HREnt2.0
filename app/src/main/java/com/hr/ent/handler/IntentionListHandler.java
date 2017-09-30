package com.hr.ent.handler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.IntentionCommAdapter;
import com.hr.ent.async.AsyncExecutant;
import com.hr.ent.model.IntentionInfoBean2;
import com.hr.ent.model.IntentionListBean;
import com.hr.ent.task.IntentionListTask;
import com.hr.ent.task.Task;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.Parser;
import com.hr.ent.view.LoadMoreListView;

import java.util.ArrayList;
import java.util.Map;

/**
 * 作者：Colin
 * 日期：2016/9/6 08:57
 * 邮箱：bestxt@qq.com
 */
public class IntentionListHandler extends Handler {
    public static final int wGetIntentionListStart = 1;// 开始获取意向沟通记录
    public static final int wGetIntentionListSuccess = 2;// 获取意向沟通记录成功
    public static final int wGetIntentionListFailed = 3;// 获取意向沟通记录失败

    public static final int wGetMoreIntentionListStart = 4;// 开始获取更多意向沟通记录
    public static final int wGetMoreIntentionListSuccess = 5;// 获取更多意向沟通记录成功
    public static final int wGetMoreIntentionListFailed = 6;// 获取更多意向沟通记录失败
    private Context context;
    private IntentionCommAdapter adapter;
    private LoadMoreListView loadMoreListView;

    public IntentionListHandler(Context context, IntentionCommAdapter adapter, LoadMoreListView loadMoreListView) {
        this.context = context;
        this.adapter = adapter;
        this.loadMoreListView = loadMoreListView;
    }
    @SuppressWarnings("unchecked")
    @Override
    public void dispatchMessage(Message msg) {
        // TODO Auto-generated method stub
        super.dispatchMessage(msg);
        switch (msg.what) {
            case wGetIntentionListStart:
                wGetIntentionListStart((Map<String, Object>) msg.obj);
                break;
            case wGetIntentionListSuccess:
                wGetIntentionListSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetIntentionListFailed:
                wGetIntentionListFailed((Map<String, Object>) msg.obj);
                break;
            case wGetMoreIntentionListStart:
                wGetMoreIntentionListStart((Map<String, Object>) msg.obj);
                break;
            case wGetMoreIntentionListSuccess:
                wGetMoreIntentionListSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetMoreIntentionListFailed:
                wGetMoreIntentionListFailed((Map<String, Object>) msg.obj);
                break;
            default:
                break;
        }
    }

    /**
     * 再次获取邀请记录失败
     *
     * @param obj
     */
    private void wGetMoreIntentionListFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "加载失败more", Toast.LENGTH_SHORT).show();
    }


    private void wGetMoreIntentionListStart(Map<String, Object> obj) {
        Task task = new IntentionListTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetMoreIntentionListSuccess, wGetMoreIntentionListFailed);
        executant.execute();
    }

    /**
     * 获取邀请记录失败
     *
     * @param obj
     */
    private void wGetIntentionListFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "加载失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取邀请记录成功
     *
     * @param obj
     */
    private void wGetIntentionListSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey("result")) {
            IntentionListBean intentionListBean = (IntentionListBean) obj.get("result");
            adapter.setTotalPage(intentionListBean.getNavpage_info().getTotal_pages());
            adapter.setTotalNums(intentionListBean.getNavpage_info().getPage_nums());
            adapter.setList((ArrayList<IntentionInfoBean2>) intentionListBean.getlist());
            adapter.notifyDataSetChanged();
            loadMoreListView.onLoadMoreComplete();
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }

    private void wGetMoreIntentionListSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey("result")) {
            IntentionListBean intentionListBean = (IntentionListBean) obj.get("result");
            adapter.setTotalPage(intentionListBean.getNavpage_info().getTotal_pages());
            adapter.setTotalNums(intentionListBean.getNavpage_info().getPage_nums());
            adapter.addList((ArrayList<IntentionInfoBean2>) intentionListBean.getlist());
            adapter.notifyDataSetChanged();
            loadMoreListView.onLoadMoreComplete();
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 开始获取邀请记录
     *
     * @param obj
     */
    private void wGetIntentionListStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new IntentionListTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetIntentionListSuccess, wGetIntentionListFailed);
        executant.execute();
    }
}

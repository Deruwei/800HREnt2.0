package com.hr.ent.handler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.TalentsListAdapter;
import com.hr.ent.async.AsyncExecutant;
import com.hr.ent.model.TalentsListBean;
import com.hr.ent.task.TalentsListTask;
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
public class TalentsListHandler extends Handler {
    public static final int wGetTalentsListStart = 1;// 开始获取意向沟通记录
    public static final int wGetTalentsListSuccess = 2;// 获取意向沟通记录成功
    public static final int wGetTalentsListFailed = 3;// 获取意向沟通记录失败

    public static final int wGetMoreTalentsListStart = 4;// 开始获取更多意向沟通记录
    public static final int wGetMoreTalentsListSuccess = 5;// 获取更多意向沟通记录成功
    public static final int wGetMoreTalentsListFailed = 6;// 获取更多意向沟通记录失败
    private Context context;
    private TalentsListAdapter adapter;
    private LoadMoreListView loadMoreListView;

    public TalentsListHandler(Context context, TalentsListAdapter adapter, LoadMoreListView loadMoreListView) {
        this.context = context;
        this.adapter = adapter;
        this.loadMoreListView = loadMoreListView;
    }

//    public TalentsListHandler(Context context , LoadMoreListView loadMoreListView) {
//        this.context = context;
//        this.loadMoreListView = loadMoreListView;
//    }

    @SuppressWarnings("unchecked")
    @Override
    public void dispatchMessage(Message msg) {
        // TODO Auto-generated method stub
        super.dispatchMessage(msg);
        switch (msg.what) {
            case wGetTalentsListStart:
                wGetTalentsListStart((Map<String, Object>) msg.obj);
                break;
            case wGetTalentsListSuccess:
                wGetTalentsListSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetTalentsListFailed:
                wGetTalentsListFailed((Map<String, Object>) msg.obj);
                break;
            case wGetMoreTalentsListStart:
                wGetMoreTalentsListStart((Map<String, Object>) msg.obj);
                break;
            case wGetMoreTalentsListSuccess:
                wGetMoreTalentsListSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetMoreTalentsListFailed:
                wGetMoreTalentsListFailed((Map<String, Object>) msg.obj);
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
    private void wGetMoreTalentsListFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "加载失败more", Toast.LENGTH_SHORT).show();
    }


    private void wGetMoreTalentsListStart(Map<String, Object> obj) {
        Task task = new TalentsListTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetMoreTalentsListSuccess, wGetMoreTalentsListFailed);
        executant.execute();
    }
    /**
     * 获取邀请记录失败
     * @param obj
     */
    private void wGetTalentsListFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "加载失败", Toast.LENGTH_SHORT).show();
    }
    /**
     * 获取邀请记录成功
     * @param obj
     */
    private void wGetTalentsListSuccess(Map<String, Object> obj) {

        // TODO Auto-generated method stub
        if (obj.containsKey("result")) {
            TalentsListBean talentsListBean = (TalentsListBean) obj.get("result");
            adapter.setTotalPage(Integer.parseInt(talentsListBean.getNavpage_info().getTotal_pages()));
            adapter.setTotalNums(Integer.parseInt(talentsListBean.getNavpage_info().getPage_nums()));
            adapter.setList((ArrayList<TalentsListBean.ListBean>) talentsListBean.getList());
            adapter.notifyDataSetChanged();
            loadMoreListView.onLoadMoreComplete();
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }

    }
    private void wGetMoreTalentsListSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey("result")) {
            TalentsListBean talentsListBean = (TalentsListBean) obj.get("result");
            adapter.setTotalPage(Integer.parseInt(talentsListBean.getNavpage_info().getTotal_pages()));
            adapter.setTotalNums(Integer.parseInt(talentsListBean.getNavpage_info().getPage_nums()));
            adapter.addList((ArrayList<TalentsListBean.ListBean>) talentsListBean.getList());
            adapter.notifyDataSetChanged();
            loadMoreListView.onLoadMoreComplete();
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 开始获取邀请记录
     * @param obj
     */
    private void wGetTalentsListStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new TalentsListTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetTalentsListSuccess, wGetTalentsListFailed);
        executant.execute();
    }
}

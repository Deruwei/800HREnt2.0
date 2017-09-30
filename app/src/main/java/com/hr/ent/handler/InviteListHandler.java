package com.hr.ent.handler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.InviteListAdapter;
import com.hr.ent.async.AsyncExecutant;
import com.hr.ent.model.InviteInfoBean;
import com.hr.ent.model.InviteListBean;
import com.hr.ent.task.InviteListTask;
import com.hr.ent.task.Task;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.Parser;
import com.hr.ent.view.LoadMoreListView;

import java.util.ArrayList;
import java.util.Map;

/**
 * 作者：Colin
 * 日期：2016/9/1 14:59
 * 邮箱：bestxt@qq.com
 */
public class InviteListHandler extends Handler {
    public static final int wGetInviteListStart = 1;// 开始获取邀请记录
    public static final int wGetInviteListSuccess = 2;// 获取邀请记录成功
    public static final int wGetInviteListFailed = 3;// 获取邀请记录失败

    public static final int wGetMoreInviteListStart = 4;// 开始获取邀请记录
    public static final int wGetMoreInviteListSuccess = 5;// 获取邀请记录成功
    public static final int wGetMoreInviteListFailed = 6;// 获取邀请记录失败

    private Context context;
    private InviteListAdapter adapter;
    private LoadMoreListView loadMoreListView;

    public InviteListHandler(Context context, InviteListAdapter adapter, LoadMoreListView loadMoreListView) {
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
            case wGetInviteListStart:
                wGetInviteListStart((Map<String, Object>) msg.obj);
                break;
            case wGetInviteListSuccess:
                wGetInviteListSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetInviteListFailed:
                wGetInviteListFailed((Map<String, Object>) msg.obj);
                break;
            case wGetMoreInviteListStart:
                wGetMoreInviteListStart((Map<String, Object>) msg.obj);
                break;
            case wGetMoreInviteListSuccess:
                wGetMoreInviteListSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetMoreInviteListFailed:
                wGetMoreInviteListFailed((Map<String, Object>) msg.obj);
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
    private void wGetMoreInviteListFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "加载失败", Toast.LENGTH_SHORT).show();
    }


    private void wGetMoreInviteListStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new InviteListTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetMoreInviteListSuccess, wGetMoreInviteListFailed);
        executant.execute();
    }

    /**
     * 获取邀请记录失败
     *
     * @param obj
     */
    private void wGetInviteListFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "加载失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取邀请记录成功
     *
     * @param obj
     */
    private void wGetInviteListSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey("result")) {
            InviteListBean inviteListBean = (InviteListBean) obj.get("result");
            adapter.setTotalPage(inviteListBean.getNavpage_info().getTotal_pages());
            adapter.setTotalNums(inviteListBean.getNavpage_info().getPage_nums());
            adapter.setList((ArrayList<InviteInfoBean>) inviteListBean.getReturn_data());
            adapter.notifyDataSetChanged();
            loadMoreListView.onLoadMoreComplete();
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }

    private void wGetMoreInviteListSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey("result")) {
            InviteListBean inviteListBean = (InviteListBean) obj.get("result");
            adapter.setTotalPage(inviteListBean.getNavpage_info().getTotal_pages());
            adapter.setTotalNums(inviteListBean.getNavpage_info().getPage_nums());
            adapter.addList((ArrayList<InviteInfoBean>) inviteListBean.getReturn_data());
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
    private void wGetInviteListStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new InviteListTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetInviteListSuccess, wGetInviteListFailed);
        executant.execute();
    }
}

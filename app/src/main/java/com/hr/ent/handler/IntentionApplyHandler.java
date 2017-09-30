package com.hr.ent.handler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.app.App;
import com.hr.ent.async.AsyncExecutant;
import com.hr.ent.task.IntentionApplyTask;
import com.hr.ent.task.Task;
import com.hr.ent.ui.DoIntentionCommunicationActivity;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.Parser;

import java.util.Map;

/**
 * 发起意向沟通
 *
 * @author 800hr：zhuhui
 *         <p/>
 *         2014-12-30
 */
public class IntentionApplyHandler extends Handler {
    public static final int wIntentionApplyStart = 1;// 开始意向沟通
    public static final int wIntentionApplySuccess = 2;// 意向沟通成功
    public static final int wIntentionApplyFailed = 3;// 意向沟通失败

    private Context context;

    public IntentionApplyHandler(Context context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void dispatchMessage(Message msg) {
        // TODO Auto-generated method stub
        super.dispatchMessage(msg);
        switch (msg.what) {
            case wIntentionApplyStart:
                wIntentionApplyStart((Map<String, Object>) msg.obj);
                break;
            case wIntentionApplySuccess:
                wIntentionApplySuccess((Map<String, Object>) msg.obj);
                break;
            case wIntentionApplyFailed:
                wIntentionApplyFailed((Map<String, Object>) msg.obj);
                break;
            default:
                break;
        }
    }

    /**
     * 意向沟通失败
     *
     * @param obj
     */
    private void wIntentionApplyFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "意向沟通失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 意向沟通成功
     *
     * @param obj
     */
    private void wIntentionApplySuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
//            ((ResumeParticularInviteActivity) context).closeForwardDialog();
            App.intentionNum = App.intentionNum - 1;
            Toast.makeText(context, "发起意向沟通成功", Toast.LENGTH_SHORT).show();
            DoIntentionCommunicationActivity.doIntentionComm.finish();
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 开始意向沟通
     *
     * @param obj
     */
    private void wIntentionApplyStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new IntentionApplyTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wIntentionApplySuccess, wIntentionApplyFailed);
        executant.execute();
    }

}

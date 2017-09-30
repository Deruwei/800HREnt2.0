package com.hr.ent.handler;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.app.App;
import com.hr.ent.async.AsyncExecutant;
import com.hr.ent.fragment.HomeFragment;
import com.hr.ent.model.ContractBean;
import com.hr.ent.model.ContractInfoBean;
import com.hr.ent.model.ContractLimitBean;
import com.hr.ent.model.ContractServeInfoBean;
import com.hr.ent.model.ResumeBoxInfoBean;
import com.hr.ent.model.ResumeTypeAllBean;
import com.hr.ent.task.GetBaseInfoTask;
import com.hr.ent.task.GetContractInfoTask;
import com.hr.ent.task.GetContractStateTask;
import com.hr.ent.task.GetContractTask;
import com.hr.ent.task.GetNewResumeNumTask;
import com.hr.ent.task.ResumeBoxFilterTask;
import com.hr.ent.task.Task;
import com.hr.ent.ui.PostJobActivity;
import com.hr.ent.ui.ServerInfoActivity;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.Parser;

import java.util.List;
import java.util.Map;

/**
 * 主界面逻辑控制类
 *
 * @author 800hr：yelong
 *         <p/>
 *         2015-7-3
 */
public class HomeHandler extends Handler {
    public static final int wGetContractStart = 1;// 开始获取当前合同状态
    public static final int wGetContractSuccess = 2;// 获取成功
    public static final int wGetContractFailed = 3;// 获取失败

    public static final int wGetServiceStart = 4;// 开始获取服务限量
    public static final int wGetServiceSuccess = 5;// 获取服务限量成功
    public static final int wGetServiceFailed = 6;// 获取服务限量失败

    public static final int wGetServiceInfoStart = 20;// 开始获取服务限量
    public static final int wGetServiceInfoSuccess = 21;// 获取服务限量成功
    public static final int wGetServiceInfoFailed = 22;// 获取服务限量失败

    public static final int wGetServiceStart2 = 16;// 开始获取服务限量
    public static final int wGetServiceSuccess2 = 17;// 获取服务限量成功
    public static final int wGetServiceFailed2 = 18;// 获取服务限量失败

    public static final int wGetServiceStart3 = 23;// 开始获取服务限量
    public static final int wGetServiceSuccess3 = 24;// 获取服务限量成功
    public static final int wGetServiceFailed3 = 25;// 获取服务限量失败

    public static final int wGetResumeNumStart = 7;// 获取简历总数开始
    public static final int wGetResumeNumSuccess = 8;// 获取简历总数成功
    public static final int wGetResumeNumFailed = 9;// 获取简历总数失败

    public static final int wGetResumeFilterStart = 10;// 开始获取简历数
    public static final int wGetResumeFilterSuccess = 11;// 获取简历数成功
    public static final int wGetResumeFilterFailed = 12;// 获取简历数失败

    public static final int wGetBaseInfoStart = 13;// 开始获取基本信息
    public static final int wGetBaseInfoSuccess = 14;// 获取基本信息成功
    public static final int wGetBaseInfoFailed = 15;// 获取基本信息失败

    private Context context;
    private App app;

    public HomeHandler(Context context) {
        this.context = context;
        app = (App) context.getApplicationContext();
    }

    @SuppressWarnings("unchecked")
    public void dispatchMessage(android.os.Message msg) {
        switch (msg.what) {
            case wGetContractStart:
                wGetContractStart((Map<String, Object>) msg.obj);
                break;
            case wGetContractSuccess:
                wGetContractSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetContractFailed:
                wGetContractFailed((Map<String, Object>) msg.obj);
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
            case wGetResumeNumStart:
                wGetResumeNumStart((Map<String, Object>) msg.obj);
                break;
            case wGetResumeNumSuccess:
                wGetResumeNumSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetResumeNumFailed:
                wGetResumeNumFailed((Map<String, Object>) msg.obj);
                break;
            case wGetResumeFilterStart:
                wGetResumeFilterStart((Map<String, Object>) msg.obj);
                break;
            case wGetResumeFilterSuccess:
                wGetResumeFilterSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetResumeFilterFailed:
                wGetResumeFilterFailed((Map<String, Object>) msg.obj);
                break;
            case wGetBaseInfoStart:
                wGetBaseInfoStart((Map<String, Object>) msg.obj);
                break;
            case wGetBaseInfoSuccess:
                wGetBaseInfoSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetBaseInfoFailed:
                wGetBaseInfoFailed((Map<String, Object>) msg.obj);
                break;
            case wGetServiceStart2:
                wGetServiceStart2((Map<String, Object>) msg.obj);
                break;
            case wGetServiceSuccess2:
                wGetServiceSuccess2((Map<String, Object>) msg.obj);
                break;
            case wGetServiceFailed2:
                wGetServiceFailed2((Map<String, Object>) msg.obj);
                break;
            case wGetServiceStart3:
                wGetServiceStart3((Map<String, Object>) msg.obj);
                break;
            case wGetServiceSuccess3:
                wGetServiceSuccess3((Map<String, Object>) msg.obj);
                break;
            case wGetServiceFailed3:
                wGetServiceFailed3((Map<String, Object>) msg.obj);
                break;
            case wGetServiceInfoStart:
                wGetServiceInfoStart((Map<String, Object>) msg.obj);
                break;
            case wGetServiceInfoSuccess:
                wGetServiceInfoSuccess((Map<String, Object>) msg.obj);
                break;
            case wGetServiceInfoFailed:
                wGetServiceInfoFailed();
                break;
        }
    }

    private void wGetServiceInfoStart(Map<String,Object> map) {
        Task task = new GetContractInfoTask(context, map);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetServiceInfoSuccess, wGetServiceFailed);
        executant.execute();
    }

    private void wGetServiceInfoSuccess(Map<String,Object> map) {
        if (map.containsKey(Const.ERROR_CODE) && map.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
           List<ContractServeInfoBean> infoBean = (List<ContractServeInfoBean>) map.get("result");
           // Log.i("合同结束时间",infoBean.get(0).getCbegin_time()+"");
            ((PostJobActivity) context).setEndTime(infoBean.get(0).getCend_time());
        } else {
            Toast.makeText(context, Parser.parseErrorCode(map.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }

    private void wGetServiceInfoFailed() {
        Toast.makeText(context, "获取失败", Toast.LENGTH_SHORT).show();
    }

    private void wGetContractFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "获取失败", Toast.LENGTH_SHORT).show();
    }

    private void wGetContractSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE)
                && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            if (!obj.get("result").toString().equals("")) {
                ContractBean contractBean = (ContractBean) obj.get("result");
//                app.setContractBean(contractBean);
                ((ServerInfoActivity) context).updateContractInfo(contractBean);
            }
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }

    private void wGetContractStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new GetContractTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetContractSuccess, wGetContractFailed);
        executant.execute();
    }


    private void wGetServiceFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "获取合同信息失败", Toast.LENGTH_SHORT).show();
    }

    private void wGetServiceSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            ContractLimitBean contractLimitBean = (ContractLimitBean) obj.get("result");
            ((ServerInfoActivity) context).updateServiceLimit(contractLimitBean.getBase_contract());
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }

    private void wGetServiceStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new GetContractStateTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetServiceSuccess, wGetServiceFailed);
        executant.execute();
    }

    private void wGetServiceFailed2(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "获取合同信息失败", Toast.LENGTH_SHORT).show();
    }

    private void wGetServiceSuccess2(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            ContractLimitBean contractLimitBean = (ContractLimitBean) obj.get("result");
            HomeFragment.homeFragment.updateServiceLimit(contractLimitBean.getBase_contract());
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }

    private void wGetServiceStart2(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new GetContractStateTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetServiceSuccess2, wGetServiceFailed2);
        executant.execute();
    }
    private void wGetServiceFailed3(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "获取合同信息失败", Toast.LENGTH_SHORT).show();
    }

    private void wGetServiceSuccess3(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
            ContractLimitBean contractLimitBean = (ContractLimitBean) obj.get("result");
            ((PostJobActivity)context).setLimitNum(Integer.parseInt(contractLimitBean.getBase_contract().getJob_open_limit())-Integer.parseInt(contractLimitBean.getBase_contract().getJob_open_limit_used()));
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
        }
    }

    private void wGetServiceStart3(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new GetContractStateTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetServiceSuccess3, wGetServiceFailed3);
        executant.execute();
    }
    private void wGetResumeNumFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "获取简历总数失败", Toast.LENGTH_SHORT).show();
    }

    private void wGetResumeNumSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE)
                && obj.get(Const.ERROR_CODE).toString()
                .equals(Const.ApiSuccess + "")) {
            // NewResumeNum resumeNum = (NewResumeNum) obj.get("result");
            // HomeFragment.homeFragment.updateNewResumeNum(resumeNum);
        } else {
            Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void wGetResumeNumStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new GetNewResumeNumTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetResumeNumSuccess, wGetResumeNumFailed);
        executant.execute();
    }

    /**
     * 获取简历数失败
     *
     * @param obj
     */
    private void wGetResumeFilterFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "获取简历数失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取简历数成功
     */
    private void wGetResumeFilterSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE)
                && obj.get(Const.ERROR_CODE).toString()
                .equals(Const.ApiSuccess + "")) {
            ResumeTypeAllBean resumeTypeAllBean = (ResumeTypeAllBean) obj
                    .get("result");
            String day_count = resumeTypeAllBean.getReturn_data()
                    .getDay_count();
            List<ResumeBoxInfoBean> boxInfoBeans = resumeTypeAllBean
                    .getReturn_data().getBox();
            String unread = "0";
            for (ResumeBoxInfoBean resumeBoxInfoBean : boxInfoBeans) {
                if (resumeBoxInfoBean.getBox_id().equals("0")) {
                    unread = resumeBoxInfoBean.getUnread();
                }
            }
            HomeFragment.homeFragment.updateNewResumeNum(day_count, unread);
        } else {
            Toast.makeText(
                    context,
                    Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取简历数
     *
     * @param obj
     */
    private void wGetResumeFilterStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new ResumeBoxFilterTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task,
                R.string.loading, this, wGetResumeFilterSuccess,
                wGetResumeFilterFailed);
        executant.execute();
    }

    /**
     * 获取基本信息失败
     *
     * @param obj
     */
    private void wGetBaseInfoFailed(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "获取用户名失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取基本信息成功
     *
     * @param obj
     */
    private void wGetBaseInfoSuccess(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        if (obj.containsKey(Const.ERROR_CODE)
                && obj.get(Const.ERROR_CODE).toString()
                .equals(Const.ApiSuccess + "")) {
            String name = (String) obj.get("result");
            HomeFragment.homeFragment.updateName(name);
        } else {
            Toast.makeText(
                    context,
                    Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 开始获取基本信息
     *
     * @param obj
     */
    private void wGetBaseInfoStart(Map<String, Object> obj) {
        // TODO Auto-generated method stub
        Task task = new GetBaseInfoTask(context, obj);
        AsyncExecutant executant = new AsyncExecutant(context, task,
                R.string.loading, this, wGetBaseInfoSuccess, wGetBaseInfoFailed);
        executant.execute();
    }
}

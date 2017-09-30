package com.hr.ent.handler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.ResumeAdapter;
import com.hr.ent.async.AsyncExecutant;
import com.hr.ent.model.JobResumeCountListBean;
import com.hr.ent.model.ResumeBoxInfoBean;
import com.hr.ent.model.ResumeDownloadBean;
import com.hr.ent.model.ResumeFilterInfoBean;
import com.hr.ent.model.ResumeNum;
import com.hr.ent.model.ResumeNumComparator;
import com.hr.ent.model.ResumeTypeAllBean;
import com.hr.ent.task.GetJobResumeCountTask;
import com.hr.ent.task.ResumeBoxFilterTask;
import com.hr.ent.task.Task;
import com.hr.ent.ui.MainActivity;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.Parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ResumeHandler extends Handler {
	public static final int wGetResumeFilterStart = 1;// 开始获取简历数
	public static final int wGetResumeFilterSuccess = 2;// 获取简历数成功
	public static final int wGetResumeFilterFailed = 3;// 获取简历数失败

	public static final int wGetResumeByJobStart = 4;// 获取职位列表
	public static final int wGetResumeByJobSuccess = 5;// 获取职位列表成功
	public static final int wGetResumeByJobFailed = 6;// 获取职位列表失败

	private Context context;
	private ResumeAdapter adapter;

	public ResumeHandler(Context context, ResumeAdapter adapter) {
		this.context = context;
		this.adapter = adapter;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void dispatchMessage(Message msg) {
		// TODO Auto-generated method stub
		super.dispatchMessage(msg);
		switch (msg.what) {
		case wGetResumeFilterStart:
			wGetResumeFilterStart((Map<String, Object>) msg.obj);
			break;
		case wGetResumeFilterSuccess:
			wGetResumeFilterSuccess((Map<String, Object>) msg.obj);
			break;
		case wGetResumeFilterFailed:
			wGetResumeFilterFailed((Map<String, Object>) msg.obj);
			break;

		case wGetResumeByJobStart:
			wGetResumeByJobStart((Map<String, Object>) msg.obj);
			break;
		case wGetResumeByJobSuccess:
			wGetResumeByJobSuccess((Map<String, Object>) msg.obj);
			break;
		case wGetResumeByJobFailed:
			wGetResumeByJobFailed((Map<String, Object>) msg.obj);
			break;
		}
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
	 *
	 * @param obj
	 */
	@SuppressWarnings("unchecked")
	private void wGetResumeFilterSuccess(Map<String, Object> obj) {
		// TODO Auto-generated method stub
		if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
			ResumeTypeAllBean resumeTypeAllBean = (ResumeTypeAllBean) obj.get("result");
			List<ResumeNum> nums = new ArrayList<ResumeNum>();
			List<ResumeBoxInfoBean> boxInfoBeans = resumeTypeAllBean.getReturn_data().getBox();
			List<ResumeFilterInfoBean> filterInfoBeans = resumeTypeAllBean.getReturn_data().getFilter();
			ResumeDownloadBean resumeDownloadBean = resumeTypeAllBean.getReturn_data().getDownload();
			nums.add(resumeDownloadBean);
			nums.addAll(boxInfoBeans);
			nums.addAll(filterInfoBeans);

			List<ResumeNum> lastList = new ArrayList<ResumeNum>();

			for (int i = 0; i < nums.size(); i++) {
				ResumeNum num = nums.get(i);
				if (num.getTypeName() != null && !num.getTypeName().equals("")
						&& num.getTypeNum() != null) {
					if (num.getTypeName().equals("已下载简历")) {
						num.setSortID(1);
					} else if (num.getTypeName().contains("简历收藏夹")) {
						num.setSortID(2);
					} else if (num.getTypeName().contains("全部应聘简历")) {
						num.setSortID(3);
					} else if (num.getTypeName().equals("未筛选")) {
						num.setSortID(4);
					} else if (num.getTypeName().equals("已通过筛选")) {
						num.setSortID(5);
					} else if (num.getTypeName().equals("未通过筛选")) {
						num.setSortID(6);
					} else if (num.getTypeName().equals("待定")) {
						num.setSortID(7);
					} else {
						num.setSortID(8 + i);
					}
					lastList.add(num);
				}
			}

			ResumeNumComparator comparator = new ResumeNumComparator();
			Collections.sort(lastList, comparator);
			adapter.setNums(lastList);
			adapter.notifyDataSetChanged();

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
		AsyncExecutant executant = new AsyncExecutant(context, task, R.string.loading, this, wGetResumeFilterSuccess, wGetResumeFilterFailed);
		executant.execute();
	}

	/**
	 * 获取职位列表失败
	 *
	 * @param obj
	 */
	private void wGetResumeByJobFailed(Map<String, Object> obj) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "获取职位列表失败", Toast.LENGTH_SHORT).show();
	}

	/**
	 * 获取职位列表成功
	 *
	 * @param obj
	 */
	private void wGetResumeByJobSuccess(Map<String, Object> obj) {
		// TODO Auto-generated method stub
		if (obj.containsKey(Const.ERROR_CODE) && obj.get(Const.ERROR_CODE).toString().equals(Const.ApiSuccess + "")) {
			JobResumeCountListBean listBean = (JobResumeCountListBean) obj.get("result");
			if (listBean.getReturn_data().size() > 0) {
				((MainActivity) context).findViewById(R.id.byjob_tishi).setVisibility(View.GONE);
				((MainActivity) context).findViewById(R.id.byjob_listview).setVisibility(View.VISIBLE);
				List<ResumeNum> resumeNums = new ArrayList<ResumeNum>();
				resumeNums.addAll(listBean.getReturn_data());
				adapter.setNums(resumeNums);
			} else {
				((MainActivity) context).findViewById(R.id.byjob_tishi).setVisibility(View.VISIBLE);
				((MainActivity) context).findViewById(R.id.byjob_listview)
				.setVisibility(View.GONE);
			}
			adapter.notifyDataSetChanged();
		} else {
			Toast.makeText(context, Parser.parseErrorCode(obj.get(Const.ERROR_CODE).toString()), Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 获取职位列表开始
	 * 
	 * @param obj
	 */
	private void wGetResumeByJobStart(Map<String, Object> obj) {
		// TODO Auto-generated method stub
		Task task = new GetJobResumeCountTask(context, obj);
		AsyncExecutant executant = new AsyncExecutant(context, task,
				R.string.loading, this, wGetResumeByJobSuccess,
				wGetResumeByJobFailed);
		executant.execute();
	}

}

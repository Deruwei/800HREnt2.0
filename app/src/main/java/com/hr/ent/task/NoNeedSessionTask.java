package com.hr.ent.task;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.hr.ent.app.App;
import com.hr.ent.utils.Const;

/**
 * 所有无需会话sessionkey接口的网络访问任务从此类继承
 * 
 * @author 800hr：zhuhui
 * 
 *         2014-3-26
 */
public abstract class NoNeedSessionTask implements Task {
	protected Context context;
	protected Map<String, Object> params;

	public NoNeedSessionTask(Context context, Map<String, Object> params) {
		super();
		this.context = context;
		this.params = params;
	}

	@Override
	public Map<String, Object> execute() throws Exception {
		App app = (App) (context.getApplicationContext());
		// 如果网络不能连接，立即返回
		if (!app.getNetworkMng().isCanConnect()) {
			Map<String, Object> r = new HashMap<String, Object>();
			r.put("ret", Const.ApiNoNet);
			return r;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("session_key", "");
		if (params != null) {
			map.putAll(params);
		}

		return sendData(map);
	}

	@Override
	public Object getParam() {
		return params;
	}

	/**
	 * 提交服务时,要将此params参数提交给HttpUtils的方法,可以删除或者添加参数.
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	protected abstract Map<String, Object> sendData(Map<String, Object> params)
			throws Exception;
}

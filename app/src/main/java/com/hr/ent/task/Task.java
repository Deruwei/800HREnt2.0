package com.hr.ent.task;

import java.util.Map;

/**
 * 独立任务请求操作接口
 * @author 800hr:zhuhui
 * 
 * 2014-3-27
 */
public interface Task
{
	//执行动作,并且返回结果
	public Map<String, Object> execute() throws Exception;
	//得到调用参数
	public Object getParam();
}

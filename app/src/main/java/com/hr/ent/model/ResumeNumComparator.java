package com.hr.ent.model;

import java.util.Comparator;

/**
 * 按简历类型查询排序规则类
 * @author 800hr：zhuhui
 *
 * 2014-12-12
 */
public class ResumeNumComparator implements Comparator {

	@Override
	public int compare(Object lhs, Object rhs) {
		// TODO Auto-generated method stub
		ResumeNum num = (ResumeNum)lhs;
		ResumeNum num2 = (ResumeNum)rhs;
		
		if(num.getSortID() > num2.getSortID()){
			return 1;
		}else if(num.getSortID() < num2.getSortID()){
			return -1;
		}
		return 1;
	}

}

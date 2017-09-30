package com.hr.ent.model;

/**
 * 简历
 * @author 800hr：zhuhui
 *
 * 2014-11-28
 */
public abstract class ResumeNum {
	
	private int sortID;
	
	public int getSortID() {
		return sortID;
	}
	public void setSortID(int sortID) {
		this.sortID = sortID;
	}
	public abstract String getTypeNum();
	public abstract String getTypeName();
	public abstract String getType();
	public abstract String getTypeID();
	public abstract String getTypeQuYu();
	public abstract String getTypeDate();
	public abstract String getUnread();
}

package com.hr.ent.fragment;
import android.support.v4.app.Fragment;

/**
 * 懒加载父类
 * 
 * @author 800hr：yelong
 * 
 *         2015-7-2
 */
public abstract class LazyFragment extends Fragment {
	protected boolean isVisible;

	/**
	 * 在这里实现Fragment数据的缓加载.
	 * 
	 * @param isVisibleToUser
	 */
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (getUserVisibleHint()) {
			isVisible = true;
			onVisible();
		} else {
			isVisible = false;

				onInvisible();
		}
	}
	protected void onVisible() {
		lazyLoad();
	}
	protected abstract void lazyLoad();
	protected void onInvisible() {
	}
}

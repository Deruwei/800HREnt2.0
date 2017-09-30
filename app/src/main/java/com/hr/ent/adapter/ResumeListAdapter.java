package com.hr.ent.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hr.ent.R;
import com.hr.ent.model.BaseResumeInfoBean;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.DateUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 简历列表适配器
 * 
 * @author 800hr：yelong
 * 
 *         2015-6-26
 */
public class ResumeListAdapter extends BaseAdapter {
	private Context context;
	private int currentPage;
	private int totalPage;
	private List<BaseResumeInfoBean> list = new ArrayList<BaseResumeInfoBean>();
	private DisplayImageOptions options;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private Calendar calendar = Calendar.getInstance();

	private int currentSelectedItem;
	private int totalNums;

	public int getTotalNums() {
		return totalNums;
	}

	public void setTotalNums(int totalNums) {
		this.totalNums = totalNums;
	}

	public int getCurrentSelectedItem() {
		return currentSelectedItem;
	}

	public void setCurrentSelectedItem(int currentSelectedItem) {
		this.currentSelectedItem = currentSelectedItem;
	}

	public List<BaseResumeInfoBean> getList() {
		return list;
	}

	public void setList(List<BaseResumeInfoBean> list) {
		this.list = list;
	}

	public void setItemRead(String id) {
		for (BaseResumeInfoBean bean : list) {
			if (id.equals(bean.getResumeDeleteID())) {
				bean.setIsnew("0");
			}
		}

		notifyDataSetChanged();
	}

	public void add(List<BaseResumeInfoBean> list) {
		this.list.addAll(list);
	}

	public void clear() {
		list.clear();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public ResumeListAdapter(Context context) {
		this.context = context;
		options = new DisplayImageOptions.Builder().cacheInMemory(false)
				.cacheOnDisk(true).displayer(new FadeInBitmapDisplayer(200))
				.build();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public BaseResumeInfoBean getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder vh = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.resumelist_item, parent, false);
			vh = new ViewHolder();
			vh.resume_userphoto = (ImageView) convertView
					.findViewById(R.id.resume_userphoto);

			vh.resume_degree = (TextView) convertView.findViewById(R.id.resume_degree);
//			vh.resume_job = (TextView) convertView
//					.findViewById(R.id.resume_job);
			vh.resume_manager = (TextView) convertView.findViewById(R.id.resume_manager);
			vh.resume_sex = (TextView) convertView.findViewById(R.id.resume_sex);
			vh.resume_update = (TextView) convertView.findViewById(R.id.resume_update);
			vh.resume_username = (TextView) convertView.findViewById(R.id.resume_username);
			vh.resume_workyears = (TextView) convertView.findViewById(R.id.resume_workyears);
			vh.resume_yearsold = (TextView) convertView.findViewById(R.id.resume_yearsold);
			//
			// vh.resume_address = (TextView) convertView
			// .findViewById(R.id.resume_addres);
			//
//			vh.resumelist_item_layout = (RelativeLayout) convertView.findViewById(R.id.resumelist_item_layout);
//			vh.resumelist_item_check = (ImageView) convertView
//					.findViewById(R.id.resumelist_item_check);
			vh.resume_high = (TextView) convertView.findViewById(R.id.resume_high);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		BaseResumeInfoBean infoBean = getItem(position);

		vh.resume_username.setText(infoBean.getUserName());
		vh.resume_update.setText(DateUtils.transforMillToDate(Long
				.parseLong(infoBean.getResumeDate()) * 1000));
		vh.resume_sex.setText(infoBean.getSex().equals("1") ? "男" : "女");
		if (!infoBean.getYear().equals("") && infoBean.getYear() != null) {
			vh.resume_yearsold.setText((calendar.get(Calendar.YEAR) - Integer
					.parseInt(infoBean.getYear())) + "岁");
		}
		// vh.resume_address.setText(infoBean.getLocation());
		if (!infoBean.getWorkBeginYear().equals("")
				&& infoBean.getWorkBeginYear() != null) {
			if (infoBean.getWorkBeginYear().equals("-1")) {
				vh.resume_workyears.setText("无经验");
			} else if (infoBean.getWorkBeginYear().equals("0")) {
				vh.resume_workyears.setText("1年以下");
			} else if (Integer.parseInt(infoBean.getWorkBeginYear()) > calendar.get(Calendar.YEAR)) {
				vh.resume_workyears.setText("无经验");
			} else {
				vh.resume_workyears.setText(infoBean.getWorkBeginYear() + "年");
			}
		}
//		vh.resume_job.setText(infoBean.getJobName());
		vh.resume_manager.setText(infoBean.getMoreMojor().replaceAll("&nbsp", "").replaceAll(";", ""));
		vh.resume_degree.setText(infoBean.getHighEdu());

//		if (list.get(currentSelectedItem).getResumeDeleteID().equals(infoBean.getResumeDeleteID())) {
////			vh.resumelist_item_layout.setBackgroundResource(R.color.yellow_resumelistitem);
//		} else {
////			vh.resumelist_item_layout.setBackgroundResource(R.color.alpha);
//		}

//		if (infoBean.getIsNew() != null && infoBean.getIsNew().equals("0")) {
//			vh.resumelist_item_check.setVisibility(View.INVISIBLE);
//		} else {
//			vh.resumelist_item_check.setVisibility(View.VISIBLE);
//		}

		if (infoBean.getPicPhoto() != null
				&& !infoBean.getPicPhoto().equals("")) {
			if (vh.resume_userphoto.getTag() == null
					|| !infoBean.getPicPhoto().equals(
							vh.resume_userphoto.getTag().toString())) {
				imageLoader.displayImage(
						Const.IMAGE_ROOTPATH + infoBean.getPicPhoto(),
						vh.resume_userphoto, options, animateFirstListener);
				vh.resume_userphoto.setTag(infoBean.getPicPhoto());
			}
		} else {
			if (infoBean.getSex().equals("1")) {
				vh.resume_userphoto.setImageResource(R.drawable.photo_man);
				vh.resume_userphoto.setTag("man");
			} else {
				vh.resume_userphoto.setImageResource(R.drawable.photo_women);
				vh.resume_userphoto.setTag("women");
			}
		}

		if (infoBean.getResumeID().equals("6")) {
			vh.resume_high.setVisibility(View.VISIBLE);
			vh.resume_high.setText("高端");
		} else {
			vh.resume_high.setVisibility(View.INVISIBLE);
		}

		return convertView;
	}

	static class ViewHolder {
//		RelativeLayout resumelist_item_layout;
		ImageView resume_userphoto, resumelist_item_check;
		TextView resume_username, resume_update, resume_sex, resume_yearsold,
				resume_workyears, resume_manager, resume_degree,
				resume_high;
//		, resume_job
	}

	private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

}

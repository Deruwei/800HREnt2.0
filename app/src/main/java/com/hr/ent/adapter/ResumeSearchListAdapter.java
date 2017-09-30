package com.hr.ent.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hr.ent.R;
import com.hr.ent.model.ResumeSearchListBean;
import com.hr.ent.ui.ResumeParticularInviteActivity;
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
 *         <p/>
 *         2015-6-26
 */
public class ResumeSearchListAdapter extends BaseAdapter {
    private Context context;
    private int currentPage;
    private int totalPage;
    private List<ResumeSearchListBean> list;
    private DisplayImageOptions options;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private Calendar calendar = Calendar.getInstance();

//	private int currentSelectedItem;
//	private int totalNums;

//	public int getTotalNums() {
//		return totalNums;
//	}
//
//	public void setTotalNums(int totalNums) {
//		this.totalNums = totalNums;
//	}
//
//	public int getCurrentSelectedItem() {
//		return currentSelectedItem;
//	}

//	public void setCurrentSelectedItem(int currentSelectedItem) {
//		this.currentSelectedItem = currentSelectedItem;
//	}

    public List<ResumeSearchListBean> getList() {
        return list;
    }

    public void setList(List<ResumeSearchListBean> list) {
        this.list = list;
    }

    public void refresh() {
        notifyDataSetChanged();
        Log.i("=========ResumeSearchListAdapter", "notifyDataSetChanged");
    }

    //	public void setItemRead(String id) {
//		for (ResumeSearchListBean bean : list) {
//			if (id.equals(bean.getResumeDeleteID())) {
//				bean.setIsnew("0");
//			}
//		}
//		notifyDataSetChanged();
//	}
    public void add(List<ResumeSearchListBean> list) {
        this.list.addAll(list);
    }
//
//	public void clear() {
//		list.clear();
//	}

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

    public ResumeSearchListAdapter(Context context) {
        this.context = context;
        list = new ArrayList<ResumeSearchListBean>();
        options = new DisplayImageOptions.Builder().cacheInMemory(false).cacheOnDisk(true).displayer(new FadeInBitmapDisplayer(200)).build();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        Log.i("=========ResumeSearchListAdapter", list.size() + "");
        return list.size();
    }

    @Override
    public ResumeSearchListBean getItem(int position) {
        // TODO Auto-generated method stub
        Log.i("=========ResumeSearchListAdapter", "getItem");
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        Log.i("=========ResumeSearchListAdapter", "position");
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.resumelist_item, parent, false);
            vh = new ViewHolder();
            Log.i("=========ResumeSearchListAdapter", "已加载到适配器");
            vh.resume_userphoto = (ImageView) convertView.findViewById(R.id.resume_userphoto);
            vh.resume_degree = (TextView) convertView.findViewById(R.id.resume_degree);
//			vh.resume_job = (TextView) convertView.findViewById(R.id.resume_job);
            vh.resume_manager = (TextView) convertView.findViewById(R.id.resume_manager);
            vh.resume_sex = (TextView) convertView.findViewById(R.id.resume_sex);
            vh.resume_update = (TextView) convertView.findViewById(R.id.resume_update);
            vh.resume_username = (TextView) convertView.findViewById(R.id.resume_username);
            vh.resume_workyears = (TextView) convertView.findViewById(R.id.resume_workyears);
            vh.resume_yearsold = (TextView) convertView.findViewById(R.id.resume_yearsold);
            vh.resumelist_item_layout = (RelativeLayout) convertView.findViewById(R.id.resumelist_item_layout);
            // vh.resume_address = (TextView) convertView
            // .findViewById(R.id.resume_addres);
//			vh.resumelist_item_layout = (RelativeLayout) convertView.findViewById(R.id.resumelist_item_layout);
//			vh.resumelist_item_check = (ImageView) convertView.findViewById(R.id.resumelist_item_check);
            vh.resume_high = (TextView) convertView.findViewById(R.id.resume_high);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        final ResumeSearchListBean infoBean = getItem(position);

        vh.resume_username.setText(infoBean.getName());
        vh.resume_update.setText(DateUtils.transforMillToDate(Long.parseLong(infoBean.getIssue_date()) * 1000));
        vh.resume_sex.setText(infoBean.getSex().equals("1") ? "男" : "女");
        if (!infoBean.getYear().equals("") && infoBean.getYear() != null) {
            vh.resume_yearsold.setText((calendar.get(Calendar.YEAR) - Integer.parseInt(infoBean.getYear())) + "岁");
        }
        // vh.resume_address.setText(infoBean.getLocation());
        if (!infoBean.getWork_beginyear().equals("")
                && infoBean.getWork_beginyear() != null) {
            if (infoBean.getWork_beginyear().equals("-1")) {
                vh.resume_workyears.setText("无经验");
            } else if (infoBean.getWork_beginyear().equals("0")) {
                vh.resume_workyears.setText("1年以下");
            } else if (Integer.parseInt(infoBean.getWork_beginyear()) > calendar.get(Calendar.YEAR)) {
                vh.resume_workyears.setText("无经验");
            } else {
                if ((calendar.get(Calendar.YEAR) - Integer.parseInt(infoBean.getWork_beginyear())) == 0) {
                    vh.resume_workyears.setText("1年以下");
                } else {
                    vh.resume_workyears.setText((calendar.get(Calendar.YEAR) - Integer.parseInt(infoBean.getWork_beginyear())) + "年");
                }
            }
        }

//		vh.resume_job.setText(infoBean.getJobName());
        vh.resume_manager.setText(infoBean.getMoremajor().replaceAll("&nbsp", "").replaceAll(";", ""));
        vh.resume_degree.setText(infoBean.getHigh_education());
        if (infoBean.getPic_filekey() != null
                && !infoBean.getPic_filekey().equals("")) {
            if (vh.resume_userphoto.getTag() == null || !infoBean.getPic_filekey().equals(vh.resume_userphoto.getTag().toString())) {
                imageLoader.displayImage(Const.IMAGE_ROOTPATH + infoBean.getPic_filekey(), vh.resume_userphoto, options, animateFirstListener);
                vh.resume_userphoto.setTag(infoBean.getPic_filekey());
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
        if (infoBean.getResume_id().equals("6")) {
            vh.resume_high.setVisibility(View.VISIBLE);
            vh.resume_high.setText("高端");
        } else {
            vh.resume_high.setVisibility(View.INVISIBLE);
        }
        vh.resumelist_item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ResumeParticularInviteActivity.class);
                intent.putExtra("resume_id", infoBean.getResume_id());
                intent.putExtra("userid", infoBean.getUser_id());
//                intent.putExtra("resume_fdID", inviteInfoBean.getResume_id());
                intent.putExtra("resume_language", infoBean.getResume_language());
                intent.putExtra("userName", infoBean.getName());
                intent.putExtra("apply_state", infoBean.getIntention_info().getApply_state());
//                intent.putExtra("intention_state", infoBean.getIntention_info().getIntention_state());
                intent.putExtra("id", infoBean.getResume_number());
                intent.putExtra("type", "2");
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        ImageView resume_userphoto;
        TextView resume_username, resume_update, resume_sex, resume_yearsold,
                resume_workyears, resume_manager, resume_degree,
                resume_high;
        RelativeLayout resumelist_item_layout;
    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
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

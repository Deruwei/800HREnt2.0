package com.hr.ent.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.handler.ResumeHandler;
import com.hr.ent.handler.ResumeOperationHandler;
import com.hr.ent.handler.ResumeOperationResumeHandler;
import com.hr.ent.model.ResumeNum;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.view.ChoiceSingleView;
import com.hr.ent.view.WindowUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 简历移库
 */
public class ResumeOperationActivity extends BaseActivity {
    @Bind(R.id.tv_operation_back)
    ImageView tvOperationBack;
    @Bind(R.id.tv_operation_do)
    TextView tvOperationDo;
    @Bind(R.id.tv_operation2)
    TextView tvOperation2;
    @Bind(R.id.lv_operation)
    ListView lvOperation;
    @Bind(R.id.lv_operation2)
    ListView lvOperation2;
    private ResumeOperationResumeHandler handler;
    private ResumeOperationHandler handlerOperation;
    public static ResumeOperationActivity resumeOperationActivity = null;
    private int positionList;
    private int positionList2;
    private List<ResumeNum> lastList2;
    private List<ResumeNum> lastList3;
    private String user_id, resume_id, resume_language, ids, boxtype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_resume_operation);
        ButterKnife.bind(this);
        resumeOperationActivity = ResumeOperationActivity.this;
        handlerOperation = new ResumeOperationHandler(this);
        handler = new ResumeOperationResumeHandler(this);
        initData();
    }

    public void initData() {
        user_id = getIntent().getStringExtra("user_id");
        resume_id = getIntent().getStringExtra("resume_id");
        resume_language = getIntent().getStringExtra("resume_language");
        ids = getIntent().getStringExtra("ids");
        boxtype = getIntent().getStringExtra("boxtype");
        // TODO Auto-generated method stub
        if (app.getNetworkMng().isCanConnect()) {
            Map<String, Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put(HttpHelper.METHOD, HttpHelper.BOXFILTER);
            MsgHandler.sendMessage(paramsMap, handler, ResumeHandler.wGetResumeFilterStart);
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    public void initLV(List<ResumeNum> list) {
        lastList2 = new ArrayList<>();
        ArrayList<String> listStr = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getTypeID().equals("30")) {
                listStr.add(list.get(i).getTypeName());
                lastList2.add(list.get(i));
            }
        }
        SingleAdapter singleAdapter = new SingleAdapter();
        lvOperation.setAdapter(singleAdapter);
        singleAdapter.notifyDataSetChanged();
    }

    public void initLV2(List<ResumeNum> list) {
        if (boxtype != null && !boxtype.equals("1")) {
            lastList3 = new ArrayList<>();
            ArrayList<String> listStr = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).getTypeID().equals("30")) {
                    listStr.add(list.get(i).getTypeName());
                    lastList3.add(list.get(i));
                }
            }
            SingleAdapter2 singleAdapter2 = new SingleAdapter2();
            lvOperation2.setAdapter(singleAdapter2);
            singleAdapter2.notifyDataSetChanged();
        } else {
            tvOperation2.setVisibility(View.GONE);
            lvOperation2.setVisibility(View.GONE);
        }
    }

    private void PickNum() {
        if (app.getNetworkMng().isCanConnect()) {
            positionList = lvOperation.getCheckedItemPosition();
            if (!boxtype.equals("1")) {
                positionList2 = lvOperation2.getCheckedItemPosition();
                if (positionList == -1 && positionList2 == -1) {
                    Toast.makeText(this, "请选择设置状态或人才库", Toast.LENGTH_SHORT).show();
                } else {
                    if (positionList == -1) {
                        Map<String, Object> paramsMap2 = new HashMap<String, Object>();
                        paramsMap2.put(HttpHelper.METHOD, HttpHelper.RESUMESETFILTER);
                        paramsMap2.put("ids", ids);
                        paramsMap2.put("filter", (positionList2 + 1) + "");
                        MsgHandler.sendMessage(paramsMap2, handlerOperation, ResumeOperationHandler.wSetResumeFilterStart);
                    } else if (positionList2 == -1) {
                        Map<String, Object> paramsMap = new HashMap<String, Object>();
                        paramsMap.put(HttpHelper.METHOD, HttpHelper.RESUMESETBOX);
                        paramsMap.put("ids", ids);
                        paramsMap.put("type", boxtype);
                        paramsMap.put("boxid", lastList2.get(positionList).getTypeID() + "");
                        MsgHandler.sendMessage(paramsMap, handlerOperation, ResumeOperationHandler.wSetResumeOperation);
                    } else {
                        Map<String, Object> paramsMap2 = new HashMap<String, Object>();
                        paramsMap2.put(HttpHelper.METHOD, HttpHelper.RESUMESETFILTER);
                        paramsMap2.put("ids", ids);
                        paramsMap2.put("filter", (positionList2 + 1) + "");
                        MsgHandler.sendMessage(paramsMap2, handlerOperation, ResumeOperationHandler.wSetResumeFilterStart);
                        Map<String, Object> paramsMap = new HashMap<String, Object>();
                        paramsMap.put(HttpHelper.METHOD, HttpHelper.RESUMESETBOX);
                        paramsMap.put("ids", ids);
                        paramsMap.put("type", boxtype);
                        paramsMap.put("boxid", lastList2.get(positionList).getTypeID() + "");
                        MsgHandler.sendMessage(paramsMap, handlerOperation, ResumeOperationHandler.wSetResumeOperation);
                    }
                }
            } else {
                if (positionList == -1) {
                    Toast.makeText(this, "请选择人才库", Toast.LENGTH_SHORT).show();
                } else {
                    Map<String, Object> paramsMap = new HashMap<String, Object>();
                    paramsMap.put(HttpHelper.METHOD, HttpHelper.RESUMESEARCHSETBOX);
                    paramsMap.put("ids", ids);
                    paramsMap.put("type", "1");
                    paramsMap.put("boxid", lastList2.get(positionList).getTypeID() + "");
                    MsgHandler.sendMessage(paramsMap, handlerOperation, ResumeOperationHandler.wSetResumeOperation2);
                }
            }

        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.tv_operation_back, R.id.tv_operation_do})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_operation_back:
                finish();
                break;
            case R.id.tv_operation_do:
                PickNum();
                break;
        }
    }

    private class SingleAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return lastList2.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ChoiceSingleView singleView = new ChoiceSingleView(ResumeOperationActivity.this);
            singleView.setTitle(lastList2.get(position).getTypeName());
            return singleView;
        }
    }

    private class SingleAdapter2 extends BaseAdapter {

        @Override
        public int getCount() {
            return lastList3.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            final ChoiceSingleView singleView = new ChoiceSingleView(ResumeOperationActivity.this);
            singleView.setTitle(lastList3.get(position).getTypeName());
            return singleView;

        }
    }
}
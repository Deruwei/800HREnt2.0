package com.hr.ent.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.app.App;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.SharedPreferencedUtils;
import com.hr.ent.view.WindowUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 职能选择（可多选）
 */
public class SelectFunctionForEditJobActivity extends BaseActivity implements OnClickListener {
    private int index; // 0, 代表一级分类。1, 代表二级分类
    private MyBaseAdapterFindJobFunctionSelect adapter;
    private MyBaseAdapterFindJobFunctionSelect2 adapter2;
    public static HashMap<String, String> selectMap = new HashMap<String, String>();// String：职能id，string:名称
    private TextView funtionselect_selectedinfo;
    private HashMap<String, String> dataItem, dataItem2;
    private ListView functionselect_listview, functionselect_listview2;
    private ArrayList<HashMap<String, String>> data;
    private ArrayList<HashMap<String, String>> dataList2;
    private LinearLayout functionselect_showinforlayout;// 动态布局
    private LinearLayout funtionselect_showmessage;
    private ImageView iv_selectfunction_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_select_function_job_order);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        int flags = WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
        getWindow().addFlags(flags);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        try {
            Bundle bundle = getIntent().getExtras();
            final String filter = bundle.getString("filter");
            final String id = bundle.getString("id");
            final String value = bundle.getString("value");
            Map<String, String> tempMap = (Map<String, String>) bundle.getSerializable("selectMap");
            if (tempMap != null) {
                if (id == null) {
                    this.selectMap.clear();
                }
                if (tempMap.size() > 0) {
                    this.selectMap.putAll(tempMap);
                }
            }
            System.out.println(selectMap.toString());
            iv_selectfunction_back = (ImageView) findViewById(R.id.iv_selectfunction_back);
            funtionselect_showmessage = (LinearLayout) findViewById(R.id.funtionselect_showmessage);//已选职位
            funtionselect_showmessage.setOnClickListener(this);
            iv_selectfunction_back.setOnClickListener(this);
            functionselect_showinforlayout = (LinearLayout) findViewById(R.id.functionselect_showinforlayout);//已选职位
            functionselect_showinforlayout.setVisibility(View.GONE);// 默认收起
            funtionselect_selectedinfo = (TextView) findViewById(R.id.funtionselect_selectedinfo);//已选职位
            funtionselect_selectedinfo.setText(selectMap.size() + "/5");//最多只能选3像

            if (selectMap != null && selectMap.size() > 0) {
                Set<String> keySet = selectMap.keySet();
                for (String string : keySet) {
                    addView(string, selectMap.get(string));
                }
            }

            findViewById(R.id.tv_selectfunction_save).setOnClickListener(
                    (OnClickListener) this);
            final TextView functionselect_title = (TextView) findViewById(R.id.functionselect_title);
            functionselect_title.setText("职能");
            functionselect_listview = (ListView) findViewById(R.id.functionselect_listview);
            functionselect_listview2 = (ListView) findViewById(R.id.functionselect_listview2);


            data = setData(filter, id, value);
            adapter = new MyBaseAdapterFindJobFunctionSelect(this, data, index, selectMap);
            functionselect_listview.setAdapter(adapter);
            functionselect_listview
                    .setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                            // 一级实例
                            SelectFunctionForEditJobActivity functionSelectActivity1 = SelectFunctionForEditJobActivity.this;
                            dataItem = data.get(arg2);
                            String value2 = dataItem.get("value");
                            String id2 = dataItem.get("key");
                            // System.out.println(dataItem.toString());
                            if (arg2 == 0) {
//                                // 所有职能
//                                if (selectMap.containsKey(dataItem
//                                        .get("key"))) {
//                                    selectMap.clear();
//                                    clearView();
//                                } else {
//                                    selectMap.clear();
//                                    clearView();
//                                    selectMap.put(dataItem.get("key"),
//                                            dataItem.get("value"));
//                                    addView(dataItem.get("key"),
//                                            dataItem.get("value"));
//                                }
                            } else {
                                // 非所有职能
                                try {
                                    dataList2 = setData(filter, id2, value2);
                                    adapter2 = new MyBaseAdapterFindJobFunctionSelect2(SelectFunctionForEditJobActivity.this, dataList2, index, selectMap);
                                    functionselect_listview2.setAdapter(adapter2);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            ;
                        }
                    });
            functionselect_listview2.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    SelectFunctionForEditJobActivity functionSelectActivity1 = SelectFunctionForEditJobActivity.this;

                    dataItem2 = dataList2.get(arg2);
                    if (selectMap.containsKey(dataItem2.get("key"))) {
                        selectMap.remove(dataItem2.get("key"));
                        removeView(dataItem2.get("key"),
                                functionSelectActivity1);
                    } else {
                        selectMap.remove("0");// 移除‘所有职能’
                        removeView("0", functionSelectActivity1);
                        if (arg2 == 0) {
                            // 移除该职能下所有子项
                            List<String> tempKeyList = new ArrayList<String>();
                            Set<String> keySet = selectMap.keySet();
                            String tagKeyString = (String) dataItem2
                                    .get("key").subSequence(0, 3);
                            for (Iterator<String> iterator = keySet
                                    .iterator(); iterator.hasNext(); ) {
                                String keyString = (String) iterator
                                        .next();
                                if (keyString
                                        .contains(tagKeyString)) {
                                    tempKeyList.add(keyString);
                                }
                            }
                            for (int i = 0; i < tempKeyList.size(); i++) {
                                selectMap.remove(tempKeyList.get(i));
                                removeView(tempKeyList.get(i),
                                        functionSelectActivity1);
                            }
                            if (checkSelected()) {
                                selectMap.put(dataItem2.get("key"),
                                        dataItem2.get("value"));
                                addView(dataItem2.get("key"),
                                        dataItem2.get("value"));
                            }
                        } else if (checkSelected()) {
                            // 移除二级页面第一项选择状态
                            selectMap
                                    .remove(dataList2.get(0).get("key"));
                            removeView(dataList2.get(0).get("key"),
                                    functionSelectActivity1);
                            // 添加所点击项
                            selectMap.put(dataItem2.get("key"),
                                    dataItem2.get("value"));
                            addView(dataItem2.get("key"),
                                    dataItem2.get("value"));
                        }
                    }
                    // 设置显示已选择数量信息(一级和二级数据统一)
                    funtionselect_selectedinfo.setText(selectMap.size()
                            + "/5");
                    functionSelectActivity1.setSelectCount(selectMap
                            .size());
                    functionSelectActivity1.adapter2
                            .notifyDataSetChanged();
                    adapter2.notifyDataSetChanged();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置已选择的数量
     *
     * @param selectedCount
     */
    public void setSelectCount(int selectedCount) {
        funtionselect_selectedinfo.setText(selectedCount + "/5");
    }

    /**
     * 添加已选职能视图
     *
     * @param keyString
     * @param valueString
     */
    private void addView(final String keyString, String valueString) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.leftMargin = 5;
        params.topMargin = 3;
        params.bottomMargin = 3;
        params.rightMargin = 0;
        final TextView tv = (TextView) LayoutInflater.from(this).inflate(
                R.layout.textview_selected, null, false);
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER);
        tv.setPadding(6, 4, 6, 3);
        if ("0".equalsIgnoreCase(keyString)
                || "000".contains(keyString.subSequence(keyString.length() - 3,
                keyString.length()))) {
            tv.setTextSize(20f);
            // 加粗
            // TextPaint tpaint = tv.getPaint();
            // tpaint.setFakeBoldText(true);
        }
        tv.setText(valueString.trim());
        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMap.remove(keyString);
                funtionselect_selectedinfo.setText(selectMap.size() + "/5");
                adapter.notifyDataSetChanged();
                functionselect_showinforlayout.removeView(tv);
                SelectFunctionForEditJobActivity functionSelectActivity1 = SelectFunctionForEditJobActivity.this;
                if (functionSelectActivity1 != null) {
                    functionSelectActivity1.setSelectCount(selectMap.size());
                }

            }
        });
        // Animation animationadd = AnimationUtils.loadAnimation(this,
        // R.anim.slide_in_left);
        // Interpolator interpolator = new CubicInterpolator(Type.IN);
        // animationadd.setInterpolator(interpolator);
        // tv.setAnimation(animationadd);
        tv.setTag(keyString);
        functionselect_showinforlayout.addView(tv);

    }

    /**
     * 移除指定已选职能视图
     *
     * @param tagString
     * @param functionSelectActivity
     */
    private void removeView(String tagString,
                            SelectFunctionForEditJobActivity functionSelectActivity) {
        functionselect_showinforlayout
                .removeView(functionselect_showinforlayout
                        .findViewWithTag(tagString));
        if (functionSelectActivity != null) {
            functionSelectActivity.functionselect_showinforlayout
                    .removeView(functionselect_showinforlayout
                            .findViewWithTag(tagString));
        }

    }

    /**
     * 清除已选职能视图
     */
    private void clearView() {
        functionselect_showinforlayout.removeAllViews();
    }

    /**
     * 初始化数据
     *
     * @param filter
     * @param id
     * @param value
     * @return
     * @throws Exception
     */
    private ArrayList<HashMap<String, String>> setData(String filter, String id, String value) throws Exception {
        ArrayList<HashMap<String, String>> data = null;
        SharedPreferencedUtils sb = new SharedPreferencedUtils(context);
        if (id == null) { // 一级分类
            Log.i("======jsobJobObject", "======jsobJobObject1");
//                Log.i("======jsobJobObject", "2");
//                SharedPreferencedUtils utils = new SharedPreferencedUtils(context);
//                Log.i("======getStringValue",utils.getStringValue(Const.JOB_DATE, "")+"333");
//                JSONObject jsonObject = new JSONObject(utils.getStringValue(Const.JOB_DATE, ""));
//                Log.i("======jsobJobObject", jsonObject.toString());
//                JSONArray jonArray = jsonObject.getJSONArray(app.getIndustry());
//                data = getJobArray(jonArray, "0");
            Log.i("======jsobJobObject2", App.job_json.toString());
//            HttpUtils.updateArrayFile(context, Const.JOB_ARRAY_URL, Const.JOB_FILENAME);
            JSONObject jsonObject = new JSONObject(sb.getStringValue(Const.JOB_FILENAME, ""));
            JSONArray jonArray = jsonObject.getJSONArray(app.getIndustry() + "");
            data = getJobArray(jonArray, "0");
        } else {
            Log.i("======jsobJobObject", "======jsobJobObject1");
            index = 1;
//                Log.i("======jsobJobObject", "2");
//                SharedPreferencedUtils utils = new SharedPreferencedUtils(context);
//                Log.i("======getStringValue",utils.getStringValue(Const.JOB_DATE, "")+"333");
//                JSONObject jsonObject = new JSONObject(utils.getStringValue(Const.JOB_DATE, ""));
//                Log.i("======jsobJobObject", jsonObject.toString());
//                JSONArray jonArray = jsonObject.getJSONArray(app.getIndustry());
//                data = getJobArray(jonArray, id);
//                InputStream in = getAssets().open("job.json");
//                JSONObject jsonObject = new JSONObject(NetUtils.readAsString(in, Const.ENCODE));
//                JSONArray jonArray = jsonObject.getJSONArray(app.getIndustry() + "");
//                data = getJobArray(jonArray, id);
            Log.i("======jsobJobObject2", App.job_json.toString());
//            HttpUtils.updateArrayFile(context, Const.JOB_ARRAY_URL, Const.JOB_FILENAME);
            JSONObject jsonObject = new JSONObject(sb.getStringValue(Const.JOB_FILENAME, ""));
            JSONArray jonArray = jsonObject.getJSONArray(app.getIndustry() + "");
            data = getJobArray(jonArray, id);
        }

        // 增加”所有职能“选项
        HashMap<String, String> item = new HashMap<String, String>();
        if (id == null) {
            item.put("key", "0");
            item.put("value", "全部职能");
//            item.put("value", "");
            data.add(0, item);
        } else {
            item.put("key", id);
            item.put("value", value);
            data.add(0, item);
        }
        return data;
    }

    /**
     * 获得 job 数组信息
     *
     * @param id
     * @return
     */
    private ArrayList<HashMap<String, String>> getJobArray(
            JSONArray jsonArray, String id) throws Exception {
        String endString = "000";
        int endIndex = 3;

        return getArrayList(jsonArray, id, endString, endIndex);
    }

    private ArrayList<HashMap<String, String>> getArrayList(
            JSONArray jsonArray, String id, String endString, int endIndex)
            throws JSONException {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        int len = jsonArray.length();
        JSONObject jsonObject = null;

        if ("0".equals(id)) { // 一级分类
            for (int i = 0; i < len; i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String key = (String) jsonObject.keys().next();
                if (key.endsWith(endString)) {
                    HashMap<String, String> item = new HashMap<String, String>();
                    item.put("key", key);
                    item.put("value", jsonObject.getString(key));
                    list.add(item);
                }
            }
        } else {
            String preId = id.substring(0, endIndex);
            for (int i = 0; i < len; i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String key = (String) jsonObject.keys().next();
                if (key.startsWith(preId) && !key.endsWith(endString)) {
                    HashMap<String, String> item = new HashMap<String, String>();
                    item.put("key", key);
                    item.put("value", "    " + jsonObject.getString(key));
                    list.add(item);
                }
            }
        }
        return list;
    }

    /**
     * 检测已选择职能数目
     *
     * @return
     */
    private boolean checkSelected() {
        if (selectMap.size() == 5) {
            Toast.makeText(SelectFunctionForEditJobActivity.this, "最多选择5个职位",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_selectfunction_back:
                finish();
                break;
            case R.id.tv_selectfunction_save:
                // 设置搜索页信息
                EditJobActivity.editJobActivity.setFunctionSelected(selectMap);
                selectMap.clear();
                finish();
                break;
            case R.id.funtionselect_showmessage:
                // 语句虽重复，但顺序不可调换
                if (functionselect_showinforlayout.getVisibility() == View.VISIBLE) {
                    functionselect_showinforlayout.setVisibility(View.GONE);
                    functionselect_showinforlayout.removeAllViews();
                    funtionselect_selectedinfo.setCompoundDrawablesWithIntrinsicBounds(null, null,
                            getResources().getDrawable(R.mipmap.xiajiantou),
                            //图标下拉
                            null);
                } else {
                    refreshShowLayout();
                }
                break;
            default:
                break;
        }

    }

    //图标下拉
    private void refreshShowLayout() {
        functionselect_showinforlayout.removeAllViews();
        functionselect_showinforlayout.setVisibility(View.VISIBLE);
        Set<String> keySet = selectMap.keySet();
        for (String string : keySet) {
            addView(string, selectMap.get(string));
        }
        funtionselect_selectedinfo
                .setCompoundDrawablesWithIntrinsicBounds(null, null,
                        getResources().getDrawable(R.mipmap.xiajiantou), null);
    }

    /**
     * 职能适配器
     *
     * @author 800hr:xuebaohua
     */
    class MyBaseAdapterFindJobFunctionSelect extends BaseAdapter {
        Context context;
        ArrayList<HashMap<String, String>> data;
        int index;
        Map<String, String> selectMap;

        public MyBaseAdapterFindJobFunctionSelect(Context context, ArrayList<HashMap<String, String>> data,
                                                  int index, Map<String, String> selectMap) {
            this.context = context;
            this.data = data;
            this.index = index;
            this.selectMap = selectMap;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return data.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.functionselect_listview_item, null);
                viewHolder = new ViewHolder();
                viewHolder.textView = (TextView) convertView
                        .findViewById(R.id.functionselect_listview_item_textview);
                viewHolder.imageview = (ImageView) convertView
                        .findViewById(R.id.imageview1);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(data.get(position).get("value"));
            if (index == 0 && position != 0) {
                viewHolder.imageview.setBackgroundResource(R.mipmap.jiantou_right);
            }
            if (index == 0 && position == 0 || index == 1) {
                if (selectMap.containsKey(data.get(position).get("key"))) {
                    viewHolder.imageview.setBackgroundResource(R.mipmap.duihao);
                } else {
                    viewHolder.imageview.setBackgroundDrawable(null);
                }
            }
            return convertView;
        }

        class ViewHolder {
            TextView textView;
            ImageView imageview;

        }
    }

    /**
     * 职能适配器
     */
    class MyBaseAdapterFindJobFunctionSelect2 extends BaseAdapter {
        Context context;
        ArrayList<HashMap<String, String>> data2;
        int index;
        Map<String, String> selectMap;

        public MyBaseAdapterFindJobFunctionSelect2(Context context, ArrayList<HashMap<String, String>> data,
                                                   int index, Map<String, String> selectMap) {
            this.context = context;
            this.data2 = data;
            this.index = index;
            this.selectMap = selectMap;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return data2.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.functionselect_listview_item, null);
                viewHolder = new ViewHolder();
                viewHolder.textView = (TextView) convertView
                        .findViewById(R.id.functionselect_listview_item_textview);
                viewHolder.imageview = (ImageView) convertView
                        .findViewById(R.id.imageview1);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(data2.get(position).get("value"));
            if (index == 0 && position != 0) {
                viewHolder.imageview.setBackgroundResource(R.mipmap.jiantou_right);
            }
            if (index == 0 && position == 0 || index == 1) {
                if (selectMap.containsKey(data2.get(position).get("key"))) {
                    viewHolder.imageview.setBackgroundResource(R.mipmap.duihao);
                } else {
                    viewHolder.imageview.setBackgroundDrawable(null);
                }
            }
            return convertView;
        }

        class ViewHolder {
            TextView textView;
            ImageView imageview;

        }
    }
}

package com.hr.ent.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.fragment.SearchResumeFragment;
import com.hr.ent.view.WindowUtils;


/**
 * 领域选择页面
 *
 * @author Colin
 */
public class SelectTerritoryActivity extends BaseActivity implements
        OnClickListener {

    private ListView listView;
    private String[] textStrings, idStrings;
    private String lingyuIdString;
    private Boolean[] checkStates;
    private ImageView iv_selectterritory_back;
    private TextView tv_selectterritory_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_resume_order_lingyuselect);
        this.lingyuIdString = getIntent().getStringExtra("lingyuIdString");
        iv_selectterritory_back = (ImageView) findViewById(R.id.iv_selectterritory_back);
        tv_selectterritory_save = (TextView) findViewById(R.id.tv_selectterritory_save);
        listView = (ListView) findViewById(R.id.activity_resumeorder_lingyu_listview);

        iv_selectterritory_back.setOnClickListener(this);
        tv_selectterritory_save.setOnClickListener(this);

        if ("11".equals(app.getIndustry())) {// 建筑
            textStrings = getResources().getStringArray(R.array.lingyu_jianzhu);
            idStrings = getResources().getStringArray(R.array.lingyu_jianzhu_ids);
        } else if ("12".equals(app.getIndustry())) {// 金融
            textStrings = getResources().getStringArray(R.array.lingyu_jinrong);
            idStrings = getResources().getStringArray(R.array.lingyu_jinrong_ids);
        } else if ("14".equals(app.getIndustry())) {// 医药
            textStrings = getResources().getStringArray(R.array.lingyu_yiyao);
            idStrings = getResources().getStringArray(R.array.lingyu_yiyao_ids);
        } else if ("29".equals(app.getIndustry())) {// 化工
            textStrings = getResources().getStringArray(R.array.lingyu_huagong);
            idStrings = getResources().getStringArray(R.array.lingyu_huagong_ids);
        }
        Log.i("=======erritory", textStrings.toString());
        // 初始化
        // 过滤出当前行业下领域
        try {
            checkStates = new Boolean[textStrings.length];
            for (int i = 0; i < checkStates.length; i++) {
                checkStates[i] = false;
                Log.i("=======erritory", checkStates[i] + "");
            }
            String[] itemLingyu = lingyuIdString.split(",");
            for (String string : itemLingyu) {
//                if (string.contains(app.getIndustry())) {
                for (int i = 0; i < idStrings.length; i++) {
                    if (string.contains(idStrings[i])) {
                        checkStates[i] = true;
                        Log.i("=======erritory", checkStates[i] + "");
                    }
//                    }
                }
            }
            lingyuIdString = "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        listView.setAdapter(new LingYuAdapter(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_selectterritory_back:// 菜单
                finish();
                break;
            case R.id.tv_selectterritory_save:// 确定
            /*
             * 数据拼装
			 */
                StringBuffer newLingYuIdStingbuffer = new StringBuffer();
                try {
                    // 过滤出非当前行业下领域
                    String[] itemLingyu = lingyuIdString.split(",");
                    for (String string : itemLingyu) {
//                        if (!string.contains(app.getIndustry() + ":")) {
                        newLingYuIdStingbuffer.append("," + string);// 11:111100
//                        }
                    }
                    // 处理当前行业下已选领域
                    for (int i = 0; i < checkStates.length; i++) {
                        System.out.println(checkStates[i]);
                        if (checkStates[i]) {
                            newLingYuIdStingbuffer.append("," + idStrings[i]);
                        }
                    }
                    while (newLingYuIdStingbuffer.toString().startsWith(",")) {// 去除开头“，”
                        newLingYuIdStingbuffer.deleteCharAt(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                SearchResumeFragment resumeJobintensionActivity = ResumeSearchActivity.searchResumeFragment;
                if (resumeJobintensionActivity != null) {
                    lingyuIdString = newLingYuIdStingbuffer.toString();
                    resumeJobintensionActivity.setLingyu(lingyuIdString);
//                    System.out.println("领域id：" + lingyuIdString);
                    Log.i("===========LingYuID", lingyuIdString);
                }
                finish();
                break;
            default:
                break;
        }

    }

    private class LingYuAdapter extends BaseAdapter {
        private Context context;

        public LingYuAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return textStrings.length;
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
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.activity_resume_order_lingyuselect_item, null);
                viewHolder = new ViewHolder();
                viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.lingyu_checkbox);
                viewHolder.lingyu_text = (TextView) convertView.findViewById(R.id.lingyu_text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            convertView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    ViewHolder vh = (ViewHolder) (v.getTag());
                    if (vh.checkBox.isChecked()) {
                        vh.checkBox.setChecked(false);
                    } else {
                        vh.checkBox.setChecked(true);
                    }
                }
            });

//            convertView.setBackgroundColor((position & 1) == 0 ? 0xffFFFFFF: 0xffF4FAFA);
            viewHolder.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checkStates[position] = isChecked;
                }
            });
            viewHolder.lingyu_text.setText(textStrings[position]);
            viewHolder.checkBox.setChecked(checkStates[position]);
            return convertView;
        }

        class ViewHolder {
            public CheckBox checkBox;
            TextView lingyu_text;
        }

    }
}

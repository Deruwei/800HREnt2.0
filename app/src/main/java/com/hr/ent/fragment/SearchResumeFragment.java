package com.hr.ent.fragment;


import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.SpinnerAdapter;
import com.hr.ent.app.App;
import com.hr.ent.ui.SearchResumeResult;
import com.hr.ent.ui.SelectFunctionForSearchActivity;
import com.hr.ent.ui.SelectPlaceToResume2Activity;
import com.hr.ent.ui.SelectPlaceToResumeActivity;
import com.hr.ent.ui.SelectTerritoryActivity;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.NetUtils;
import com.hr.ent.utils.ResumeInfoIDToString;
import com.hr.ent.view.IdSpineer;
import com.hr.ent.view.PickerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResumeFragment extends Fragment {
    @Bind(R.id.edit_homefragment_search)
    EditText editHomefragmentSearch;
    @Bind(R.id.tv_homefragment_search)
    TextView tvHomefragmentSearch;
    @Bind(R.id.tv_searchresume_workplace)
    TextView tvSearchresumeWorkplace;
    @Bind(R.id.tv_searchresume_salary)
    TextView tvSearchresumeSalary;
    @Bind(R.id.tv_searchresume_nowplace)
    TextView tvSearchresumeNowplace;
    @Bind(R.id.cb_searchresume1_check1)
    CheckBox cbSearchresume1Check1;
    @Bind(R.id.cb_searchresume1_check2)
    CheckBox cbSearchresume1Check2;
    @Bind(R.id.cb_searchresume1_check3)
    CheckBox cbSearchresume1Check3;
    @Bind(R.id.cb_searchresume1_check5)
    CheckBox cbSearchresume1Check5;
    @Bind(R.id.cb_searchresume1_check6)
    CheckBox cbSearchresume1Check6;
    @Bind(R.id.cb_searchresume1_check4)
    CheckBox cbSearchresume1Check4;
    @Bind(R.id.tv_searchresume_jobclassify)
    TextView tvSearchresumeJobclassify;
    @Bind(R.id.tv_searchresume_lingyu)
    TextView tvSearchresumeLingyu;
    @Bind(R.id.rl_searchresume_jobclassify)
    RelativeLayout rlSearchresumeJobclassify;
    @Bind(R.id.rl_searchresume_customage)
    RelativeLayout rlSearchresumeCustomAge;
    @Bind(R.id.rl_searchresume_lingyu)
    RelativeLayout rlSearchresumeLingyu;
    @Bind(R.id.sp_searchresume_jobnature)
    IdSpineer spSearchresumeJobnature;
    @Bind(R.id.sp_searchresume_jobexp)
    IdSpineer spSearchresumeJobexp;
    @Bind(R.id.sp_searchresume_edu)
    IdSpineer spSearchresumeEdu;
    @Bind(R.id.sp_searchresume_age)
    IdSpineer spSearchresumeAge;
    @Bind(R.id.sp_searchresume_post)
    IdSpineer spSearchresumePost;
    @Bind(R.id.sp_searchresume_language)
    IdSpineer spSearchresumeLanguage;
    @Bind(R.id.sp_searchresume_languagegrade)
    IdSpineer spSearchresumeLanguagegrade;
    @Bind(R.id.sp_searchresume_resumeupdate)
    IdSpineer spSearchresumeResumeupdate;
    @Bind(R.id.ed_searchresume_age2)
    EditText edSearchresumeAge2;
    @Bind(R.id.ed_searchresume_age3)
    EditText edSearchresumeAge3;


    @Bind(R.id.cb_searchresume1_sex0)
    CheckBox cbSearchresume1Sex0;
    @Bind(R.id.cb_searchresume1_sex1)
    CheckBox cbSearchresume1Sex1;
    @Bind(R.id.cb_searchresume1_sex2)
    CheckBox cbSearchresume1Sex2;
    private View view;
    private App app;
    private Map<String, String> funcSelectedMap = new HashMap<String, String>();// key：职能id，value：职能名称
    private String lingyuIdString = "";// 领域id
    private PopupWindow popwindowSelectSalary;
    private View viewSelectSalary;
    PickerView minute_pv;
    PickerView second_pv;
    private TextView tv_pop_cancle, tv_pop_confirm;
    private boolean isCheckSalary;
    /**
     * 请求参数
     */
    private String keyword_type;
    private static SearchResumeFragment searchResumeFragment = null;
    private String sexId = "0";//性别
    private String funcIdString = "";// 职能id
    private String placeId = ""; // 地区 ID
    private String placeId2 = ""; // 现居住地 ID
    private String salaryFromId = ""; // salaryFromNum ID
    private String salaryToId = ""; // salaryToNum ID
    private String salaryFromString = ""; // salaryToNum ID
    private String salaryToString = ""; // salaryToNum ID

    public static SearchResumeFragment getInstance() {
        return searchResumeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_resume, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        app = (App) getActivity().getApplication();
        cbSearchresume1Check1.setChecked(true);
        cbSearchresume1Sex0.setChecked(true);
        keyword_type = "1";
        if ("11".equals(app.getIndustry()) || "12".equals(app.getIndustry()) || "14".equals(app.getIndustry()) || "19".equals(app.getIndustry())) {
            rlSearchresumeLingyu.setVisibility(View.VISIBLE);
        } else {
            rlSearchresumeLingyu.setVisibility(View.GONE);
        }
        initSpinnerData();
    }

    private void initSpinnerData() {
        spSearchresumeJobnature.setAdapter(new SpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_jobtype_zh)));
        spSearchresumeJobnature.setIds(getResources().getStringArray(R.array.array_jobtype_ids));
        spSearchresumeJobnature.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                try {
                    if (spSearchresumeJobnature.idStrings != null) {
                        spSearchresumeJobnature.idString = spSearchresumeJobnature.idStrings[arg2];
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        spSearchresumeJobnature.setSelectedItem("14");

        spSearchresumeJobexp.setAdapter(new SpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_workexp)));
        spSearchresumeJobexp.setIds(getResources().getStringArray(R.array.array_workexp_id));
        spSearchresumeJobexp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                try {
                    if (spSearchresumeJobexp.idStrings != null) {
                        spSearchresumeJobexp.idString = spSearchresumeJobexp.idStrings[arg2];
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        spSearchresumeJobexp.setSelectedItem("10");

        spSearchresumeAge.setAdapter(new SpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_agenum)));
        spSearchresumeAge.setIds(getResources().getStringArray(R.array.array_agenum_id));
        spSearchresumeAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                try {
                    if (spSearchresumeAge.idStrings != null) {
                        spSearchresumeAge.idString = spSearchresumeAge.idStrings[arg2];
                        if (spSearchresumeAge.idString.equals("18")) {
                            rlSearchresumeCustomAge.setVisibility(View.VISIBLE);
                        } else {
                            rlSearchresumeCustomAge.setVisibility(View.GONE);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        spSearchresumeAge.setSelectedItem("");

        spSearchresumeEdu.setAdapter(new SpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_degree_zh)));
        spSearchresumeEdu.setIds(getResources().getStringArray(R.array.array_degree_ids));
        spSearchresumeEdu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                try {
                    if (spSearchresumeEdu.idStrings != null) {
                        spSearchresumeEdu.idString = spSearchresumeEdu.idStrings[arg2];
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        spSearchresumeEdu.setSelectedItem("");

        spSearchresumeLanguagegrade.setAdapter(new SpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_language_level_zh)));
        spSearchresumeLanguagegrade.setIds(getResources().getStringArray(R.array.array_language_level_ids));
        spSearchresumeLanguagegrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                try {
                    if (spSearchresumeLanguagegrade.idStrings != null) {
                        spSearchresumeLanguagegrade.idString = spSearchresumeLanguagegrade.idStrings[arg2];
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        spSearchresumeLanguagegrade.setSelectedItem("0");

        spSearchresumeLanguage.setAdapter(new SpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_language_type_zh)));
        spSearchresumeLanguage.setIds(getResources().getStringArray(R.array.array_language_type_ids));
        spSearchresumeLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                try {
                    if (spSearchresumeLanguage.idStrings != null) {
                        spSearchresumeLanguage.idString = spSearchresumeLanguage.idStrings[arg2];
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        spSearchresumeLanguage.setSelectedItem("10");

        spSearchresumeResumeupdate.setAdapter(new SpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_search_resumetime)));
        spSearchresumeResumeupdate.setIds(getResources().getStringArray(R.array.array_search_resumetime_id));
        spSearchresumeResumeupdate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                try {
                    if (spSearchresumeResumeupdate.idStrings != null) {
                        spSearchresumeResumeupdate.idString = spSearchresumeResumeupdate.idStrings[arg2];
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        spSearchresumeResumeupdate.setSelectedItem("");

        spSearchresumePost.setAdapter(new SpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_zhicheng_zh)));
        spSearchresumePost.setIds(getResources().getStringArray(R.array.array_zhicheng_ids));
        spSearchresumePost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                try {
                    if (spSearchresumePost.idStrings != null) {
                        spSearchresumePost.idString = spSearchresumePost.idStrings[arg2];
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        spSearchresumePost.setSelectedItem("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.cb_searchresume1_sex0, R.id.rl_searchresume_salary, R.id.rl_searchresume_nowplace, R.id.rl_searchresume_workplace, R.id.rl_searchresume_lingyu, R.id.cb_searchresume1_sex1, R.id.cb_searchresume1_sex2, R.id.rl_searchresume_jobclassify, R.id.tv_homefragment_search, R.id.cb_searchresume1_check1, R.id.cb_searchresume1_check2, R.id.cb_searchresume1_check3, R.id.cb_searchresume1_check5, R.id.cb_searchresume1_check6, R.id.cb_searchresume1_check4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_homefragment_search:
                if (app.getNetworkMng().isCanConnect()) {
                    goActivity();
                } else {
                    Toast.makeText(getActivity(), R.string.nonet, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.cb_searchresume1_check1:
                initCheck();
                keyword_type = "1";
                cbSearchresume1Check1.setChecked(true);
                break;
            case R.id.cb_searchresume1_check2:
                initCheck();
                keyword_type = "2";
                cbSearchresume1Check2.setChecked(true);
                break;
            case R.id.cb_searchresume1_check3:
                initCheck();
                cbSearchresume1Check3.setChecked(true);
                keyword_type = "3";
                break;
            case R.id.cb_searchresume1_check5:
                initCheck();
                cbSearchresume1Check5.setChecked(true);
                keyword_type = "5";
                break;
            case R.id.cb_searchresume1_check6:
                initCheck();
                cbSearchresume1Check6.setChecked(true);
                keyword_type = "6";
                break;
            case R.id.cb_searchresume1_check4:
                initCheck();
                keyword_type = "4";
                cbSearchresume1Check4.setChecked(true);
                break;
            case R.id.rl_searchresume_jobclassify:
                // 加载职能选择页
                Intent function = new Intent(getActivity(), SelectFunctionForSearchActivity.class);
                function.putExtra("filter", "post");
                function.putExtra("selectMap", (Serializable) funcSelectedMap);
                function.putExtra("value", "职能");
                startActivity(function);
                break;
            case R.id.cb_searchresume1_sex0:
                initSexCheck();
                cbSearchresume1Sex0.setChecked(true);
                sexId = "0";
                break;
            case R.id.cb_searchresume1_sex1:
                initSexCheck();
                cbSearchresume1Sex1.setChecked(true);
                sexId = "1";
                break;
            case R.id.cb_searchresume1_sex2:
                initSexCheck();
                cbSearchresume1Sex2.setChecked(true);
                sexId = "2";
                break;
            case R.id.rl_searchresume_lingyu:
                // 加载领域选择页
                Intent intentLingyu = new Intent(getActivity(), SelectTerritoryActivity.class);
                intentLingyu.putExtra("lingyuIdString", lingyuIdString);
                startActivity(intentLingyu);
                break;
            case R.id.rl_searchresume_salary:
                chooseSalary();
                break;
            case R.id.rl_searchresume_workplace:
                Intent intentWorkplace = new Intent(getActivity(), SelectPlaceToResumeActivity.class);
                intentWorkplace.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentWorkplace.putExtra("fromtag", 101);
                intentWorkplace.putExtra("placeId", placeId);
                intentWorkplace.putExtra("isCHS", true);
                intentWorkplace.putExtra("ableselected", 5);
                intentWorkplace.putExtra("filter", "place");
                intentWorkplace.putExtra("value", "选择城市");
                startActivity(intentWorkplace);
                break;
            case R.id.rl_searchresume_nowplace:
                Intent intentNowplace = new Intent(getActivity(), SelectPlaceToResume2Activity.class);
                intentNowplace.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentNowplace.putExtra("fromtag", 101);
                intentNowplace.putExtra("placeId", placeId2);
                intentNowplace.putExtra("isCHS", true);
                intentNowplace.putExtra("ableselected", 5);
                intentNowplace.putExtra("filter", "place");
                intentNowplace.putExtra("value", "选择城市");
                startActivity(intentNowplace);
                break;
        }
    }

    public void setPlaceId(HashMap<String, Boolean> checkStateHashMap) {
        Set<String> set = checkStateHashMap.keySet();
        StringBuffer pBuffer = new StringBuffer();
        for (String string : set) {
            pBuffer.append("," + string);
        }
        if (pBuffer.length() > 0) {
            pBuffer.deleteCharAt(0);
        }
        initWorkPlace(pBuffer.toString());
    }

    public void setPlaceId2(HashMap<String, Boolean> checkStateHashMap) {
        Set<String> set = checkStateHashMap.keySet();
        StringBuffer pBuffer = new StringBuffer();
        for (String string : set) {
            pBuffer.append("," + string);
        }
        if (pBuffer.length() > 0) {
            pBuffer.deleteCharAt(0);
        }
        initWorkPlace2(pBuffer.toString());
    }

    /**
     * 初始化工作地区
     *
     * @param placeIdString
     */
    private void initWorkPlace(String placeIdString) {
        placeId = placeIdString;
        if (placeIdString == null || placeIdString.length() == 0) {// 选择的城市为空
            tvSearchresumeWorkplace.setText("不限");
            return;
        }
        JSONArray cityJSONArray = null;
        try {
            InputStream inputStream = getActivity().getAssets().open("city_zh.json");
            cityJSONArray = new JSONArray(NetUtils.readAsString(inputStream, Const.ENCODE));
            StringBuffer showText = new StringBuffer();
            if (placeId != null && placeId.length() > 0) {
                String[] placeStrings = placeId.split(",");
                for (String string : placeStrings) {
                    for (int i = 0; i < cityJSONArray.length(); i++) {
                        JSONObject object = cityJSONArray.getJSONObject(i);
                        if (object.has(string)) {
                            showText.append("," + object.getString(string));
                            break;
                        }
                    }
                }
                if (showText.length() > 0) {
                    showText.deleteCharAt(0);
                }
                tvSearchresumeWorkplace.setText(showText.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化工作地区
     *
     * @param placeIdString
     */
    private void initWorkPlace2(String placeIdString) {
        placeId2 = placeIdString;
        if (placeIdString == null || placeIdString.length() == 0) {// 选择的城市为空
            tvSearchresumeNowplace.setText("不限");
            return;
        }
        JSONArray cityJSONArray = null;
        try {
            InputStream inputStream = getActivity().getAssets().open("city_zh.json");
            cityJSONArray = new JSONArray(NetUtils.readAsString(inputStream, Const.ENCODE));
            StringBuffer showText = new StringBuffer();
            if (placeId2 != null && placeId2.length() > 0) {
                String[] placeStrings = placeId2.split(",");
                for (String string : placeStrings) {
                    for (int i = 0; i < cityJSONArray.length(); i++) {
                        JSONObject object = cityJSONArray.getJSONObject(i);
                        if (object.has(string)) {
                            showText.append("," + object.getString(string));
                            break;
                        }
                    }
                }
                if (showText.length() > 0) {
                    showText.deleteCharAt(0);
                }
                tvSearchresumeNowplace.setText(showText.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initCheck() {
        cbSearchresume1Check1.setChecked(false);
        cbSearchresume1Check2.setChecked(false);
        cbSearchresume1Check3.setChecked(false);
        cbSearchresume1Check5.setChecked(false);
        cbSearchresume1Check6.setChecked(false);
        cbSearchresume1Check4.setChecked(false);
    }

    private void initSexCheck() {
        cbSearchresume1Sex0.setChecked(false);
        cbSearchresume1Sex1.setChecked(false);
        cbSearchresume1Sex2.setChecked(false);
    }

    private void goActivity() {
        Intent intent = new Intent(getActivity(), SearchResumeResult.class);
        Bundle bundle = new Bundle();
        bundle.putString(HttpHelper.METHOD, HttpHelper.RESUME_SEARCH);
        bundle.putString("sex", sexId);
        bundle.putString("search_id", "");
        bundle.putString("norecord", "");
        bundle.putString("resume_number", "");
        bundle.putString("high_education", spSearchresumeEdu.getSelectedId());
        bundle.putString("is_include_more_degree", "1");
        bundle.putString("language", spSearchresumeLanguage.getSelectedId());
        bundle.putString("funtype", funcIdString);
        bundle.putString("workarea", placeId);
        bundle.putString("level", spSearchresumeLanguagegrade.getSelectedId());
        bundle.putString("searchtime", spSearchresumeResumeupdate.getSelectedId());
        bundle.putString("locat", placeId2);
        bundle.putString("post_rank", spSearchresumePost.getSelectedId());
        bundle.putString("age1", spSearchresumeAge.getSelectedId());
        if (spSearchresumeAge.getSelectedId().equals("18")) {
            if (Integer.parseInt(edSearchresumeAge2.getText().toString().trim()) < Integer.parseInt(edSearchresumeAge3.getText().toString().trim())) {
                bundle.putString("age2", edSearchresumeAge2.getText().toString().trim());
                bundle.putString("age3", edSearchresumeAge3.getText().toString().trim());
            } else {
                Toast.makeText(getActivity(), "自定义年龄范围错误", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        bundle.putString("work_type", spSearchresumeJobnature.getSelectedId());
        if (funcIdString.equals("") && editHomefragmentSearch.getText().toString().trim().equals("")) {
            Toast.makeText(getActivity(), "至少选择职位分类和关键词其中一项", Toast.LENGTH_SHORT).show();
            return;
        } else {
            bundle.putString("keyword_type", keyword_type);
        }
        bundle.putString("entkeysearch", editHomefragmentSearch.getText().toString().trim());
        bundle.putString("workyear", spSearchresumeJobexp.getSelectedId());
        bundle.putString("is_include_more_workyear", "1");
        bundle.putString("lingyu", lingyuIdString);
        bundle.putString("salary_from", salaryFromId);
        bundle.putString("salary_to", salaryToId);
        bundle.putString("zhixi", "");
        bundle.putString("resume_type", "");
        bundle.putString("current_yearsalary", "");
        bundle.putString("site_code", "");
        bundle.putString("yy_zhicheng", spSearchresumePost.getSelectedId());
        bundle.putString("total", "");
        bundle.putString("current_yearsalary", "");
        intent.putExtras(bundle);
        startActivity(intent);
    }


    /**
     * 添加职位分类选择项
     */
    public void setFunctionSelected(Map<String, String> selectMap) {
        // 12-20 16:58:22.572: I/System.out(29412): {257101= 无机合成研发,
        // 264101|10300= 大内科护师, 264101|10400= 大内科技师}
        funcSelectedMap.clear();
        funcSelectedMap.putAll(selectMap);
        funcIdString = "";
        // 过滤原数据
        String[] itemFuncStrings = funcIdString.split(",");// 所有行业信息
        StringBuffer buffer0 = new StringBuffer();// 除当前行业以外的职能
        if (itemFuncStrings != null && itemFuncStrings.length > 0) {
            for (int i = 0; i < itemFuncStrings.length; i++) {// 过滤出非当前行业的职能
//                if (!itemFuncStrings[i].contains("")) {
                buffer0.append("," + itemFuncStrings[i]);
//                }
            }
        }
        // 拼装新数据
        StringBuffer buffer1 = new StringBuffer();
        Set<String> set = funcSelectedMap.keySet();
        for (String string : set) {
            buffer1.append("," + string);
        }
        // 结合
        while (buffer0.toString().startsWith(",")) {// 去除开头“，”
            buffer0.deleteCharAt(0);
        }
        while (buffer1.toString().startsWith(",")) {// 去除开头“，”
            buffer1.deleteCharAt(0);
        }
        if (buffer0.length() > 0) {
            funcIdString = buffer0.toString() + "," + buffer1.toString();
        } else {
            funcIdString = buffer1.toString();
        }
        showText(funcSelectedMap);
    }

    /**
     * 显示职位分类信息
     */
    private void showText(Map<String, String> selectMap) {
        Set<String> keySet = selectMap.keySet();
        if (selectMap.size() == 0) {
            tvSearchresumeJobclassify.setText("不限");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        for (String keyString : keySet) {
            buffer.append(selectMap.get(keyString).trim() + ",");
        }
        if (buffer.toString().trim().length() > 0) {
            tvSearchresumeJobclassify.setText(buffer.toString().subSequence(0, buffer.length() - 1).toString().trim());
        } else {
            tvSearchresumeJobclassify.setText("不限");
        }
    }

    /**
     * 设置领域
     */
    public void setLingyu(String lingyuIdString) {
        this.lingyuIdString = lingyuIdString;
        initLingyu(lingyuIdString);
    }

    /**
     * 初始化领域
     */
    private void initLingyu(String lingyuId) {
//        Toast.makeText(mContext, "lingyuId:" + lingyuId.toString(), Toast.LENGTH_SHORT).show();
        // lingyu=11: 111100, 11: 111200,11: 111300,14: 141100, 14: 141200
        if (lingyuId.length() == 0) {
            tvSearchresumeLingyu.setText("不限");
            return;
        }
        StringBuffer showStringBuffer = new StringBuffer();// 要显示的文字
        showStringBuffer.delete(0, showStringBuffer.length());
        // 过滤出当前行业下领域
        ArrayList<String> curIndustryLingyu = new ArrayList<String>();
        String[] itemLingyu = lingyuId.split(",");
        for (String string : itemLingyu) {
//            if (string.contains(app.getIndustry() + ":")) {
            curIndustryLingyu.add(string);// 11:111100
//            }
        }
        if ("11".equals(app.getIndustry()) || "12".equals(app.getIndustry()) || "14".equals(app.getIndustry()) || "29".equals(app.getIndustry())) {
            rlSearchresumeLingyu.setVisibility(View.VISIBLE);
            for (String string : curIndustryLingyu) {
//                String id = string.replace(app.getIndustry() + ":", "");
                showStringBuffer.append("," + ResumeInfoIDToString.getLingYuString(getActivity(), app.getIndustry(), string));
            }
        } else {
            rlSearchresumeLingyu.setVisibility(View.GONE);
        }

        if (showStringBuffer.toString().startsWith(",")) {// 去除首个“，”
            showStringBuffer.deleteCharAt(0);
        }
        if (showStringBuffer.toString().endsWith(",")) {// 去除末尾“，”
            showStringBuffer.deleteCharAt(showStringBuffer.length() - 1);
        }
        Log.i("===========LingYuID", showStringBuffer.toString());
        tvSearchresumeLingyu.setText(showStringBuffer.toString());
        // System.out.println("领域：" + showStringBuffer.toString());
    }


    /**
     * 薪资选择
     */
    private void chooseSalary() {
        viewSelectSalary = LayoutInflater.from(getActivity()).inflate(R.layout.item_popupwindow_isappresume, null);
        WindowManager manager = (WindowManager) getActivity().getSystemService(getActivity().WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        int height = manager.getDefaultDisplay().getHeight();
        popwindowSelectSalary = new PopupWindow(getActivity());
        popwindowSelectSalary.setContentView(viewSelectSalary);
//        popwindowSelectSalary.setWidth(width / 6 * 5);
//        popwindowSelectSalary.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popwindowSelectSalary.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popwindowSelectSalary.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        popwindowSelectSalary.setFocusable(true);
        popwindowSelectSalary.setTouchable(true);
//        popwindowSelectSalary.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_popup_isresumeapp));
        popwindowSelectSalary.setBackgroundDrawable(new BitmapDrawable());
        // 设置弹窗外可点击，默认为false
//        popwindowSelectSalary.setAnimationStyle(R.style.popwindow);
//        popwindowSelectSalary.showAtLocation(viewSelectSalary, Gravity.CENTER, 0, 0);
        popwindowSelectSalary.showAtLocation(viewSelectSalary, Gravity.CENTER, 0, 0);
        minute_pv = (PickerView) viewSelectSalary.findViewById(R.id.minute_pv);
        second_pv = (PickerView) viewSelectSalary.findViewById(R.id.second_pv);
        tv_pop_confirm = (TextView) viewSelectSalary.findViewById(R.id.tv_pop_confirm);
        tv_pop_cancle = (TextView) viewSelectSalary.findViewById(R.id.tv_pop_cancle);
        List<String> data = new ArrayList<String>();
        List<String> seconds = new ArrayList<String>();
        final String[] salaryFrom = getResources().getStringArray(R.array.array_salary_custom_from);
        String[] salaryTo = getResources().getStringArray(R.array.array_salary_custom_to);
        String[] salaryFromIdArray = getResources().getStringArray(R.array.array_salary_custom_fromid);
        String[] salaryToIdArray = getResources().getStringArray(R.array.array_salary_custom_toid);
        for (int i = 0; i < salaryFrom.length; i++) {
            data.add(salaryFrom[i]);
        }
        for (int i = 0; i < salaryTo.length; i++) {
            seconds.add(salaryTo[i]);
        }
        final HashMap<String, String> salaryMapTo = new HashMap<>();
        for (int i = 0; i < salaryTo.length; i++) {
            salaryMapTo.put(salaryTo[i], salaryToIdArray[i]);
        }
        final HashMap<String, String> salaryMapFrom = new HashMap<>();
        for (int i = 0; i < salaryFrom.length; i++) {
            salaryMapFrom.put(salaryFrom[i], salaryFromIdArray[i]);
        }
        minute_pv.setData(data);
        second_pv.setData(seconds);
        if (isCheckSalary) {
            for (int i = 0; i < data.size(); i++) {
                if (salaryFromString.equals(data.get(i))) {
                    minute_pv.setSelected(i);
                }
            }
            for (int i = 0; i < seconds.size(); i++) {
                if (salaryToString.equals(seconds.get(i))) {
                    second_pv.setSelected(i);
                }
            }
        } else {
            salaryFromString = "6000";
            salaryToString = "9999";
        }
        minute_pv.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
//                if (isCheckSalary) {
//                    salaryFromId = salaryMapFrom.get(text);
                salaryFromString = text;
//                } else {
//                    isCheckSalary = true;
                salaryFromId = salaryMapFrom.get(salaryFromString);
//                }

            }
        });
        second_pv.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
//                if (isCheckSalary) {
//                    salaryToId = salaryMapTo.get(text);

                salaryToString = text;
//                } else {
//                    isCheckSalary = true;
                salaryToId = salaryMapTo.get(salaryToString);
//                }
            }
        });
        tv_pop_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!salaryToString.equals("以上") && !salaryToString.equals("不限") && !salaryFromString.equals("不限")) {
                    if (Integer.parseInt(salaryFromString) < Integer.parseInt(salaryToString)) {
                        tvSearchresumeSalary.setText(salaryFromString + "—" + salaryToString);
                        salaryToId = salaryMapTo.get(salaryToString);
                        salaryFromId = salaryMapFrom.get(salaryFromString);
                        isCheckSalary = true;
                        popwindowSelectSalary.dismiss();
                    } else {
                        Toast.makeText(getActivity(), "范围错误请重新选择！", Toast.LENGTH_SHORT).show();
                    }
                } else if (salaryToString.equals("不限") && salaryFromString.equals("不限")) {
                    tvSearchresumeSalary.setText(salaryFromString + "—" + salaryToString);
                    salaryToId = "";
                    salaryToId = "";
                    isCheckSalary = true;
                    popwindowSelectSalary.dismiss();

                } else if (salaryToString.equals("不限") || salaryFromString.equals("不限")) {
                    tvSearchresumeSalary.setText(salaryFromString + "—" + salaryToString);
                    salaryToId = salaryMapTo.get(salaryToString);
                    salaryFromId = salaryMapFrom.get(salaryFromString);
                    isCheckSalary = true;
                    popwindowSelectSalary.dismiss();
                } else {
                    tvSearchresumeSalary.setText(salaryFromString + "—" + salaryToString);
                    salaryToId = salaryMapTo.get(salaryToString);
                    salaryFromId = salaryMapFrom.get(salaryFromString);
                    isCheckSalary = true;
                    popwindowSelectSalary.dismiss();
                }
            }
        });
        tv_pop_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popwindowSelectSalary.dismiss();
            }
        });
    }
}

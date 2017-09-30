package com.hr.ent.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPopupWindow;
import com.bigkoo.pickerview.TimePopupWindow;
import com.hr.ent.R;
import com.hr.ent.handler.PostJobHandler;
import com.hr.ent.model.DepListBean;
import com.hr.ent.model.GetJobInfoBean;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.DateUtils;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.utils.NetUtils;
import com.hr.ent.view.BeautifulDialog;
import com.hr.ent.view.WindowUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditJobActivity extends Activity {
    @Bind(R.id.tv_postjob_back)
    ImageView tvPostjobBack;
    @Bind(R.id.tv_postjob_savedraft)
    TextView tvPostjobSavedraft;
    //    @Bind(R.id.tv_postjob_jobmodle)
//    TextView tvPostjobJobmodle;
    @Bind(R.id.et_postjob_jobname)
    EditText etPostjobJobname;
    @Bind(R.id.tv_postjob_department)
    TextView tvPostjobDepartment;
    @Bind(R.id.tv_postjob_type)
    TextView tvPostjobType;
    @Bind(R.id.tv_postjob_jobclassify)
    TextView tvPostjobJobClassify;
    @Bind(R.id.tv_postjob_peoplenum)
    EditText tvPostjobPeoplenum;
    //    @Bind(R.id.tv_postjob_show)
//    CheckBox tvPostjobShow;
    @Bind(R.id.tv_postjob_date)
    TextView tvPostjobDate;
    @Bind(R.id.et_postjob_describ)
    EditText etPostjobDescrib;
    @Bind(R.id.et_postjob_salaryfrom)
    EditText etPostjobSalaryfrom;
    @Bind(R.id.et_postjob_salaryto)
    EditText etPostjobSalaryto;
    @Bind(R.id.tv_postjob_moreset)
    TextView tvPostjobMoreset;
    @Bind(R.id.tv_postjob_confirm)
    TextView tvPostjobConfirm;
    @Bind(R.id.tv_postjob_jobplace)
    TextView tvPostjobJobplace;
    /**
     * 地点数
     */
    private int placNum = 0;
    //     study1="";
    public String study1Str = "请选择";
    //     post_rank="";
    public String post_rankStr = "请选择";
    //     language1="请选择";
    public String language1Str = "请选择";
    //     level1="";
    public String level1Str = "请选择";
    //     workyear="";
    public String workyearStr = "请选择";

    public String workyear = "10";
    public String invite_major = "";
    public String study1 = "10";
    public String is_include_more_degree = "";
    public String post_rank = "";
    public String language1 = "10";
    public String level1 = "1";
    public String language2 = "";
    public String level2 = "";
    public String is_show_linkman = "";
    public String is_show_address = "";
    public String is_show_phone = "";
    public String is_show_fax = "";
    public String is_show_email = "";
    public String is_send_mail = "1";
    public String is_send_mail2 = "";
    public String email2 = "";
    public String is_use_auto_reply = "1";
    public String graduate = "";
    public String resume_shield_workyear = "";
    public String degree = "";
    public String allow_locate = "";
    public String workyear_val = "";
    public String degree_val = "";
    public String allow_locate_val = "";
    public String is_templet = "";
    public String templet_name = "";
    public String job_name = "";
    /**
     * 工作性质
     */
    private String jobtype = "";
    private String jobtypeID = "";
    /**
     * 所属部门
     */
    private String did = "";
    private String dname = "";
    /**
     * 职位分类
     */
    private String hidfuntype = "";
    private Map<String, String> funcSelectedMap = new HashMap<String, String>();// key：职能id，value：职能名称
    private String funcIdString = "";// 职能id
    /**
     * 工作地点
     */
    private String hidquyu = "";
    private String placeId = "";
    /**
     * 招聘人数
     */
    private String number = "";

    /**
     * 薪资待遇 工资待遇1时为自定义
     */
    private String monthly_pay = "", monthly_pay_from = "", monthly_pay_to = "", is_show_pay_interview = "";
    /**
     * 职位截止日期
     */
    private String effect_time = "";
    private String effect_timenomal = "";

    /**
     * 职位简介
     */
    private String synopsis = "";
    private String job_id = "";

    private OptionsPopupWindow pwJobtype;
    private TimePopupWindow pwTime;
    public static EditJobActivity editJobActivity;
    private PostJobHandler postJobHandler;
    public DepListBean.ReturnDataBean returnDataBean;
//    private GetJobInfoBean getJobInfoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_post_job);
        ButterKnife.bind(this);
        initData();
        getData();
    }

    private void initData() {
        editJobActivity = EditJobActivity.this;
        postJobHandler = new PostJobHandler(this);
        returnDataBean = new DepListBean.ReturnDataBean();
//        getJobInfoBean = new GetJobInfoBean();
    }

    private void getData() {
        job_id = getIntent().getStringExtra("job_id");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(HttpHelper.METHOD, HttpHelper.ENTERPRISE_JOBGET);
        params.put("job_id", job_id);
        MsgHandler.sendMessage(params, postJobHandler, PostJobHandler.wGetJobInfoStart);
    }

    public void setData(GetJobInfoBean getJobInfoBean) {
        post_rankStr = getJobInfoBean.getPost_rank();
        language1 = getJobInfoBean.getLanguage1();
        level1 = getJobInfoBean.getLevel1();
        workyear = getJobInfoBean.getWorkyear();
        invite_major = getJobInfoBean.getInvite_major();
        study1 = getJobInfoBean.getStudy1();
        is_include_more_degree = getJobInfoBean.getIs_include_more_degree();
        is_show_linkman = getJobInfoBean.getIs_show_linkman();
        is_show_address = getJobInfoBean.getIs_show_address();
        is_show_phone = getJobInfoBean.getIs_show_phone();
        is_show_fax = getJobInfoBean.getIs_show_fax();
        is_show_email = getJobInfoBean.getIs_show_email();
        is_send_mail = getJobInfoBean.getIs_send_mail();
        is_send_mail2 = getJobInfoBean.getIs_send_mail2();
        email2 = getJobInfoBean.getEmail2();
        is_use_auto_reply = getJobInfoBean.getIs_use_auto_reply();
        job_name = getJobInfoBean.getJob_name();
        jobtype = "";
        jobtypeID = getJobInfoBean.getWork_type();
        did = getJobInfoBean.getDid() + "";
        dname = getJobInfoBean.getDepartment();
        hidfuntype = getJobInfoBean.getJob_type();
        hidquyu = "";
        placeId = getJobInfoBean.getQuyu();
        number = getJobInfoBean.getNumber();
        monthly_pay = getJobInfoBean.getMonthly_pay();
        monthly_pay_to = getJobInfoBean.getMonthly_pay_to();
        is_show_pay_interview = getJobInfoBean.getIs_show_pay_interview();
        effect_time = getJobInfoBean.getLast_refresh_time();
        effect_timenomal = "";
        synopsis = getJobInfoBean.getSynopsis();
        initInfo();
    }

    private void initInfo() {
        pwJobtype = new OptionsPopupWindow(this);
        List<String> stringList = Arrays.asList(getResources().getStringArray(R.array.array_jobtype_zh));
        ArrayList<String> stringList2 = new ArrayList<>(stringList);
        pwJobtype.setPicker(stringList2);
        pwJobtype.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                jobtype = getResources().getStringArray(R.array.array_jobtype_zh)[options1];
                jobtypeID = getResources().getStringArray(R.array.array_jobtype_ids)[options1];
                tvPostjobType.setText(jobtype);
            }
        });
        int iJobtype = howNum(getResources().getStringArray(R.array.array_workyear_ids), jobtypeID);
        pwJobtype.setSelectOptions(iJobtype);
        tvPostjobType.setText(getResources().getStringArray(R.array.array_jobtype_zh)[iJobtype]);

        pwJobtype.setSelectOptions(0);
        pwTime = new TimePopupWindow(this, TimePopupWindow.Type.YEAR_MONTH_DAY);
        pwTime.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                tvPostjobDate.setText(getTime(date));
                effect_time = getTime(date).toString();
            }
        });
        tvPostjobDate.setText(DateUtils.transforMillToDate(Long.valueOf(effect_time).longValue()));
        etPostjobJobname.setText(job_name);
        tvPostjobPeoplenum.setText(number);
        etPostjobDescrib.setText(synopsis);
        initWorkPlace(placeId);
        tvPostjobDepartment.setText(dname);
        etPostjobSalaryfrom.setText(monthly_pay);
        etPostjobSalaryto.setText(monthly_pay_to);
    }

    private int howNum(String[] stringStr, String howStr) {
        for (int i = 0; i < stringStr.length; i++) {
            if (stringStr[i].equals(howStr)) {
                return i;
            }
        }
        return 0;
    }

    public static String getTime(Date date) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @OnClick({R.id.tv_postjob_back, R.id.tv_postjob_jobplace, R.id.tv_postjob_savedraft, R.id.tv_postjob_jobclassify, R.id.tv_postjob_department, R.id.tv_postjob_type, R.id.tv_postjob_peoplenum, R.id.tv_postjob_date, R.id.tv_postjob_moreset, R.id.tv_postjob_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_postjob_back:
                finish();
                break;
            case R.id.tv_postjob_savedraft:
                saveData("1");
                break;
//            case R.id.tv_postjob_jobmodle:
//                break;
            case R.id.tv_postjob_department:
                Intent intentDep = new Intent(this, GetDepartmentActivity.class);
                startActivityForResult(intentDep, 0);
                break;
            case R.id.tv_postjob_type:
                pwJobtype.showAtLocation(tvPostjobType, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.tv_postjob_jobclassify:
                Intent function = new Intent(this, SelectFunctionForEditJobActivity.class);
                function.putExtra("filter", "post");
                function.putExtra("selectMap", (Serializable) funcSelectedMap);
                function.putExtra("value", "职能");
                startActivity(function);
                break;
            case R.id.tv_postjob_jobplace:
                Intent intentWorkplace = new Intent(this, SelectPlaceToEditJobActivity.class);
                intentWorkplace.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentWorkplace.putExtra("fromtag", 101);
                intentWorkplace.putExtra("placeId", placeId);
                intentWorkplace.putExtra("isCHS", true);
                intentWorkplace.putExtra("ableselected", 9999);
                intentWorkplace.putExtra("filter", "place");
                intentWorkplace.putExtra("value", "选择城市");
                startActivity(intentWorkplace);
                break;
            case R.id.tv_postjob_peoplenum:
                break;
            case R.id.tv_postjob_date:
                pwTime.showAtLocation(tvPostjobDate, Gravity.BOTTOM, 0, 0, new Date());
                break;
            case R.id.tv_postjob_moreset:
                if (did.toString().length() == 0) {
                    Toast.makeText(this, "请选择所属部门!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intentMore = new Intent(this, MoreEditJobSettingActivity.class);
                    startActivity(intentMore);
                }
                break;
            case R.id.tv_postjob_confirm:
                saveData("0");
                break;
        }
    }

    private BeautifulDialog.Builder saveDialog;

    /**
     * 初始化工作地区
     *
     * @param placeIdString
     */
    private void initWorkPlace(String placeIdString) {
        placeId = placeIdString;
        if (placeIdString == null || placeIdString.length() == 0) {// 选择的城市为空
            tvPostjobJobplace.setText("不限");
            return;
        }
        JSONArray cityJSONArray = null;
        try {
            InputStream inputStream = getAssets().open("city_zh.json");
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
                tvPostjobJobplace.setText(showText.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPlaceId(HashMap<String, Boolean> checkStateHashMap) {
        placNum = checkStateHashMap.size();
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
            tvPostjobJobClassify.setText("不限");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        for (String keyString : keySet) {
            buffer.append(selectMap.get(keyString).trim() + ",");
        }
        if (buffer.toString().trim().length() > 0) {
            tvPostjobJobClassify.setText(buffer.toString().subSequence(0, buffer.length() - 1).toString().trim());
        } else {
            tvPostjobJobClassify.setText("不限");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 0:
                if (data != null) {
                    Bundle bun = data.getExtras();
                    returnDataBean = bun.getParcelable("ReturnDataBean");
                    dname = returnDataBean.getDname();
                    did = returnDataBean.getDid();
                    tvPostjobDepartment.setText(dname);
                }
                break;

            default:
                break;
        }
    }


    private void saveData(final String issue_state) {
        job_name = etPostjobJobname.getText().toString();
        number = tvPostjobPeoplenum.getText().toString();
        if (job_name.length() == 0) {
            Toast.makeText(this, "请输入职位名称!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (did.length() == 0) {
            Toast.makeText(this, "请选择所属部门!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (jobtypeID.length() == 0) {
            Toast.makeText(this, "请选择工作性质!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (funcIdString.length() == 0) {
            Toast.makeText(this, "请选择职位分类!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (placeId.length() == 0) {
            Toast.makeText(this, "请选择工作地点!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (number.length() == 0) {
            Toast.makeText(this, "招聘人数必须大于0!", Toast.LENGTH_SHORT).show();
            return;
        }
        monthly_pay = etPostjobSalaryfrom.getText().toString();
        monthly_pay_to = etPostjobSalaryto.getText().toString();
        if (monthly_pay.length() == 0 || monthly_pay_to.length() == 0) {
            Toast.makeText(this, "请输入薪资待遇!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (effect_time.length() == 0) {
            Toast.makeText(this, "请选择截止日期!", Toast.LENGTH_SHORT).show();
            return;
        }
        synopsis = etPostjobDescrib.getText().toString();
        if (synopsis.length() == 0) {
            Toast.makeText(this, "请输入职位描述!", Toast.LENGTH_SHORT).show();
            return;
        }

        saveDialog = new BeautifulDialog.Builder(this);
        saveDialog.setMessage("您将使用" + placNum + "个限量发布，是否确认发布？");
        saveDialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /**
                 * 拼接参数
                 */
                Map<String, Object> params = new HashMap<String, Object>();
                params.put(HttpHelper.METHOD, HttpHelper.ENTERPRISE_JOBADD);
                params.put("issue_state", issue_state);
                params.put("job_name", job_name);
                params.put("did", did);
                params.put("job_number", "");
                params.put("jobtype", jobtypeID);
                params.put("hidfuntype", funcIdString);
                params.put("hidquyu", placeId);
                if (number.length() == 0) {
                    params.put("number", "");
                    params.put("is_show_number_some", "1");
                } else {
                    params.put("number", number);
                    params.put("is_show_number_some", "");
                }
//        params.put("monthly_play", "1");
                params.put("monthly_pay", monthly_pay);
//        params.put("monthly_play_from", monthly_play_from);
                params.put("monthly_pay_to", monthly_pay_to);
                params.put("is_show_pay_interview", "");
                params.put("average_pay", "");
                params.put("synopsis", synopsis);
                params.put("effect_time", effect_time);
/**
 * 其他额外参数
 */
                params.put("workyear", workyear);
                params.put("invite_major", invite_major);
                params.put("study1", study1);
                params.put("is_include_more_degree", is_include_more_degree);
                params.put("post_rank", post_rank);
                params.put("language1", language1);
                params.put("level1", level1);
                params.put("language2", language2);
                params.put("level2", level2);
                params.put("is_show_linkman", is_show_linkman);
                params.put("is_show_address", is_show_address);
                params.put("is_show_phone", is_show_phone);
                params.put("is_show_fax", is_show_fax);
                params.put("is_show_email", is_show_email);
                params.put("is_send_mail", is_send_mail);
                params.put("is_send_mail2", is_send_mail2);
                params.put("email2", email2);
                params.put("is_use_auto_reply", is_use_auto_reply);
                params.put("graduate", graduate);
                params.put("resume_shield_workyear", resume_shield_workyear);
                params.put("degree", degree);
                params.put("allow_locate", allow_locate);
                params.put("workyear_val", workyear_val);
                params.put("degree_val", degree_val);
                params.put("templet_name", templet_name);
                params.put("is_templet", is_templet);
                params.put("allow_locate_val", allow_locate_val);
                MsgHandler.sendMessage(params, postJobHandler, PostJobHandler.wSetJobStart);
                dialog.dismiss();
            }
        });
        saveDialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        saveDialog.create().show();


    }
}

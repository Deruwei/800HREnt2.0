package com.hr.ent.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.hr.ent.app.App;
import com.hr.ent.handler.HomeHandler;
import com.hr.ent.handler.JobInfoHandler;
import com.hr.ent.handler.PostJobHandler;
import com.hr.ent.model.DepListBean;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.utils.NetUtils;
import com.hr.ent.utils.StringTimeToDateUtils;
import com.hr.ent.utils.TimeStampUtils;
import com.hr.ent.view.BeautifulDialog;
import com.hr.ent.view.MyCustomDatePicker;
import com.hr.ent.view.WindowUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 发布职位
 */
public class PostJobActivity extends Activity {
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
    //    private String study1="";
    public String study1Str = "请选择";
    //    private String post_rank="";
    public String post_rankStr = "请选择";
    //    private String language1="请选择";
    public String language1Str = "请选择";
    //    private String level1="";
    public String level1Str = "请选择";
    //    private String workyear="";
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
    public  String endTime;

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

    private OptionsPopupWindow pwJobtype;
    private TimePopupWindow pwTime;
    public static PostJobActivity postJobActivity;
    public int limitNum;

    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }

    private PostJobHandler postJobHandler;
    public DepListBean.ReturnDataBean returnDataBean;
    private App app;
    private HomeHandler handler;
    private MyCustomDatePicker datePickerPostDay;

    public void setEndTime(String endTime) {
        this.endTime=TimeStampUtils.timet(endTime);
        Log.i("结束时间",this.endTime);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_post_job);
        ButterKnife.bind(this);
        app= (App) getApplication();
        handler=new HomeHandler(this);
        initDialog();
        getServiceData();
        getLimitJobNum();
        initData();

    }

    private void initDialog() {
        datePickerPostDay=new MyCustomDatePicker(PostJobActivity.this, new MyCustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                effect_time=time;
                tvPostjobDate.setText(time);
            }
        });
    }

    private void getServiceData() {
        if (app.getNetworkMng().isCanConnect()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpHelper.METHOD, HttpHelper.CONTRACTINFO);
            MsgHandler.sendMessage(params, handler,
                    HomeHandler.wGetServiceInfoStart);
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }
    private void getLimitJobNum(){
        if (app.getNetworkMng().isCanConnect()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpHelper.METHOD, HttpHelper.CONTRACTSTATE);
            MsgHandler.sendMessage(params, handler,
                    HomeHandler.wGetServiceStart3);
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }
    private void initData() {
        postJobActivity = PostJobActivity.this;
        postJobHandler = new PostJobHandler(this);
        returnDataBean = new DepListBean.ReturnDataBean();

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
        pwJobtype.setSelectOptions(0);
        pwTime = new TimePopupWindow(this, TimePopupWindow.Type.YEAR_MONTH_DAY);
        pwTime.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                tvPostjobDate.setText(getTime(date));
                effect_time = getTime(date).toString();
            }
        });
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
                Intent function = new Intent(this, SelectFunctionForJobActivity.class);
                function.putExtra("filter", "post");
                function.putExtra("selectMap", (Serializable) funcSelectedMap);
                function.putExtra("value", "职能");
                startActivity(function);
                break;
            case R.id.tv_postjob_jobplace:
                Intent intentWorkplace = new Intent(this, SelectPlaceToJob.class);
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
                if("请选择".equals(tvPostjobDate.getText())) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//格式为 2013年9月3日 14:44
                    Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                    String  currentDate = formatter.format(curDate);
                    datePickerPostDay.show(currentDate,endTime,3);
                }else {
                    datePickerPostDay.show(tvPostjobDate.getText().toString(),endTime, 3);
                }
                break;
            case R.id.tv_postjob_moreset:
                if (did.toString().length() == 0) {
                    Toast.makeText(this, "请选择所属部门!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intentMore = new Intent(this, MoreJobSettingActivity.class);
                    intentMore.putExtra("invite_major",invite_major);
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
    private void getLimitNum(String placeIdString){
        placNum=0;
        String[] placeStrings = placeIdString.split(",");
        for(int i=0;i<placeStrings.length;i++) {
            if ("1100".equals(placeStrings[i]) || "1200".equals(placeStrings[i]) || "1300".equals(placeStrings[i]) || "1400".equals(placeStrings[i])) {
                placNum++;
            }else if(placeStrings[i].endsWith("00")){
                try {
                    InputStream inputStream = getAssets().open("city_zh.json");
                    JSONArray cityJSONArray = new JSONArray(NetUtils.readAsString(inputStream, Const.ENCODE));
                    for (int j = 0; j < cityJSONArray.length(); j++) {
                        JSONObject object = cityJSONArray.getJSONObject(j);
                        String s=object.toString().substring(2,6);
                        if(s.substring(0,2).equals(placeStrings[i].substring(0,2))&&!s.endsWith("00")){
                            placNum++;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                placNum++;
            }
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
        getLimitNum(pBuffer.toString());
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
        if(limitNum<placNum) {
            saveDialog = new BeautifulDialog.Builder(this);
            saveDialog.setMessage("对不起，您的职位发布限量不足，请联系您的销售顾问！");
//        beautifulDialog.setTitle("提示");
            saveDialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                }
            });
            saveDialog.create().show();
        }else{
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpHelper.METHOD, HttpHelper.ENTERPRISE_JOBADD);
            params.put("issue_state", issue_state);
            params.put("job_name", job_name);
            params.put("did", did);
            params.put("job_number", "");
            params.put("jobtype", jobtypeID);
            params.put("hidfuntype", funcIdString);
            params.put("hidquyu", placeId);
//        number = tvPostjobPeoplenum.getText().toString();
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
        }


    }
}

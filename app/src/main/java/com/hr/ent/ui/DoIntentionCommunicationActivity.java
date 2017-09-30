package com.hr.ent.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.handler.IntentionApplyHandler;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.FileUtils;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.utils.NetUtils;
import com.hr.ent.view.WindowUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DoIntentionCommunicationActivity extends BaseActivity {

    @Bind(R.id.iv_dointencomm_back)
    ImageView ivDointencommBack;
    @Bind(R.id.tv_dointencomm_choosejob)
    TextView tvDointencommChoosejob;
    @Bind(R.id.cb_dointencomm_check1)
    CheckBox cbDointencommCheck1;
    @Bind(R.id.cb_dointencomm_check6)
    CheckBox cbDointencommCheck6;
    @Bind(R.id.cb_dointencomm_check2)
    CheckBox cbDointencommCheck2;
    @Bind(R.id.cb_dointencomm_check7)
    CheckBox cbDointencommCheck7;
    @Bind(R.id.cb_dointencomm_check3)
    CheckBox cbDointencommCheck3;
    @Bind(R.id.cb_dointencomm_check8)
    CheckBox cbDointencommCheck8;
    @Bind(R.id.cb_dointencomm_check4)
    CheckBox cbDointencommCheck4;
    @Bind(R.id.cb_dointencomm_check9)
    CheckBox cbDointencommCheck9;
    @Bind(R.id.cb_dointencomm_check5)
    CheckBox cbDointencommCheck5;
    @Bind(R.id.cb_dointencomm_check10)
    CheckBox cbDointencommCheck10;
    @Bind(R.id.cb_dointencomm_checkagree)
    CheckBox cbDointencommCheckagree;
    @Bind(R.id.bt_dointencomm_submit)
    Button btDointencommSubmit;
    @Bind(R.id.et_dointencomm)
    EditText etDointencomm;

    public static DoIntentionCommunicationActivity doIntentionComm;
    private IntentionApplyHandler handler;
    //    method	enterprise_intention.apply	string	是	请求动作名
//    r_id		int	否	沟通简历记录id，存在时为再次申请，未存在时为新申请
//    job_id 		string	是	职位id
//    user_id 		int	是	个人id
//    resume_id 		int	是	简历id
//    resume_language 		string	否	简历语言,默认zh
//    resume_from		string	否	1搜索简历,2推荐简历,3应聘简历
//    resume_from_id		int	否	来源简历的记录ID，推荐简历时为推荐简历记录的ID
//    linkup_options 		string	否	沟通选项id，逗号分割
//    linkup_other 		string	否	沟通其他内容
//    need_details		string	否	需要预先与企业沟通细节：1不需要（默认），2需要
//    link_man 		string	否	企业联系人
//    link_phone 		string	否	企业联系电话
    private String user_id;
    private String resume_id;
    private String resume_language;
    private String job_id = "";
    private String job_name, resume_from, isIntention, resume_from_id, type;
    private String r_id = "";
    private boolean cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10, cbAgree;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //沉浸式状态栏
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_do_intention_communication);
        ButterKnife.bind(this);
        doIntentionComm = DoIntentionCommunicationActivity.this;
        init();
    }

    private void init() {
        handler = new IntentionApplyHandler(this);
        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        resume_id = intent.getStringExtra("resume_id");
        resume_language = intent.getStringExtra("resume_language");
        resume_from = intent.getStringExtra("resume_from");
        resume_from_id = intent.getStringExtra("resume_from_id");
        type = intent.getStringExtra("type");
        if (type != null && type.equals("10")) {
            job_name = intent.getStringExtra("job_name");
            job_id = intent.getStringExtra("job_id");
            r_id = intent.getStringExtra("r_id");
            tvDointencommChoosejob.setText(job_name);
        }
    }

    public void setChoose(String job_name, String job_id, String r_id, String quyuStr) {
        this.job_id = job_id;
        this.job_name = job_name;
        this.r_id = r_id;
        try {
            InputStream inputStream = context.getAssets().open("city_zh.json");
            JSONArray cityJSONArray = new JSONArray(NetUtils.readAsString(inputStream, Const.ENCODE));
//            JSONArray cityArray = FileUtils.getCityAsJSONArray(context, "city.json");
            String[] quyuString = quyuStr.split(",");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < cityJSONArray.length(); i++) {
                JSONObject city = cityJSONArray.getJSONObject(i);
                for (String quyu : quyuString) {
                    if (city.has(quyu)) {
                        sb.append(city.getString(quyu)).append(" ");
                    }
                }
            }
            tvDointencommChoosejob.setTextColor(Color.parseColor("#333333"));
            tvDointencommChoosejob.setText(job_name + "(" + sb.toString() + ")");
        } catch (Exception e) {
            // TODO: handle exception
            tvDointencommChoosejob.setTextColor(Color.parseColor("#333333"));
            tvDointencommChoosejob.setText(job_name + "( 北京市 )");
        }
    }

    @OnClick({R.id.iv_dointencomm_back, R.id.tv_dointencomm_choosejob, R.id.cb_dointencomm_check1, R.id.cb_dointencomm_check6, R.id.cb_dointencomm_check2, R.id.cb_dointencomm_check7, R.id.cb_dointencomm_check3, R.id.cb_dointencomm_check8, R.id.cb_dointencomm_check4, R.id.cb_dointencomm_check9, R.id.cb_dointencomm_check5, R.id.cb_dointencomm_check10, R.id.cb_dointencomm_checkagree, R.id.bt_dointencomm_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_dointencomm_back:
                finish();
                break;
            case R.id.tv_dointencomm_choosejob:
                if (type != null && type.equals("10")) {
                } else {
                    Intent intent = new Intent(DoIntentionCommunicationActivity.this, ChooseJobActivity.class);
                    intent.putExtra("user_id", user_id);
                    intent.putExtra("resume_id", resume_id);
                    startActivity(intent);
                }
                break;
            case R.id.cb_dointencomm_check1:
                cb1 = !cb1;
                break;
            case R.id.cb_dointencomm_check6:
                cb6 = !cb6;
                break;
            case R.id.cb_dointencomm_check2:
                cb2 = !cb2;
                break;
            case R.id.cb_dointencomm_check7:
                cb7 = !cb7;
                break;
            case R.id.cb_dointencomm_check3:
                cb3 = !cb3;
                break;
            case R.id.cb_dointencomm_check8:
                cb8 = !cb8;
                break;
            case R.id.cb_dointencomm_check4:
                cb4 = !cb4;
                break;
            case R.id.cb_dointencomm_check9:
                cb9 = !cb9;
                break;
            case R.id.cb_dointencomm_check5:
                cb5 = !cb5;
                break;
            case R.id.cb_dointencomm_check10:
                cb10 = !cb10;
                break;
            case R.id.cb_dointencomm_checkagree:
                cbAgree = !cbAgree;
                break;
            case R.id.bt_dointencomm_submit:
                if (job_id != null && !job_id.equals("")) {
                    if (!chooseOptions().equals("") && chooseOptions().toString().trim().length() > 0) {
                        sendMessage();
                    } else {
                        Toast.makeText(context, "请选择沟通内容", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "请选择职位", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * method	enterprise_intention.apply	string	是	请求动作名
     * r_id		int	否	沟通简历记录id，存在时为再次申请，未存在时为新申请
     * job_id 		string	是	职位id
     * user_id 		int	是	个人id
     * resume_id 		int	是	简历id
     * resume_language 		string	否	简历语言,默认zh
     * resume_from		string	否	1搜索简历,2推荐简历,3应聘简历
     * resume_from_id		int	否	来源简历的记录ID，推荐简历时为推荐简历记录的ID
     * linkup_options 		string	否	沟通选项id，逗号分割
     * linkup_other 		string	否	沟通其他内容
     * need_details		string	否	需要预先与企业沟通细节：1不需要（默认），2需要
     * link_man 		string	否	企业联系人
     * link_phone 		string	否	企业联系电话
     */

    private void sendMessage() {
        if (app.getNetworkMng().isCanConnect()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(HttpHelper.METHOD, HttpHelper.INTENTION_APPLY);
            params.put("r_id", r_id);
            params.put("job_id", job_id);
            params.put("user_id", user_id);
            params.put("resume_id", resume_id);
            params.put("resume_language", resume_language);
            params.put("resume_from", resume_from);
            params.put("resume_from_id", resume_from_id);
            params.put("linkup_options", chooseOptions());
            params.put("linkup_other", etDointencomm.getText().toString().trim());
            params.put("need_details", cbAgree ? "2" : "1");
            params.put("link_man", "");
            params.put("link_phone", "");
            MsgHandler.sendMessage(params, handler, IntentionApplyHandler.wIntentionApplyStart);
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    private String chooseOptions() {
        StringBuffer chooseSBF = new StringBuffer();
        if (cb1) {
            chooseSBF.append(",1");
        }
        if (cb2) {
            chooseSBF.append(",2");
        }
        if (cb3) {
            chooseSBF.append(",3");
        }
        if (cb4) {
            chooseSBF.append(",4");
        }
        if (cb5) {
            chooseSBF.append(",5");
        }
        if (cb6) {
            chooseSBF.append(",6");
        }
        if (cb7) {
            chooseSBF.append(",7");
        }
        if (cb8) {
            chooseSBF.append(",8");
        }
        if (cb9) {
            chooseSBF.append(",9");
        }
        if (cb10) {
            chooseSBF.append(",10");
        }
        if (!chooseSBF.equals("") && chooseSBF.length() > 0) {
            if (String.valueOf(chooseSBF.charAt(0)).equals(",")) {
                chooseSBF.delete(0, 1);
            }
        }
        return chooseSBF.toString().trim();
    }
}

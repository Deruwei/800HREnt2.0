package com.hr.ent.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPopupWindow;
import com.hr.ent.R;
import com.hr.ent.handler.PostJobHandler;
import com.hr.ent.view.WindowUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoreJobSettingActivity extends Activity {

    @Bind(R.id.tv_set_job_back)
    ImageView tvSetJobBack;
    @Bind(R.id.tv_moresetting_jobexp)
    TextView tvMoresettingJobexp;
    @Bind(R.id.tv_moresetting_major)
    EditText tvMoresettingMajor;
    @Bind(R.id.tv_moresetting_edu)
    TextView tvMoresettingEdu;
    @Bind(R.id.tv_moresetting_postname)
    TextView tvMoresettingPostname;
    @Bind(R.id.tv_moresetting_language)
    TextView tvMoresettingLanguage;
    @Bind(R.id.tv_moresetting_langlevel)
    TextView tvMoresettingLanglevel;
    @Bind(R.id.check_moreset_1)
    CheckBox checkMoreset1;
    @Bind(R.id.tv_moreset_1)
    TextView tvMoreset1;
    @Bind(R.id.check_moreset_2)
    CheckBox checkMoreset2;
    @Bind(R.id.tv_moreset_2)
    TextView tvMoreset2;
    @Bind(R.id.check_moreset_3)
    CheckBox checkMoreset3;
    @Bind(R.id.tv_moreset_3)
    TextView tvMoreset3;
    @Bind(R.id.check_moreset_4)
    CheckBox checkMoreset4;
    @Bind(R.id.tv_moreset_4)
    TextView tvMoreset4;
    @Bind(R.id.tv_moreset_5)
    TextView tvMoreset5;
    @Bind(R.id.check_moreset_6)
    CheckBox checkMoreset6;
    @Bind(R.id.tv_moreset_6)
    TextView tvMoreset6;
    @Bind(R.id.check_moreset_7)
    CheckBox checkMoreset7;
    @Bind(R.id.tv_moreset_7)
    TextView tvMoreset7;
    @Bind(R.id.check_moreset_8)
    CheckBox checkMoreset8;
    @Bind(R.id.tv_moreset_8)
    TextView tvMoreset8;
    @Bind(R.id.ed_morjob_emailaddress)
    EditText edMorjobEmailaddress;
    @Bind(R.id.tv_morejob_save)
    TextView tvMorejobSave;
    OptionsPopupWindow pwEdu;
    OptionsPopupWindow pwPostName;
    OptionsPopupWindow pwLangue;
    OptionsPopupWindow pwWorkYear;
    OptionsPopupWindow pwLangueLevel;
    private String inviteMajor;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_more_job_setting);
        ButterKnife.bind(this);
        initData();
    }

    private int howNum(String[] stringStr, String howStr) {
        for (int i = 0; i < stringStr.length; i++) {
            if (stringStr[i].equals(howStr)) {
                return i;
            }
        }
        return 0;
    }

    private void initData() {
//        postJobHandler = new PostJobHandler(this);

        pwEdu = new OptionsPopupWindow(this);
        inviteMajor=getIntent().getStringExtra("invite_major");
        tvMoresettingMajor.setText(inviteMajor);
        List<String> stringListEdu = Arrays.asList(getResources().getStringArray(R.array.array_degree_zh));
        ArrayList<String> stringListEdu2 = new ArrayList<>(stringListEdu);
        pwEdu.setPicker(stringListEdu2);
        pwEdu.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                PostJobActivity.postJobActivity.study1Str = getResources().getStringArray(R.array.array_degree_zh)[options1];
                PostJobActivity.postJobActivity.study1 = getResources().getStringArray(R.array.array_degree_ids)[options1];
                tvMoresettingEdu.setText( PostJobActivity.postJobActivity.study1Str);
            }
        });
        pwEdu.setSelectOptions(howNum(getResources().getStringArray(R.array.array_degree_ids), PostJobActivity.postJobActivity.study1));
        tvMoresettingEdu.setText( PostJobActivity.postJobActivity.study1Str);



        pwWorkYear = new OptionsPopupWindow(this);
        List<String> stringListWorkYear = Arrays.asList(getResources().getStringArray(R.array.array_workyear_str));
        ArrayList<String> stringListWorkYear2 = new ArrayList<>(stringListWorkYear);
        pwWorkYear.setPicker(stringListWorkYear2);
        pwWorkYear.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                PostJobActivity.postJobActivity.workyearStr = getResources().getStringArray(R.array.array_workyear_str)[options1];
                PostJobActivity.postJobActivity.workyear = getResources().getStringArray(R.array.array_workyear_ids)[options1];
                tvMoresettingJobexp.setText( PostJobActivity.postJobActivity.workyearStr);
            }
        });
        pwWorkYear.setSelectOptions(howNum(getResources().getStringArray(R.array.array_workyear_ids), PostJobActivity.postJobActivity.workyear));
        tvMoresettingJobexp.setText( PostJobActivity.postJobActivity.workyearStr);


        pwPostName = new OptionsPopupWindow(this);
        List<String> stringListPostName = Arrays.asList(getResources().getStringArray(R.array.array_zhicheng_zh));
        ArrayList<String> stringListPostName2 = new ArrayList<>(stringListPostName);
        pwPostName.setPicker(stringListPostName2);
        pwPostName.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                PostJobActivity.postJobActivity.post_rankStr = getResources().getStringArray(R.array.array_zhicheng_zh)[options1];
                PostJobActivity.postJobActivity.post_rank = getResources().getStringArray(R.array.array_zhicheng_ids)[options1];
                tvMoresettingPostname.setText( PostJobActivity.postJobActivity.post_rankStr);
            }
        });
        pwPostName.setSelectOptions(howNum(getResources().getStringArray(R.array.array_zhicheng_ids), PostJobActivity.postJobActivity.post_rank));
        tvMoresettingPostname.setText( PostJobActivity.postJobActivity.post_rankStr);

        pwLangue = new OptionsPopupWindow(this);
        List<String> stringListLangue = Arrays.asList(getResources().getStringArray(R.array.array_language_type_zh));
        ArrayList<String> stringListLangue2 = new ArrayList<>(stringListLangue);
        pwLangue.setPicker(stringListLangue2);
        pwLangue.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                PostJobActivity.postJobActivity.language1Str = getResources().getStringArray(R.array.array_language_type_zh)[options1];
                PostJobActivity.postJobActivity.language1 = getResources().getStringArray(R.array.array_language_type_ids)[options1];
                tvMoresettingLanguage.setText( PostJobActivity.postJobActivity.language1Str);
            }
        });
        pwLangue.setSelectOptions(howNum(getResources().getStringArray(R.array.array_language_type_ids), PostJobActivity.postJobActivity.language1));
        tvMoresettingLanguage.setText( PostJobActivity.postJobActivity.language1Str);

        pwLangueLevel = new OptionsPopupWindow(this);
        List<String> stringListLangueLevel = Arrays.asList(getResources().getStringArray(R.array.array_language_level_zh));
        ArrayList<String> stringListLangueLevel2 = new ArrayList<>(stringListLangueLevel);
        pwLangueLevel.setPicker(stringListLangueLevel2);
        pwLangueLevel.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                PostJobActivity.postJobActivity.level1Str = getResources().getStringArray(R.array.array_language_level_zh)[options1];
                PostJobActivity.postJobActivity.level1 = getResources().getStringArray(R.array.array_language_level_ids)[options1];
                tvMoresettingLanglevel.setText( PostJobActivity.postJobActivity.level1Str);
            }
        });
        pwLangueLevel.setSelectOptions(howNum(getResources().getStringArray(R.array.array_language_level_ids), PostJobActivity.postJobActivity.level1));
        tvMoresettingLanglevel.setText( PostJobActivity.postJobActivity.level1Str);

        tvMoreset1.setText("联系人：" + PostJobActivity.postJobActivity.returnDataBean.getLinkman());
        tvMoreset2.setText("地址：" + PostJobActivity.postJobActivity.returnDataBean.getAddress() + " 邮编：" + PostJobActivity.postJobActivity.returnDataBean.getZipcode());
        tvMoreset3.setText("电话：" + PostJobActivity.postJobActivity.returnDataBean.getPhone());
        tvMoreset4.setText("传真：" + PostJobActivity.postJobActivity.returnDataBean.getFax());
        tvMoreset5.setText("电子邮箱：" + PostJobActivity.postJobActivity.returnDataBean.getEmail());

        tvMoreset6.setText("使用招聘平台接收应聘简历。");
        tvMoreset7.setText("使用" + PostJobActivity.postJobActivity.returnDataBean.getEmail() + "接收。");
        tvMoreset8.setText("同时使用以下邮箱接收。（最多两个邮箱地址，用英文逗号分隔）");


        if (PostJobActivity.postJobActivity.is_show_linkman.equals("1")) {
            checkMoreset1.setChecked(true);
        } else {
            checkMoreset1.setChecked(false);
        }
        checkMoreset1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PostJobActivity.postJobActivity.is_show_linkman = "1";
                } else {
                    PostJobActivity.postJobActivity.is_show_linkman = "0";
                }
            }
        });
        if (PostJobActivity.postJobActivity.is_show_address.equals("1")) {
            checkMoreset2.setChecked(true);
        } else {
            checkMoreset2.setChecked(false);
        }
        checkMoreset2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PostJobActivity.postJobActivity.is_show_address = "1";
                } else {
                    PostJobActivity.postJobActivity.is_show_address = "0";
                }
            }
        });
        if (PostJobActivity.postJobActivity.is_show_phone.equals("1")) {
            checkMoreset3.setChecked(true);
        } else {
            checkMoreset3.setChecked(false);
        }
        checkMoreset3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PostJobActivity.postJobActivity.is_show_phone = "1";
                } else {
                    PostJobActivity.postJobActivity.is_show_phone = "0";
                }
            }
        });

        if (PostJobActivity.postJobActivity.is_show_fax.equals("1")) {
            checkMoreset4.setChecked(true);
        } else {
            checkMoreset4.setChecked(false);
        }
        checkMoreset4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PostJobActivity.postJobActivity.is_show_fax = "1";
                } else {
                    PostJobActivity.postJobActivity.is_show_fax = "0";
                }
            }
        });

        checkMoreset6.setChecked(true);
        checkMoreset6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                } else {
                }
            }
        });
        if (PostJobActivity.postJobActivity.is_send_mail.equals("1")) {
            checkMoreset7.setChecked(true);
        } else {
            checkMoreset7.setChecked(false);
        }
        checkMoreset7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PostJobActivity.postJobActivity.is_send_mail = "1";
                } else {
                    PostJobActivity.postJobActivity.is_send_mail = "0";
                }
            }
        });
        if (PostJobActivity.postJobActivity.is_send_mail2.equals("1")) {
            checkMoreset8.setChecked(true);
        } else {
            checkMoreset8.setChecked(false);
        }
        checkMoreset8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PostJobActivity.postJobActivity.is_send_mail2 = "0";
                } else {
                    PostJobActivity.postJobActivity.is_send_mail2 = "0";
                }
            }
        });
    }

    @OnClick({R.id.tv_set_job_back, R.id.tv_moresetting_jobexp, R.id.tv_moresetting_major, R.id.tv_moresetting_edu, R.id.tv_moresetting_postname, R.id.tv_moresetting_language, R.id.tv_moresetting_langlevel, R.id.check_moreset_1, R.id.check_moreset_2, R.id.check_moreset_3, R.id.check_moreset_4, R.id.check_moreset_6, R.id.check_moreset_7, R.id.check_moreset_8, R.id.tv_morejob_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_set_job_back:
                break;
            case R.id.tv_moresetting_jobexp:
                pwWorkYear.showAtLocation(tvMoresettingJobexp, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.tv_moresetting_major:
                break;
            case R.id.tv_moresetting_edu:
                pwEdu.showAtLocation(tvMoresettingEdu, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.tv_moresetting_postname:
                pwPostName.showAtLocation(tvMoresettingPostname, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.tv_moresetting_language:
                pwLangue.showAtLocation(tvMoresettingLanguage, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.tv_moresetting_langlevel:
                pwLangueLevel.showAtLocation(tvMoresettingLanglevel, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.check_moreset_1:
                break;
            case R.id.check_moreset_2:
                break;
            case R.id.check_moreset_3:
                break;
            case R.id.check_moreset_4:
                break;
            case R.id.check_moreset_6:
                break;
            case R.id.check_moreset_7:
                break;
            case R.id.check_moreset_8:
                break;
            case R.id.tv_morejob_save:
                if (checkMoreset8.isChecked() && edMorjobEmailaddress.getText().length() == 0) {
                    Toast.makeText(this, "请输入邮箱地址!", Toast.LENGTH_SHORT).show();
                } else {
                    PostJobActivity.postJobActivity.email2 = edMorjobEmailaddress.getText().toString().trim();
                    PostJobActivity.postJobActivity.invite_major = tvMoresettingMajor.getText().toString().trim();
                    finish();
                }
                break;
        }
    }
}

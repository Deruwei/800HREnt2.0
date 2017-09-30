package com.hr.ent.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPopupWindow;
import com.hr.ent.R;
import com.hr.ent.handler.CompanyRegisterHandler;
import com.hr.ent.utils.CodeUtils;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.view.WindowUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 企业注册页面
 */
public class CompanyRegisterActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.rl_comregistr_save)
    RelativeLayout rlComregistrSave;
    @Bind(R.id.iv_registcom_check)
    ImageView ivRegistcomCheck;
    @Bind(R.id.iv_comregister_back)
    ImageView ivComregisterBack;
    @Bind(R.id.tv_registcom_agreement)
    TextView tvRegistcomAgreement;
    @Bind(R.id.tv_comregister_phoneregister)
    TextView tvComregisterPhoneregister;
    @Bind(R.id.tv_company_register_phone)
    TextView tvCompanyRegisterPhone;
    @Bind(R.id.tv_comregister_industry)
    TextView tvComregisterIndustry;
    @Bind(R.id.et_comregister_comname)
    EditText etComregisterComname;
    @Bind(R.id.et_comregister_comphone)
    EditText etComregisterComphone;
    @Bind(R.id.et_comregister_comcontacts)
    EditText etComregisterComcontacts;
    @Bind(R.id.et_comregister_comnemail)
    EditText etComregisterComnemail;
    @Bind(R.id.et_comregister_username)
    EditText etComregisterUsername;
    @Bind(R.id.et_comregister_password)
    EditText etComregisterPassword;
    @Bind(R.id.et_comregister_password2)
    EditText etComregisterPassword2;
    @Bind(R.id.tv_comregister_howknow)
    TextView tvComregisterHowknow;
    @Bind(R.id.iv_comregister_code)
    ImageView ivComregisterCode;
    @Bind(R.id.et_comregister_code)
    EditText etComregisterCode;

    private String emailString, usernameString, password0String, password1String, companyNameString, comPhoneString, comContactsString, how_to_know, getCode;
    private int industryID;
    private boolean isCheck = true;
    public static CompanyRegisterActivity companyRegisterActivity = null;
    private CompanyRegisterHandler companyRegisterHandler;
    private OptionsPopupWindow pwOptions;
    private ArrayList<String> howkonwList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_company_regist);
        ButterKnife.bind(this);
        companyRegisterActivity = CompanyRegisterActivity.this;
        initData();

    }

    public void initData() {
        companyRegisterHandler = new CompanyRegisterHandler(this);
        industryID = Integer.parseInt(app.getIndustry());
        switch (industryID) {
            case 11:   //建筑
                tvCompanyRegisterPhone.setText("010-82197168");
                break;
            case 12:   //金融
                tvCompanyRegisterPhone.setText("010-82197466");
                break;
            case 14:   //医药
                tvCompanyRegisterPhone.setText("010-82197575");
                break;
            case 26:   //服装
                tvCompanyRegisterPhone.setText("010-62123388");
                break;
            case 29:   //化工
                tvCompanyRegisterPhone.setText("010-82197555");
                break;
            case 22:   //制造
                tvCompanyRegisterPhone.setText("010-82197338");
                break;
            default:
                tvCompanyRegisterPhone.setText("010-62123388");
                break;
        }
        tvComregisterIndustry.setText(app.getIndustryName() + "行业");

        howkonwList = new ArrayList<>();
        howkonwList.add("销售顾问推荐");
        howkonwList.add("同事朋友介绍");
        howkonwList.add("网络渠道");
        howkonwList.add("其他渠道");
        pwOptions = new OptionsPopupWindow(this);
        pwOptions.setPicker(howkonwList);

        pwOptions.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                tvComregisterHowknow.setText(howkonwList.get(options1));
                how_to_know = options1 + 1 + "";
            }
        });
        pwOptions.setSelectOptions(0);


        ivComregisterCode.setImageBitmap(CodeUtils.getInstance().getBitmap());
        getCode = CodeUtils.getInstance().getCode(); // 获取显示的验证码

    }

    @OnClick({R.id.rl_comregistr_save,R.id.iv_comregister_code, R.id.iv_registcom_check, R.id.tv_comregister_howknow, R.id.tv_registcom_agreement, R.id.tv_comregister_industry, R.id.iv_comregister_back, R.id.tv_comregister_phoneregister})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_comregistr_save:
                saveData();
                break;
            case R.id.iv_registcom_check:
                if (isCheck) {
                    ivRegistcomCheck.setImageResource(R.mipmap.hui);
                    isCheck = false;
                } else {
                    ivRegistcomCheck.setImageResource(R.mipmap.lv);
                    isCheck = true;
                }
                break;
            case R.id.tv_registcom_agreement:
                Intent intentUserAgreement = new Intent(this, UserAgreementActivity.class);
                intentUserAgreement.putExtra("userOrCom", "2");
                startActivity(intentUserAgreement);
                break;
            case R.id.tv_comregister_industry:
                Intent intentIndustry = new Intent(this, ChooseIndustriesActivity.class);
                startActivity(intentIndustry);
                break;
            case R.id.iv_comregister_back:
                finish();
                break;
            case R.id.tv_comregister_howknow:
                pwOptions.showAtLocation(tvComregisterHowknow, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.tv_comregister_phoneregister:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("http://m.800hr.com/ent/register/phonereg.php");
                intent.setData(content_url);
                startActivity(intent);
                break;
            case R.id.iv_comregister_code:
                ivComregisterCode.setImageBitmap(CodeUtils.getInstance().getBitmap());
                getCode = CodeUtils.getInstance().getCode();
                break;
        }
    }

    private void saveData() {
        emailString = etComregisterComnemail.getText().toString().trim();
        usernameString = etComregisterUsername.getText().toString().trim();
        password0String = etComregisterPassword.getText().toString().trim();
        password1String = etComregisterPassword2.getText().toString().trim();
        companyNameString = etComregisterComname.getText().toString().trim();
        comPhoneString = etComregisterComphone.getText().toString().trim();
        comContactsString = etComregisterComcontacts.getText().toString().trim();
        if (!checkData()) {
            return;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(HttpHelper.METHOD, HttpHelper.COMPANY_REGISTER);
        params.put("site_code", industryID);
        params.put("user_name", usernameString);
        params.put("password", password0String);
        params.put("passwordre", password1String);
        params.put("enterprise_name", companyNameString);
        params.put("linkman", comContactsString);
        params.put("phone", comPhoneString);
        params.put("email", emailString);
        params.put("is_login", "0");
        params.put("how_to_know", how_to_know);
        MsgHandler.sendMessage(params, companyRegisterHandler, CompanyRegisterHandler.wLoginStart);
    }
    /**
     * 数据验证
     */
    private boolean checkData() {
        // ---验证 邮箱
        String check = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(emailString);
        //验证固话
        String check2 = "^\\d{3,4}-\\d{7,8}$";
        Pattern regex2 = Pattern.compile(check2);
        Matcher matcher2 = regex2.matcher(comPhoneString);
        boolean isMatched = matcher.matches();
        boolean isMatched2 = matcher2.matches();
        if (companyNameString.length() == 0) {
            Toast.makeText(this, getString(R.string.comname_null),
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (comPhoneString.length() == 0) {
            Toast.makeText(this, getString(R.string.comphone_null),
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (!isMatched2) {
            Toast.makeText(this, getString(R.string.comphone_mistake), Toast.LENGTH_LONG).show();
            return false;
        }
        if (comContactsString.length() == 0) {
            Toast.makeText(this, getString(R.string.comcontacts_null), Toast.LENGTH_LONG).show();
            return false;
        }
        if (emailString.length() == 0) {
            Toast.makeText(this, "请输入电子邮箱", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!isMatched) {
            Toast.makeText(this, "电子邮箱地址格式错误", Toast.LENGTH_LONG).show();
            return false;
        }
        // ---验证用户名
        if (usernameString.length() == 0) {
            Toast.makeText(this, getString(R.string.username_null), Toast.LENGTH_LONG).show();
            return false;
        }
        if (usernameString.length() < 4 || usernameString.length() > 25) {
            Toast.makeText(this, getString(R.string.username_length_error), Toast.LENGTH_LONG).show();
            return false;
        }

        Pattern pattern1 = Pattern.compile("^[_.@a-zA-Z0-9]+$");
        Matcher matcher1 = pattern1.matcher(usernameString);
        if (!matcher1.find()) {
            Toast.makeText(this, getString(R.string.username_error),
                    Toast.LENGTH_LONG).show();
            return false;
        }
        // ---验证密码
        if (password0String.length() == 0) {
            Toast.makeText(this, getString(R.string.password_null), Toast.LENGTH_LONG).show();
            return false;
        }
        if (password0String.length() < 6) {
            Toast.makeText(this, getString(R.string.password_length_error),
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (!password0String.equals(password1String)) {
            Toast.makeText(this,
                    getString(R.string.password_pswconfirm_not_equal),
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (password0String.equals(usernameString)) {
            Toast.makeText(this, getString(R.string.password_username_equal),
                    Toast.LENGTH_LONG).show();
            return false;
        }
        Pattern patternPwd = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,25}$");
        Matcher matcherPwd = patternPwd.matcher(password1String);
        if (!matcherPwd.find()) {
            Toast.makeText(this, getString(R.string.password_error), Toast.LENGTH_LONG).show();
            return false;
        }

        if (tvComregisterHowknow.getText().toString().trim().length() == 0) {
            Toast.makeText(CompanyRegisterActivity.this, "请选择得知途径", Toast.LENGTH_SHORT).show();
            return false;
        }

        String v_code = etComregisterCode.getText().toString().trim();
        if (v_code == null || v_code.equals("")) {
            Toast.makeText(CompanyRegisterActivity.this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!v_code.equalsIgnoreCase(getCode)) {
            Toast.makeText(CompanyRegisterActivity.this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isCheck) {
            Toast.makeText(this, "您未同意注册协议", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}

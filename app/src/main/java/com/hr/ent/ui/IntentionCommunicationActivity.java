package com.hr.ent.ui;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;
import com.hr.ent.adapter.IntentionCommAdapter;
import com.hr.ent.handler.IntentionListHandler;
import com.hr.ent.handler.InviteListHandler;
import com.hr.ent.utils.Const;
import com.hr.ent.utils.HttpHelper;
import com.hr.ent.utils.MsgHandler;
import com.hr.ent.view.LoadMoreListView;
import com.hr.ent.view.WindowUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Home意向沟通
 */
public class IntentionCommunicationActivity extends BaseActivity implements LoadMoreListView.OnLoadMoreListener {

    @Bind(R.id.tv_intention_back)
    ImageView tvIntentionBack;
    @Bind(R.id.tv_intention_num)
    TextView tvIntentionNum;
    @Bind(R.id.tv_intention_filtrate)
    TextView tvIntentionFiltrate;
    @Bind(R.id.lv_intention)
    LoadMoreListView lvIntention;
    @Bind(R.id.rl_intention_comm)
    RelativeLayout rlIntentionComm;
    private IntentionCommAdapter intentionCommAdapter;
    private IntentionListHandler handler;
    private View viewSelectSalary;
    private PopupWindow popwFiltrate;
    private String apply_state = "";
    private String intention_state = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtils.initWindow(this);
        setContentView(R.layout.activity_intention_communication);
        ButterKnife.bind(this);
        initData();
    }


    private void initData() {
        intentionCommAdapter = new IntentionCommAdapter(this);
        handler = new IntentionListHandler(this, intentionCommAdapter, lvIntention);
        lvIntention.setAdapter(intentionCommAdapter);
        lvIntention.setOnLoadMoreListener(this);
        lvIntention.setEmptyView(findViewById(R.id.tv_intention_empty));
        initContractDataByNet();
    }

    public void initContractDataByNet() {
        if (app.getNetworkMng().isCanConnect()) {
            intentionCommAdapter.setCurrentPage(1);
            Map<String, Object> params = initParams();
            intentionCommAdapter.setCurrentPage(1);
            params.put("page", "1");
            MsgHandler.sendMessage(params, handler, IntentionListHandler.wGetIntentionListStart);
        } else {
            Toast.makeText(this, R.string.nonet, Toast.LENGTH_SHORT).show();
        }
    }

    private Map<String, Object> initParams() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(HttpHelper.METHOD, HttpHelper.INTENTIONLIST);
        params.put("job_name", "");
        params.put("user_name", "");
        params.put("resume_number", "");
        params.put("last_position", "");
        params.put("last_company", "");
        params.put("resume_from", "");
        params.put("apply_state", apply_state);
        params.put("intention_state", intention_state);
        return params;
    }

    /**
     * 筛选
     */
    private void popFiltrate() {
        viewSelectSalary = LayoutInflater.from(this).inflate(R.layout.item_popfilta, null);
        WindowManager manager = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        int height = manager.getDefaultDisplay().getHeight();
        popwFiltrate = new PopupWindow(this);
        popwFiltrate.setContentView(viewSelectSalary);
        popwFiltrate.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popwFiltrate.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popwFiltrate.setTouchable(true);
        popwFiltrate.setBackgroundDrawable(new BitmapDrawable());
//        popwFiltrate.showAtLocation(viewSelectSalary, Gravity.TOP, 0, 0);
        popwFiltrate.showAsDropDown(rlIntentionComm);
        final ImageView iv_popfilter_0 = (ImageView) viewSelectSalary.findViewById(R.id.iv_popfilter_0);
        final ImageView iv_popfilter_1 = (ImageView) viewSelectSalary.findViewById(R.id.iv_popfilter_1);
        final ImageView iv_popfilter_2 = (ImageView) viewSelectSalary.findViewById(R.id.iv_popfilter_2);
        final ImageView iv_popfilter_3 = (ImageView) viewSelectSalary.findViewById(R.id.iv_popfilter_3);
        final ImageView iv_popfilter_4 = (ImageView) viewSelectSalary.findViewById(R.id.iv_popfilter_4);
        final ImageView iv_popfilter_5 = (ImageView) viewSelectSalary.findViewById(R.id.iv_popfilter_5);

        viewSelectSalary.findViewById(R.id.linear_popfilter_0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apply_state = "4,5";
                intention_state = "";
                initContractDataByNet();
                popwFiltrate.dismiss();
            }
        });
        viewSelectSalary.findViewById(R.id.linear_popfilter_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intention_state = "1";
                apply_state = "";
                initContractDataByNet();
                popwFiltrate.dismiss();

            }
        });
        viewSelectSalary.findViewById(R.id.linear_popfilter_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intention_state = "2";
                apply_state = "";
                initContractDataByNet();
                popwFiltrate.dismiss();
            }
        });
        viewSelectSalary.findViewById(R.id.linear_popfilter_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intention_state = "3";
                apply_state = "";
                initContractDataByNet();
                popwFiltrate.dismiss();
            }
        });
        viewSelectSalary.findViewById(R.id.linear_popfilter_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intention_state = "4";
                apply_state = "";
                initContractDataByNet();
                popwFiltrate.dismiss();
            }
        });
        viewSelectSalary.findViewById(R.id.linear_popfilter_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intention_state = "5";
                apply_state = "";
                initContractDataByNet();
                popwFiltrate.dismiss();
            }
        });
    }

    @OnClick({R.id.tv_intention_back, R.id.tv_intention_filtrate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_intention_back:
                finish();
                break;
            case R.id.tv_intention_filtrate:
                if (popwFiltrate == null) {
                    popFiltrate();
                } else if (popwFiltrate.isShowing()) {
                    popwFiltrate.dismiss();
                } else {
                    popFiltrate();
                }
                break;
        }
    }

    @Override
    public void onLoadMore() {
//        if (intentionCommAdapter.getCurrentPage() < intentionCommAdapter.getTotalPage()) {
        if (intentionCommAdapter.getCurrentPage() < 1000) {
//            Toast.makeText(context, "到底了", Toast.LENGTH_SHORT).show();
            Map<String, Object> params = initParams();
            intentionCommAdapter.setCurrentPage(intentionCommAdapter.getCurrentPage() + 1);
            params.put("page", intentionCommAdapter.getCurrentPage() + "");
            MsgHandler.sendMessage(params, handler, IntentionListHandler.wGetMoreIntentionListStart);
        } else {
            lvIntention.onLoadMoreComplete();
        }
    }
}

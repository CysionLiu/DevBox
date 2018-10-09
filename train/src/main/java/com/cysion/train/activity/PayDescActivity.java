package com.cysion.train.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.listener.PureListener;
import com.cysion.baselib.ui.TopBar;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.train.R;
import com.cysion.train.entity.ConfigBean;
import com.cysion.train.logic.ConfigLogic;
import com.cysion.train.view.MyToast;

import butterknife.BindView;

public class PayDescActivity extends BaseActivity {

    @BindView(R.id.bar_expert)
    TopBar mBarExpert;
    @BindView(R.id.tv_head)
    TextView mTvHead;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_account)
    TextView mTvAccount;
    @BindView(R.id.tv_copy_num)
    TextView mTvCopyNum;
    @BindView(R.id.tv_account_name)
    TextView mTvAccountName;
    @BindView(R.id.tv_copy_name)
    TextView mTvCopyName;
    @BindView(R.id.tv_account_bank)
    TextView mTvAccountBank;
    @BindView(R.id.tv_remark)
    TextView mTvRemark;
    @BindView(R.id.tv_explain)
    TextView mTvExplain;
    @BindView(R.id.tv_reminder)
    TextView mTvReminder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_explain;
    }

    @Override
    protected void initView() {
        ShowUtil.whiteStatusBar(this, true);
        mTvCopyNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager c = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData myClip = ClipData.newPlainText("text", mTvAccount.getText().toString().trim());
                c.setPrimaryClip(myClip);
                MyToast.quickShow("已复制到剪贴板");
            }
        });
        mTvCopyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager c = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData myClip = ClipData.newPlainText("text", mTvAccountName.getText().toString().trim());
                c.setPrimaryClip(myClip);
                MyToast.quickShow("已复制到剪贴板");
            }
        });
        mBarExpert.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onIconClicked(View aView, TopBar.Pos aPosition) {
                if (aPosition == TopBar.Pos.LEFT) {
                    finish();
                }
            }
        });
        mBarExpert.setTitle("付款说明");
        fillView();
    }

    private void fillView() {
        ConfigBean bean = ConfigLogic.obj().sConfigBean;
        if (bean==null) {
            return;
        }
        mTvAccount.setText(bean.getAccount_num());
        mTvAccountBank.setText(bean.getAccount());
        mTvAccountName.setText(bean.getAccount_name());
        mTvRemark.setText(bean.getRemark());
        if (!TextUtils.isEmpty(bean.getExplain())) {
            mTvExplain.setText(Html.fromHtml(bean.getExplain()));
        }
        if (!TextUtils.isEmpty(bean.getReminder())) {
            mTvReminder.setText(Html.fromHtml(bean.getReminder()));
        }
    }

    @Override
    protected void initData() {
        super.initData();
        ConfigLogic.obj().getConfig(new PureListener<String>() {
            @Override
            public void done(String result) {
                fillView();
            }

            @Override
            public void dont(int flag, String msg) {

            }
        });
    }
}

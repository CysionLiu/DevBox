package com.cysion.train.activity;

import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cysion.baselib.Box;
import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.listener.PureListener;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.train.PageConstant;
import com.cysion.train.R;
import com.cysion.train.logic.UserLogic;
import com.cysion.train.view.MyToast;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.tv_phone_fix)
    TextView mTvPhoneFix;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.tv_sms_fix)
    TextView mTvSmsFix;
    @BindView(R.id.et_smscode)
    EditText mEtSmscode;
    @BindView(R.id.btn_get_code)
    Button mBtnGetCode;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        ShowUtil.darkAndWhite(this,true);
        mBtnLogin.setOnClickListener(this);
        mBtnGetCode.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.btn_get_code:
                String trim = invalidateMobile();
                if (trim == null) return;
                getSmsCode(trim);
                break;
            case R.id.btn_login:
                String mobile = invalidateMobile();
                if (mobile == null) return;
                String code = mEtSmscode.getText().toString().trim();
                if (TextUtils.isEmpty(code)) {
                    new MyToast.Builder().text("验证码为空").buildToShow();
                    return;
                }
                toLogin(mobile, code);
                break;
            default:

                break;
        }
    }

    private void toLogin(String aMobile, String aCode) {
        UserLogic.obj().login(aMobile, aCode, new PureListener<String>() {
            @Override
            public void done(String result) {
                new MyToast.Builder().text("登录成功").buildToShow();
                setResult(PageConstant.RESULT_OK);
                finish();
            }

            @Override
            public void dont(int flag, String msg) {
                new MyToast.Builder().text(msg).buildToShow();
            }
        });
    }

    @Nullable
    private String invalidateMobile() {
        String trim = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            new MyToast.Builder()
                    .text("手机号为空")
                    .buildToShow();
            return null;
        }
        if (trim.length() != 11) {
            new MyToast.Builder().gravity(Gravity.CENTER).text("手机号格式不对").buildToShow();
            return null;
        }
        return trim;
    }

    private void getSmsCode(String aTrim) {
        final MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60000, 1000);
        myCountDownTimer.start();
        Toast.makeText(LoginActivity.this, "功能未开放", Toast.LENGTH_SHORT).show();

    }

    //复写倒计时
    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击
            if (mBtnGetCode != null) {
                mBtnGetCode.setClickable(false);
                mBtnGetCode.setText(l / 1000 + "s后重试");
                mBtnGetCode.setTextColor(Box.color(R.color.main_background));
                mBtnGetCode.setBackgroundColor(Box.color(R.color.min_background));
            }
        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            if (mBtnGetCode != null) {
                //重新给Button设置文字
                mBtnGetCode.setText("重新获取验证码");
                mBtnGetCode.setClickable(true);
                //设置可点击
                mBtnGetCode.setTextColor(Box.color(R.color.main_background));
                mBtnGetCode.setBackgroundColor(Box.color(R.color.colorPrimary));
            }
        }
    }
}

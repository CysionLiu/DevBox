package com.cysion.train.activity;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.RegexUtils;
import com.bumptech.glide.Glide;
import com.cysion.baselib.Box;
import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.image.GlideCircleTransform;
import com.cysion.baselib.listener.Action;
import com.cysion.baselib.listener.PureListener;
import com.cysion.baselib.ui.TopBar;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.train.Constant;
import com.cysion.train.R;
import com.cysion.train.entity.ClientEntity;
import com.cysion.train.entity.TradeEntity;
import com.cysion.train.entity.UserEntity;
import com.cysion.train.logic.UserCache;
import com.cysion.train.logic.UserLogic;
import com.cysion.train.simple.SimpleInstaller;
import com.cysion.train.utils.Alert;
import com.cysion.train.utils.FileUpUtil;
import com.cysion.train.view.MyToast;
import com.yuyh.library.imgsel.ISNav;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class PersonActivity extends BaseActivity implements View.OnClickListener {

    public static final int TOCHOSEIMG = 63;
    @BindView(R.id.bar_train)
    TopBar mBarTrain;
    @BindView(R.id.iv_user_avatar)
    ImageView mIvUserAvatar;
    @BindView(R.id.et_nickname)
    EditText mEtNickname;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.et_contactor)
    EditText mEtContactor;
    @BindView(R.id.et_contact_phone)
    EditText mEtContactPhone;
    @BindView(R.id.tv_fix_taitou)
    TextView mTvFixTaitou;
    @BindView(R.id.tv_company)
    TextView mTvCompany;
    @BindView(R.id.tv_trade)
    TextView mTvTrade;
    @BindView(R.id.tv_not_company)
    TextView mTvNotCompany;
    @BindView(R.id.et_taitou_fapiao)
    EditText mEtTaitouFapiao;
    @BindView(R.id.et_suihao)
    EditText mEtSuihao;
    @BindView(R.id.rl_suihao_box)
    RelativeLayout mRlSuihaoBox;
    @BindView(R.id.rl_head_box)
    RelativeLayout mRlHeadBox;
    @BindView(R.id.tv_to_logout)
    TextView mTvToLogout;

    private String mAvatarUrl = "";
    private String mTradeId = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_info;
    }

    @Override
    protected void initView() {
        ShowUtil.darkAndWhite(this, true);
        mBarTrain.setTitle("个人资料");
        mBarTrain.imageRight(false);
        mBarTrain.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onIconClicked(View aView, TopBar.Pos aPosition) {
                if (aPosition == TopBar.Pos.LEFT) {
                    PersonActivity.this.finish();
                } else if (aPosition == TopBar.Pos.RIGHT) {
                    uploadAvatar();
                }
            }
        });
        mRlHeadBox.setOnClickListener(this);

        mTvNotCompany.setOnClickListener(this);
        mTvCompany.setOnClickListener(this);
        mTvTrade.setOnClickListener(this);
        mTvToLogout.setOnClickListener(this);
        findViewById(R.id.rl_trade_box).setOnClickListener(this);
        showClientInfo();
        showUserInfo();
        updateUserInfo();
    }

    private void uploadAvatar() {
        Map<String, String> pa = new HashMap<>();
        pa.put("key", "1");
        Alert.obj().loading(self);
        if (TextUtils.isEmpty(mAvatarUrl) || mAvatarUrl.startsWith("http")) {
            saveUserInfo();
            return;
        }
        FileUpUtil.obj().postFile(Constant.SAVE_PIC,
                pa, new File(mAvatarUrl), new PureListener<String>() {
                    @Override
                    public void done(String result) {
                        mAvatarUrl = result;
                        saveUserInfo();
                    }

                    @Override
                    public void dont(int flag, String msg) {
                        Alert.obj().loaded();
                    }
                });
    }

    private void showUserInfo() {
        UserEntity userEntity = UserCache.obj().getUserEntity();
        if (userEntity != null) {
            if (!TextUtils.isEmpty(userEntity.getPic())) {
                Glide.with(this).load(userEntity.getPic())
                        .placeholder(R.drawable.user_avatar_default)
                        .transform(new GlideCircleTransform(this))
                        .into(mIvUserAvatar);
            }
            mEtNickname.setText(userEntity.getName());
            mTvPhone.setText(userEntity.getMobile());
        }
    }

    private void showClientInfo() {
        ClientEntity clientEntity = UserCache.obj().getClientEntity();
        if (clientEntity != null) {
            mEtContactor.setText(clientEntity.getName());
            mEtContactPhone.setText(clientEntity.getPhone());
            if ("1".equals(clientEntity.getBill())) {
                isCompany();
            } else {
                notCompany();
            }
            mEtTaitouFapiao.setText(clientEntity.getBill_name());
            mEtSuihao.setText(clientEntity.getBill_num());
            mTradeId = clientEntity.getTrade_id();
            List<TradeEntity> tradeEntities = UserCache.obj().getTradeEntities();
            if (tradeEntities != null && !TextUtils.isEmpty(mTradeId)) {
                for (TradeEntity tradeEntity : tradeEntities) {
                    if (mTradeId.equals(tradeEntity.getId())) {
                        mTvTrade.setText(tradeEntity.getName());
                    }
                }
            }
        }
    }

    private void updateUserInfo() {
        UserLogic.obj().getUserInfo(PureListener.DEFAULT);
        UserLogic.obj().getClientInfo(PureListener.DEFAULT);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.tv_company:
                if (!mTvCompany.isSelected()) {
                    isCompany();
                }
                break;
            case R.id.tv_not_company:
                if (!mTvNotCompany.isSelected()) {
                    notCompany();
                }
                break;
            case R.id.rl_head_box:
                ISNav.getInstance().toListActivity(this, SimpleInstaller.obj().initSingleSelect(), TOCHOSEIMG);
                break;
            case R.id.tv_trade:
            case R.id.rl_trade_box:
                onTradeClicked();
                break;
            case R.id.tv_to_logout:
                onLogoutClicked();
                break;
            default:

                break;
        }
    }

    private void onLogoutClicked() {
        Alert.obj().logoutDialog(self, new Action<String>() {
            @Override
            public void done(String aS) {
                UserCache.obj().clearCache();
                finish();
            }
        });
    }

    OptionsPickerView mTradePickView;

    private void onTradeClicked() {
        if (mTradePickView == null) {
            final List<TradeEntity> tradeEntities = UserCache.obj().getTradeEntities();
            mTradePickView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    if (tradeEntities.size() < 1) {
                        return;
                    }
                    mTradeId = tradeEntities.get(options1).getId();
                    mTvTrade.setText(tradeEntities.get(options1).getName());
                }
            }).setContentTextSize(18).setCancelColor(Color.GRAY).setSubmitColor(Box.color(R.color.main_tag)).build();
            mTradePickView.setPicker(tradeEntities);
        }
        mTradePickView.show();
    }

    private void notCompany() {
        mTvCompany.setSelected(false);
        mTvNotCompany.setSelected(true);
        mRlSuihaoBox.setVisibility(View.GONE);
    }

    private void isCompany() {
        mTvCompany.setSelected(true);
        mTvNotCompany.setSelected(false);
        mRlSuihaoBox.setVisibility(View.VISIBLE);
    }


    private void saveUserInfo() {
        String nickname = mEtNickname.getText().toString().trim();
        UserLogic.obj().updateUserInfo(nickname, mAvatarUrl, "1", new PureListener<String>() {
            @Override
            public void done(String result) {
                saveClientInfo();
                Alert.obj().loaded();
            }

            @Override
            public void dont(int flag, String msg) {
                MyToast.quickShow(msg);
                Alert.obj().loaded();
            }
        });

    }

    private void saveClientInfo() {
        String name = mEtContactor.getText().toString().trim();
        String cphone = mEtContactPhone.getText().toString().trim();
        String taitou = mEtTaitouFapiao.getText().toString().trim();
        String shuihao = mEtSuihao.getText().toString().trim();
        String bill = "1";
        if (!RegexUtils.isMobileExact(cphone)) {
            MyToast.quickShow(getString(R.string.str_error_phone));
            return;
        }
        if (!mTvCompany.isSelected()) {
            bill = "2";
        }
        UserLogic.obj().updateClientInfo(name, cphone, bill, taitou, shuihao, mTradeId, new PureListener<String>() {
            @Override
            public void done(String result) {
                MyToast.quickShow("保存成功");
                updateUserInfo();
            }

            @Override
            public void dont(int flag, String msg) {
                MyToast.quickShow(msg);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 图片选择结果回调
        if (requestCode == TOCHOSEIMG && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra("result");
            if (pathList != null && pathList.size() > 0) {
                mAvatarUrl = pathList.get(0);
                Glide.with(this).load(mAvatarUrl)
                        .placeholder(R.drawable.user_avatar_default)
                        .transform(new GlideCircleTransform(this))
                        .into(mIvUserAvatar);
            } else {
                MyToast.quickShow("未选取可用照片");
            }

        }
    }
}

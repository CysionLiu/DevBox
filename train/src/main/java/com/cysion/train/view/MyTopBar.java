package com.cysion.train.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cysion.train.R;


/**
 * Created by xianshang.liu on 2017/5/17.
 */

public class MyTopBar extends RelativeLayout {

    private View mRootView;
    private TextView mTxtTitle;
    private TextView mTvRight;
    private TextView mTvLeft;
    private OnTopBarClickListener mOnTopBarClickListener = new OnTopBarClickListener() {
        @Override
        public void onIconClicked(View aView, Pos aPosition) {
            try {
                throw new Exception("should invoke set method");
            } catch (Exception aE) {
                aE.printStackTrace();
            }
        }
    };
    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int viewId = v.getId();
            if (viewId == R.id.img_left_topbar) {
                mOnTopBarClickListener.onIconClicked(v, Pos.LEFT);
            } else if (viewId == R.id.txt_title_topbar) {
                mOnTopBarClickListener.onIconClicked(v, Pos.MIDDLE);
            } else if (viewId == R.id.img_right_topbar) {
                mOnTopBarClickListener.onIconClicked(v, Pos.RIGHT);
            }
        }

    };


    public enum Pos {
        LEFT, RIGHT, MIDDLE
    }

    public interface OnTopBarClickListener {
        void onIconClicked(View aView, Pos aPosition);
    }

    public MyTopBar(Context context) {
        super(context);
    }


    public MyTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init(Context aContext) {
        LayoutInflater inflater = (LayoutInflater) aContext.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mRootView = inflater.inflate(R.layout.meeting_list_top_bar, this);
        mTvLeft = mRootView.findViewById(R.id.txt_title_left);
        mTvRight = mRootView.findViewById(R.id.txt_title_right);
        mTxtTitle = (TextView) mRootView.findViewById(R.id.txt_title_topbar);
        mTvLeft.setOnClickListener(mOnClickListener);
        mTxtTitle.setOnClickListener(mOnClickListener);
        mTvRight.setOnClickListener(mOnClickListener);
    }

    public void setOnTopBarClickListener(OnTopBarClickListener aOnTopBarClickListener) {
        mOnTopBarClickListener = aOnTopBarClickListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setTitle(String text) {
        mTxtTitle.setText(text);
    }

    public View getView(Pos aPos) {
        switch (aPos) {
            case LEFT:
                return mTvLeft;
            case MIDDLE:
                return mTxtTitle;
            case RIGHT:
                return mTvRight;
        }
        return null;
    }

    public void setLeftText(String msg) {
        mTvLeft.setText(msg);
    }

    public void setRightText(String msg) {
        mTvRight.setText(msg);
    }
}

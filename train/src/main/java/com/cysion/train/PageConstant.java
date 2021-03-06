package com.cysion.train;


/**
 * 页面和界面传参时用到的常量
 */
public class PageConstant {

    //机构或专家id
    public static final String EXPERT_OR_ORG_ID = "expert_or_ORG_ID";
    public static final String EXPERT_TYPE = "expert_type";
    //培训id
    public static final String TRAIN_ID = "train_id";
    //用户id
    public static final String USER_ID = "user_id";
    //会议对象
    public static final String TRAIN_ENTITY = "train_entity";
    //专家
    public static final String IS_EXPERT = "is_expert";
    public static final String IS_ORG = "is_org";
    //近期排序,传参
    public static final int RECENT_SORT = 2;

    //所有页面请求结果成功码
    public static final int RESULT_OK = 200;


    /**
     * EventBus事件
     */
    //登录成功
    public static final int LOGIN_SUCCESS = 1000;
    //直接登录
    public static final int LOGIN_DIRECT = 1001;
    //取消收藏
    public static final int DEL_COLLECT = 1002;

}

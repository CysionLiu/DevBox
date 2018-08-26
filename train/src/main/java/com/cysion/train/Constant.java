package com.cysion.train;

public class Constant {

    /*
    ---------URL----
     */
    public static final String HOST = "https://trade.5dev.cn/cultivate/";
    public static final String PASSPORT_HOST = "https://cm.5dev.cn/applet/content/";
    public static final String HOTLINE_NUMBER = "400 026 3866";

    //是否返回json,1返回
    public static final int COMMON_QUERY_JSON = 1;
    //appid,测试13，上线45
    public static final int COMMON_QUERY_APPID = 1;
    //登录来源
    public static final String COMMON_APP_TYPE = "3";
    //通用数据返回状态
    public static final int STATUS_SUCCESS = 1;
    //已收藏状态
    public static final int COLLECTED_STATE = 1;
    //未收藏状态
    public static final int NOT_COLLECTED_STATE = 2;

    //请求登录页面
    public static final int LOGIN_REQ = 355;
    /**
     * 以下，会议列表不同形式
     */
    //主页列表
    public static final int HOME_LIST = 100;
    //培训列表
    public static final int MAIN_LIST = 101;
    //机构列表
    public static final int ORG_LIST = 102;
    //收藏列表
    public static final int COLLECT_LIST = 103;
    //推荐会议列表
    public static final int RECOMMAND_LIST = 104;

    //----------------------------------------------------------------------------------------------

    /**
     * 以下，是用户页面的各类型
     */
    public static final int MY_PROFILE = 200;
    public static final int MY_SIGN = 201;
    public static final int MY_COLLECT = 202;
    public static final int MY_HOTLINE = 203;
    //----------------------------------------------------------------------------------------------
}

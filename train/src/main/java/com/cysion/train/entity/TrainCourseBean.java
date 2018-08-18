package com.cysion.train.entity;

import java.util.List;

/**
 * 培训详情
 */
public class TrainCourseBean extends BaseEntity {


    /**
     * data : {"id":"3","uid":"-1","name":"长租公寓盈利模式与运营创新研讨会第2季","share_name":"长租公寓盈利模式与运营创新研讨会第2季","share_desc":"存量地产时代，地产操盘手共同的思考与前","title":null,"area":"9,74,-1","style_id":"5","tag":"9,13,16,17","start":"2018.08.24","end":"08.26","bao_end":"1534920939","train":"7","expert":"4","company_id":"1","agent_id":"-1","top":"http://trade.5dev.cn/upload/data/upload/1/2018/08/08/4a47a0db6e60853dedfcfdf08a5ca249.png","pic":"http://trade.5dev.cn/upload/data/upload/1/2018/08/08/c6de4fd275bd9a37dfff6fc80749f788.jpg","video":"","content":[{"order":"1","title":"会议简介","info":"<p>\r\n\t<span style=\"font-size:16px;\">集体建设用地、工业用地是否适合做长租公寓？<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">房企转型长租公寓领域如何与现有业务结合？<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">非一线城市长租公寓资本市场如何评估？<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">酒店物业改造升级为长租公寓是否更经济？<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">如何通过前期尽调避免后期运营的坑？<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">长租公寓如何组建和培养稳定运营团队？<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">园区商业配套长租公寓如何评价盈利指标？<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">度假区域或项目做长租公寓的市场空间和定位？<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">ABS、CMBS、REITs有什么特点和优势？<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">公寓品牌资本市场估值的依据有哪些？<\/span> \r\n<\/p>\r\n<p>\r\n\t<img src=\"http://trade.5dev.cn/upload/data/upload/1/2018/08/08/42c74d81f811c5cdbb63214b6d0de324.png\" alt=\"\" /> \r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">面对痛点，关注趋势、了解运营关键，掌握资本策略与工具，是地产人在长租公寓版块的必修认知。<\/span> \r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">8月24-26日<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">聚焦长租公寓蓝海<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">3个教学模块，12个长租公寓案例<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">1场围炉夜话<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">存量地产时代，地产操盘手共同的思考与前行<\/span> \r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>"},{"order":"2","title":"专家及议程","info":"<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:18px;\"><strong>主要教学案例<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>链家自如<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">A轮融资40亿，6大产品线，完善居住产业链配套服务<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>万科泊寓<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">房企标杆率先转型打造租赁产品适应居住模式变化<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>安歆公寓<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">国内首个专业连锁型企业员工宿舍，蓝领公寓标杆<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>魔方公寓<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">中国首家连锁集中式长租公寓，规模化运营持续盈利<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>斯维登公寓<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">长短租结合，经营房源超40000套，目的地近200个<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>湾流国际青年社区<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">上海规模最大的集中型共享式国际青年社区<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>未来域<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">深耕公寓7年，管理存量资产超百万平，资产规模逾百亿<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>YOU+公寓<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">国内最早一批长租公寓品牌，产品和营销并驾齐驱<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>新派公寓<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">轻重结合，发型首单长租公寓资产权益型类REITs<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>窝趣<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">铂涛酒店集团旗下公寓品牌，获58集团战略领投<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>雅诗阁公寓<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">中国逾18000套服务公寓，长租公寓趋势下运营为王<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>辉盛阁<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">高品质服务公寓，80% 顾客来自 500 强和福布斯上榜企业<\/span> \r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t<img src=\"http://trade.5dev.cn/upload/data/upload/1/2018/08/08/c2b8192a9be5e0888d46638a2029121f.jpg\" alt=\"\" /> \r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>"},{"order":"3","title":"围炉夜话","info":"<p>\r\n\t白天课上学习，晚上围炉夜话热烈讨论。\r\n<\/p>\r\n<p>\r\n\t从开发到运营，人，是最关键的！\r\n<\/p>\r\n<p>\r\n\t魔方学院被称为公寓行业的\u201c黄埔军校\u201d，本次围炉夜话特别邀请魔方学院校长于雷老师与同学一起，共同讨论深度运营的长租公寓领域如何通过人才战略建立自己的护城河。\r\n<\/p>\r\n<p>\r\n\t围炉夜话主题：长租公寓人才体系构建和培养\r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t<img src=\"http://trade.5dev.cn/upload/data/upload/1/2018/08/08/cc9c600c3f31a3f48461cb1abf2e58d9.jpg\" alt=\"\" /> \r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t议题：\r\n<\/p>\r\n<p>\r\n\t一、从开发到运营，人才需求发生哪些变化\r\n<\/p>\r\n<p>\r\n\t二、长租公寓人才选拔有哪些关键点\r\n<\/p>\r\n<p>\r\n\t三、人才培养体系如何搭建\r\n<\/p>\r\n<p>\r\n\t五、人才培养中核心能力的确立和培养\r\n<\/p>\r\n<p>\r\n\t六、如何选人、培养人、留住人\r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>"}],"biao":"","map":"上海","reorder":"1","type":"1","state":"1","cdate":"1533712331","style":"民宿","price":{"max":0,"min":"9800元","price_ext":"起"},"tags":[{"id":"17","name":"IP","colour":"#000000","reorder":"1","state":"1","cdate":"1533724321"},{"id":"16","name":"民宿设计","colour":"#000000","reorder":"1","state":"1","cdate":"1533724316"},{"id":"13","name":"品牌","colour":"#000000","reorder":"1","state":"1","cdate":"1533722262"},{"id":"9","name":"民宿","colour":"#000000","reorder":"1","state":"1","cdate":"1533710676"}],"company":{"id":"1","name":"知学学院","top":"http://trade.5dev.cn/upload/data/upload/1/2018/08/08/756fe34e9c24ae8064c4898ee4c42148.jpg","desc":"<p>\r\n\t<span style=\"font-size:16px;\">知学学院成立于2013年，亲历着中国房地产行业从零售型向经营型转变这一波澜壮阔的大时代。我们的初心是打造房地产下半场操盘手的学习型社群，以\u201c爱是最好的经营\u201d为办学理念，培养房地产项目高层管理人员完成\u201c从开发到经营\u201d的转变。<\/span>\r\n<\/p>","reorder":"1","state":"1","cdate":"1533708301"},"config":{"id":"1","head":"橙猫培训平台","phone":"400 026 3866","title":"行猫培训平台","name":"行猫培训平台","account_desc":"橙猫网络教育科技有限公司","account_num":"693509731","account_name":"橙猫网络教育科技有限公司","account":"北京市民生银行德胜门支行","remark":"请您付款时备注您的姓名及会议信息","explain":"<p>\r\n\t<span style=\"font-size:16px;\">橙猫教育目前支持的付款方式为对公汇款，您可以通过手机银行app或柜台转账完成。<\/span> \r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">1、使用手机银行转账充值，收款账户开户行请选择\"民生银行\"；使用网银充值，收款账户开户行请输入\"中国民生银行股份有限公司北京德胜门支行\"；<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">2、请您务必备注您的姓名及所参加的会议<\/span> \r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>","reminder":"<p>\r\n\t<span style=\"font-size:16px;\">1、转账信息与本页面给出的转账信息不一致将导致资金挂账，挂账资金退款时间依赖银行流程在1-3个工作日到账；<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">2、转账充值功能不支持支付宝、微信、ATM机、存折、现金转账到存管账户；<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">3、严禁利用充值功能进行信用卡套现、转账、洗钱等行为，一经发现，将封停账号30天。<\/span> \r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>","state":"1","cdate":"1532593003","acc_desc":"橙猫培训平台","serve":"<p style=\"text-align:left;\">\r\n\t<strong><span style=\"font-size:16px;\">在使用北京橙猫教育科技有限公司产品-橙猫教育（以下简称橙猫教育，包括但不限于网页版本、移动端应用APP及其他类型产品）前，请您务必仔细阅读并透彻理解本声明。使用橙猫教育的服务将被视为对本条款的认可。<\/span><\/strong>\r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">橙猫教育将商业会议与展览信息汇集于互联网平台、供您搜索，除特别标注说明之活动外，橙猫教育均不负责直接组织活动。<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>您了解并同意橙猫教育上的会议与展览描述，系会议与展览组织或主办方者、提供，橙猫教育对其合法性概不负责，亦不承担任何法律责任。<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">橙猫教育无法控制组织或主办方所发布信息的真实性或准确性，以及履行其在会议与展览等相关活动描述中表述内容的能力。您应自行谨慎判断确定相关服务及/或信息的真实性、合法性和有效性，并自行承担因此产生的责任与损失。<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">若您对活动有任何疑问，您应联系该会议与展览等相关活动的组织或主办方。橙猫教育通过自有团队尽力保证组织或主办方与登记的名称一致，拥有主办会议与展览活动的资质，并尽力更新有效的联系方式，但不对具体会议与展览活动负责。我们也希望您及时反馈在使用中遇到的问题并对欺诈行为进行举报，以提高橙猫教育的网络信息服务质量。<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>橙猫教育不保证为了会议与展览等相关活动之组织或主办方设置的外部链接的准确性和完整性，同时，对于该等外部链接指向的不由橙猫教育实际控制的任何网页上的内容，橙猫教育不承担任何责任。<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">第三方链接可能被会议与展览等相关活动的组织或主办方用于提供活动的详细信息或提供报名渠道。由于第三方站点与橙猫教育无关，故橙猫教育一律不对该站点上的内容负责。若第三方站点上的活动说明与橙猫教育上的活动描述冲突，请联系会议与展览等相关活动的组织或主办方。<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>橙猫教育不保证页面上显示报名成功即为最终报名成功凭证。会议与展览等相关活动的最终解释权完全归其组织或主办方所有。橙猫教育仅对技术性故障负责。<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">会议与展览等相关活动的组织或主办方有权力在活动管理中对参与者进行筛选，若您被剔除，橙猫教育会提示组织或主办方给您发送说明原因，但该功能并非强制。组织或主办方也可能会通过其他形式通知您报名状态的更改。除技术性故障外，活动组织或主办方对报名的有效性完全负责。<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>请您及时保存或备份您的文字、图片等其他信息，您完全理解并同意，由于橙猫教育储存设备有限、设备故障、设备更新或设备受到攻击等设备原因或人为原因，您使用网路服务储存的信息或数据等全部或部分发生删除、毁损、灭失或无法恢复的风险，均由您须自行承担，橙猫教育不承担任何责任。<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">不论在何种情况下，橙猫教育均不对由于信息网络正常的设备维护，信息网络连接故障，电脑、通讯或其他系统的故障，黑客，电脑病毒，电力故障，罢工，劳动争议，暴乱，起义，骚乱，生产力或生产资料不足，火灾，洪水，风暴，爆炸，战争，政府行为，司法行政机关的命令，第三方原因等其他橙猫教育不能预测或控制的行为而造成的不能服务或延迟服务承担责任，但将尽力减少因此而给您造成的损失和影响。<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">任何单位或个人认为通过橙猫教育的内容可能涉嫌侵犯其合法权益，应该及时向橙猫教育或服务网站书面反馈，并提供身份证明、权属证明及详细侵权情况证明，橙猫教育在收到上述法律文件后，将会尽快移除被控侵权内容。详见权利声明。<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">本声明适用中华人民共和国法律，您和橙猫教育一致同意服从四川省成都市高新区人民法院管辖。如其中任何条款与中华人民共和国法律相抵触时，则这些条款将完全按法律规定重新解释，而其它条款依旧具有法律效力。<\/span> \r\n<\/p>\r\n<br />"},"states":2}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 3
         * uid : -1
         * name : 长租公寓盈利模式与运营创新研讨会第2季
         * share_name : 长租公寓盈利模式与运营创新研讨会第2季
         * share_desc : 存量地产时代，地产操盘手共同的思考与前
         * title : null
         * area : 9,74,-1
         * style_id : 5
         * tag : 9,13,16,17
         * start : 2018.08.24
         * end : 08.26
         * bao_end : 1534920939
         * train : 7
         * expert : 4
         * company_id : 1
         * agent_id : -1
         * top : http://trade.5dev.cn/upload/data/upload/1/2018/08/08/4a47a0db6e60853dedfcfdf08a5ca249.png
         * pic : http://trade.5dev.cn/upload/data/upload/1/2018/08/08/c6de4fd275bd9a37dfff6fc80749f788.jpg
         * video :
         * content : [{"order":"1","title":"会议简介","info":"<p>\r\n\t<span style=\"font-size:16px;\">集体建设用地、工业用地是否适合做长租公寓？<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">房企转型长租公寓领域如何与现有业务结合？<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">非一线城市长租公寓资本市场如何评估？<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">酒店物业改造升级为长租公寓是否更经济？<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">如何通过前期尽调避免后期运营的坑？<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">长租公寓如何组建和培养稳定运营团队？<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">园区商业配套长租公寓如何评价盈利指标？<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">度假区域或项目做长租公寓的市场空间和定位？<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">ABS、CMBS、REITs有什么特点和优势？<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">公寓品牌资本市场估值的依据有哪些？<\/span> \r\n<\/p>\r\n<p>\r\n\t<img src=\"http://trade.5dev.cn/upload/data/upload/1/2018/08/08/42c74d81f811c5cdbb63214b6d0de324.png\" alt=\"\" /> \r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">面对痛点，关注趋势、了解运营关键，掌握资本策略与工具，是地产人在长租公寓版块的必修认知。<\/span> \r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">8月24-26日<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">聚焦长租公寓蓝海<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">3个教学模块，12个长租公寓案例<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">1场围炉夜话<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">存量地产时代，地产操盘手共同的思考与前行<\/span> \r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>"},{"order":"2","title":"专家及议程","info":"<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:18px;\"><strong>主要教学案例<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>链家自如<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">A轮融资40亿，6大产品线，完善居住产业链配套服务<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>万科泊寓<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">房企标杆率先转型打造租赁产品适应居住模式变化<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>安歆公寓<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">国内首个专业连锁型企业员工宿舍，蓝领公寓标杆<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>魔方公寓<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">中国首家连锁集中式长租公寓，规模化运营持续盈利<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>斯维登公寓<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">长短租结合，经营房源超40000套，目的地近200个<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>湾流国际青年社区<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">上海规模最大的集中型共享式国际青年社区<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>未来域<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">深耕公寓7年，管理存量资产超百万平，资产规模逾百亿<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>YOU+公寓<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">国内最早一批长租公寓品牌，产品和营销并驾齐驱<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>新派公寓<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">轻重结合，发型首单长租公寓资产权益型类REITs<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>窝趣<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">铂涛酒店集团旗下公寓品牌，获58集团战略领投<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>雅诗阁公寓<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">中国逾18000套服务公寓，长租公寓趋势下运营为王<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>辉盛阁<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">高品质服务公寓，80% 顾客来自 500 强和福布斯上榜企业<\/span> \r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t<img src=\"http://trade.5dev.cn/upload/data/upload/1/2018/08/08/c2b8192a9be5e0888d46638a2029121f.jpg\" alt=\"\" /> \r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>"},{"order":"3","title":"围炉夜话","info":"<p>\r\n\t白天课上学习，晚上围炉夜话热烈讨论。\r\n<\/p>\r\n<p>\r\n\t从开发到运营，人，是最关键的！\r\n<\/p>\r\n<p>\r\n\t魔方学院被称为公寓行业的\u201c黄埔军校\u201d，本次围炉夜话特别邀请魔方学院校长于雷老师与同学一起，共同讨论深度运营的长租公寓领域如何通过人才战略建立自己的护城河。\r\n<\/p>\r\n<p>\r\n\t围炉夜话主题：长租公寓人才体系构建和培养\r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t<img src=\"http://trade.5dev.cn/upload/data/upload/1/2018/08/08/cc9c600c3f31a3f48461cb1abf2e58d9.jpg\" alt=\"\" /> \r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t议题：\r\n<\/p>\r\n<p>\r\n\t一、从开发到运营，人才需求发生哪些变化\r\n<\/p>\r\n<p>\r\n\t二、长租公寓人才选拔有哪些关键点\r\n<\/p>\r\n<p>\r\n\t三、人才培养体系如何搭建\r\n<\/p>\r\n<p>\r\n\t五、人才培养中核心能力的确立和培养\r\n<\/p>\r\n<p>\r\n\t六、如何选人、培养人、留住人\r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>"}]
         * biao :
         * map : 上海
         * reorder : 1
         * type : 1
         * state : 1
         * cdate : 1533712331
         * style : 民宿
         * price : {"max":0,"min":"9800元","price_ext":"起"}
         * tags : [{"id":"17","name":"IP","colour":"#000000","reorder":"1","state":"1","cdate":"1533724321"},{"id":"16","name":"民宿设计","colour":"#000000","reorder":"1","state":"1","cdate":"1533724316"},{"id":"13","name":"品牌","colour":"#000000","reorder":"1","state":"1","cdate":"1533722262"},{"id":"9","name":"民宿","colour":"#000000","reorder":"1","state":"1","cdate":"1533710676"}]
         * company : {"id":"1","name":"知学学院","top":"http://trade.5dev.cn/upload/data/upload/1/2018/08/08/756fe34e9c24ae8064c4898ee4c42148.jpg","desc":"<p>\r\n\t<span style=\"font-size:16px;\">知学学院成立于2013年，亲历着中国房地产行业从零售型向经营型转变这一波澜壮阔的大时代。我们的初心是打造房地产下半场操盘手的学习型社群，以\u201c爱是最好的经营\u201d为办学理念，培养房地产项目高层管理人员完成\u201c从开发到经营\u201d的转变。<\/span>\r\n<\/p>","reorder":"1","state":"1","cdate":"1533708301"}
         * config : {"id":"1","head":"橙猫培训平台","phone":"400 026 3866","title":"行猫培训平台","name":"行猫培训平台","account_desc":"橙猫网络教育科技有限公司","account_num":"693509731","account_name":"橙猫网络教育科技有限公司","account":"北京市民生银行德胜门支行","remark":"请您付款时备注您的姓名及会议信息","explain":"<p>\r\n\t<span style=\"font-size:16px;\">橙猫教育目前支持的付款方式为对公汇款，您可以通过手机银行app或柜台转账完成。<\/span> \r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">1、使用手机银行转账充值，收款账户开户行请选择\"民生银行\"；使用网银充值，收款账户开户行请输入\"中国民生银行股份有限公司北京德胜门支行\"；<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">2、请您务必备注您的姓名及所参加的会议<\/span> \r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>","reminder":"<p>\r\n\t<span style=\"font-size:16px;\">1、转账信息与本页面给出的转账信息不一致将导致资金挂账，挂账资金退款时间依赖银行流程在1-3个工作日到账；<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">2、转账充值功能不支持支付宝、微信、ATM机、存折、现金转账到存管账户；<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">3、严禁利用充值功能进行信用卡套现、转账、洗钱等行为，一经发现，将封停账号30天。<\/span> \r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>\r\n<p>\r\n\t<br />\r\n<\/p>","state":"1","cdate":"1532593003","acc_desc":"橙猫培训平台","serve":"<p style=\"text-align:left;\">\r\n\t<strong><span style=\"font-size:16px;\">在使用北京橙猫教育科技有限公司产品-橙猫教育（以下简称橙猫教育，包括但不限于网页版本、移动端应用APP及其他类型产品）前，请您务必仔细阅读并透彻理解本声明。使用橙猫教育的服务将被视为对本条款的认可。<\/span><\/strong>\r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">橙猫教育将商业会议与展览信息汇集于互联网平台、供您搜索，除特别标注说明之活动外，橙猫教育均不负责直接组织活动。<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>您了解并同意橙猫教育上的会议与展览描述，系会议与展览组织或主办方者、提供，橙猫教育对其合法性概不负责，亦不承担任何法律责任。<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">橙猫教育无法控制组织或主办方所发布信息的真实性或准确性，以及履行其在会议与展览等相关活动描述中表述内容的能力。您应自行谨慎判断确定相关服务及/或信息的真实性、合法性和有效性，并自行承担因此产生的责任与损失。<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">若您对活动有任何疑问，您应联系该会议与展览等相关活动的组织或主办方。橙猫教育通过自有团队尽力保证组织或主办方与登记的名称一致，拥有主办会议与展览活动的资质，并尽力更新有效的联系方式，但不对具体会议与展览活动负责。我们也希望您及时反馈在使用中遇到的问题并对欺诈行为进行举报，以提高橙猫教育的网络信息服务质量。<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>橙猫教育不保证为了会议与展览等相关活动之组织或主办方设置的外部链接的准确性和完整性，同时，对于该等外部链接指向的不由橙猫教育实际控制的任何网页上的内容，橙猫教育不承担任何责任。<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">第三方链接可能被会议与展览等相关活动的组织或主办方用于提供活动的详细信息或提供报名渠道。由于第三方站点与橙猫教育无关，故橙猫教育一律不对该站点上的内容负责。若第三方站点上的活动说明与橙猫教育上的活动描述冲突，请联系会议与展览等相关活动的组织或主办方。<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>橙猫教育不保证页面上显示报名成功即为最终报名成功凭证。会议与展览等相关活动的最终解释权完全归其组织或主办方所有。橙猫教育仅对技术性故障负责。<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">会议与展览等相关活动的组织或主办方有权力在活动管理中对参与者进行筛选，若您被剔除，橙猫教育会提示组织或主办方给您发送说明原因，但该功能并非强制。组织或主办方也可能会通过其他形式通知您报名状态的更改。除技术性故障外，活动组织或主办方对报名的有效性完全负责。<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\"><strong>请您及时保存或备份您的文字、图片等其他信息，您完全理解并同意，由于橙猫教育储存设备有限、设备故障、设备更新或设备受到攻击等设备原因或人为原因，您使用网路服务储存的信息或数据等全部或部分发生删除、毁损、灭失或无法恢复的风险，均由您须自行承担，橙猫教育不承担任何责任。<\/strong><\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">不论在何种情况下，橙猫教育均不对由于信息网络正常的设备维护，信息网络连接故障，电脑、通讯或其他系统的故障，黑客，电脑病毒，电力故障，罢工，劳动争议，暴乱，起义，骚乱，生产力或生产资料不足，火灾，洪水，风暴，爆炸，战争，政府行为，司法行政机关的命令，第三方原因等其他橙猫教育不能预测或控制的行为而造成的不能服务或延迟服务承担责任，但将尽力减少因此而给您造成的损失和影响。<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">任何单位或个人认为通过橙猫教育的内容可能涉嫌侵犯其合法权益，应该及时向橙猫教育或服务网站书面反馈，并提供身份证明、权属证明及详细侵权情况证明，橙猫教育在收到上述法律文件后，将会尽快移除被控侵权内容。详见权利声明。<\/span> \r\n<\/p>\r\n<p>\r\n\t<span style=\"font-size:16px;\">本声明适用中华人民共和国法律，您和橙猫教育一致同意服从四川省成都市高新区人民法院管辖。如其中任何条款与中华人民共和国法律相抵触时，则这些条款将完全按法律规定重新解释，而其它条款依旧具有法律效力。<\/span> \r\n<\/p>\r\n<br />"}
         * states : 2
         */

        private String id;
        private String uid;
        private String name;
        private String share_name;
        private String share_desc;
        private Object title;
        private String area;
        private String style_id;
        private String tag;
        private String start;
        private String end;
        private String bao_end;
        private String train;
        private String expert;
        private String company_id;
        private String agent_id;
        private String top;
        private String pic;
        private String video;
        private String biao;
        private String map;
        private String reorder;
        private String type;
        private String state;
        private String cdate;
        private String style;
        private PriceBean price;
        private CompanyBean company;
        private ConfigBean config;
        private int states;
        private List<ContentBean> content;
        private List<TagsBean> tags;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShare_name() {
            return share_name;
        }

        public void setShare_name(String share_name) {
            this.share_name = share_name;
        }

        public String getShare_desc() {
            return share_desc;
        }

        public void setShare_desc(String share_desc) {
            this.share_desc = share_desc;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getStyle_id() {
            return style_id;
        }

        public void setStyle_id(String style_id) {
            this.style_id = style_id;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getBao_end() {
            return bao_end;
        }

        public void setBao_end(String bao_end) {
            this.bao_end = bao_end;
        }

        public String getTrain() {
            return train;
        }

        public void setTrain(String train) {
            this.train = train;
        }

        public String getExpert() {
            return expert;
        }

        public void setExpert(String expert) {
            this.expert = expert;
        }

        public String getCompany_id() {
            return company_id;
        }

        public void setCompany_id(String company_id) {
            this.company_id = company_id;
        }

        public String getAgent_id() {
            return agent_id;
        }

        public void setAgent_id(String agent_id) {
            this.agent_id = agent_id;
        }

        public String getTop() {
            return top;
        }

        public void setTop(String top) {
            this.top = top;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getBiao() {
            return biao;
        }

        public void setBiao(String biao) {
            this.biao = biao;
        }

        public String getMap() {
            return map;
        }

        public void setMap(String map) {
            this.map = map;
        }

        public String getReorder() {
            return reorder;
        }

        public void setReorder(String reorder) {
            this.reorder = reorder;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCdate() {
            return cdate;
        }

        public void setCdate(String cdate) {
            this.cdate = cdate;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public PriceBean getPrice() {
            return price;
        }

        public void setPrice(PriceBean price) {
            this.price = price;
        }

        public CompanyBean getCompany() {
            return company;
        }

        public void setCompany(CompanyBean company) {
            this.company = company;
        }

        public ConfigBean getConfig() {
            return config;
        }

        public void setConfig(ConfigBean config) {
            this.config = config;
        }

        public int getStates() {
            return states;
        }

        public void setStates(int states) {
            this.states = states;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class PriceBean {
            /**
             * max : 0
             * min : 9800元
             * price_ext : 起
             */

            private int max;
            private String min;
            private String price_ext;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public String getMin() {
                return min;
            }

            public void setMin(String min) {
                this.min = min;
            }

            public String getPrice_ext() {
                return price_ext;
            }

            public void setPrice_ext(String price_ext) {
                this.price_ext = price_ext;
            }
        }

        public static class CompanyBean {
            /**
             * id : 1
             * name : 知学学院
             * top : http://trade.5dev.cn/upload/data/upload/1/2018/08/08/756fe34e9c24ae8064c4898ee4c42148.jpg
             * desc : <p>
             * <span style="font-size:16px;">知学学院成立于2013年，亲历着中国房地产行业从零售型向经营型转变这一波澜壮阔的大时代。我们的初心是打造房地产下半场操盘手的学习型社群，以“爱是最好的经营”为办学理念，培养房地产项目高层管理人员完成“从开发到经营”的转变。</span>
             * </p>
             * reorder : 1
             * state : 1
             * cdate : 1533708301
             */

            private String id;
            private String name;
            private String top;
            private String desc;
            private String reorder;
            private String state;
            private String cdate;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTop() {
                return top;
            }

            public void setTop(String top) {
                this.top = top;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getReorder() {
                return reorder;
            }

            public void setReorder(String reorder) {
                this.reorder = reorder;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getCdate() {
                return cdate;
            }

            public void setCdate(String cdate) {
                this.cdate = cdate;
            }
        }

        public static class ConfigBean {
            /**
             * id : 1
             * head : 橙猫培训平台
             * phone : 400 026 3866
             * title : 行猫培训平台
             * name : 行猫培训平台
             * account_desc : 橙猫网络教育科技有限公司
             * account_num : 693509731
             * account_name : 橙猫网络教育科技有限公司
             * account : 北京市民生银行德胜门支行
             * remark : 请您付款时备注您的姓名及会议信息
             * explain : <p>
             * <span style="font-size:16px;">橙猫教育目前支持的付款方式为对公汇款，您可以通过手机银行app或柜台转账完成。</span>
             * </p>
             * <p>
             * <br />
             * </p>
             * <p>
             * <span style="font-size:16px;">1、使用手机银行转账充值，收款账户开户行请选择"民生银行"；使用网银充值，收款账户开户行请输入"中国民生银行股份有限公司北京德胜门支行"；</span>
             * </p>
             * <p>
             * <span style="font-size:16px;">2、请您务必备注您的姓名及所参加的会议</span>
             * </p>
             * <p>
             * <br />
             * </p>
             * reminder : <p>
             * <span style="font-size:16px;">1、转账信息与本页面给出的转账信息不一致将导致资金挂账，挂账资金退款时间依赖银行流程在1-3个工作日到账；</span>
             * </p>
             * <p>
             * <span style="font-size:16px;">2、转账充值功能不支持支付宝、微信、ATM机、存折、现金转账到存管账户；</span>
             * </p>
             * <p>
             * <span style="font-size:16px;">3、严禁利用充值功能进行信用卡套现、转账、洗钱等行为，一经发现，将封停账号30天。</span>
             * </p>
             * <p>
             * <br />
             * </p>
             * <p>
             * <br />
             * </p>
             * state : 1
             * cdate : 1532593003
             * acc_desc : 橙猫培训平台
             * serve : <p style="text-align:left;">
             * <strong><span style="font-size:16px;">在使用北京橙猫教育科技有限公司产品-橙猫教育（以下简称橙猫教育，包括但不限于网页版本、移动端应用APP及其他类型产品）前，请您务必仔细阅读并透彻理解本声明。使用橙猫教育的服务将被视为对本条款的认可。</span></strong>
             * </p>
             * <p>
             * <span style="font-size:16px;">橙猫教育将商业会议与展览信息汇集于互联网平台、供您搜索，除特别标注说明之活动外，橙猫教育均不负责直接组织活动。</span>
             * </p>
             * <p>
             * <span style="font-size:16px;"><strong>您了解并同意橙猫教育上的会议与展览描述，系会议与展览组织或主办方者、提供，橙猫教育对其合法性概不负责，亦不承担任何法律责任。</strong></span>
             * </p>
             * <p>
             * <span style="font-size:16px;">橙猫教育无法控制组织或主办方所发布信息的真实性或准确性，以及履行其在会议与展览等相关活动描述中表述内容的能力。您应自行谨慎判断确定相关服务及/或信息的真实性、合法性和有效性，并自行承担因此产生的责任与损失。</span>
             * </p>
             * <p>
             * <span style="font-size:16px;">若您对活动有任何疑问，您应联系该会议与展览等相关活动的组织或主办方。橙猫教育通过自有团队尽力保证组织或主办方与登记的名称一致，拥有主办会议与展览活动的资质，并尽力更新有效的联系方式，但不对具体会议与展览活动负责。我们也希望您及时反馈在使用中遇到的问题并对欺诈行为进行举报，以提高橙猫教育的网络信息服务质量。</span>
             * </p>
             * <p>
             * <span style="font-size:16px;"><strong>橙猫教育不保证为了会议与展览等相关活动之组织或主办方设置的外部链接的准确性和完整性，同时，对于该等外部链接指向的不由橙猫教育实际控制的任何网页上的内容，橙猫教育不承担任何责任。</strong></span>
             * </p>
             * <p>
             * <span style="font-size:16px;">第三方链接可能被会议与展览等相关活动的组织或主办方用于提供活动的详细信息或提供报名渠道。由于第三方站点与橙猫教育无关，故橙猫教育一律不对该站点上的内容负责。若第三方站点上的活动说明与橙猫教育上的活动描述冲突，请联系会议与展览等相关活动的组织或主办方。</span>
             * </p>
             * <p>
             * <span style="font-size:16px;"><strong>橙猫教育不保证页面上显示报名成功即为最终报名成功凭证。会议与展览等相关活动的最终解释权完全归其组织或主办方所有。橙猫教育仅对技术性故障负责。</strong></span>
             * </p>
             * <p>
             * <span style="font-size:16px;">会议与展览等相关活动的组织或主办方有权力在活动管理中对参与者进行筛选，若您被剔除，橙猫教育会提示组织或主办方给您发送说明原因，但该功能并非强制。组织或主办方也可能会通过其他形式通知您报名状态的更改。除技术性故障外，活动组织或主办方对报名的有效性完全负责。</span>
             * </p>
             * <p>
             * <span style="font-size:16px;"><strong>请您及时保存或备份您的文字、图片等其他信息，您完全理解并同意，由于橙猫教育储存设备有限、设备故障、设备更新或设备受到攻击等设备原因或人为原因，您使用网路服务储存的信息或数据等全部或部分发生删除、毁损、灭失或无法恢复的风险，均由您须自行承担，橙猫教育不承担任何责任。</strong></span>
             * </p>
             * <p>
             * <span style="font-size:16px;">不论在何种情况下，橙猫教育均不对由于信息网络正常的设备维护，信息网络连接故障，电脑、通讯或其他系统的故障，黑客，电脑病毒，电力故障，罢工，劳动争议，暴乱，起义，骚乱，生产力或生产资料不足，火灾，洪水，风暴，爆炸，战争，政府行为，司法行政机关的命令，第三方原因等其他橙猫教育不能预测或控制的行为而造成的不能服务或延迟服务承担责任，但将尽力减少因此而给您造成的损失和影响。</span>
             * </p>
             * <p>
             * <span style="font-size:16px;">任何单位或个人认为通过橙猫教育的内容可能涉嫌侵犯其合法权益，应该及时向橙猫教育或服务网站书面反馈，并提供身份证明、权属证明及详细侵权情况证明，橙猫教育在收到上述法律文件后，将会尽快移除被控侵权内容。详见权利声明。</span>
             * </p>
             * <p>
             * <span style="font-size:16px;">本声明适用中华人民共和国法律，您和橙猫教育一致同意服从四川省成都市高新区人民法院管辖。如其中任何条款与中华人民共和国法律相抵触时，则这些条款将完全按法律规定重新解释，而其它条款依旧具有法律效力。</span>
             * </p>
             * <br />
             */

            private String id;
            private String head;
            private String phone;
            private String title;
            private String name;
            private String account_desc;
            private String account_num;
            private String account_name;
            private String account;
            private String remark;
            private String explain;
            private String reminder;
            private String state;
            private String cdate;
            private String acc_desc;
            private String serve;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAccount_desc() {
                return account_desc;
            }

            public void setAccount_desc(String account_desc) {
                this.account_desc = account_desc;
            }

            public String getAccount_num() {
                return account_num;
            }

            public void setAccount_num(String account_num) {
                this.account_num = account_num;
            }

            public String getAccount_name() {
                return account_name;
            }

            public void setAccount_name(String account_name) {
                this.account_name = account_name;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getExplain() {
                return explain;
            }

            public void setExplain(String explain) {
                this.explain = explain;
            }

            public String getReminder() {
                return reminder;
            }

            public void setReminder(String reminder) {
                this.reminder = reminder;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getCdate() {
                return cdate;
            }

            public void setCdate(String cdate) {
                this.cdate = cdate;
            }

            public String getAcc_desc() {
                return acc_desc;
            }

            public void setAcc_desc(String acc_desc) {
                this.acc_desc = acc_desc;
            }

            public String getServe() {
                return serve;
            }

            public void setServe(String serve) {
                this.serve = serve;
            }
        }

        public static class ContentBean {
            /**
             * order : 1
             * title : 会议简介
             * info : <p>
             * <span style="font-size:16px;">集体建设用地、工业用地是否适合做长租公寓？</span>
             * </p>
             * <p>
             * <span style="font-size:16px;">房企转型长租公寓领域如何与现有业务结合？</span>
             * </p>
             * <p>
             * <span style="font-size:16px;">非一线城市长租公寓资本市场如何评估？</span>
             * </p>
             * <p>
             * <span style="font-size:16px;">酒店物业改造升级为长租公寓是否更经济？</span>
             * </p>
             * <p>
             * <span style="font-size:16px;">如何通过前期尽调避免后期运营的坑？</span>
             * </p>
             * <p>
             * <span style="font-size:16px;">长租公寓如何组建和培养稳定运营团队？</span>
             * </p>
             * <p>
             * <span style="font-size:16px;">园区商业配套长租公寓如何评价盈利指标？</span>
             * </p>
             * <p>
             * <span style="font-size:16px;">度假区域或项目做长租公寓的市场空间和定位？</span>
             * </p>
             * <p>
             * <span style="font-size:16px;">ABS、CMBS、REITs有什么特点和优势？</span>
             * </p>
             * <p>
             * <span style="font-size:16px;">公寓品牌资本市场估值的依据有哪些？</span>
             * </p>
             * <p>
             * <img src="http://trade.5dev.cn/upload/data/upload/1/2018/08/08/42c74d81f811c5cdbb63214b6d0de324.png" alt="" />
             * </p>
             * <p>
             * <br />
             * </p>
             * <p>
             * <span style="font-size:16px;">面对痛点，关注趋势、了解运营关键，掌握资本策略与工具，是地产人在长租公寓版块的必修认知。</span>
             * </p>
             * <p>
             * <br />
             * </p>
             * <p>
             * <span style="font-size:16px;">8月24-26日</span>
             * </p>
             * <p>
             * <span style="font-size:16px;">聚焦长租公寓蓝海</span>
             * </p>
             * <p>
             * <span style="font-size:16px;">3个教学模块，12个长租公寓案例</span>
             * </p>
             * <p>
             * <span style="font-size:16px;">1场围炉夜话</span>
             * </p>
             * <p>
             * <span style="font-size:16px;">存量地产时代，地产操盘手共同的思考与前行</span>
             * </p>
             * <p>
             * <br />
             * </p>
             */

            private String order;
            private String title;
            private String info;

            public String getOrder() {
                return order;
            }

            public void setOrder(String order) {
                this.order = order;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }
        }

        public static class TagsBean {
            /**
             * id : 17
             * name : IP
             * colour : #000000
             * reorder : 1
             * state : 1
             * cdate : 1533724321
             */

            private String id;
            private String name;
            private String colour;
            private String reorder;
            private String state;
            private String cdate;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getColour() {
                return colour;
            }

            public void setColour(String colour) {
                this.colour = colour;
            }

            public String getReorder() {
                return reorder;
            }

            public void setReorder(String reorder) {
                this.reorder = reorder;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getCdate() {
                return cdate;
            }

            public void setCdate(String cdate) {
                this.cdate = cdate;
            }
        }
    }
}

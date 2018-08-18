package com.cysion.train.entity;

import java.io.Serializable;

/**
 * 列表中的培训
 */
public class TrainOrgBean implements Serializable {

    /**
     * id : 3
     * name : 读道文旅
     * work : 读道文旅
     * cate_id : 1
     * desc : <p>
     <span style="font-size:16px;font-family:宋体;background:white;">读道文旅集团集团旗下围绕文旅</span><span style="font-size:16px;font-family:Arial;background:white;">IP</span><span style="font-size:16px;font-family:宋体;background:white;">孵化，旅游商品，规划设计、民宿管家、旅游培训、及品牌营销六大核心板块，</span><span style="font-size:10.5pt;font-family:Arial;background:white;"> </span><span style="font-size:16px;font-family:宋体;background:white;">致力成为最具实力的中国旅业好管家。读道文旅集团自成立之初就秉承“不辜负每一个客户和每一块土地的期望”的发展理念，已经为成百上千的政府、企业提供了从创意策划到落地运营的优质服务，打造了众多的旅游产品与成功典范，赢得客户广泛赞誉。</span><span style="font-size:10.5pt;font-family:Arial;background:white;"></span>
     </p>
     <br />
     * reorder : 1
     * state : 1
     * cdate : 1533180008
     * logo : https://trade.5dev.cn/upload/data/upload/1/2018/08/06/b8296a02bce443fe00bd9034aca4779e.jpg
     */

    private String id;
    private String name;
    private String work;
    private String cate_id;
    private String desc;
    private String reorder;
    private String state;
    private String cdate;
    private String logo;

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

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}

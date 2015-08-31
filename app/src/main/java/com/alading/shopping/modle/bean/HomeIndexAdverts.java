package com.alading.shopping.modle.bean;

/**
 * Created by Administrator on 2015/8/26.
 * 首页轮播广告
 */
public class HomeIndexAdverts {

    private int aid;//
    private String icon;//轮播图
    private int type;//
    private int content;//
    private String createTime;//
    private int mobile;//是否手机
    private int position;//
    private int status;//


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HomeIndexAdverts{" +
                "content='" + content + '\'' +
                ", createTime=" + createTime +
                ", icon='" + icon + '\'' +
                ", aid=" + aid +
                ", type=" + type +
                ", mobile=" + mobile +
                '}';
    }
}

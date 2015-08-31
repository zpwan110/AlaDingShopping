package com.alading.shopping.modle.bean;

/**
 * Created by Administrator on 2015/8/26.
 * 分类
 */
public class HomeCategorys {
    private String icon;//图标	string
    private int tid;
    private int sort;//排序
    private int cid;//类别id	number
    private String indexIcon;//索引图	string
    private String info;//信息	string
    private String name;//名称	string


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getIndexIcon() {
        return indexIcon;
    }

    public void setIndexIcon(String indexIcon) {
        this.indexIcon = indexIcon;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HomeCategorys(String icon, int tid, int sort, int cid, String indexIcon, String info, String name) {
        this.icon = icon;
        this.tid = tid;
        this.sort = sort;
        this.cid = cid;
        this.indexIcon = indexIcon;
        this.info = info;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HomeCategorys{" +
                "icon='" + icon + '\'' +
                ", tid=" + tid +
                ", sort=" + sort +
                ", cid=" + cid +
                ", indexIcon='" + indexIcon + '\'' +
                ", info='" + info + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

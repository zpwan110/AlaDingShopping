package com.alading.shopping.modle.bean;

/**
 * Created by Administrator on 2015/8/26.
 * 商品产地
 */
public class ProductOrigin {
    private int country;//	国别代码
    private String icon;//	国标
    private String name;//	名字
    private int oid;//国标的主键

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }
}

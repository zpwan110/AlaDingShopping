package com.alading.shopping.modle.bean;

/**
 * Created by Administrator on 2015/8/26.
 * 全球特卖
 */
public class GlobalSale {

    private int fid;//	id	number
    private int mobile;//	手机	number是否收集登陆
    private int pfid;//	商品特卖id	number
    private Product product;//	商品	object
    private Double salePrice;//	销售价格	number
    private String title;//标题

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public int getPfid() {
        return pfid;
    }

    public void setPfid(int pfid) {
        this.pfid = pfid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }
}

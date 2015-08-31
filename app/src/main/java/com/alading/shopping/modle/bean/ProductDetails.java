package com.alading.shopping.modle.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/26.
 * 商品信息
 */
public class ProductDetails {
    private Product product;
    private Other other;//其他
    private int evaluateCount;//评价数
    private String detalis;//商品描述 html
    private List<ProductImage> productImgs;//商品图片集合
    private ArrayList<ProductAttributes> productAttributes;//商品属性
    private TimeInfo tmInfo;// 时间信息

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Other getOther() {
        return other;
    }

    public void setOther(Other other) {
        this.other = other;
    }

    public int getEvaluateCount() {
        return evaluateCount;
    }

    public void setEvaluateCount(int evaluateCount) {
        this.evaluateCount = evaluateCount;
    }

    public String getDetalis() {
        return detalis;
    }

    public void setDetalis(String detalis) {
        this.detalis = detalis;
    }

    public List<ProductImage> getProductImgs() {
        return productImgs;
    }

    public void setProductImgs(List<ProductImage> productImgs) {
        this.productImgs = productImgs;
    }

    public ArrayList<ProductAttributes> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(ArrayList<ProductAttributes> productAttributes) {
        this.productAttributes = productAttributes;
    }

    public TimeInfo getTmInfo() {
        return tmInfo;
    }

    public void setTmInfo(TimeInfo tmInfo) {
        this.tmInfo = tmInfo;
    }

    public class Other {
        private String isMobile;//	是否手机端
        private String otherInfo;//	其他信息	string
        private String url;//商品链接 http://115.231.183.42:8081/aladdin/mobile/share?pid=35

        public String getIsMobile() {
            return isMobile;
        }

        public void setIsMobile(String isMobile) {
            this.isMobile = isMobile;
        }

        public String getOtherInfo() {
            return otherInfo;
        }

        public void setOtherInfo(String otherInfo) {
            this.otherInfo = otherInfo;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public class ProductImage implements Serializable {
        private int imgid;// 136,
        private int pid;// 35,
        private String img;//productOriginImg/912ca112-e65f-4988-b243-62a9a81121e4.jpg",
        private String imgThumb;//缩略图null,
        private String info;//商品图片",
        private int sort;//排序

        public int getImgid() {
            return imgid;
        }

        public void setImgid(int imgid) {
            this.imgid = imgid;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getImgThumb() {
            return imgThumb;
        }

        public void setImgThumb(String imgThumb) {
            this.imgThumb = imgThumb;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
    public class TimeInfo {
        private int days;//	天	number
        private int hour;//时	number
        private int minute;//	分	number
        private int pfid;//		number
        private Double salePrice;//	销售价	number
        private int second;//秒

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public int getMinute() {
            return minute;
        }

        public void setMinute(int minute) {
            this.minute = minute;
        }

        public int getPfid() {
            return pfid;
        }

        public void setPfid(int pfid) {
            this.pfid = pfid;
        }

        public Double getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(Double salePrice) {
            this.salePrice = salePrice;
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            this.second = second;
        }
    }

}

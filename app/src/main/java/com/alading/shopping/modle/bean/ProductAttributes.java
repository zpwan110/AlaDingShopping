package com.alading.shopping.modle.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/30.
 */
public class ProductAttributes implements Parcelable {
    private int aid;//	id	number
    private String name;//	属性名	string
    private int pid;//	商品id	number
    private String value;//值

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static final Parcelable.Creator<ProductAttributes> CREATOR = new Parcelable.Creator<ProductAttributes>() {
        public ProductAttributes createFromParcel(Parcel source) {
            ProductAttributes proAttributes = new ProductAttributes();
            proAttributes.aid = source.readInt();
            proAttributes.name = source.readString();
            proAttributes.pid = source.readInt();
            proAttributes.value = source.readString();
            return proAttributes;
        }
        public ProductAttributes[] newArray(int size) {
            return new ProductAttributes[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(aid);
        parcel.writeString(name);
        parcel.writeInt(pid);
        parcel.writeString(value);
    }
}

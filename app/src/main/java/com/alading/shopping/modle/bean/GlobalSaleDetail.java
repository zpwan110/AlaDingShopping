package com.alading.shopping.modle.bean;

/**
 * Created by Administrator on 2015/8/26.
 *
 * 全球特卖信息
 */
public class GlobalSaleDetail {
    private int days;//	天	number
    private int hour;//	时	number
    private String info;//	信息	string
    private int minute;//分	number
    private int second;//秒 number


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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}

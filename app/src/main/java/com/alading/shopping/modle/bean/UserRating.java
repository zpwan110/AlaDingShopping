package com.alading.shopping.modle.bean;

/**
 * Created by Administrator on 2015/8/29.
 */
public class UserRating {
    //-----------------member-start-------------
    private String username;
    private String nickname;
    //-----------------member-end-------------
    private String content;
    private String createTime;
    private int star;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public UserRating(String username, String nickname, String content, String createTime, int star) {
        this.username = username;
        this.nickname = nickname;
        this.content = content;
        this.createTime = createTime;
        this.star = star;
    }

    @Override
    public String toString() {
        return "UserRating{" +
                "username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", star=" + star +
                '}';
    }
}

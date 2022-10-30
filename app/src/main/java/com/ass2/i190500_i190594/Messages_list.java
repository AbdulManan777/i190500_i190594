package com.ass2.i190500_i190594;

public class Messages_list {
    String name, mobile, lastMsg, profile_pic;
    int unseenMsg;

    public Messages_list(String name, String mobile, String lastMsg,String profile_pic, int unseenMsg) {
        this.name = name;
        this.mobile = mobile;
        this.lastMsg = lastMsg;
        this.profile_pic = profile_pic;
        this.unseenMsg = unseenMsg;
    }

    public String getName() {
        return name;
    }

    public String getProfile_pic() { return profile_pic; }

    public String getMobile() {
        return mobile;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public int getUnseenMsg() {
        return unseenMsg;
    }
}

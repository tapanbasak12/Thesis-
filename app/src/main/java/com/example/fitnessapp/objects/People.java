package com.example.fitnessapp.objects;

public class People {
    private String key;
    private String cid;
    private String uid;


    public People(String key, String cid, String uid) {
        this.key = key;
        this.cid = cid;
        this.uid = uid;
    }

    public String getKey() {
        return key;
    }

    public String getCid() {
        return cid;
    }

    public String getUid() {
        return uid;
    }
}

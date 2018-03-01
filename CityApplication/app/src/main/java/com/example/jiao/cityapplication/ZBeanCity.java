package com.example.jiao.cityapplication;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by jiao3 on 2018/3/1.
 */

public class ZBeanCity extends RealmObject implements Serializable{


    /**
     * id : 1
     * code : 10000
     * parent : 0
     * name : 中国
     * type : 0
     * latitude :
     * longitude :
     * deltime : 0
     */

    private String id;
    private String code;
    private String parent;
    private String name;
    private String type;
    private String latitude;
    private String longitude;
    private String deltime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDeltime() {
        return deltime;
    }

    public void setDeltime(String deltime) {
        this.deltime = deltime;
    }
}

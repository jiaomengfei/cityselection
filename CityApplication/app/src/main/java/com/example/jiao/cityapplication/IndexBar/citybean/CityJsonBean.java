package com.example.jiao.cityapplication.IndexBar.citybean;

import android.os.Parcelable;

import java.io.Serializable;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by jiao3 on 2018/2/26.
 */

public class CityJsonBean  implements Serializable{
    private int id;
    private int code;

    @Override
    public String toString() {
        return "CityJsonBean{" +
                "id=" + id +
                ", code=" + code +
                ", parent=" + parent +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", deltime='" + deltime + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
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

    private int parent;
    private String  name;
    private String type;
    private String latitude;
    private String longitude;
    private String deltime;

}

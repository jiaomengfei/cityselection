package com.example.jiao.cityapplication;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by jiao3 on 2018/2/26.
 */

public class CityJsonBean implements Serializable{
    private String id;
    private String code;
    private int parent;
    private String  name;

    @Override
    public String toString() {
        return "CityJsonBean{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", parent='" + parent + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    private String type;

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
}

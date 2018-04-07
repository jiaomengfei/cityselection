package com.example.jiao.cityapplication.IndexBar.citybean;


import com.example.jiao.cityapplication.IndexBar.BaseIndexPinyinBean;

import java.io.Serializable;

public class CityBean extends BaseIndexPinyinBean implements Serializable{
    private String city;//城市名字

    @Override
    public String toString() {
        return "CityBean{" +
                "city='" + city + '\'' +
                '}';
    }

    public CityBean() {
    }

    public CityBean(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public CityBean setCity(String city) {
        this.city = city;
        return this;
    }

    @Override
    public String getTarget() {
        return city;
    }
}

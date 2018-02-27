package com.example.jiao.cityapplication;

import android.content.Context;

import java.util.List;


public class CityAdapter extends CommonAdapter<CityBean> {
    public CityAdapter(Context context, int layoutId, List<CityBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, final CityBean cityBean) {
        holder.setText(R.id.tvCity, cityBean.getCity());
    }
}
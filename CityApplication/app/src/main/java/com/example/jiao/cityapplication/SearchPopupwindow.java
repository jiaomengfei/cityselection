package com.example.jiao.cityapplication;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup.LayoutParams;

/**
 * Created by jmf on 2018/3/2.
 */

public class SearchPopupwindow extends PopupWindow {

    private Handler mHandler;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private final ExpandableListView mExpandableLV;

    public SearchPopupwindow(Context mContext , Handler mHandler) {
        super();
        this.mContext=mContext;
        this.mHandler=mHandler;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.city_result_popup, null);
        this.setWidth(LayoutParams.MATCH_PARENT);
        this.setHeight(LayoutParams.MATCH_PARENT);
        this.setContentView(view);
        mExpandableLV = view.findViewById(R.id.search_expandlv);
        this.setFocusable(false);
    }

    @Override
    public void showAsDropDown(View anchor) {
        super.showAsDropDown(anchor);
        mExpandableLV.setGroupIndicator(null);
        loadData();
    }

    private void loadData() {
    }
}

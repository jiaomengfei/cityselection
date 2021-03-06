package com.example.jiao.cityapplication.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup.LayoutParams;

import com.example.jiao.cityapplication.R;

/**
 * Created by jmf on 2018/3/2.
 */

public class CoverPopupwindow extends PopupWindow {

    private Handler mHandler;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private final ExpandableListView mExpandableLV;

    public CoverPopupwindow(Context mContext) {
        super();
        this.mContext=mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.city_result_popup, null);
        this.setWidth(LayoutParams.MATCH_PARENT);
        this.setHeight(LayoutParams.MATCH_PARENT);
        this.setContentView(view);
        mExpandableLV = view.findViewById(R.id.search_expandlv);
        this.setOutsideTouchable(true);
        this.setFocusable(false);
        ColorDrawable dw = new ColorDrawable(0x90000000);
        this.setBackgroundDrawable(dw);
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

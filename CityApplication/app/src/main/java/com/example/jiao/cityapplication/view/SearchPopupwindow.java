package com.example.jiao.cityapplication.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup.LayoutParams;

import com.example.jiao.cityapplication.R;

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
        mContext=mContext;
        this.mHandler=mHandler;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.city_result_popup, null);
        this.setWidth(LayoutParams.MATCH_PARENT);
        this.setHeight(LayoutParams.MATCH_PARENT);
        this.setContentView(view);
        mExpandableLV = view.findViewById(R.id.search_expandlv);
        this.setOutsideTouchable(false);
//        this.setFocusable(false);
//        ColorDrawable dw = new ColorDrawable(0x90000000);
//        this.setBackgroundDrawable(dw);
        this.setTouchable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setFocusable(true);
        //这句是为了防止弹出菜单获取焦点之后，点击activity的其他组件没有响应

        //防止虚拟软键盘被弹出菜单遮住
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
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

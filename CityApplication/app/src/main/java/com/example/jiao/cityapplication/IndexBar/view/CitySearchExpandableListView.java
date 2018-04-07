package com.example.jiao.cityapplication.IndexBar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by jmf on 2018/3/2.
 */

public class CitySearchExpandableListView extends ExpandableListView {

    public CitySearchExpandableListView(Context context) {
        super(context);
    }

    public CitySearchExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int expandSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec,expandSpec);

    }
}

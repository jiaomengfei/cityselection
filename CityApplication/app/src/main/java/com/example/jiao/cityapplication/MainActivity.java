package com.example.jiao.cityapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ChinessCityFragment mChinessCityFragment;
    private InternationalCityFragment mInternationalCityFragment;
    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mTitles;
    private CityFragmentAdapter mAdapter;
    private ViewPager mViewPager;
    private TabLayout mTab;
    private int prepareShowPosition = 0;
    private FrameLayout mCover;
    private EditText mEtSearch;

    public int prepareToShowPosition() {
        return prepareShowPosition;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
    }

    private void initView() {

        mTab = (TabLayout) findViewById(R.id.city_search_tab);
        LinearLayout linearLayout = (LinearLayout) mTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,R.drawable.layout_divider_vertical));
        linearLayout.setDividerPadding(DensityUtil.dip2px(this,5));
        mViewPager= (ViewPager) findViewById(R.id.city_search_vp);
        mEtSearch = (EditText) findViewById(R.id.et_search);
        mCover = (FrameLayout) findViewById(R.id.cover);
        mCover.setOnClickListener(this);
        mEtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mCover.setVisibility(View.VISIBLE);
                    InputMethodManager imm = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                }else{
                    mCover.setVisibility(View.GONE);
                    InputMethodManager imm = ( InputMethodManager ) MainActivity.this.getSystemService( Context.INPUT_METHOD_SERVICE );
                    if ( imm.isActive( ) ) {
                        imm.hideSoftInputFromWindow( v.getApplicationWindowToken( ) , 0 );

                    }
                }
            }
        });
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mChinessCityFragment=new ChinessCityFragment();
        mInternationalCityFragment=new InternationalCityFragment();
        mFragments.add(mChinessCityFragment);
        mFragments.add(mInternationalCityFragment);
        mTitles = new ArrayList<>();
        mTitles.add("国内");
        mTitles.add("国际");
        mAdapter=new CityFragmentAdapter(getSupportFragmentManager(),mFragments,mTitles);
        mViewPager.setAdapter(mAdapter);
//        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                prepareShowPosition = position;
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
        mTab.setTabTextColors(R.color.tabs_text_clolor
                ,R.color.colorAccent);
        mTab.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cover:
                mCover.setVisibility(View.GONE);
                mEtSearch.clearFocus();
                InputMethodManager imm = ( InputMethodManager ) MainActivity.this.getSystemService( Context.INPUT_METHOD_SERVICE );
                if ( imm.isActive( ) ) {
                    imm.hideSoftInputFromWindow( v.getApplicationWindowToken( ) , 0 );

                }
                break;
        }
    }
}

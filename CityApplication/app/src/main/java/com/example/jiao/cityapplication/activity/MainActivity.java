package com.example.jiao.cityapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jiao.cityapplication.fragment.ChinessCityFragment;
import com.example.jiao.cityapplication.fragment.InternationalCityFragment;
import com.example.jiao.cityapplication.R;
import com.example.jiao.cityapplication.IndexBar.adapter.CityFragmentAdapter;
import com.example.jiao.cityapplication.IndexBar.citybean.CityJsonBean;

import java.util.ArrayList;

import utils.DensityUtil;

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
    private TextView mCancel;
    private TextView mCancelRight;
    public static final String KEY_PICKED_CITY = "picked_city";
    private ArrayList<CityJsonBean> arrayList;
    private LinearLayout mSearchTop;
 //   private SearchPopupwindow mSearchPopupwindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
//        Intent intent =getIntent();
//        Bundle bundle=intent.getExtras();
//        arrayList = (ArrayList<CityJsonBean>) bundle.getSerializable("cityData");
        initFragment();
    }

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private void initView() {
        mCancel = (TextView) findViewById(R.id.tv_search_cancel);
        mCancelRight = (TextView) findViewById(R.id.tv_search_cancel_right);
        mTab = (TabLayout) findViewById(R.id.city_search_tab);
        LinearLayout linearLayout = (LinearLayout) mTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,R.drawable.layout_divider_vertical));
        linearLayout.setDividerPadding(DensityUtil.dip2px(this,5));
        mViewPager= (ViewPager) findViewById(R.id.city_search_vp);
        mEtSearch = (EditText) findViewById(R.id.et_search);
        mCover = (FrameLayout) findViewById(R.id.cover);
        mSearchTop = (LinearLayout) findViewById(R.id.search_title);
        //mSearchPopupwindow=new SearchPopupwindow(this,mHandler);
        mCover.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        mCancelRight.setOnClickListener(this);
        mEtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus){
//                    mCancel.setVisibility(View.GONE);
//                    mCancelRight.setVisibility(View.VISIBLE);
//                    mCover.setVisibility(View.VISIBLE);
//                    InputMethodManager imm = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//                }else{
//                    mCancel.setVisibility(View.VISIBLE);
//                    mCancelRight.setVisibility(View.GONE);
//                    mCover.setVisibility(View.GONE);
//                    InputMethodManager imm = ( InputMethodManager ) MainActivity.this.getSystemService( Context.INPUT_METHOD_SERVICE );
//                    if ( imm.isActive( ) ) {
//                        imm.hideSoftInputFromWindow( v.getApplicationWindowToken( ) , 0 );
//
//                    }
               // }
            }
        });

        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //mCover.setVisibility(View.GONE);

//                if(null==mSearchPopupwindow){
//                    return;
//                }
//                if(mSearchPopupwindow.isShowing()){
//                  //  mSearchPopupwindow.dismiss();
//                }else{
//                    mSearchPopupwindow.showAsDropDown(mSearchTop);
//                    mEtSearch.setFocusable(true);
//                    InputMethodManager imm = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//                }
            }

            @Override
            public void afterTextChanged(Editable s) {
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
            case R.id.tv_search_cancel:
                mEtSearch.getText().clear();
                finish();
                break;
            case R.id.tv_search_cancel_right:
                mEtSearch.getText().clear();
                mEtSearch.clearFocus();
//                if (null != mSearchPopupwindow) {
//                    mSearchPopupwindow.dismiss();
//                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (null != mSearchPopupwindow) {
//            mSearchPopupwindow.dismiss();
//        }
    }
}

package com.example.jiao.cityapplication.activity

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.design.widget.TabLayout
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView

import com.example.jiao.cityapplication.fragment.InternationalCityFragment
import com.example.jiao.cityapplication.R
import com.example.jiao.cityapplication.IndexBar.adapter.CityFragmentAdapter
import com.example.jiao.cityapplication.IndexBar.citybean.CityJsonBean
import com.example.jiao.cityapplication.fragment.ChinessCityFragment

import java.util.ArrayList

import utils.DensityUtil

class MainActivity : AppCompatActivity(), View.OnClickListener {

  private var mChinessCityFragment: ChinessCityFragment? = null
  private var mInternationalCityFragment: InternationalCityFragment? = null
  private var mFragments: ArrayList<Fragment>? = null
  private var mTitles: ArrayList<String>? = null
  private var mAdapter: CityFragmentAdapter? = null
  private var mViewPager: ViewPager? = null
  private var mTab: TabLayout? = null
  private val prepareShowPosition = 0
  private var mCover: FrameLayout? = null
  private var mEtSearch: EditText? = null
  private var mCancel: TextView? = null
  private var mCancelRight: TextView? = null
  private val arrayList: ArrayList<CityJsonBean>? = null
  private var mSearchTop: LinearLayout? = null

  private val mHandler = object : Handler() {
    override fun handleMessage(msg: Message) {
      super.handleMessage(msg)
    }
  }
  //   private SearchPopupwindow mSearchPopupwindow;

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    setContentView(R.layout.activity_main)
    initView()
    //        Intent intent =getIntent();
    //        Bundle bundle=intent.getExtras();
    //        arrayList = (ArrayList<CityJsonBean>) bundle.getSerializable("cityData");
    initFragment()
  }

  private fun initView() {
    mCancel = findViewById(R.id.tv_search_cancel) as TextView
    mCancelRight = findViewById(R.id.tv_search_cancel_right) as TextView
    mTab = findViewById(R.id.city_search_tab) as TabLayout
    val linearLayout = mTab!!.getChildAt(0) as LinearLayout
    linearLayout.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
    linearLayout.dividerDrawable = ContextCompat.getDrawable(
        this, R.drawable.layout_divider_vertical
    )
    linearLayout.dividerPadding = DensityUtil.dip2px(this, 5f)
    mViewPager = findViewById(R.id.city_search_vp) as ViewPager
    mEtSearch = findViewById(R.id.et_search) as EditText
    mCover = findViewById(R.id.cover) as FrameLayout
    mSearchTop = findViewById(R.id.search_title) as LinearLayout
    //mSearchPopupwindow=new SearchPopupwindow(this,mHandler);
    mCover!!.setOnClickListener(this)
    mCancel!!.setOnClickListener(this)
    mCancelRight!!.setOnClickListener(this)
    mEtSearch!!.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
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

    mEtSearch!!.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(
        s: CharSequence,
        start: Int,
        count: Int,
        after: Int
      ) {

      }

      override fun onTextChanged(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
      ) {
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

      override fun afterTextChanged(s: Editable) {}
    })
  }

  private fun initFragment() {
    mFragments = ArrayList()
    mChinessCityFragment = ChinessCityFragment()
    mInternationalCityFragment = InternationalCityFragment()
    mFragments!!.add(mChinessCityFragment!!)
    mFragments!!.add(mInternationalCityFragment!!)

    mTitles = ArrayList()
    mTitles!!.add("国内")
    mTitles!!.add("国际")
    mAdapter = CityFragmentAdapter(supportFragmentManager, mFragments, mTitles)
    mViewPager!!.adapter = mAdapter

    mTab!!.setTabTextColors(R.color.tabs_text_clolor, R.color.colorAccent)
    mTab!!.setupWithViewPager(mViewPager)
  }

  override fun onClick(v: View) {
    when (v.id) {
      R.id.cover -> {
        mCover!!.visibility = View.GONE
        mEtSearch!!.clearFocus()
        val imm = this@MainActivity.getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        if (imm.isActive) {
          imm.hideSoftInputFromWindow(v.applicationWindowToken, 0)

        }
      }
      R.id.tv_search_cancel -> {
        mEtSearch!!.text.clear()
        finish()
      }
      R.id.tv_search_cancel_right -> {
        mEtSearch!!.text.clear()
        mEtSearch!!.clearFocus()
      }
    }//                if (null != mSearchPopupwindow) {
    //                    mSearchPopupwindow.dismiss();
    //                }
  }

  override fun onDestroy() {
    super.onDestroy()
    //        if (null != mSearchPopupwindow) {
    //            mSearchPopupwindow.dismiss();
    //        }
  }

  companion object {
    val KEY_PICKED_CITY = "picked_city"
  }
}

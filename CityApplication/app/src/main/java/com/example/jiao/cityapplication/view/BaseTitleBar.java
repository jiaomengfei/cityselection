package com.example.jiao.cityapplication.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jiao.cityapplication.R;

import utils.DensityUtil;


/**
 * Created by  on 2016/7/21.
 */
public class BaseTitleBar extends FrameLayout implements View.OnClickListener {
	private Context mContext;
	private TextView mTitleTextView;
	private TextView mMainTitleTextView;

	private OnClickListener mOnClickListener;
	private TextView mSubMainTitleTextView;
	private ImageView phoneIcon;
	private ImageView gotoIcon;

	public BaseTitleBar(Context context) {
		this(context, null);
		this.mContext=context;
	}

	public BaseTitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext=context;
		initView(context, attrs);
	}

	public BaseTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.mContext=context;
	}

	private void initView(Context context, AttributeSet attrs) {
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.BaseTitleBar);

		String mainTitleText = array
				.getString(R.styleable.BaseTitleBar_baseMainTitle);
		boolean isMainTitleShow = array.getBoolean(
				R.styleable.BaseTitleBar_isBaseMainTitleShow, true);

		String titleText = array.getString(R.styleable.BaseTitleBar_baseTitle);
		boolean isTitleShow = array.getBoolean(
				R.styleable.BaseTitleBar_isBaseTitleShow, true);
		boolean isBackIconShow = array.getBoolean(
				R.styleable.BaseTitleBar_isBaseBackIconShow, true);
		@SuppressWarnings("unused")
		boolean isYPShow = array.getBoolean(
				R.styleable.BaseTitleBar_isBaseYPShow, true);
		@SuppressWarnings("unused")
		boolean isPhoneShow = array.getBoolean(
				R.styleable.BaseTitleBar_isBasePhoneShow, true);
		
		@SuppressWarnings("deprecation")
		int textColor = array.getColor(R.styleable.BaseTitleBar_baseTextColor,
				getResources().getColor(R.color.color_cccccc));
		@SuppressWarnings("deprecation")
		int bgColor = array.getColor(R.styleable.BaseTitleBar_baseBgColor,
				getResources().getColor(R.color.callme_green_top));
		array.recycle();
		View view = View.inflate(context, R.layout.callme_common_title_bar,
				null);

		addView(view);

		ImageView backIcon = (ImageView) findViewById(R.id.callme_back);
		backIcon.setOnClickListener(this);
		backIcon.setVisibility(isBackIconShow ? VISIBLE : GONE);

		mTitleTextView = (TextView) findViewById(R.id.callme_title_text);
		mTitleTextView.setText(titleText);
		mTitleTextView.setVisibility(isTitleShow ? VISIBLE : GONE);
		mTitleTextView.setTextColor(textColor);

		mMainTitleTextView = (TextView) findViewById(R.id.callme_main_title_text);
		mMainTitleTextView.setText(mainTitleText);
		mMainTitleTextView.setVisibility(isMainTitleShow ? VISIBLE : GONE);
		mMainTitleTextView.setTextColor(textColor);

		mSubMainTitleTextView = (TextView) findViewById(R.id.callme_sub_main_title_text);
		mSubMainTitleTextView.setTextColor(textColor);
		
		phoneIcon = (ImageView) findViewById(R.id.callme_phone);
		phoneIcon.setVisibility(View.INVISIBLE);
		phoneIcon.setOnClickListener(this);
				
		gotoIcon = (ImageView) findViewById(R.id.callme_goto);
		gotoIcon.setVisibility(View.INVISIBLE);
		gotoIcon.setOnClickListener(this);
				
		LinearLayout titleBarContainer = (LinearLayout) findViewById(R.id.callme_title_bar_container);
		view.setBackgroundColor(bgColor);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.callme_back) {
			((Activity) getContext()).finish();
		} else if (v.getId() == R.id.callme_phone && mOnClickListener != null) {
			this.mOnClickListener.onClick(v);
		}else if(v.getId()==R.id.callme_goto && mOnClickListener != null){
			this.mOnClickListener.onClick(v);
		}
	}

	public void setTitle(String title) {
		mTitleTextView.setText(title);
	}

	public String getTitle() {
		return mTitleTextView.getText().toString();
	}

	public void setLogo(Bitmap bitmap) {
		RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
		drawable.setCornerRadius(DensityUtil.dip2px(this.mContext,17.5f));
		if (null != drawable) {
			drawable.setBounds(0, 0, DensityUtil.dip2px(this.mContext,35), DensityUtil.dip2px(this.mContext,35));
			mTitleTextView.setCompoundDrawables(drawable, null, null, null);
		}
	}

	public void setMainTitle(String mainTitle) {
		mMainTitleTextView.setText(mainTitle);
	}

	public String getMainTitle() {
		return mMainTitleTextView.getText().toString();
	}

	public void setSubMainTitle(String title) {
		mSubMainTitleTextView.setText(title);
	}

	public void setSubMainTitleVisibility(int visibility) {
		mSubMainTitleTextView.setVisibility(visibility);
	}

	public void setSubMainTitleTextSize(int size) {
		this.mSubMainTitleTextView.setTextSize(size);
	}

	public void setPhoneOnClickListener(OnClickListener mOnClickListener) {
		this.mOnClickListener = mOnClickListener;
	}

	public void setMainTitleVisibility(int visibility) {
		mMainTitleTextView.setVisibility(visibility);
	}
	
	public void setPhoneIconVisibility(int visibility){
		phoneIcon.setVisibility(visibility);
	}
	
	public void setGotoIconVisibility(int visibility){
		gotoIcon.setVisibility(visibility);
	}
	
	public void setTitleTextViewVisibility(int visibility){
		mTitleTextView.setVisibility(visibility);
	}
	
}

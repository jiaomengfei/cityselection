package com.example.jiao.cityapplication.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.jiao.cityapplication.IndexBar.BaseIndexPinyinBean;
import com.example.jiao.cityapplication.IndexBar.IndexBar;
import com.example.jiao.cityapplication.IndexBar.SuspensionDecoration;
import com.example.jiao.cityapplication.IndexBar.adapter.OnItemClickListener;
import com.example.jiao.cityapplication.R;
import com.example.jiao.cityapplication.IndexBar.adapter.ViewHolder;
import com.example.jiao.cityapplication.ZBeanCity;
import com.example.jiao.cityapplication.IndexBar.adapter.CityAdapter;
import com.example.jiao.cityapplication.IndexBar.adapter.CommonAdapter;
import com.example.jiao.cityapplication.IndexBar.citybean.CityBean;
import com.example.jiao.cityapplication.IndexBar.citybean.CityHeaderBean;
import com.example.jiao.cityapplication.IndexBar.view.DividerItemDecoration;
import com.example.jiao.cityapplication.IndexBar.view.HeaderRecyclerAndFooterWrapperAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import utils.DataUtil;

import static android.app.Activity.RESULT_OK;
import static com.example.jiao.cityapplication.activity.MainActivity.KEY_PICKED_CITY;


public class ChinessCityFragment extends Fragment {


    private IndexBar mIndexBar;
    private RecyclerView mRv;
    private TextView mTvSideBarHint;
    private LinearLayoutManager mManager;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private CityAdapter mAdapter;
    //主体部分数据源（城市数据）
    private List<CityBean> mBodyDatas;
    //头部数据源
    private List<CityHeaderBean> mHeaderDatas;
    //设置给InexBar、ItemDecoration的完整数据集
    private List<BaseIndexPinyinBean> mSourceDatas;
    private SuspensionDecoration mDecoration;
    private FrameLayout mCover;
    private LinkedList<String> mHistoryCity;
    private RealmResults<ZBeanCity> mResultList;
    public Realm myRealm;

//    static class MyHandler extends Handler {
//        private final WeakReference<ChinessCityFragment> reference;
//
//        public MyHandler(ChinessCityFragment cityFragment) {
//            reference = new WeakReference<>(cityFragment);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            int what = msg.what;
//            switch (what) {
//                case 111:
//                    ArrayList<ZBeanCity> data = (ArrayList<ZBeanCity>) msg.obj;
//                    ChinessCityFragment cityFragment = reference.get();
//                    if (cityFragment != null) {
//                        cityFragment.initDatas(data);
//                    }
//                    break;
//            }
//        }
//    }
//
//    private MyHandler myHandler;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //myHandler = new MyHandler(this);
    }
    public ChinessCityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myRealm = Realm.getDefaultInstance();
        initData();
    }

    private void initData() {
        mSourceDatas = new ArrayList<>();
        mHeaderDatas = new ArrayList<>();
        mHistoryCity = new LinkedList<>();

        LinkedList<String> hCitys = new LinkedList<>();
        LinkedList<String> citys= DataUtil.getHistoryCityName(getActivity(),"china_history_nums","china_item");
        mHistoryCity.add("北京");

        if (citys.size()>3){
            for(int i=0;i<3;i++){
                hCitys.add(citys.get(i));
            }
            mHistoryCity.addAll(hCitys);
        }else{
            mHistoryCity.addAll(citys);
        }
        mHeaderDatas.add(new CityHeaderBean(mHistoryCity, "历史", "历史"));
        mSourceDatas.addAll(mHeaderDatas);
        getDatas();
    }



    private void getDatas() {
        mResultList = myRealm.where(ZBeanCity.class).findAllAsync();
        mResultList.addChangeListener(new RealmChangeListener<RealmResults<ZBeanCity>>() {
            @Override
            public void onChange(RealmResults<ZBeanCity> element) {
                element= element.sort("id");
                List<ZBeanCity> datas=  myRealm.copyFromRealm(element);
                initDatas(datas);
            }
        });

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chiness_city, container, false);
        //mCover = view.findViewById(R.id.cover);
        mTvSideBarHint = (TextView) view.findViewById(R.id.tvSideBarHint);
        mRv = (RecyclerView)view.findViewById(R.id.rv);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(getActivity()));
        mAdapter = new CityAdapter(getActivity(), R.layout.meituan_item_select_city, mBodyDatas);
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                switch (layoutId) {
                    case R.layout.meituan_item_header:
                        final CityHeaderBean meituanHeaderBean = (CityHeaderBean) o;
                        //网格
                        RecyclerView recyclerView = holder.getView(R.id.rvCity);
                        recyclerView.setAdapter(
                                new CommonAdapter<String>(getActivity(), R.layout.city_item_header_item, meituanHeaderBean.getCityList()) {
                                    @Override
                                    public void convert(ViewHolder holder, final String cityName) {
                                        holder.setText(R.id.tvName, cityName);
                                        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                backWithData(cityName);
                                            }
                                        });
                                    }
                                });
                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
                        break;
                    default:
                        break;
                }
            }
        };
        mHeaderAdapter.setHeaderView(0,R.layout.meituan_item_header,mHeaderDatas.get(0));
        mRv.setAdapter(mHeaderAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                backWithData(mBodyDatas.get(position).getCity());
                mHistoryCity.add(1,mBodyDatas.get(position).getCity());
                mHistoryCity.remove(0);
                DataUtil.updateHistoryCityName(getActivity(),mHistoryCity,"china_history_nums","china_item");
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(getActivity(), mSourceDatas)
                .setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics()))
                .setColorTitleBg(0xffefefef)
                .setTitleFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()))
                .setColorTitleFont(getActivity().getResources().getColor(android.R.color.black))
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size()));
        mRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        mIndexBar = view.findViewById(R.id.indexBar);
        mIndexBar.setmPressedShowTextView(mTvSideBarHint)
                 .setNeedRealIndex(true)//设置需要真实的索引
                 .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                 .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size());
        return view;
    }

    private void backWithData(String city){
        Intent data = new Intent();
        data.putExtra(KEY_PICKED_CITY, city);
        getActivity().setResult(RESULT_OK, data);
        getActivity().finish();
    }

    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final List<ZBeanCity> data) {
        //延迟两秒 模拟加载数据中....
        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                mBodyDatas = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    CityBean cityBean = new CityBean();
                    cityBean.setCity(data.get(i).getName());//设置城市名称
                    mBodyDatas.add(cityBean);
                }
                //先排序
                mIndexBar.getDataHelper().sortSourceDatas(mBodyDatas);
                //只能使用List的子类
                mAdapter.setDatas(mBodyDatas);
                mHeaderAdapter.notifyDataSetChanged();
                mSourceDatas.addAll(mBodyDatas);

                mIndexBar.setmSourceDatas(mSourceDatas)//设置数据
                        .invalidate();
                mDecoration.setmDatas(mSourceDatas);
            }
        }, 1);

    }


}

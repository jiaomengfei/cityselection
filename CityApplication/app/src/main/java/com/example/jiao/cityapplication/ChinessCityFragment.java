package com.example.jiao.cityapplication;


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
import android.widget.Toast;

import com.example.jiao.cityapplication.IndexBar.BaseIndexPinyinBean;
import com.example.jiao.cityapplication.IndexBar.IndexBar;
import com.example.jiao.cityapplication.IndexBar.SuspensionDecoration;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;



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
    private List<MeituanHeaderBean> mHeaderDatas;
    //设置给InexBar、ItemDecoration的完整数据集
    private List<BaseIndexPinyinBean> mSourceDatas;
    private SuspensionDecoration mDecoration;
    private FrameLayout mCover;

    public ChinessCityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSourceDatas = new ArrayList<>();
        mHeaderDatas = new ArrayList<>();
        List<String> locationCity = new ArrayList<>();
        locationCity.add("定位中");
        locationCity.add("乌鲁木齐");
        locationCity.add("上海");
        locationCity.add("天津");
        mHeaderDatas.add(new MeituanHeaderBean(locationCity, "历史", "历史"));
//        List<String> recentCitys = new ArrayList<>();
//        mHeaderDatas.add(new MeituanHeaderBean(recentCitys, "最近访问城市", "近"));
//        List<String> hotCitys = new ArrayList<>();
//        mHeaderDatas.add(new MeituanHeaderBean(hotCitys, "热门城市", "热"));
        mSourceDatas.addAll(mHeaderDatas);
        getDatas();

    }

    private void getDatas() {
        String cityDatas=ReadFromAssets.ReadJsonFile("city.json",getActivity());
        Gson gson=new Gson();
        ArrayList<CityJsonBean> arrayList= (ArrayList) jsonToArrayList(cityDatas,CityJsonBean.class);
        initDatas(arrayList);
    }

    public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz)
    {
        Type type = new TypeToken<ArrayList<JsonObject>>()
        {}.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);

        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects)
        {
            arrayList.add(new Gson().fromJson(jsonObject, clazz));
        }
        return arrayList;
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
                        final MeituanHeaderBean meituanHeaderBean = (MeituanHeaderBean) o;
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
                                                Toast.makeText(mContext, "cityName:" + cityName, Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                });
                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
                        break;
//                    case R.layout.meituan_item_header_top:
//                        MeituanTopHeaderBean meituanTopHeaderBean = (MeituanTopHeaderBean) o;
//                        holder.setText(R.id.tvCurrent, meituanTopHeaderBean.getTxt());
//                        break;
                    default:
                        break;
                }
            }
        };
        mHeaderAdapter.setHeaderView(0,R.layout.meituan_item_header,mHeaderDatas.get(0));
       // mHeaderAdapter.setHeaderView(0, R.layout.meituan_item_header_top, new MeituanTopHeaderBean("当前：上海徐汇"));
//        mHeaderAdapter.setHeaderView(0, R.layout.meituan_item_header, mHeaderDatas.get(0));
//        mHeaderAdapter.setHeaderView(1, R.layout.meituan_item_header, mHeaderDatas.get(1));
//        mHeaderAdapter.setHeaderView(2, R.layout.meituan_item_header, mHeaderDatas.get(2));
        mRv.setAdapter(mHeaderAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Toast.makeText(getActivity(), "pos:" + mBodyDatas.get(position), Toast.LENGTH_SHORT).show();
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

    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final ArrayList<CityJsonBean> data) {
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

                mAdapter.setDatas(mBodyDatas);
                mHeaderAdapter.notifyDataSetChanged();
                mSourceDatas.addAll(mBodyDatas);

                mIndexBar.setmSourceDatas(mSourceDatas)//设置数据
                        .invalidate();
                mDecoration.setmDatas(mSourceDatas);
            }
        }, 1);

        //延迟两秒加载头部
//        getActivity().getWindow().getDecorView().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                MeituanHeaderBean header1 = mHeaderDatas.get(0);
//                header1.getCityList().clear();
//                header1.getCityList().add("上海");
//
//                MeituanHeaderBean header2 = mHeaderDatas.get(1);
//                List<String> recentCitys = new ArrayList<>();
//                recentCitys.add("日本");
//                recentCitys.add("北京");
//                header2.setCityList(recentCitys);
//
//                MeituanHeaderBean header3 = mHeaderDatas.get(2);
//                List<String> hotCitys = new ArrayList<>();
//                hotCitys.add("上海");
//                hotCitys.add("北京");
//                hotCitys.add("杭州");
//                hotCitys.add("广州");
//                header3.setCityList(hotCitys);
//
//                mHeaderAdapter.notifyItemRangeChanged(1, 3);
//
//            }
//        }, 2000);

    }


}

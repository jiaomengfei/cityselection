package com.example.jiao.cityapplication;


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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.example.jiao.cityapplication.MainActivity.KEY_PICKED_CITY;


public class InternationalCityFragment extends Fragment {

    private IndexBar mInIndexBar;
    private RecyclerView mInRv;
    private TextView mInSideBarHint;
    private LinearLayoutManager mInManager;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private CityAdapter mInAdapter;
    //主体部分数据源（城市数据）
    private List<CityBean> mBodyDatas;
    //头部数据源
    private List<CityHeaderBean> mHeaderDatas;
    //设置给InexBar、ItemDecoration的完整数据集
    private List<BaseIndexPinyinBean> mSourceDatas;
    private SuspensionDecoration mDecoration;
    private FrameLayout mCover;
    private LinkedList<String> mHistoryCity;
    public InternationalCityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_international_city, container, false);
        mInSideBarHint = (TextView) view.findViewById(R.id.inSideBarHint);
        mInRv = (RecyclerView)view.findViewById(R.id.in_rv);
        mInRv.setLayoutManager(mInManager = new LinearLayoutManager(getActivity()));
        mInAdapter = new CityAdapter(getActivity(), R.layout.meituan_item_select_city, mBodyDatas);
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mInAdapter) {
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
        // mHeaderAdapter.setHeaderView(0, R.layout.meituan_item_header_top, new MeituanTopHeaderBean("当前：上海徐汇"));
//        mHeaderAdapter.setHeaderView(0, R.layout.meituan_item_header, mHeaderDatas.get(0));
//        mHeaderAdapter.setHeaderView(1, R.layout.meituan_item_header, mHeaderDatas.get(1));
//        mHeaderAdapter.setHeaderView(2, R.layout.meituan_item_header, mHeaderDatas.get(2));
        mInRv.setAdapter(mHeaderAdapter);
        mInAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                backWithData(mBodyDatas.get(position).getCity());
                mHistoryCity.add(1,mBodyDatas.get(position).getCity());
                mHistoryCity.remove(0);
                DataUtil.updateHistoryCityName(getActivity(),mHistoryCity,"inter_history_nums","item_inter");
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        mInRv.addItemDecoration(mDecoration = new SuspensionDecoration(getActivity(), mSourceDatas)
                .setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics()))
                .setColorTitleBg(0xffefefef)
                .setTitleFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()))
                .setColorTitleFont(getActivity().getResources().getColor(android.R.color.black))
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size()));
        mInRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        mInIndexBar = view.findViewById(R.id.inIndexBar);
        mInIndexBar.setmPressedShowTextView(mInSideBarHint)
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mInManager)//设置RecyclerView的LayoutManager
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size());
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }
    private void backWithData(String city){
        Intent data = new Intent();
        data.putExtra(KEY_PICKED_CITY, city);
        getActivity().setResult(RESULT_OK, data);
        getActivity().finish();
    }
    private void initData() {
        mSourceDatas = new ArrayList<>();
        mHeaderDatas = new ArrayList<>();
        mHistoryCity = new LinkedList<>();

        LinkedList<String> hCitys = new LinkedList<>();
        LinkedList<String> citys=DataUtil.getHistoryCityName(getActivity(),"inter_history_nums","item_inter");
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
        String cityDatas=ReadFromAssets.ReadJsonFile("foreign.json",getActivity());
        ArrayList<CityJsonBean> arrayList=  DataUtil.jsonToArrayList(cityDatas,CityJsonBean.class);
        initDatas(arrayList);
    }

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
                mInIndexBar.getDataHelper().sortSourceDatas(mBodyDatas);

                mInAdapter.setDatas(mBodyDatas);
                mHeaderAdapter.notifyDataSetChanged();
                mSourceDatas.addAll(mBodyDatas);

                mInIndexBar.setmSourceDatas(mSourceDatas)//设置数据
                        .invalidate();
                mDecoration.setmDatas(mSourceDatas);
            }
        }, 1);

    }


}

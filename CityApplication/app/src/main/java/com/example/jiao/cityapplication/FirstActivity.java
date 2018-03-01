package com.example.jiao.cityapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mButton;
    private TextView mDisplay;
    private static final int REQUEST_CODE_PICK_CITY = 112;
    private ArrayList<CityJsonBean> mCityList;
    public Realm myRealm = Realm.getDefaultInstance();
    private Button mSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mButton = (Button) findViewById(R.id.city_choice);
        mSearch = (Button) findViewById(R.id.city_search);
        mDisplay = (TextView) findViewById(R.id.city_display);
        mButton.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
               // String cityDatas=ReadFromAssets.ReadJsonFile("china.json",FirstActivity.this);
//                TypeToken<ArrayList<CityJsonBean>> type = new TypeToken<ArrayList<CityJsonBean>>() {
//                };

//                ZBeanCity zBeanCity=new Gson().fromJson(cityDatas,ZBeanCity.class);
//                    myRealm.beginTransaction();
//                    myRealm.copyToRealm(zBeanCity);
//                    myRealm.commitTransaction();
//                    myRealm.createObjectFromJson(ZBeanCity.class,cityDatas);




//
        //mCityList=  DataUtil.jsonToArrayList(cityDatas,CityJsonBean.class);

            }
        }).start();

        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    InputStream is = FirstActivity.this.getAssets().open("china.json");
                    realm.createAllFromJson(ZBeanCity.class, is);
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
        });

                //mCityList=  DataUtil.jsonToArrayList(cityDatas,CityJsonBean.class);
        RealmResults<ZBeanCity> userList = myRealm.where(ZBeanCity.class).findAll();
        Log.e("result",userList.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.city_search:
                RealmResults<ZBeanCity> pups = myRealm.where(ZBeanCity.class)
                        .findAll();
                Log.e("result",pups.toString());
                break;
        }
    Intent intent=new Intent(this,MainActivity.class);
        startActivityForResult(intent,
                REQUEST_CODE_PICK_CITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE_PICK_CITY&& resultCode == RESULT_OK){
            if (data != null){
                String city = data.getStringExtra(MainActivity.KEY_PICKED_CITY);
                mDisplay.setText("当前选择：" + city);
            }
        }
    }
}

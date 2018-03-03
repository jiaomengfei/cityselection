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
        Boolean is =DataUtil.getDatabaseConfig(this);
        if(!is){
            createDatabase();
        }

    }
    private void createDatabase() {
        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    InputStream is = FirstActivity.this.getAssets().open("china.json");
                    realm.createAllFromJson(ZBeanCity.class, is);
                    DataUtil.setDatabaseConfig(FirstActivity.this,true);
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.city_search:
//                RealmResults<ZBeanCity> pups = myRealm.where(ZBeanCity.class)
//                        .findAll();
//                Log.e("result",pups.toString());
//                break;
//        }
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

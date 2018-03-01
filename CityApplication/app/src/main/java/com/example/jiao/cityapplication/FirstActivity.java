package com.example.jiao.cityapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mButton;
    private TextView mDisplay;
    private static final int REQUEST_CODE_PICK_CITY = 112;
    private ArrayList<CityJsonBean> mCityList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mButton = (Button) findViewById(R.id.city_choice);
        mDisplay = (TextView) findViewById(R.id.city_display);
        mButton.setOnClickListener(this);


             //   String cityDatas=ReadFromAssets.ReadJsonFile("china.json",FirstActivity.this);
              //  mCityList=  DataUtil.jsonToArrayList(cityDatas,CityJsonBean.class);


    }

    @Override
    public void onClick(View v) {
    Intent intent=new Intent(this,MainActivity.class);
//     Bundle bundle=new Bundle();
//     bundle.putSerializable("cityData",mCityList);
//        intent.putExtras(bundle);
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

package com.example.jiao.cityapplication.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jiao.cityapplication.R;
import com.example.jiao.cityapplication.ZBeanCity;
import com.example.jiao.cityapplication.view.BaseTitleBar;

import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;
import utils.DataUtil;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private TextView mDisplay;
    private static final int REQUEST_CODE_PICK_CITY = 112;
    public Realm myRealm = Realm.getDefaultInstance();
    private Button mView;
    private BaseTitleBar mCommonTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mButton = (Button) findViewById(R.id.city_choice);
        mView = (Button) findViewById(R.id.view_display);
        mDisplay = (TextView) findViewById(R.id.city_display);
        mButton.setOnClickListener(this);
        mView.setOnClickListener(this);
        mCommonTitleBar = (BaseTitleBar) findViewById(R.id.ctbar);
        //initTitleBar();
        Boolean is = DataUtil.getDatabaseConfig(this);
        if (!is) {
            createDatabase();
        }

    }

    private void initTitleBar() {

        mCommonTitleBar.setMainTitle("自定义view");
        mCommonTitleBar.setSubMainTitle("自定义view");
        setDefaultLogo();
    }

    private void setDefaultLogo() {
        InputStream inputStream = null;
        try {
            inputStream = this.getAssets().open("icon_sms_default.png");
            mCommonTitleBar.setLogo(BitmapFactory.decodeStream(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createDatabase() {

        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    InputStream is = FirstActivity.this.getAssets().open("china.json");
                    realm.createAllFromJson(ZBeanCity.class, is);
                    DataUtil.setDatabaseConfig(FirstActivity.this, true);
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.city_choice:
                Intent intent = new Intent(this, MainActivity.class);
                startActivityForResult(intent,
                        REQUEST_CODE_PICK_CITY);
                break;
            case R.id.view_display:
                Intent intent1 = new Intent(this, ViewActivity.class);
                startActivity(intent1);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(MainActivity.KEY_PICKED_CITY);
                mDisplay.setText("当前选择：" + city);
            }
        }
    }
}

package com.example.jiao.cityapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mButton;
    private TextView mDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mButton = (Button) findViewById(R.id.city_choice);
        mDisplay = (TextView) findViewById(R.id.city_display);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    Intent intent=new Intent(this,MainActivity.class);
    startActivity(intent);
    }
}

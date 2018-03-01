package com.example.jiao.cityapplication;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by jiao3 on 2018/3/1.
 */

public class MyApplication extends Application {
    private static Realm mInstance;
    private static RealmConfiguration config;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        //保存在内存中
        config = new RealmConfiguration.Builder()
                 .name("myrealm.realm")//保存在内存中
                .inMemory() .build();
    }

    public static Realm getRealmInstance() {
        if(null == mInstance) {
            synchronized (MyApplication.class) {
                if(null == mInstance) {
                    mInstance = Realm.getInstance(config);
                }
            }
        }
        return mInstance;
    }


}

package com.yhyy.clearedit;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;
import com.yhyy.clearedit.config.AppConfig;
import com.yhyy.clearedit.config.CrashHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyApplication extends MultiDexApplication {
    private static Context context;
    private static MyApplication myApplication;
    private Thread thread;

    public static MyApplication getInstance() {
        // 这里不用判断instance是否为空
        return myApplication;
    }

    @Override
    public void onCreate() {
        MultiDex.install(this);
        super.onCreate();
        myApplication = this;
        context = this.getApplicationContext();
        Stetho.initializeWithDefaults(this);
        CrashHandler.create(context, AppConfig.SAVEFILENAME);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}

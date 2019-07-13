package com.example.administrator.mygridview.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    //在首次创建service时被调用，只运行一次。
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("myService","OnCreate()被调用");
    }
    //在通过startService时，会调用次方法，可以多次运行
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("myService","onStartCommand()被调用");
        return super.onStartCommand(intent, flags, startId);
    }
    //在调用stopService时调用次方法
    @Override
    public void onDestroy() {
        Log.d("myService","onDestroy()被调用");
        super.onDestroy();
    }
}

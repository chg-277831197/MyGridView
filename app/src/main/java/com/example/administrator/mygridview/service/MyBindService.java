package com.example.administrator.mygridview.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.administrator.mygridview.R;

import java.io.IOException;

public class MyBindService extends Service {
    private MediaPlayer player;
    public MyBindService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("MyBindService","onBind()被调用");
       return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyBindService","onCreate()被调用");
        player = new MediaPlayer();
        try {
            player.setDataSource(getResources().openRawResourceFd(R.raw.banhusha));
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyBindService","onStartCommand()被调用");

        return super.onStartCommand(intent, flags, startId);
    }

    public  void startMeida(){
        player.start();
    }
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("MyBindService","onUnbind()被调用");
        player.stop();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d("MyBindService","onDestroy()被调用");
        super.onDestroy();
    }

    public class MyBinder extends Binder{

        public MyBindService getBinderService(){
            Log.d("MyBindService","getBinderService()被调用");
            return MyBindService.this;
        }
    }

}

package com.example.administrator.mygridview.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.mygridview.R;
import com.example.administrator.mygridview.service.MyBindService;
import com.example.administrator.mygridview.service.MyMediaService;
import com.example.administrator.mygridview.service.MyService;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private Button start_button,stop_button,bind_button,unbing_button;
    private MyBindService bindService;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBindService.MyBinder myBinder = (MyBindService.MyBinder) service;
            bindService = myBinder.getBinderService();
            bindService.startMeida();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("connection","连接失败");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        init();
    }

    private void init() {
        start_button = findViewById(R.id.start_service);
        stop_button = findViewById(R.id.stop_service);
        bind_button= findViewById(R.id.bind_service);
        unbing_button = findViewById(R.id.unbind_service);

        setClickListener();
    }

    private void setClickListener() {
        start_button.setOnClickListener(this);
        stop_button.setOnClickListener(this);
        bind_button.setOnClickListener(this);
        unbing_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,MyService.class);
        Intent intent1 = new Intent(this,MyBindService.class);

        switch (v.getId()){
            case R.id. start_service:
                startService(intent);
                break;
            case R.id.stop_service:
                stopService(intent);
                break;
            case R.id.bind_service:
                bindService(intent1,connection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

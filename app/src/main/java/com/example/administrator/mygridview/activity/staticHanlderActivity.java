package com.example.administrator.mygridview.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.administrator.mygridview.R;

import java.lang.ref.WeakReference;

public class staticHanlderActivity extends AppCompatActivity implements View.OnClickListener{
    private Handler myHandler = new MyHandler(this);
    private ProgressBar progressBar;
    private Button send_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_hanlder);
        init();
    }

    private void init() {
        progressBar = findViewById(R.id.static_handler_progressBar);
        send_button = findViewById(R.id.static_send_button);
        setClickListener();
    }

    private void setClickListener() {
        send_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.static_send_button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int progress = 0;
                        while (progress<=100){
                            Message message = myHandler.obtainMessage();
                            message.what=0;
                            message.arg1 = progress;
                            myHandler.handleMessage(message);
                            progress++;
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                break;
        }
    }

    static class MyHandler extends Handler{
        //弱引用
        WeakReference<AppCompatActivity> weakReference;
        private MyHandler(AppCompatActivity activity){
            weakReference=new WeakReference<>(activity);
        }


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //通过get方法获得到弱引的对象
            staticHanlderActivity activity = (staticHanlderActivity) weakReference.get();
            if (activity!=null){
                //直接在ui主线程中更新
                activity.progressBar.setProgress(msg.arg1);
            }else {
                removeCallbacksAndMessages(null);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacksAndMessages(null);
    }
}

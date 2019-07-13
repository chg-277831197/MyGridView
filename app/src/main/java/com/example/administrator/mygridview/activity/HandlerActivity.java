package com.example.administrator.mygridview.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.administrator.mygridview.R;


public class HandlerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button send_Button,post_Button;
    private ProgressBar progressBar;
    int progress=0;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                Log.d("progress",msg.arg1+"");
                progressBar.setProgress(msg.arg1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        init();
        setClickListener();
    }

    private void setClickListener() {
        send_Button.setOnClickListener(this);
        post_Button.setOnClickListener(this);
    }

    private void init() {
        send_Button = findViewById(R.id.send_button);
        post_Button = findViewById(R.id.post_button);
        progressBar = findViewById(R.id.handler_progressBar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int progress = 0;
                        while (progress<=100){
                            progress++;
                            Log.d("progress",progress+"");
                            Message message = new Message();
                            message.arg1=progress;
                            mHandler.sendMessage(message);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                break;
            case R.id.post_button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        while (progress<=100){
                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(progress);
                                }
                            },1000);
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
}

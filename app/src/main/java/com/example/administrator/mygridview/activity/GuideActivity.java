package com.example.administrator.mygridview.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.mygridview.MainActivity;
import com.example.administrator.mygridview.R;

public class GuideActivity extends AppCompatActivity {
    private ImageView imageView;
    private SharedPreferences sp;
    private boolean FRIST_ROOT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("text",MODE_PRIVATE);
        FRIST_ROOT = sp.getBoolean("FRIST",false);
        if (FRIST_ROOT){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }
        setContentView(R.layout.activity_guide);
        init();
        if (!FRIST_ROOT){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        SharedPreferences.Editor editor = sp.edit();
                        FRIST_ROOT=true;
                        editor.putBoolean("FRIST",FRIST_ROOT);
                        editor.apply();
                        Thread.sleep(2000);
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private void init() {
        imageView=findViewById(R.id.guide_image);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

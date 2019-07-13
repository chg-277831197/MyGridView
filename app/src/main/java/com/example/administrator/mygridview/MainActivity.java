package com.example.administrator.mygridview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.mygridview.activity.AnalysisActivity;
import com.example.administrator.mygridview.activity.HandlerActivity;
import com.example.administrator.mygridview.activity.HttpActivity;
import com.example.administrator.mygridview.activity.SQLiteActivity;
import com.example.administrator.mygridview.activity.ServiceActivity;
import com.example.administrator.mygridview.activity.staticHanlderActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button handler_button,static_handler_button,xml_button,
            service_button,http_button,sqlite_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setClickListener();
    }

    private void setClickListener() {
        handler_button.setOnClickListener(this);
        static_handler_button.setOnClickListener(this);
        xml_button.setOnClickListener(this);
        service_button.setOnClickListener(this);
        http_button.setOnClickListener(this);
        sqlite_button.setOnClickListener(this);
    }

    private void init() {
        handler_button =findViewById(R.id.handler_button);
        static_handler_button = findViewById(R.id.static_handler_button);
        xml_button = findViewById(R.id.activity_xml_button);
        service_button = findViewById(R.id.activity_service_button);
        http_button = findViewById(R.id.http_button);
        sqlite_button = findViewById(R.id.sqlite_button);
    }

//    @Override
//    public void onBackPressed() {
//        this.finish();
//        System.exit(0);
//    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.handler_button:
                intent = new Intent(this,HandlerActivity.class);
                startActivity(intent);
                break;
            case R.id.static_handler_button:
                intent = new Intent(this,staticHanlderActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_xml_button:
                intent = new Intent(this,AnalysisActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_service_button:
                intent = new Intent(this,ServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.http_button:
                intent = new Intent(this,HttpActivity.class);
                startActivity(intent);
                break;
            case R.id.sqlite_button:
                intent = new Intent(this,SQLiteActivity.class);
                startActivity(intent);
                break;
        }
    }
}

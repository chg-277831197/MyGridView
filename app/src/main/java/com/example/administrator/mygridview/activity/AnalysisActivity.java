package com.example.administrator.mygridview.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.mygridview.R;
import com.example.administrator.mygridview.adapter.myRecyclerAdapter;
import com.example.administrator.mygridview.bean.Book;
import com.example.administrator.mygridview.bean.Books;
import com.example.administrator.mygridview.bean.Result;
import com.example.administrator.mygridview.util.PullPersonService;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class AnalysisActivity extends AppCompatActivity implements View.OnClickListener{
    private Button xml_button,json_button;
    private List<Book> dataList;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        init();
    }

    private void init() {
        xml_button = findViewById(R.id.xml_button);
        json_button= findViewById(R.id.json_button);
        recyclerView = findViewById(R.id.recycleView);
        setClickListener();
    }

    private void setClickListener() {
        xml_button.setOnClickListener(this);
        json_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.xml_button:
                try {
                    getXmlData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.json_button:
                try {
                    getJsonData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void getJsonData() throws IOException {
        InputStream inputStream= getAssets().open("json.txt");
        Gson gson = new Gson();
        Result result = gson.fromJson(new InputStreamReader(inputStream,"GBK"),Result.class);
        Books books  = result.getResult();
        Log.d("books",books.getData().size()+"");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerAdapter adapter = new myRecyclerAdapter(books.getData(),this);
        recyclerView.setAdapter(adapter);
    }

    private void getXmlData() throws Exception {
        InputStream inputStream= getAssets().open("xml.txt");
        dataList = PullPersonService.getData(inputStream);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerAdapter adapter = new myRecyclerAdapter(dataList,this);
        recyclerView.setAdapter(adapter);
    }
}

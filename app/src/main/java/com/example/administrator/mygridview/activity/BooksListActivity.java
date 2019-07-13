package com.example.administrator.mygridview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.mygridview.R;
import com.example.administrator.mygridview.bean.BookData;
import com.example.administrator.mygridview.bean.BookDetails;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BooksListActivity extends AppCompatActivity {
    private String URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);
        GetData();
    }

    private void GetData() {
        URL = getIntent().getStringExtra("URL");
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
        Request request = new Request.Builder().url(URL).method("GET",null).build();
        //3.创建一个call对象,参数就是Request请求对象
        final Call call = okHttpClient.newCall(request);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    Log.e("url",response.body().string());
                    Gson gson = new Gson();
                    BookData data = gson.fromJson(response.body().string(),BookData.class);
                    List<BookDetails> bookDetailsList = data.getResult();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

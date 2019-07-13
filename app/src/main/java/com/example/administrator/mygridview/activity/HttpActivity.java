package com.example.administrator.mygridview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.mygridview.R;
import com.example.administrator.mygridview.adapter.myhttpRecyclerAdapter;
import com.example.administrator.mygridview.bean.BookType;
import com.example.administrator.mygridview.bean.BooksType;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HttpActivity extends AppCompatActivity implements View.OnClickListener {
    private Button volley_button,okHttp_button;
    private RecyclerView recyclerView;
    private String URL = "http://apis.juhe.cn/goodbook/catalog?" +
            "key=738a4e6e68cb4193f43fdcad374e3c03&dtype=json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        init();
    }

    private void init() {
        volley_button = findViewById(R.id.volley_button);
        recyclerView = findViewById(R.id.http_recycler);
        setClickListener();
    }

    private void setClickListener() {
        volley_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.volley_button:
                getvolleyData();
                break;
        }
    }


    private void getvolleyData() {
        final RequestQueue requestQueue=Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response",response.toString());
                Gson gson = new Gson();
                BooksType booksType = gson.fromJson(response.toString(),BooksType.class);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                myhttpRecyclerAdapter recyclerAdapter = new myhttpRecyclerAdapter(booksType.getResult(),getApplicationContext());
                recyclerView.setAdapter(recyclerAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError",error.toString());
            }
        });
        requestQueue.add(request);
    }
}

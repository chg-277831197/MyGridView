package com.example.administrator.mygridview.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.mygridview.activity.BooksListActivity;
import com.example.administrator.mygridview.bean.Book;
import com.example.administrator.mygridview.bean.BookType;
import com.example.administrator.mygridview.bean.BooksType;

import java.util.List;

public class myhttpRecyclerAdapter extends RecyclerView.Adapter<myhttpRecyclerAdapter.MyViewHolder> {
    private List<BookType> dataList;
    private Context mContext;
    private int id;
    public myhttpRecyclerAdapter(List<BookType> dataList, Context mContext) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_spinner_item,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.textView.setText(dataList.get(i).getCatalog());
//        Log.e("BookType",dataList.get(i).toString());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id = Math.toIntExact(dataList.get(getLayoutPosition()).getId());
                    String URL = "http://apis.juhe.cn/goodbook/query?" +
                            "key=738a4e6e68cb4193f43fdcad374e3c03&catalog_id="+id+"&rn=0&rn=30";
                    Intent intent = new Intent(mContext,BooksListActivity.class);
                    intent.putExtra("URL",URL);
                    mContext.startActivity(intent);
                }
            });
        }

    }
}

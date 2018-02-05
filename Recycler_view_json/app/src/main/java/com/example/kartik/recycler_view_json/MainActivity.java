package com.example.kartik.recycler_view_json;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    RecyclerView rv_list;
    ListAdapter l_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_list = findViewById(R.id.rv_list);
        // rv_list.setHasFixedSize(false);

        l_adapter = new ListAdapter(this);
        rv_list.setAdapter(l_adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_list.setLayoutManager(linearLayoutManager);
    }
}
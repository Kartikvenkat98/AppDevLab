package com.example.kartik.recycler_view_json;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    RecyclerView rv_list;
    ListAdapter l_adapter;
    Button b;
    TextView t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        rv_list = findViewById(R.id.rv_list);
        // rv_list.setHasFixedSize(false);
        t=findViewById(R.id.tv);

        l_adapter = new ListAdapter(this,t);
        rv_list.setAdapter(l_adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_list.setLayoutManager(linearLayoutManager);


    }

}
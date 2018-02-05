package com.example.kartik.recycler_view_json;

/**
 * Created by kartik on 5/2/18.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private String mItem;
        private TextView mTextView;
        private ListData ld;

        public ViewHolder(View view,ListData d) {
            super(view);
            view.setOnClickListener(this);
            mTextView = (TextView) view.findViewById(R.id.tv_list_item);
            ld=d;
        }


        @Override
        public void onClick(View view) {

            Log.d("Clicked:",mTextView.getText().toString());
            String con=ld.displayContent(mTextView.getText().toString());
            



        }
    }





    Context context;
    ListData data;



    public ListAdapter(Context context){
        this.context = context;
        data = new ListData(context);


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_content,parent,false);
        ListViewHolder vh_list = new ListViewHolder(view);
        ViewHolder vh=new ViewHolder(view,data);


        return vh_list;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        String item = data.getData(position);

        ((ListViewHolder) holder).tv_content.setText(item);

    }

    @Override
    public int getItemCount() {
        return data.getCount();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_content;

        ListViewHolder(View view){
            super(view);
            tv_content = view.findViewById(R.id.tv_list_item);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}


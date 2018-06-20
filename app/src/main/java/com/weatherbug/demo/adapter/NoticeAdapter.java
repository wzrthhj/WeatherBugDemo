package com.weatherbug.demo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weatherbug.demo.R;
import com.weatherbug.demo.main_activity.RecyclerItemClickListener;
import com.weatherbug.demo.model.Notice;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.EmployeeViewHolder> {

    private List<Notice> dataList;
    private RecyclerItemClickListener recyclerItemClickListener;
    private Context mContext;
    public NoticeAdapter(Context ctx, List<Notice> dataList , RecyclerItemClickListener
            recyclerItemClickListener) {
        mContext = ctx;
        this.dataList = dataList;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }


    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        String photourl = "https://s3.amazonaws.com/sc.va.util.weatherbug" +
                ".com/interviewdata/mobilecodingchallenge/" + dataList.get(position).getFilename();
        Picasso.with(mContext).load(photourl).into(holder.imageView);
        holder.txtNoticeTitle.setText(dataList.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(dataList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txtNoticeTitle;

        EmployeeViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            txtNoticeTitle =  itemView.findViewById(R.id.txt_notice_title);
        }
    }
}
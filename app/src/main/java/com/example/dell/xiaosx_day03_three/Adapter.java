package com.example.dell.xiaosx_day03_three;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by dell on 2019/5/29.
 */

public class Adapter extends XRecyclerView.Adapter<Adapter.Vholder>{
    private Context mContext;
    private List<User>mList;

    public Adapter(Context context, List<User> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public Adapter.Vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new Vholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.Vholder holder, final int position) {
        final User user = mList.get(position);
        Glide.with(mContext).load(user.getUrl()).into(holder.iv);
        holder.tv.setText(user.getDesc());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext,Main2Activity.class));
                final MyEvent myEvent = new MyEvent();
               myEvent.mList=mList;
                EventBus.getDefault().postSticky(myEvent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class Vholder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        public Vholder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            tv=itemView.findViewById(R.id.tv);
        }
    }
}

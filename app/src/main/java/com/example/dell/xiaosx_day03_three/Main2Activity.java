package com.example.dell.xiaosx_day03_three;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private ViewPager mVp;
    private TextView mTvW;
    private int mInt;
    private List<User> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EventBus.getDefault().register(this);
        initView();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void ahehe(MyEvent myEvent){
        mList = myEvent.mList;
    }
    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mTvW = (TextView) findViewById(R.id.tv_w);
        final vp_adapter adapter = new vp_adapter();
        mVp.setAdapter(adapter);
    }
    class  vp_adapter extends PagerAdapter{
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            final View view = LayoutInflater.from(Main2Activity.this).inflate(R.layout.image, null);
            final ImageView iv = view.findViewById(R.id.v_iv);
            Glide.with(Main2Activity.this).load(mList.get(position).getUrl()).into(iv);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}

package com.example.dell.xiaosx_day03_three;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.dell.xiaosx_day03_three.IView.IView_UI;
import com.example.dell.xiaosx_day03_three.Presenter.Presenter_Main;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IView_UI {

    private XRecyclerView rck;
    private List<User> mList;
    private Adapter mAdapter;
    private Presenter_Main mMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rck = findViewById(R.id.rck);
        rck.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        mAdapter = new Adapter(this, mList);
        rck.setAdapter(mAdapter);
        mMain = new Presenter_Main(this);
        mMain.Natereqeust();
    }

    @Override
    public void success(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final JSONObject jsonObject = new JSONObject(string);
                    final JSONArray results = jsonObject.optJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        final JSONObject object = results.optJSONObject(i);
                        final String url = object.optString("url");
                        final String desc = object.optString("desc");
                        final User user = new User();
                        user.setDesc(desc);
                        user.setUrl(url);
                        mList.add(user);
                    }
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

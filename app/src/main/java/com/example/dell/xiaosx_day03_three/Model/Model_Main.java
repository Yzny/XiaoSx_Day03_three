package com.example.dell.xiaosx_day03_three.Model;

import com.example.dell.xiaosx_day03_three.Presenter.Callback_UI;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dell on 2019/5/29.
 */

public class Model_Main implements Model_UI{
    @Override
    public void Naterequest(final Callback_UI callback_ui) {
        final OkHttpClient client = new OkHttpClient();
        final Request build = new Request.Builder()
                .url("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1")
                .build();
        client.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                callback_ui.Datasuccess(string);
            }
        });
    }
}

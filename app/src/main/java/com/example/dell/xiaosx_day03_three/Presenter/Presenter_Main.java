package com.example.dell.xiaosx_day03_three.Presenter;

import com.example.dell.xiaosx_day03_three.IView.IView_UI;
import com.example.dell.xiaosx_day03_three.Model.Model_Main;

/**
 * Created by dell on 2019/5/29.
 */

public class Presenter_Main implements Presenter_UI{

    private final Model_Main mMain;
    IView_UI view;
    public Presenter_Main(IView_UI view) {
        mMain = new Model_Main();
        this.view=view;
    }

    @Override
    public void Natereqeust() {
        mMain.Naterequest(new Callback_UI() {
            @Override
            public void Datasuccess(String string) {
                view.success(string);
            }
        });
    }
}

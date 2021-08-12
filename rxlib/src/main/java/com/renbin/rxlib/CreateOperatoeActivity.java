package com.renbin.rxlib;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * data:2021-08-11
 * Author:renbin
 */
public class CreateOperatoeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_operator);
    }

    public void interval(View view) {
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Throwable {
                        Log.e("---->","计时  "+aLong);
                    }
                });

    }

    public void Timer(View view) {
        Log.e("---->","倒计时  "+10 +"秒后执行");
        Observable.timer(10,TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                Log.e("---->","开始执行");
            }
        });
    }

    public void delay(View view) {
        Observable.just(1,2).delay(2, TimeUnit.SECONDS).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.e("---->","开始执行" +integer);
            }
        });
    }

}

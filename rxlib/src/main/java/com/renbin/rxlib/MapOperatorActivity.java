package com.renbin.rxlib;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;

/**
 * data:2021-08-11
 * Author:renbin
 */
public class MapOperatorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_operator);
    }

    public void concatMap(View view) {
        Observable.fromArray(1,2,3,4,5)
                .concatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Throwable {
                        int delay = 0;
                        if(integer == 3){
                            delay = 500;//延迟500ms发射
                        }
                        return Observable.just(integer *10).delay(delay, TimeUnit.SECONDS);
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.e("---->","concatMap  :"+integer);
            }
        });


        Observable.fromArray(1,2,3,4,5)
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Throwable {
                        int delay = 0;
                        if(integer == 3){
                            delay = 500;//延迟500ms发射
                        }
                        return Observable.just(integer *10).delay(delay, TimeUnit.SECONDS);
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.e("---->","flatMap  :"+integer);
            }
        });
    }

}

package com.renbin.rxlib;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Supplier;

/**
 * data:2021-08-11
 * Author:renbin
 */
public class MergeOperatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge_operator);
    }

    public void combineLatest(View view) {
        Observable.combineLatest(Observable.just(1, 2, 3),
                Observable.intervalRange(3, 5, 2, 1, TimeUnit.SECONDS),
                new BiFunction<Integer, Long, String>() {
                    @Override
                    public String apply(Integer integer, Long aLong) throws Throwable {
                        return "合并后的数据为：" + integer + aLong;
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
            @Override
            public void onNext(String s) {
                Log.e("---->","combineLatest:"+ s);
            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onComplete() {
                Log.e("---->","onComplete");
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void collect(View view) {
        Observable
                .just(1, 2, 3, 4, 5).collect(new Supplier<ArrayList<Integer>>() {
            @Override
            public ArrayList<Integer> get() throws Throwable {
                return new ArrayList<>();
            }
        }, new BiConsumer<ArrayList<Integer>, Integer>() {
            @Override
            public void accept(ArrayList<Integer> integers, Integer integer) throws Throwable {
                integers.add(integer);
            }
        }).subscribe(new Consumer<ArrayList<Integer>>() {
            @Override
            public void accept(ArrayList<Integer> integers) throws Throwable {
                Log.e("---->",""+integers);
            }
        });
    }
}

package com.renbin.rxlib;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * data:2021-08-11
 * Author:renbin
 */
public class FilterOperatorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_operator);
    }

    public void throttleFirst(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);//第一次发送
                Thread.sleep(500);
                emitter.onNext(2);//时间没有超过1s 不发送
                Thread.sleep(500);
                emitter.onNext(3); //时间为500+500 为1 s 发送
                Thread.sleep(500);
                emitter.onNext(4); //时间没有超过1s 不发送
                Thread.sleep(1500);
                emitter.onNext(5); //时间超过1 s 发送
                Thread.sleep(500);
                emitter.onNext(6);//时间没有超过1s 不发送
                Thread.sleep(500);
                emitter.onNext(7);//时间超过1 s 发送
            }
        })
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        Log.e("---->" , ""+integer);
                    }
                });
    }

    public void throttleLast(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);//第一不发送
                Thread.sleep(500);
                emitter.onNext(2);
                Thread.sleep(500);
                emitter.onNext(3);
                Thread.sleep(500);
                emitter.onNext(4);
                Thread.sleep(1500);
                emitter.onNext(5);
                Thread.sleep(500);
                emitter.onNext(6);
                Thread.sleep(500);
                emitter.onNext(7);
            }
        })
                .throttleLast(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        Log.e("---->" , ""+integer);
                    }
                });
    }
}

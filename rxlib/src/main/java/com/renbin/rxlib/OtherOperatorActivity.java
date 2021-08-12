package com.renbin.rxlib;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BooleanSupplier;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;

/**
 * data:2021-08-11
 * Author:renbin
 */
public class OtherOperatorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_operator);
    }

    public void repeat(View view) {
        Observable.range(0,2).repeat(2).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e("---->","Disposable:"+d);
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.e("---->","onNext:"+integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("---->","onError:"+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e("---->","onComplete:");
            }
        });

    }

    public void repeatWhen(View view) {
        Observable.just(1,2,3)
                .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Object> objectObservable) throws Throwable {
                        return Observable.timer(3, TimeUnit.SECONDS);
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.e("---->","integer:"+integer);
            }
        });
    }

    public void repeatUntil(View view) {
        Observable.just(1,2)
                .repeatUntil(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() throws Throwable {
                        boolean b;
                        int rand = (int) (Math.random() * 10);
                        //如果随机产生的整数大于5，停止重复。
                        Log.e("---->","rand:"+rand);
                        if (rand > 5) {
                            b = true;
                        } else {
                            b = false;
                        }
                        return b;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.e("---->","integer:"+integer);
            }
        });
    }

    public void timeout(View view) {
//        Observable<Long> take = Observable.interval(100, TimeUnit.MICROSECONDS).take(3);
//        Observable<Long> take1 = Observable.interval(500, TimeUnit.MICROSECONDS).take(3);
//        Observable<Long> take2 = Observable.interval(100, TimeUnit.MICROSECONDS).take(3);

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                emitter.onNext(2);
                Thread.sleep(100);
                emitter.onNext(3);
                Thread.sleep(1000);
                emitter.onNext(4);
                emitter.onNext(5);
                emitter.onComplete();
            }
            //如果不指定备用Observable将会抛出异常
        }).timeout(999,TimeUnit.MICROSECONDS,Observable.just(99,100))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e("---->","Disposable: "+d);
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.e("---->","integer: "+integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("---->","Throwable: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("---->","onComplete: ");
                    }
                });
    }
}

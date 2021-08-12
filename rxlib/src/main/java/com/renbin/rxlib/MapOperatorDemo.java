package com.renbin.rxlib;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observables.GroupedObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * data:2021-08-11
 * Author:renbin
 * 变换操作符
 */
public class MapOperatorDemo {
    public static void main(String[] args) {
    //    map();
     //   FlatMap();
    //    concatMap();
      //  buffer();
       // groupBy();
        //scan();
        window();
    }

    private static void window() {
        Observable.range(0,5)
                .window(2)
                .subscribe(new Consumer<Observable<Integer>>() {
                    @Override
                    public void accept(Observable<Integer> integerObservable) throws Throwable {
                        System.out.println("onOutsideNext -->" + integerObservable);
                        integerObservable.subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Throwable {
                                System.out.println(integer);
                            }
                        });
                    }
                });
    }

    private static void scan() {
        Observable.range(0,5)
                // 第一个参数是上次的结算结果，
                // 第二个参数是当此的源observable的输入值
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Throwable {
                        return integer+integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println(integer);
            }
        });
    }

    private static void groupBy() {
        Observable.range(0,10)
                .groupBy(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Throwable {
                        return integer/2;
                    }
                }).subscribe(new Consumer<GroupedObservable<Integer, Integer>>() {
            @Override
            public void accept(GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) throws Throwable {
                System.out.println(integerIntegerGroupedObservable.getKey());
            }
        });
    }

    private static void buffer() {
//        Observable.range(0,10)
//                .buffer(3)
//                .subscribe(new Consumer<List<Integer>>() {
//                    @Override
//                    public void accept(List<Integer> integers) throws Throwable {
//                        System.out.println(integers);
//                    }
//                });

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("消息" + System.currentTimeMillis());
            }
        }).subscribeOn(Schedulers.io()).buffer(3,TimeUnit.SECONDS)
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("onSubscribe" + d);
                    }

                    @Override
                    public void onNext(@NonNull List<String> strings) {
                        System.out.println("onNext" + strings.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onCompleted");
                    }
                });
    }

    private static void concatMap() {

    }

    private static void FlatMap() {
        Observable.just("register").flatMap(new Function<String, ObservableSource<?>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Throwable {
                System.out.println(s);
                System.out.println("注册成功");
                return Observable.just("请求登录");
            }

        }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                System.out.println(o);
                System.out.println("登录成功 "  );
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private static void map() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("https://www.baidu.com");
            }
        }).map(new Function<String, URL>() {
            @Override
            public URL apply(String s) throws Throwable {
                return new URL(s);
            }
        }).subscribe(new Consumer<URL>() {
            @Override
            public void accept(URL url) throws Throwable {
                System.out.println(url);
            }
        });
    }
}

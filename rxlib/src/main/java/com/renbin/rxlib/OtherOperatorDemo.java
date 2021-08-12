package com.renbin.rxlib;

import java.io.Serializable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;

/**
 * data:2021-08-11
 * Author:renbin
 * 其他操作符
 */
public class OtherOperatorDemo {
    public static void main(String[] args) {
    //    doOnEach();
       // doOnSubscribe();
      //  doOnNext();
      //  doAfterNext();
       // doOnComplete();
      //  doOnErro();
     //   onErrorRetuturn();
        //      onErrorResumeNext();
       // retry();
        repeat();
    }

    private static void repeat() {

    }

    private static void retry() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                emitter.onNext(2);
                int a = 0;
                int b = 2/a;
                emitter.onNext(3);
                emitter.onNext(4);
            }
        }).retry().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println(integer);
            }
        });
    }

    private static void onErrorResumeNext() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                emitter.onNext(2);
                int a = 0;
                int b = 2/a;
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> apply(Throwable throwable) throws Throwable {
                return Observable.just(11,22);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println(integer);
            }
        });
    }

    private static void onErrorRetuturn() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                emitter.onNext(2);
                int a = 0;
                int b = 2/a;
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
            }
        }).onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) throws Throwable {
                return 100;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println(integer);
            }
        });
    }

    private static void doOnErro() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(121231);
                int a = 0;
                int b = 2/a;
            }
        }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        System.out.println("执行到我拉 : "+throwable);
                    }
                }).subscribe(new Observer<Serializable>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe ： "+d);
            }

            @Override
            public void onNext(@NonNull Serializable serializable) {
                System.out.println("onNext :"+serializable);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError "+e);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    private static void doOnComplete() {
        Observable.just(1,2,3)
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Throwable {
                        System.out.println("执行到我拉");
                    }
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe ： "+d);
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("onNext :"+integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError "+e);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    private static void doAfterNext() {
        Observable.just(1,2,3)
                .doAfterNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        System.out.println("integer: "+integer);
                    }
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe ： "+d);
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("onNext :"+integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError "+e);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    private static void doOnNext() {
        Observable.just(1,2,3)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        System.out.println("integer: "+integer);
                    }
                }) .subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe ： "+d);
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("onNext :"+integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError "+e);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    private static void doOnSubscribe() {
        Observable.just(1,2,3)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Throwable {
                        System.out.println("disposable:"+disposable.toString());
                    }
                })     .subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe ： "+d);
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("onNext :"+integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError "+e);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    private static void doOnEach() {
        Observable.just(1,2,3)
                .doOnEach(new Consumer<Notification<Integer>>() {
                    @Override
                    public void accept(Notification<Integer> integerNotification) throws Throwable {
                        System.out.println("integerNotification:"+integerNotification.toString());
                    }
                })
        .subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe ： "+d);
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("onNext :"+integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError "+e);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}

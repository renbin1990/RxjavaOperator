package com.renbin.rxlib;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function3;

/**
 * data:2021-08-11
 * Author:renbin
 * 合并操作符
 */
public class MergeOperatorDemo {
    public static void main(String[] args) {

       // merge();
      //  concat();
      //  concatDelayError();
     //   zip();
      //  combineLatest();
       // reduce();
      //  collect();
      //  startWith();
        count();
    }

    private static void count() {
        Observable.just(1,2,3,4,5,9)
                .count()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Throwable {
                        System.out.println(aLong);
                    }
                });
    }

    private static void startWith() {
        Observable.just(1,2,3)
                .startWithArray(5,6)
                .startWith(Observable.just(1,2,3))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        System.out.println(integer);
                    }
                });

    }

    private static void collect() {

    }

    private static void reduce() {
        Observable.just(1,2,3,4,5,6)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
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

    private static void combineLatest() {
    }

    private static void zip() {
        Observable observable1 = Observable.just(1, 2, 3);
        Observable observable2 = Observable.just("苹果", "香蕉", "橘子");
        Observable observable3 = Observable.just(false,true);

        Observable.zip(observable1, observable2, observable3, new Function3<Integer, String, Boolean, String>() {
            @Override
            public String apply(Integer integer, String s, Boolean aBoolean) throws Throwable {
                return integer +s +aBoolean;
            }
        }). subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println( "onNext:  "+ o.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println( "onComplete");
            }
        });
    }

    private static void concatDelayError() {

        Observable
                .mergeArrayDelayError(Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onError(new NullPointerException());
                        emitter.onNext(3);
                        emitter.onNext(4);
                    }
                }), Observable.just(5, 6))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Integer integer) {
                        System.out.println( "收到消息  "+String.valueOf(integer));
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.println("cDelayError"+ "onError");
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println( "onComplete");
                    }
                });


//        Observable
//                .concatArrayDelayError(Observable.create(new ObservableOnSubscribe<Integer>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                        emitter.onNext(1);
//                        emitter.onNext(2);
//                        emitter.onError(new NullPointerException());
//                        emitter.onNext(3);
//                        emitter.onNext(4);
//                    }
//                }), Observable.just(5, 6))
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//                    @Override
//                    public void onNext(Integer integer) {
//                        System.out.println( "收到消息  "+String.valueOf(integer));
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println("cDelayError"+ "onError");
//                        System.out.println(e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        System.out.println( "onComplete");
//                    }
//                });
    }

    private static void concat() {
        Observable observable1 = Observable.just(1, 2, 3);
        Observable observable2 = Observable.just("苹果", "香蕉", "橘子");
        Observable observable3 = Observable.just(false,true);
        Observable.concat(observable1,observable2,observable3)
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@androidx.annotation.NonNull Object o) {
                        System.out.println("concat:"+ o.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }

    private static void merge() {
        Observable observable1 = Observable.just(1, 2, 3);
        Observable observable2 = Observable.just("苹果", "香蕉", "橘子");
        Observable observable3 = Observable.just(false,true);

        Observable.merge(observable1,observable2,observable3)
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Throwable {
                        System.out.println("merge:"+ o.toString());
                    }
                });
    }


}

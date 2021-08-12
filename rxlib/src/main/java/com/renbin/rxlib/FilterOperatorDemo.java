package com.renbin.rxlib;

import java.io.Serializable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;

/**
 * data:2021-08-11
 * Author:renbin
 * 过滤操作符
 */
public class FilterOperatorDemo {

    public static void main(String[] args) {
     //   filter();
     //   ofType();
      //  skip();
       // distinct();
      //  take();
     //   elementAt();
    //     throttleFirst();
        throttleFirst();
    }

    private static void throttleFirst() {

    }

    private static void elementAt() {
        Observable.just("哈哈",2,2,2,3,"相机",3,"相机",7,"苹果")
                .elementAt(5)
                .subscribe(new Consumer<Serializable>() {
                    @Override
                    public void accept(Serializable serializable) throws Throwable {
                        System.out.println(serializable);
                    }
                });
    }

    private static void take() {
        Observable.just("哈哈",2,2,2,3,"相机",3,"相机",7,"苹果")
                .take(4)
                .subscribe(new Consumer<Serializable>() {
                    @Override
                    public void accept(Serializable serializable) throws Throwable {
                        System.out.println(serializable);
                    }
                });
    }

    private static void distinct() {
        Observable.just("哈哈",2,2,2,3,"相机",3,"相机",7,"苹果")
                .distinctUntilChanged()
                .subscribe(new Consumer<Serializable>() {
                    @Override
                    public void accept(Serializable serializable) throws Throwable {
                        System.out.println(serializable);
                    }
                });


//        Observable.just("哈哈",2,2,3,"相机",3,"相机",7,5,"苹果")
//                .distinct()
//                .subscribe(new Consumer<Serializable>() {
//                    @Override
//                    public void accept(Serializable serializable) throws Throwable {
//                        System.out.println(serializable);
//                    }
//                });
    }

    private static void skip() {

        Observable.just("哈哈",2,3,"相机",7,5,"苹果")
                .skip(4)
                .subscribe(new Consumer<Serializable>() {
                    @Override
                    public void accept(Serializable serializable) throws Throwable {
                        System.out.println(serializable);
                    }
                });

    }

    private static void ofType() {
        Observable.just("哈哈",2,3,4,5,"苹果")
                .ofType(String.class)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        System.out.println(s);
                    }
                });

    }

    private static void filter() {
        Observable.just(1,2,3,4,5,6)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Throwable {
                        return integer>4;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println(integer);
            }
        });
    }
}

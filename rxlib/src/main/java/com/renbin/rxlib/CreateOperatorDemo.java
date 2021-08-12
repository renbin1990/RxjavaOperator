package com.renbin.rxlib;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Supplier;

/**
 * data:2021-08-10
 * Author:renbin
 * 创建操作符
 *
 */
public class CreateOperatorDemo {

    private static List<String> mList = new ArrayList<>();

    public static void main(String[] args) {
        mList.add("zhangsan");
        mList.add("lisi");
        mList.add("wangwu");
        mList.add("laoliu");
      //  create();
     //   just();
     //   from();
     //   defer();
     //   range();
      //  interval();
      //  empty();
     //   never();
    //    error();
     //   repeat();
        Delay();
    }

    private static void Delay() {

    }

    private static void repeat() {
        Observable.just(1,2).repeat(10).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println("accept : "+integer);
            }
        });
    }

    private static void error() {
        Observable<String> error = Observable.error(new Throwable("Observable error"));
        error.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe : "+d);
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println("onNext: "+s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError :"+e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });

    }

    private static void never() {
        Observable<String> never = Observable.never();
        never.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe : "+d);
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println("onNext: "+s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError :"+e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });

    }

    private static void empty() {
        Observable<String> empty = Observable.empty();
        empty.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe : "+d);
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println("onNext: "+s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError :"+e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    private static void interval() {
        //必须在安卓项目执行
    }

    private static void range() {
        Observable.range(100,10).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println(integer);
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

    private static void defer() {
        Consumer<String> action1 = new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                System.out.println(s);
            }
        };
        // defer
        Observable<String> defer = Observable.defer(new Supplier<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> get() throws Throwable {
                Object o = new Object();
                return Observable.just("defer : hashCode  = " + o.hashCode());
            }
        });

        defer.subscribe(action1);
        defer.subscribe(action1);
        defer.subscribe(action1);

        // just
        Observable<String> just = Observable.just("just : hashCode  = " + new Object().hashCode());
        just.subscribe(action1);
        just.subscribe(action1);
        just.subscribe(action1);
    }

    private static void from() {
        Observable.fromArray(mList).subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<String> strings) {
                System.out.println(strings);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });

        Observable.fromArray(mList).subscribe(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) throws Throwable {
                System.out.println(strings);
            }
        });
    }

    private static void just() {
        Observable.just(mList).subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<String> strings) {
                System.out.println(strings);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        Observable.just(1,2,3,4,5).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println(integer);
            }
        });
    }

    private static void create() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("hahahhahhh");
                int a = 0;
                int b = 11/a;  //会抛异常，回调到onError方法
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("hahahhahhh");
                int a = 0;
                int b = 11/a;  //会抛异常，回调到onError方法
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                System.out.println(s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                System.out.println("我是异常"+throwable.getMessage());
            }
        });

    }
}

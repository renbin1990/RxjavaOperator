package com.renbin.rxlib;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;

/**
 * data:2021-08-11
 * Author:renbin
 */
public class ConditionOperatorDemo {
    public static void main(String[] args) {
     //   all();
     //   takeWhile();
       // skipWhile();
      //  takeUntil();
       // skipUntil();
      //  sequenceEqual();
    //    contains();
       // isEmpty();
        amb();
    }

    private static void amb() {
        List<Observable<Integer>> list = new ArrayList<>();
        Observable<Integer> just1 = Observable.just(1,2,3);
        Observable<Integer> just2 = Observable.just(1,2,3,4);
        list.add(just1);
        list.add(just2);
        Observable.amb(list)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        System.out.println(integer);
                    }
                });
    }

    private static void isEmpty() {
        List<String> list = new ArrayList<>();
        Observable.just(list)
                .isEmpty()
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Throwable {
                        System.out.println(aBoolean);
                    }
                });
    }

    private static void contains() {
        Observable.just(1,2,3,4,6,7)
                .contains(2)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Throwable {
                        System.out.println(aBoolean);
                    }
                });
    }

    private static void sequenceEqual() {
        Observable<Integer> just1 = Observable.just(1,2,3);
        Observable<Integer> just2 = Observable.just(1,2,3,4);

        Observable.sequenceEqual(just1,just2)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Throwable {
                        System.out.println(aBoolean);
                    }
                });
    }

    private static void skipUntil() {
        Observable.just(1,2,3,45,56,7)
                .skipUntil(Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                        emitter.onNext("hahah");
                        System.out.println("我是先执行的");
                    }
                })).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println(integer);
            }
        });
    }

    private static void takeUntil() {
        Observable.just(1,2,3,45,56,7)
                .takeUntil(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Throwable {
                        return integer==3;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println(integer);
            }
        });
    }

    private static void skipWhile() {
        Observable.just(2,2,2,3,3,7,6,85)
                .skipWhile(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Throwable {
                        return integer>=4;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println(integer);
            }
        });
    }

    private static void takeWhile() {
        Observable.just(2,2,2,3,3,7,6,85)
                .takeWhile(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Throwable {
                        return integer>=2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println(integer);
            }
        });
    }

    private static void all() {
        Observable.just(2, 2, 2, 3, 3, 7, 6, 85)
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Throwable {
                        return integer >= 2;
                    }
                }).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Throwable {
                        System.out.println(aBoolean);
                    }
                });
    }
}

package com.example.shalini.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Observable
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5)
                .filter(integer -> {
                    return integer % 2 != 0;
                });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onError(Throwable e) {
                Log.e("RXJAVA","Error received: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e("RXJAVA","All data emitted.");
            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.e("RXJAVA","New data received: " + integer);
            }
        };
        observable.subscribeOn(Schedulers.io())       //observable will run on IO thread.
                .observeOn(AndroidSchedulers.mainThread())      //Observer will run on main thread.
                .subscribe(observer);
    }
}
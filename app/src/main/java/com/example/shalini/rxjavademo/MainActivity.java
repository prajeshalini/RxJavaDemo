package com.example.shalini.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.shalini.rxjavademo.data.source.remote.AllDogsAPI;
import com.example.shalini.rxjavademo.data.source.remote.AuthorDetails;
import com.example.shalini.rxjavademo.data.source.remote.RemoteDataSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private Button mNewtorkCallButton;
    public static final boolean FETCH_REMOTE_DATA = true;
    private RemoteDataSource remoteDataSource;
    private AllDogsAPI mAllDogsBreedAPi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNewtorkCallButton = findViewById(R.id.button_network_call);
        remoteDataSource = RemoteDataSource.getInstance();


        mNewtorkCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FETCH_REMOTE_DATA) {
                    Observable<List<String>> listObservable = Observable.just(fetchList())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
                    listObservable.subscribe((list) -> {
                        for (String s : list) {
                            Log.e("RXJAVA", s);
                        }
                    });

                } else {
                    mAllDogsBreedAPi = remoteDataSource.createApiService(AllDogsAPI.class);
                    Observable<AuthorDetails> observable = mAllDogsBreedAPi.getAuthorDetails()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());

                    observable.subscribe((authorDetails -> {
//                        for (String breed :
//                                dogsBreedList.dogsnameList) {
                            Log.e("RXJAVA", authorDetails.getAuthor() + authorDetails.getContact() + authorDetails.getDocs() + authorDetails.getHome());
                        //}
                    }));

                }
            }
        });
//        // Observable
//        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5)
//                .filter(integer -> {
//                    return integer % 2 != 0;
//                });
//
//        Observer<Integer> observer = new Observer<Integer>() {
//            @Override
//            public void onError(Throwable e) {
//                Log.e("RXJAVA","Error received: " + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                Log.e("RXJAVA","All data emitted.");
//            }
//
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.e("RXJAVA","New data received: " + integer);
//            }
//        };
//        observable.subscribeOn(Schedulers.io())       //observable will run on IO thread.
//                .observeOn(AndroidSchedulers.mainThread())      //Observer will run on main thread.
//                .subscribe(observer);
    }

    private List<String> fetchList() {
        List<String> list = new ArrayList<>();
        list.add("Dog");
        list.add("cat");
        list.add("Rat");
        list.add("mouse");
        return list;
    }
}
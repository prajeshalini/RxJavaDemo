package com.example.shalini.rxjavademo.data.source.remote;

import retrofit2.Retrofit;

/**
 * Created by Shalini Prajesh on 13/2/18.
 */

public class RemoteDataSource {
        private static RemoteDataSource mRemoteDataSource;
        private Retrofit mRestClient;

        private RemoteDataSource(Retrofit restClient) {
            mRestClient = restClient;
        }

        public static RemoteDataSource getInstance() {
            if (mRemoteDataSource == null) {
                mRemoteDataSource = new RemoteDataSource(RestClient.getClient());
            }
            return mRemoteDataSource;
        }

        public <T> T createApiService(Class<T> apiInterface) {
            return mRestClient.create(apiInterface);
        }
}

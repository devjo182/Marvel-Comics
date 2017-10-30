package com.app.devjo.marvelcomics.apiConnect;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import com.app.devjo.marvelcomics.models.Configuration;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kjoha on 17/3/2017.
 */

public class RetrofitApiConnect {

    private static RetrofitApiConnect retrofitApiConnect;
    private Retrofit retrofit;
    private  Gson gson;
    private OkHttpClient httpClient;
    private RxJava2CallAdapterFactory rxAdapter;


    public RetrofitApiConnect() {
        this.rxAdapter = RxJava2CallAdapterFactory.create();
        this.httpClient = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .build();
        this.gson = new GsonBuilder()
            .setDateFormat(Configuration.ts)
            .create();
        Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Configuration.API_BASE_URL)
            .addCallAdapterFactory(rxAdapter)
            .addConverterFactory(GsonConverterFactory.create(gson)
        );
       this.retrofit = builder.client(httpClient).build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static RetrofitApiConnect getRetrofitConnect(){
        if (retrofitApiConnect == null)
            retrofitApiConnect = new RetrofitApiConnect();
        return retrofitApiConnect;
    }

    class LoggingInterceptor implements Interceptor {
        @Override public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            Log.d("OkHttp", String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            okhttp3.Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Log.d("OkHttp", String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }

    /*
    private OkHttpClient getOkHttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.connectTimeoutMillis(60, TimeUnit.SECONDS);
        okHttpClient.readTimeoutMillis(60, TimeUnit.SECONDS);
        return httpClient;
    }

*/




}

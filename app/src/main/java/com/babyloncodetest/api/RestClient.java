package com.babyloncodetest.api;

import android.content.Context;

import com.babyloncodetest.BuildConfig;
import com.babyloncodetest.util.Utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

import static com.google.common.net.HttpHeaders.CACHE_CONTROL;

/**
 * Created by maximiliano.ferraiuolo on 08/11/2016.
 */

public class RestClient {

    private static BHEndpoints sEndPoints;

    public static void init(Context context) {
        sEndPoints = getRetrofit(context).create(BHEndpoints.class);
    }

    public static BHEndpoints getApiService() {
        return sEndPoints;
    }

   private static Retrofit getRetrofit(Context context) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BH_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(getHttpClient(context))
                .build();
    }

    private static OkHttpClient getHttpClient(Context context) {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder()
                .addInterceptor(getHttpDebugInterceptor())
                .addInterceptor(getCacheInterceptor())
                .addInterceptor(getOfflineCacheInterceptor(context))
                .cache(getCache(context));

        return httpBuilder.build();
    }


    public static Interceptor getCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());

                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(10, TimeUnit.SECONDS)
                        .build();

                return response.newBuilder()
                        .header(CACHE_CONTROL, cacheControl.toString())
                        .build();
            }
        };
    }

    public static Interceptor getOfflineCacheInterceptor(final Context context) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                if (!Utils.isNetworkAvailable(context)) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(10, TimeUnit.SECONDS)
                            .build();

                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }

                return chain.proceed(request);
            }
        };
    }

    private static Interceptor getHttpDebugInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor.Level body =
                BuildConfig.DEBUG ?
                        HttpLoggingInterceptor.Level.BODY :
                        HttpLoggingInterceptor.Level.NONE;
        loggingInterceptor.setLevel(body);
        return loggingInterceptor;
    }

    private static Cache getCache(Context context) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(context.getCacheDir(), cacheSize);
    }
}

package com.test.nycschools.remote;

import androidx.annotation.NonNull;

import com.squareup.moshi.Moshi;
import com.test.nycschools.remote.services.SchoolService;

import javax.inject.Singleton;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@InstallIn(SingletonComponent.class)
@Module(includes = NetworkSettings.class)
public class NetworkModule {

    @Provides
    public HttpUrl providesBaseUrl() {
        return HttpUrl.parse("https://data.cityofnewyork.us/resource/");
    }

    @Provides
    @Singleton
    public OkHttpClient providesOkHttpClient(HttpLoggingInterceptor.Level level)  {
        HttpLoggingInterceptor.Logger logger = HttpLoggingInterceptor.Logger.DEFAULT;

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(logger);
        logging.setLevel(level);
        builder.addInterceptor(logging);

        builder.addInterceptor(chain -> chain.proceed(
                chain.request().newBuilder()
                        .addHeader("X-App-Token", "w4KAZ8tY1MROgvuKEsrMqgrHl")
                        .build()
        ));

        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit providesRetrofit(@NonNull HttpUrl url, Lazy<OkHttpClient> client)  {
        Call.Factory callFactory = request -> client.get().newCall(request);

        Moshi moshi = new Moshi.Builder().build();

        return new Retrofit.Builder()
                .baseUrl(url)
                .callFactory(callFactory)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

    }

    @Provides
    @Singleton
    public SchoolService providesSchoolService(Retrofit retrofit)  {
        return retrofit.create(SchoolService.class);
    }
}

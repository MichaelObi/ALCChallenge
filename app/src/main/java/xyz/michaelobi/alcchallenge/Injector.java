package xyz.michaelobi.alcchallenge;

import java.io.File;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.michaelobi.alcchallenge.data.GithubUsersRepository;
import xyz.michaelobi.alcchallenge.data.UsersRepository;
import xyz.michaelobi.alcchallenge.data.remote.GithubService;

/**
 * Created by Michael on 10/03/2017.
 */

public class Injector {
    private static OkHttpClient okHttpClient;
    private static Retrofit retrofitInstance;
    private static GithubService githubService;



    public static Retrofit provideRetrofit() {
        if (retrofitInstance == null) {
            Retrofit.Builder retrofit = new Retrofit.Builder().client(Injector.provideOkHttpClient())
                    .baseUrl("https://api.github.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
            retrofitInstance = retrofit.build();

        }
        return retrofitInstance;
    }

    public static OkHttpClient provideOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        HttpUrl httpUrl = chain.request().url();
                        HttpUrl newUrl = httpUrl.newBuilder()
                                .addQueryParameter("access_token", "0580426c42f7f9b2b915cd6c989315813559375f")
                                .build();
                        Request.Builder newRequestBuilder = chain.request().newBuilder()
                                .url(newUrl);
                        newRequestBuilder.header("Cache-Control", "public, max-age=" + 300);
                        return chain.proceed(newRequestBuilder.build());
                    }).build();
        }

        return okHttpClient;
    }

    public static GithubService provideGithubService() {
        if (githubService == null) {
            githubService = provideRetrofit().create(GithubService.class);
        }

        return githubService;
    }

    public static UsersRepository provideUserRepository() {
        return new GithubUsersRepository();
    }
}

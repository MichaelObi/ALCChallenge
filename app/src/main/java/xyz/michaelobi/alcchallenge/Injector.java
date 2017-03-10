package xyz.michaelobi.alcchallenge;

import okhttp3.OkHttpClient;
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
            okHttpClient = new OkHttpClient.Builder().build();
        }

        return okHttpClient;
    }

    private static GithubService githubService;

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

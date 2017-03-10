package xyz.michaelobi.alcchallenge.data;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import xyz.michaelobi.alcchallenge.Injector;
import xyz.michaelobi.alcchallenge.data.remote.GithubService;
import xyz.michaelobi.alcchallenge.data.remote.model.User;

/**
 * Created by Michael on 10/03/2017.
 */

public class GithubUsersRepository implements UsersRepository {
    private GithubService mGithubService;

    public GithubUsersRepository() {
        mGithubService = Injector.provideGithubService();
    }

    @Override
    public Observable<List<User>> getAllLagosJavaDevelopers() {
        String searchQuery = "location:lagos+language:java";
        return Observable.defer(() -> mGithubService.searchUsers(searchQuery)
                .concatMap(userList -> Observable.from(userList.getUsers())
                        .concatMap(user -> mGithubService.getUser(user.getLogin())).toList()))
                .retryWhen(observable -> observable.flatMap(o -> {
                            if (o instanceof IOException) {
                                return Observable.just(null);
                            }
                            return Observable.error(o);
                        })
                );
    }
}

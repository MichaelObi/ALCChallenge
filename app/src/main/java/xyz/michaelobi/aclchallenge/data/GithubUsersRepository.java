package xyz.michaelobi.aclchallenge.data;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import xyz.michaelobi.aclchallenge.data.remote.model.User;

/**
 * Created by Michael on 10/03/2017.
 */

public class GithubUsersRepository implements UsersRepository {

    public GithubUsersRepository() {

    }

    @Override
    public Observable<List<User>> getAllLagosJavaDevelopers() {
        String searchQuery = "location:abuja+language:java";
        return Observable.defer(() -> );
    }
}

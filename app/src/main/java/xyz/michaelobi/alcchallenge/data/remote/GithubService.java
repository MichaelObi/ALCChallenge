package xyz.michaelobi.alcchallenge.data.remote;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import xyz.michaelobi.alcchallenge.data.remote.model.User;
import xyz.michaelobi.alcchallenge.data.remote.model.UserList;

/**
 * Created by Michael on 10/03/2017.
 */

public interface GithubService {

//    https://api.github.com/search/users?q=

    @GET("/search/users?per_page=2")
    Observable<UserList> searchUsers(@Query("q") String searchTerm);

    @GET("/users/{username}")
    Observable<User> getUser(@Path("username") String username);
}

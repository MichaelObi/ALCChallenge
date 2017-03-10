package xyz.michaelobi.aclchallenge.data;

import java.util.List;

import rx.Observable;
import xyz.michaelobi.aclchallenge.data.remote.model.User;

/**
 * Created by Michael on 10/03/2017.
 */

public interface UsersRepository {

    Observable<List<User>> getAllLagosJavaDevelopers();
}

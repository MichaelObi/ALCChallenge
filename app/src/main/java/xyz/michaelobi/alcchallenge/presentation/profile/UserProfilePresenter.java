package xyz.michaelobi.alcchallenge.presentation.profile;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import xyz.michaelobi.alcchallenge.data.UsersRepository;
import xyz.michaelobi.alcchallenge.data.remote.model.User;
import xyz.michaelobi.mvp.BasePresenter;

/**
 * Created by Michael on 11/03/2017.
 */

public class UserProfilePresenter extends BasePresenter<UserProfileContract.View> implements UserProfileContract.Presenter {

    private UsersRepository userRepository;

    public UserProfilePresenter(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void getUserProfile(String username) {
        checkViewAttached();
        getView().showLoading();
        addSubscription(userRepository.getUserProfile(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoading();
                        getView().showError(e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        getView().hideLoading();
                        getView().showUserProfile(user);
                    }
                }));
    }
}

package xyz.michaelobi.alcchallenge.presentation.profile;

import xyz.michaelobi.alcchallenge.data.remote.model.User;
import xyz.michaelobi.mvp.Mvp;

/**
 * Created by Michael on 11/03/2017.
 */

public interface UserProfileContract {

    public interface View extends Mvp.View {
        void showUserProfile(User user);

        void showError(String error);

        void showLoading();

        void hideLoading();
    }

    public interface Presenter extends Mvp.Presenter<View> {
        void getUserProfile(String username);
    }
}

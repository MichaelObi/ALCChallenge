package xyz.michaelobi.alcchallenge.presentation.profile;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import xyz.michaelobi.alcchallenge.Injector;
import xyz.michaelobi.alcchallenge.R;
import xyz.michaelobi.alcchallenge.data.remote.model.User;

/**
 * Created by Michael on 10/03/2017.
 */

public class UserProfileActivity extends AppCompatActivity implements UserProfileContract.View {

    UserProfileContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        mPresenter = new UserProfilePresenter(Injector.provideUserRepository());
        mPresenter.attachView(this);
        mPresenter.getUserProfile(username);
    }

    @Override
    public void showUserProfile(User user) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}

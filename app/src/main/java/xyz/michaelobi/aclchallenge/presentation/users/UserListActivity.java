package xyz.michaelobi.aclchallenge.presentation.users;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import xyz.michaelobi.aclchallenge.R;
import xyz.michaelobi.aclchallenge.data.remote.model.User;

public class UserListActivity extends AppCompatActivity implements  UsersListMvpContract.View{

    UsersListMvpContract.Presenter presenter = new UserListPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        presenter.getUsers();
    }

    @Override
    public void showUsers(List<User> users) {

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

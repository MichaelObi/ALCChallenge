package xyz.michaelobi.alcchallenge.presentation.profile;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import xyz.michaelobi.alcchallenge.Injector;
import xyz.michaelobi.alcchallenge.R;
import xyz.michaelobi.alcchallenge.data.remote.model.User;

/**
 * Created by Michael on 10/03/2017.
 */

public class UserProfileActivity extends AppCompatActivity implements UserProfileContract.View {

    private UserProfileContract.Presenter mPresenter;

    private Button btnGitHubLink;
    private Toolbar toolbar;
    private User mUser;
    private ImageView ivAvatar;
    private TextView tvUsername, tvName, tvBio, tvRepoCount, tvLocation;
    private ProgressBar progressBar;
    private TextView textViewErrorMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnGitHubLink = (Button) findViewById(R.id.btn_github_link);
        ivAvatar = (ImageView) findViewById(R.id.iv_avatar);
        tvUsername = (TextView) findViewById(R.id.tv_username);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvBio = (TextView) findViewById(R.id.tv_bio);
        tvRepoCount = (TextView) findViewById(R.id.tv_repo_count);
        tvLocation = (TextView) findViewById(R.id.tv_location);
        textViewErrorMessage = (TextView) findViewById(R.id.text_view_error_msg);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mPresenter = new UserProfilePresenter(Injector.provideUserRepository());
        mPresenter.attachView(this);
        mPresenter.getUserProfile(username);
    }

    @Override
    public void showUserProfile(User user) {
        mUser = user;
        supportInvalidateOptionsMenu();
        btnGitHubLink.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(user.getHtmlUrl()));
            startActivity(i);
        });
        Picasso.with(this).load(user.getAvatarUrl()).into(ivAvatar);
        tvUsername.setText(user.getLogin());
        tvName.setText(user.getName());
        tvBio.setText(user.getBio());
        tvRepoCount.setText(String.format("%d public repositories", user.getPublicRepos()));
        tvLocation.setText(user.getLocation());
    }

    @Override
    public void showError(String error) {
        textViewErrorMessage.setVisibility(View.VISIBLE);
        textViewErrorMessage.setText(error);
        hideProfileElements();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        textViewErrorMessage.setVisibility(View.GONE);
        hideProfileElements();
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        textViewErrorMessage.setVisibility(View.GONE);
        showProfileElements();
    }


    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mUser == null) {
            return false;
        }
        getMenuInflater().inflate(R.menu.menu_profile,
                menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_share) {
            if (mUser == null) {
                return false;
            }
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome developer @" + mUser.getLogin() + ", " + mUser.getHtmlUrl() + ".");
            intent.setType("text/plain");
            startActivity(intent);
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    private void showProfileElements() {
        btnGitHubLink.setVisibility(View.VISIBLE);
        ivAvatar.setVisibility(View.VISIBLE);
        tvUsername.setVisibility(View.VISIBLE);
        tvBio.setVisibility(View.VISIBLE);
        tvName.setVisibility(View.VISIBLE);
        tvLocation.setVisibility(View.VISIBLE);
        tvRepoCount.setVisibility(View.VISIBLE);
    }

    private void hideProfileElements() {
        ivAvatar.setVisibility(View.GONE);
        btnGitHubLink.setVisibility(View.GONE);
        tvUsername.setVisibility(View.GONE);
        tvName.setVisibility(View.GONE);
        tvBio.setVisibility(View.GONE);
        tvLocation.setVisibility(View.GONE);
        tvRepoCount.setVisibility(View.GONE);
    }
}

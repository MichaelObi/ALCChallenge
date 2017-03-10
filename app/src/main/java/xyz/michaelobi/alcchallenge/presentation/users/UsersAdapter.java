package xyz.michaelobi.alcchallenge.presentation.users;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import xyz.michaelobi.alcchallenge.R;
import xyz.michaelobi.alcchallenge.data.remote.model.User;

/**
 * Created by Michael on 10/03/2017.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private Context mContext;

    private List<User> users = new ArrayList<>();

    public UsersAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_list_item, parent, false);
        return new ViewHolder(v);
    }

    private static final String TAG = "UsersAdapter";
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        User user = users.get(position);
        holder.tvRepositoryCount.setText(user.getPublicRepos() + " public repositories");
        holder.tvUsername.setText(user.getLogin());
        Picasso.with(mContext).load(user.getAvatarUrl()).into(holder.ivAvatar);
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount: " + users.size());
        return users.size();
    }

    public void setUsers(List<User> users) {
        Log.e(TAG, "setUsers() called with: users = [" + users + "]");
        this.users = users;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivAvatar;
        private final TextView tvUsername;
        private final TextView tvRepositoryCount;

        public ViewHolder(View itemView) {
            super(itemView);
            tvRepositoryCount = (TextView) itemView.findViewById(R.id.tv_repo_count);
            tvUsername = (TextView) itemView.findViewById(R.id.tv_username);
            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
        }

    }
}

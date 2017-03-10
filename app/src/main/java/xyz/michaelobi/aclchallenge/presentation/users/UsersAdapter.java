package xyz.michaelobi.aclchallenge.presentation.users;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import xyz.michaelobi.aclchallenge.R;

/**
 * Created by Michael on 10/03/2017.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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

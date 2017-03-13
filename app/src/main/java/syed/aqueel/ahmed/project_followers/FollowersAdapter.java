package syed.aqueel.ahmed.project_followers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import syed.aqueel.ahmed.project_followers.model.Response;

/**
 * Created by syedaqueelahmed on 3/12/17.
 */

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.FollowersViewHolder> {

    private List<Response> followersList;
    private Context context;
    private ClickListner clickListner;

    FollowersAdapter(Context context, List<Response> followersList) {
        this.followersList = followersList;
        this.context = context;
    }

    interface ClickListner {
        void onItemClicked(View view, int position);
    }

    @Override
    public FollowersAdapter.FollowersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.follower_view, parent, false);
        return new FollowersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FollowersViewHolder holder, int position) {
        String followerName = followersList.get(position).getUserName();
        holder.followerName.setText(followerName);
        Picasso.with(context)
                .load(followersList.get(position).getImageUrl())
                .resize(200, 200)
                .into(holder.followerImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        Bitmap imageBitmap = ((BitmapDrawable) holder.followerImage.getDrawable()).getBitmap();
                        RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), imageBitmap);
                        imageDrawable.setCircular(true);
                        imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                        holder.followerImage.setImageDrawable(imageDrawable);
                    }

                    @Override
                    public void onError() {
                    }
                });
        holder.followerImage.setOnClickListener(v -> clickListner.onItemClicked(v, holder.getAdapterPosition()));
    }


    @Override
    public int getItemCount() {
        return followersList.size();
    }

    public void setClickListner(ClickListner clickListner) {
        this.clickListner = clickListner;
    }

    static class FollowersViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.follower_image)
        ImageView followerImage;
        @BindView(R.id.follower_name)
        TextView followerName;

        FollowersViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}

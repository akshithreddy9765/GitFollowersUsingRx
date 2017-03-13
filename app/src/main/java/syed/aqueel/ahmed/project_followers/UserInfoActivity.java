package syed.aqueel.ahmed.project_followers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import syed.aqueel.ahmed.project_followers.model.UserResponse;
import syed.aqueel.ahmed.project_followers.presenter.UserInfoPresenter;

import static syed.aqueel.ahmed.project_followers.HomeActivity.USER_NAME;

/**
 * Created by syedaqueelahmed on 3/12/17.
 */

public class UserInfoActivity extends AppCompatActivity implements UserInfoPresenter.UserLayoutView {

    @BindView(R.id.imageLayout)
    LinearLayout imageLayout;
    @BindView(R.id.follower_image_view)
    ImageView userImage;
    @BindView(R.id.follower_first_name)
    TextView userId;
    @BindView(R.id.follower_last_name)
    TextView userName;
    @BindView(R.id.followers_count)
    TextView followers;
    @BindView(R.id.following_count)
    TextView following;
    @BindView(R.id.repositories_count)
    TextView repositories;
    @BindView(R.id.location_image)
    ImageView locationImage;
    @BindView(R.id.location_text)
    TextView location;
    @BindView(R.id.email_image)
    ImageView emailImage;
    @BindView(R.id.email_text)
    TextView email;

    UserInfoPresenter userInfoPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.follower_view_info);
        ButterKnife.bind(this);
        userInfoPresenter = new UserInfoPresenter();
        userInfoPresenter.attachView(this);
        String userName = "";
        if (savedInstanceState == null) {
            savedInstanceState = getIntent().getExtras();
        }
        if (savedInstanceState != null) {
            userName = savedInstanceState.getString(USER_NAME);
        }
        userInfoPresenter.retrieveUserInfo(userName);

    }

    @Override
    public void setUserInfo(UserResponse userInfo) {


        Picasso
                .with(this)
                .load(userInfo.getImageURL())
                .resize(300, 300)
                .into(userImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        Bitmap imageBitmap = ((BitmapDrawable) userImage.getDrawable()).getBitmap();
                        RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), imageBitmap);
                        imageDrawable.setCircular(true);
                        imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                        userImage.setImageDrawable(imageDrawable);
                        BitmapDrawable bitmapDrawable = new BitmapDrawable(getApplicationContext().getResources(), imageBitmap);
                        bitmapDrawable.setAlpha(200);
                        imageLayout.setBackgroundDrawable(bitmapDrawable);
                    }

                    @Override
                    public void onError() {
                    }
                });
        userId.setText(userInfo.getUserId());
        userName.setText(userInfo.getUserName());
        followers.setText(userInfo.getFollowers());
        following.setText(userInfo.getFollowing());
        repositories.setText(userInfo.getRepositories());
        location.setText(userInfo.getLocation());
        email.setText(userInfo.getEmail());
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userInfoPresenter.detachView();
    }
}

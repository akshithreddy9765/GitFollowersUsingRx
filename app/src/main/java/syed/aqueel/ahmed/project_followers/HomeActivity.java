package syed.aqueel.ahmed.project_followers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import syed.aqueel.ahmed.project_followers.model.Response;
import syed.aqueel.ahmed.project_followers.presenter.HomePresenter;

public class HomeActivity extends AppCompatActivity implements HomePresenter.HomeLayoutView, FollowersAdapter.ClickListner {

    public static final String USER_NAME = "user_name";

    @Inject
    HomePresenter presenter;

    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.followers_recycler_view)
    RecyclerView followers;

    List<Response> followersData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        presenter = new HomePresenter();
        presenter.attachView(this);
    }

    @OnClick(R.id.search)
    public void onSearchClicked() {
        hideKeyboard();
        presenter.retreiveFollowersData(userName.getText().toString());
    }

    @Override
    public void setFollowersData(List<Response> followersData) {
        if (followersData.size() == 0) {
            Toast.makeText(this, "No data found for this user.", Toast.LENGTH_LONG).show();
            return;
        }
        this.followersData = followersData;
        FollowersAdapter adapter = new FollowersAdapter(this, followersData);
        adapter.setClickListner(this);
        followers.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        followers.setAdapter(adapter);

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onItemClicked(View view, int position) {
        Intent intent = new Intent(this, UserInfoActivity.class);
        intent.putExtra(USER_NAME, followersData.get(position).getUserName());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(findViewById(android.R.id.content).getWindowToken(), 0);
    }
}

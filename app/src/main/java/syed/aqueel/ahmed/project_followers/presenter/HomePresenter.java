package syed.aqueel.ahmed.project_followers.presenter;

/**
 * Created by syedaqueelahmed on 3/12/17.
 */

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import syed.aqueel.ahmed.project_followers.model.Response;
import syed.aqueel.ahmed.project_followers.rest.APIClient;
import syed.aqueel.ahmed.project_followers.rest.APIService;

public class HomePresenter {

    private HomeLayoutView layoutView;
    private Subscription subscription;

    public interface HomeLayoutView {
        void setFollowersData(List<Response> followersData);
        Context getContext();
    }

    public void attachView(HomeLayoutView view) {
        this.layoutView = view;
    }

    public void retreiveFollowersData(String userName) {
        APIService apiService = APIClient.getClient().create(APIService.class);
        subscription = apiService.getFollowerResponse(userName).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(followerResponse -> {
            layoutView.setFollowersData(followerResponse);
        }, error -> {
            Toast.makeText(layoutView.getContext(), "There was a Problem. Please Try Again.", Toast.LENGTH_LONG).show();
        });
    }

    public void detachView() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}

package syed.aqueel.ahmed.project_followers.presenter;

import android.content.Context;
import android.widget.Toast;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import syed.aqueel.ahmed.project_followers.model.UserResponse;
import syed.aqueel.ahmed.project_followers.rest.APIClient;
import syed.aqueel.ahmed.project_followers.rest.APIService;

/**
 * Created by syedaqueelahmed on 3/12/17.
 */

public class UserInfoPresenter {


    private Subscription subscription;
    private UserLayoutView userLayoutView;

    public interface UserLayoutView {
        void setUserInfo(UserResponse userInfo);

        Context getContext();
    }

    public void attachView(UserLayoutView userLayoutView) {
        this.userLayoutView = userLayoutView;
    }

    public void retrieveUserInfo(String userName) {

        APIService apiService = APIClient.getClient().create(APIService.class);
        subscription = apiService.getUserResponse(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userResponse -> {
                    userLayoutView.setUserInfo(userResponse);
                }, error -> {
                    Toast.makeText(userLayoutView.getContext(), "There was a Problem. Please Try Again.", Toast.LENGTH_LONG).show();
                });
    }

    public void detachView() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}

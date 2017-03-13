package syed.aqueel.ahmed.project_followers.rest;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import syed.aqueel.ahmed.project_followers.model.Response;
import syed.aqueel.ahmed.project_followers.model.UserResponse;

/**
 * Created by syedaqueelahmed on 3/12/17.
 */

public interface APIService {

    @GET("users/{username}")
    Observable<UserResponse> getUserResponse(@Path("username") String userName);

    @GET("users/{username}/followers")
    Observable<List<Response>> getFollowerResponse(@Path("username") String userName);

}

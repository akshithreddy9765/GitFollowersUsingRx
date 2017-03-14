package syed.aqueel.ahmed.project_followers.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by syedaqueelahmed on 3/11/17.
 */

public class Response {

    @SerializedName("login")
    private String userName;

    @SerializedName("avatar_url")
    private String imageUrl;

    private String password;

    public Response(String userName, String imageUrl) {
        this.userName = userName;
        this.imageUrl = imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

package syed.aqueel.ahmed.project_followers.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by syedaqueelahmed on 3/11/17.
 */

public class UserResponse {

    @SerializedName("login")
    private String userId;
    @SerializedName("name")
    private String userName;
    @SerializedName("avatar_url")
    private String imageURL;
    @SerializedName("followers")
    private String followers;
    @SerializedName("following")
    private String following;
    @SerializedName("public_repos")
    private String repositories;
    @SerializedName("location")
    private String location;
    @SerializedName("email")
    private String email;

    public UserResponse(String userId, String userName, String imageURL, String followers, String following, String repositories, String location, String email) {
        this.userId = userId;
        this.userName = userName;
        this.imageURL = imageURL;
        this.followers = followers;
        this.following = following;
        this.repositories = repositories;
        this.location = location;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }

    public String getRepositories() {
        return repositories;
    }

    public String getLocation() {
        return location != null ? location : "Location Not Given";
    }

    public String getEmail() {
        return email != null ? email : "Email Not Given";
    }
}

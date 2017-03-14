package syed.aqueel.ahmed.project_followers.model;

import java.util.List;

/**
 * Created by syedaqueelahmed on 3/11/17.
 */

public class FollowerResponse {
    private List<Response> responseList;

    private List<UserResponse> userResponses;

    public List<Response> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<Response> responseList) {
        this.responseList = responseList;
    }

    public List<UserResponse> getUserResponses() {
        return userResponses;
    }

    public void setUserResponses(List<UserResponse> userResponses) {
        this.userResponses = userResponses;
    }

    public FollowerResponse(List<Response> responseList) {
        this.responseList = responseList;
    }

    public List<Response> getIndividualResponse() {
        return responseList;
    }
}

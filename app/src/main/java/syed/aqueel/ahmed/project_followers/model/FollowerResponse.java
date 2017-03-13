package syed.aqueel.ahmed.project_followers.model;

import java.util.List;

/**
 * Created by syedaqueelahmed on 3/11/17.
 */

public class FollowerResponse {
    private List<Response> responseList;

    public FollowerResponse(List<Response> responseList) {
        this.responseList = responseList;
    }

    public List<Response> getIndividualResponse() {
        return responseList;
    }
}

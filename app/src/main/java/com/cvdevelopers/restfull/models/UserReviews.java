package com.cvdevelopers.restfull.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by CamiloVega on 2/22/17.
 */

public class UserReviews extends GUIDModel{

    public static final String FROM_USER = "from_user";
    public static final String TO_USER = "to_user";
    public static final String RATING = "rating";
    public static final String COMMENT = "comment";
    public static final String DATE = "date";

    @JsonProperty(FROM_USER)
    private String fromUser;

    @JsonProperty(TO_USER)
    private String toUser;

    @JsonProperty(RATING)
    private String rating;

    @JsonProperty(COMMENT)
    private String comment;

    @JsonProperty(DATE)
    private String date;

    public UserReviews() {
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

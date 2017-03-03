package com.cvdevelopers.restfull.models;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Date;

/**
 * Created by CamiloVega on 2/20/17.
 */

@JsonRootName("rental_item")
public class RentalItem extends GUIDModel {

    public static final String DISPLAY_NAME_FIELD = "display_name";
    public static final String PICTURE_FIELD = "picture";
    public static final String PRICE_FIELD = "price";
    public static final String PRICING_TYPE_FIELD = "pricing_type";
    public static final String REGISTRATION_DATE_FIELD = "registration_date";
    public static final String AVAILABILITY_FIELD = "availability";
    public static final String OWNER_FIELD = "owner";
    public static final String ITEM_VALUE_FIELD = "item_value";
    public static final String DESCRIPTION_FIELD = "description";
    public static final String HISTORY_FIELD = "history";
    public static final String PICKUP_ADDRESS_FIELD = "pickup_address";
    public static final String CURRENT_LEASER_FIELD = "current_leaser";
    public static final String CATEGORIES_FIELD = "categories";
    public static final String STATS_FIELD = "stats";
    public static final String CURRENT_STATE_FIELD = "current_state";


    public static final String CURRENT_STATE_AVAILABLE = "available";

    @JsonProperty(DISPLAY_NAME_FIELD)
    private String name;

    @JsonProperty(PICTURE_FIELD)
    private String picture;

    @JsonProperty(PRICE_FIELD)
    private Integer price;

    @JsonProperty(PRICING_TYPE_FIELD)
    private String pricingType;

    @JsonProperty(REGISTRATION_DATE_FIELD)
    private Date registrationDate;

    @JsonProperty(AVAILABILITY_FIELD)
    private Date availability;

    @JsonProperty(OWNER_FIELD)
    private User owner;

    @JsonProperty(ITEM_VALUE_FIELD)
    private Integer itemValue;

    @JsonProperty(DESCRIPTION_FIELD)
    private String description;

//    TODO define how history should work
//    @JsonProperty(HISTORY_FIELD)
//    private String name;

    //TODO should be formated to have city, country and other
    @JsonProperty(PICKUP_ADDRESS_FIELD)
    private String pickUpAddress;

    @JsonProperty(CURRENT_LEASER_FIELD)
    private User currentLeaser;

    //should be an array
    @JsonProperty(CATEGORIES_FIELD)
    private String[] categories;

    //TODO Should define the stats object
//    @JsonProperty(STATS_FIELD)
//    private Stats stats;

    @JsonProperty(CURRENT_STATE_FIELD)
    private String currentState;

    public RentalItem() {
    }

    public RentalItem(String name, String picture, Integer price, User owner) {
        this.name = name;
        this.picture = picture;
        this.price = price;
        this.owner = owner;
        this.currentState = CURRENT_STATE_AVAILABLE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPricingType() {
        return pricingType;
    }

    public void setPricingType(String pricingType) {
        this.pricingType = pricingType;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getAvailability() {
        return availability;
    }

    public void setAvailability(Date availability) {
        this.availability = availability;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Integer getItemValue() {
        return itemValue;
    }

    public void setItemValue(Integer itemValue) {
        this.itemValue = itemValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(String pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public User getCurrentLeaser() {
        return currentLeaser;
    }

    public void setCurrentLeaser(User currentLeaser) {
        this.currentLeaser = currentLeaser;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }
}

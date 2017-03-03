package com.cvdevelopers.restfull.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

/**
 * Created by CamiloVega on 3/1/17.
 */

public class RentalItems {

    @JsonProperty("rental_items")
    private List<RentalItem> rentalItems;

    public RentalItems() {
    }

    public List<RentalItem> getRentalItems() {
        return rentalItems;
    }

    public void setRentalItems(List<RentalItem> rentalItems) {
        this.rentalItems = rentalItems;
    }
}

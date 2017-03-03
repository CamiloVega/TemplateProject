package com.cvdevelopers.restfull.services;

import com.cvdevelopers.restfull.api.RestManager;
import com.cvdevelopers.restfull.models.RentalItem;
import com.cvdevelopers.restfull.models.RentalItems;
import com.cvdevelopers.restfull.models.Response;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

/**
 * Created by CamiloVega on 3/1/17.
 */

@EBean(scope = EBean.Scope.Singleton)
public class RentalItemService {

    @Bean
    RestManager restManager;

    public RentalItems getRentalItems(){
        return restManager.getClient().getRentalItems();
    }

    public RentalItem getRentalItem(){
        return restManager.getClient().getRentalItem();
    }

    public RentalItem postRentalItem(RentalItem rentalItem) {
        return restManager.getClient().postRentalItem(rentalItem);
    }
}

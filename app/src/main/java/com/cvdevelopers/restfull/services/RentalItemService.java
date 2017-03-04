package com.cvdevelopers.restfull.services;

import com.cvdevelopers.restfull.api.RestManager;
import com.cvdevelopers.restfull.api.exceptions.ApiUnavailableException;
import com.cvdevelopers.restfull.api.exceptions.ServiceException;
import com.cvdevelopers.restfull.models.RentalItem;
import com.cvdevelopers.restfull.models.RentalItems;
import com.cvdevelopers.restfull.models.Response;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.springframework.http.ResponseEntity;

/**
 * Created by CamiloVega on 3/1/17.
 */

@EBean(scope = EBean.Scope.Singleton)
public class RentalItemService {

    @Bean
    RestManager restManager;

    public RentalItems getRentalItems() throws ApiUnavailableException, ServiceException {
        return new ApiRequest<RentalItems, ServiceException>() {
            @Override
            protected ResponseEntity<RentalItems> onRequest() {
                return restManager.getClient().getRentalItems();
            }
        }.invoke(new ServiceException());

    }

    public RentalItem getRentalItem() throws ApiUnavailableException, ServiceException {
        return new ApiRequest<RentalItem, ServiceException>() {
            @Override
            protected ResponseEntity<RentalItem> onRequest() {
                return restManager.getClient().getRentalItem();
            }
        }.invoke(new ServiceException());

    }

    public RentalItem postRentalItem(final RentalItem rentalItem) throws ApiUnavailableException, ServiceException {
        return new ApiRequest<RentalItem, ServiceException>() {
            @Override
            protected ResponseEntity<RentalItem> onRequest() {
                return restManager.getClient().postRentalItem(rentalItem);
            }
        }.invoke(new ServiceException());
    }
}

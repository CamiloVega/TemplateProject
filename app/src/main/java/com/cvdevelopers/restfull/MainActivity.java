package com.cvdevelopers.restfull;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.cvdevelopers.restfull.activity.CreateItemRentalActivity_;
import com.cvdevelopers.restfull.adapters.AvailableItemsGridAdapter;
import com.cvdevelopers.restfull.api.RestManager;
import com.cvdevelopers.restfull.models.RentalItem;
import com.cvdevelopers.restfull.models.RentalItems;
import com.cvdevelopers.restfull.models.Response;
import com.cvdevelopers.restfull.services.PingService;
import com.cvdevelopers.restfull.services.RentalItemService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

//annotation to make it generate the respective annotated class.
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    //Annotation that finds the view with that id and attaches it to the label
    @ViewById(R.id.hello_world_text)
    TextView helloWorldLabel;

    @ViewById(R.id.available_items_gridview)
    GridView availableItemsGridView;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;
    //annotation that injects the instance of pingservice,
    @Bean
    PingService pingService;

    @Bean
    RentalItemService rentalItemService;

    AvailableItemsGridAdapter gridAdapter;
    //annotation that is executed after all the views are accessible
    @AfterViews
    protected void afterViews() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (listener != null) {
//                    listener.onFragmentBackPressed();
//                }
            }
        });

        gridAdapter = new AvailableItemsGridAdapter(this);
        availableItemsGridView.setAdapter(gridAdapter);
        availableItemsGridView.setNumColumns(3);
        availableItemsGridView.setHorizontalSpacing((int) getResources().getDimension(R.dimen.small_margin));
    }

    @Override
    protected void onResume() {
        super.onResume();
        pingTheServer();
    }

    //annotation to run this method in the background
    @Background
    protected void pingTheServer() {
        Response response = pingService.pingServer();
        updateLabelWithResponse(response.getResponse());
        RentalItems rentalItems = rentalItemService.getRentalItems();
        updateGridViewList(rentalItems.getRentalItems());
    }

    @UiThread
    protected void updateGridViewList(List<RentalItem> rentalItemList){
        gridAdapter.setRentalItemList(rentalItemList);
        gridAdapter.notifyDataSetChanged();
    }

    @Click(R.id.create_rental_item_button)
    protected void onCreateRentalItemButton() {
        Intent intent = new Intent(this, CreateItemRentalActivity_.class);
        startActivity(intent);
    }
    //annotation to run this method in the main thread
    @UiThread
    protected void updateLabelWithResponse (String response){
        helloWorldLabel.setText(response);
    }

}

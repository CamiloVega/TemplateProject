package com.cvdevelopers.restfull.activity;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.cvdevelopers.restfull.R;
import com.cvdevelopers.restfull.api.exceptions.ApiUnavailableException;
import com.cvdevelopers.restfull.api.exceptions.ServiceException;
import com.cvdevelopers.restfull.fragments.CreateItemDetailsFragment;
import com.cvdevelopers.restfull.fragments.CreateItemDetailsFragment_;
import com.cvdevelopers.restfull.fragments.RentalItemConfirmationFragment;
import com.cvdevelopers.restfull.fragments.RentalItemConfirmationFragment_;
import com.cvdevelopers.restfull.managers.ImageManager;
import com.cvdevelopers.restfull.models.RentalItem;
import com.cvdevelopers.restfull.services.RentalItemService;
import com.cvdevelopers.restfull.util.FileUtils;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.UiThread;

import java.io.IOException;

import static com.cvdevelopers.restfull.managers.ImageManager.REQUEST_CODE_SELECT_IMAGE;

/**
 * Created by CamiloVega on 2/28/17.
 */

@EActivity(R.layout.create_item_rental_activity)
public class CreateItemRentalActivity extends AppCompatActivity
        implements CreateItemDetailsFragment.CreateItemDetailsFragmentListener, RentalItemConfirmationFragment.RentalItemConfirmationFragmentListener {

    private static final String TAG = CreateItemRentalActivity.class.getName();

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Bean
    ImageManager imageManager;

    @Bean
    RentalItemService rentalItemService;

    private ImageManager.ImagePicker imagePicker;

    @AfterViews
    protected void afterViews(){
        startAddItemFlow();
    }

    @AfterInject
    protected void afterInject(){
        imagePicker = imageManager.getPicker(new ImageManager.PickerListener() {
            @Override
            public void onImageSelected(Uri fileUri) {
                Fragment createItemFragment = CreateItemDetailsFragment_.builder().pictureURI(fileUri).build();
                setVisibleFragment(createItemFragment, "");
            }

            @Override
            public void onImageSelectCanceled() {

            }

            @Override
            public void onImageSelectFailed() {

            }
        });
    }

    private void startAddItemFlow() {
        if (imagePicker != null) {
            imagePicker.pick(this);
        }
    }

    @OnActivityResult(REQUEST_CODE_SELECT_IMAGE)
    protected void pictureTaken (int resultCode, Intent data){
        try {
            imagePicker.handleImageSelectionResult(resultCode, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @UiThread
    protected void setVisibleFragment(Fragment fragment, String fragmentTag) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment, fragmentTag);
            fragmentTransaction.commit();
    }

    @Override
    public void onItemPublished(RentalItem rentalItem) {
       postRentalItem(rentalItem);
    }

    @Background
    protected void postRentalItem(RentalItem rentalItem) {
        try {
            RentalItem newRentalItem = rentalItemService.postRentalItem(rentalItem);
            Log.i("ITEM", newRentalItem.toString());
            RentalItemConfirmationFragment rentalItemFragment = RentalItemConfirmationFragment_.builder().build();
            rentalItemFragment.setRentalItem(rentalItem);
            setVisibleFragment(rentalItemFragment, "");
        } catch (ApiUnavailableException e) {
           finish();
        } catch (ServiceException e) {
            Log.e(TAG, e.toString());
        }

    }

    @Override
    public void onAddAnotherItemClicked() {
        startAddItemFlow();
    }

    @Override
    @UiThread
    public void onGoHomePressed() {
        finish();
    }
}

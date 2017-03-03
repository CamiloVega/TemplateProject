package com.cvdevelopers.restfull.view;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cvdevelopers.restfull.R;
import com.cvdevelopers.restfull.managers.ImageManager;
import com.cvdevelopers.restfull.models.RentalItem;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * Created by CamiloVega on 3/2/17.
 */
@EViewGroup(R.layout.rental_item_cell_view)
public class RentalItemCellView extends LinearLayout {

    @ViewById(R.id.item_image)
    ImageView itemImage;

    @ViewById(R.id.item_name)
    TextView itemName;

    @ViewById(R.id.item_price)
    TextView itemPrice;

    @Bean
    ImageManager imageManager;


    private RentalItem rentalItem;

    public RentalItemCellView(Context context) {
        super(context);
    }

    public RentalItemCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RentalItemCellView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void bindItem(RentalItem rentalItem){
        this.rentalItem = rentalItem;
//        try {
            File imageFile = new File(getContext().getExternalCacheDir(), rentalItem.getPicture());
            if (imageFile.exists()) {
                itemImage.setImageURI(imageManager.getUriFromFile(imageFile));
            }
//        }
//        catch (URISyntaxException e) {
//            e.printStackTrace();
//        }

        itemName.setText(rentalItem.getName());
        itemPrice.setText("$ " + rentalItem.getPrice() + "/ dia");
    }

}

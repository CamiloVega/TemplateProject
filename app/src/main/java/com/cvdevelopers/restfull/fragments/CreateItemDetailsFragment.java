package com.cvdevelopers.restfull.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cvdevelopers.restfull.R;
import com.cvdevelopers.restfull.models.RentalItem;
import com.cvdevelopers.restfull.models.User;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateItemDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */

@EFragment (R.layout.fragment_create_item_details)
public class CreateItemDetailsFragment extends Fragment {

    public static final String PICTURE_URI = "picture_uri";

    @FragmentArg
    Uri pictureURI;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    @ViewById(R.id.item_name)
    EditText itemName;

    @ViewById(R.id.item_image)
    ImageView itemImage;

    @ViewById(R.id.item_price)
    EditText itemPrice;

    @ViewById (R.id.comission_text)
    TextView comissionText;

    @ViewById(R.id.total_price_text)
    TextView totalPriceText;

    @ViewById
    Toolbar toolbar;


    private CreateItemDetailsFragmentListener mListener;

    public CreateItemDetailsFragment() {
        // Required empty public constructor
    }

    @AfterViews
    protected void afterViews() {
        //Handles the back button of the toolbar for this particular fragment.
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (listener != null) {
//                    listener.onFragmentBackPressed();
//                }
            }
        });

        itemImage.setImageURI(pictureURI);

        comissionText.setText("$ 0");
        totalPriceText.setText("$ 0");
    }

    @AfterTextChange (R.id.item_price)
    protected void afterItemPriceText() {
        String priceText = itemPrice.getText().toString();
        int itemRentalPrice = 0;
        if (!priceText.isEmpty()) {
            itemRentalPrice = Integer.parseInt(itemPrice.getText().toString());
        }
        comissionText.setText("$ " + (Math.ceil(itemRentalPrice * 0.15)));
        totalPriceText.setText("$ " + (Math.ceil(itemRentalPrice * 1.15)));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CreateItemDetailsFragmentListener) {
            mListener = (CreateItemDetailsFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface CreateItemDetailsFragmentListener {
        void onItemPublished(RentalItem rentalItem);
    }

    @Click(R.id.publish_item_button)
    protected void onPublishRentalItemButton() {
        if (verifyFields()){
            int itemRentalPrice = (int) Math.ceil(Integer.parseInt(itemPrice.getText().toString()) * 1.15);

            RentalItem rentalItem = new RentalItem(itemName.getText().toString(),
                    pictureURI.getLastPathSegment(),itemRentalPrice, null );
            mListener.onItemPublished(rentalItem);
        }


    }

    private boolean verifyFields (){
        if (itemName.getText().length() == 0){
            Toast.makeText(getActivity(), "Por favor ponle un nombre al articulo",
                    Toast.LENGTH_LONG).show();
            return false;
        } else if (itemPrice.getText().length() == 0){
            Toast.makeText(getActivity(), "Por favor ponle un precio al articulo",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}

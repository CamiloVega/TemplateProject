package com.cvdevelopers.restfull.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cvdevelopers.restfull.R;
import com.cvdevelopers.restfull.models.RentalItem;
import com.cvdevelopers.restfull.view.RentalItemCellView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RentalItemConfirmationFragment.RentalItemConfirmationFragmentListener} interface
 * to handle interaction events.
 * Use the {@link RentalItemConfirmationFragment} factory method to
 * create an instance of this fragment.
 */
@EFragment(R.layout.fragment_rental_item_confirmation)
public class RentalItemConfirmationFragment extends Fragment {

    private RentalItemConfirmationFragmentListener mListener;

    private RentalItem rentalItem;

    @ViewById(R.id.item_cell)
    RentalItemCellView rentalItemCell;

    public RentalItemConfirmationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RentalItemConfirmationFragmentListener) {
            mListener = (RentalItemConfirmationFragmentListener) context;
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
    public interface RentalItemConfirmationFragmentListener {
        // TODO: Update argument type and name
        void onAddAnotherItemClicked();

        void onGoHomePressed();
    }

    public void setRentalItem(RentalItem rentalItem) {
        this.rentalItem = rentalItem;
    }

    @AfterViews
    protected void afterViews() {
        if (rentalItem != null) {
            rentalItemCell.bindItem(rentalItem);
        }
    }

    @Click(R.id.add_another_item_button)
    protected void onAddAnotherItemClicked() {
        mListener.onAddAnotherItemClicked();
    }

    @Click(R.id.go_home_button)
    protected void onGoHomePressed(){
        mListener.onGoHomePressed();
    }
}

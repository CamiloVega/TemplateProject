package com.cvdevelopers.restfull.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.cvdevelopers.restfull.models.RentalItem;
import com.cvdevelopers.restfull.view.RentalItemCellView;
import com.cvdevelopers.restfull.view.RentalItemCellView_;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Custom Base adapter to manage the information to be displayed in the User List View
 */

public class AvailableItemsGridAdapter extends BaseAdapter implements Filterable, Serializable {

    private List<RentalItem> rentalItemList;
    private List<RentalItem> filteredRentalItemList;
    private transient Context context;

    public AvailableItemsGridAdapter(Context context, List<RentalItem> rentalItemList) {
        this.context = context;
        this.rentalItemList = rentalItemList;
        this.filteredRentalItemList = rentalItemList;
    }

    public AvailableItemsGridAdapter(Context context) {
        this.context = context;
        this.rentalItemList = new ArrayList<>();
        this.filteredRentalItemList = this.rentalItemList;
    }

    public void resetFilter(){
        filteredRentalItemList = rentalItemList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return filteredRentalItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return filteredRentalItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void setRentalItemList(List<RentalItem> rentalItemList) {
        this.rentalItemList = rentalItemList;
        resetFilter();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = RentalItemCellView_.build(context);
        }
        ((RentalItemCellView) view).bindItem(filteredRentalItemList.get(i));
        return view;
    }

    public void filterDataByString(String filterString) {
        getFilter().filter(filterString);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredRentalItemList = (List<RentalItem>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<RentalItem> filteredResults = filterArray(constraint);
                Collections.sort(filteredResults, new Comparator<RentalItem>(){
                    public int compare(RentalItem rentalItem1, RentalItem rentalItem2) {
                        return rentalItem1.getName().compareToIgnoreCase(rentalItem2.getName());
                    }
                });
                FilterResults results = new FilterResults();
                results.values = filteredResults;
                results.count = filteredResults.size();
                return results;
            }

            protected ArrayList<RentalItem> filterArray(CharSequence constraint) {
                ArrayList<RentalItem> filterArray = new ArrayList<>();
                for (RentalItem rentalItem : rentalItemList) {
                    if (rentalItem.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filterArray.add(rentalItem);
                    }
                }
                return filterArray;
            }
        };
    }

}

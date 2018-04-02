package andro.geeks.pack.autocallrecorder.Recycleerview;

import android.widget.Filter;

import java.util.ArrayList;

/**
 * Created by pallob on 4/2/18.
 */

public class CustomFilter extends Filter {
    CustomRecyclerView adapter;
    ArrayList<Callers> filterList;


    public CustomFilter(ArrayList<Callers> filterList,CustomRecyclerView adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }


    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();

        if(constraint != null && constraint.length() > 0)
        {
            constraint=constraint.toString().toUpperCase();
            ArrayList<Callers> filteredCallers=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {

                if(filterList.get(i).getName().toUpperCase().contains(constraint))
                {

                    filteredCallers.add(filterList.get(i));
                }
            }

            results.count=filteredCallers.size();
            results.values=filteredCallers;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;

        }


        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.callers= (ArrayList<Callers>) results.values;

        adapter.notifyDataSetChanged();
    }
}
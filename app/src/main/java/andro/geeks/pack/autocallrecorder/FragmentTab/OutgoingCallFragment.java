package andro.geeks.pack.autocallrecorder.FragmentTab;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import andro.geeks.pack.autocallrecorder.R;
import andro.geeks.pack.autocallrecorder.Recycleerview.Callers;
import andro.geeks.pack.autocallrecorder.Recycleerview.CustomRecyclerView;


/**
 * Created by pallob on 3/28/18.
 */
@SuppressLint("ValidFragment")
public class OutgoingCallFragment extends Fragment {
    List<String>Name;
    List<String>Number;
    List<String>Date;
    List<String>Duration;
    ArrayList<Callers> callersArrayList;
    ArrayList<Callers> callersArrayListp;
    View view;
    CustomRecyclerView customRecyclerView;

    @SuppressLint("ValidFragment")
    public OutgoingCallFragment( ArrayList<Callers> callersArrayList){

       this.callersArrayList=callersArrayList;


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.outgoing,container,false);
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.outgoingCallList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
         customRecyclerView=new CustomRecyclerView(getActivity(),callersArrayList);
        recyclerView.setAdapter(customRecyclerView);


        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.topmenu,menu);
        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                customRecyclerView.getFilter().filter(newText);
                return false;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.sort:
                SortingDialog();
                break;


        }

        return super.onOptionsItemSelected(item);
    }


    public void SortingDialog(){
        Dialog dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.sortingmenu);
        dialog.setCancelable(true);
       final CheckBox Ascending=(CheckBox)dialog.findViewById(R.id.Ascending);
        final CheckBox Descending=(CheckBox)dialog.findViewById(R.id.Descending);
        dialog.show();
        Ascending.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Descending.setChecked(false);

            }
        });

        Descending.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Ascending.setChecked(false);

            }
        });





    }




}

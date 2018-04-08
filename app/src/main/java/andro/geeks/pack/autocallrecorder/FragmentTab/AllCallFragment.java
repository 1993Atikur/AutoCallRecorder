package andro.geeks.pack.autocallrecorder.FragmentTab;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
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
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import andro.geeks.pack.autocallrecorder.MainActivity;
import andro.geeks.pack.autocallrecorder.R;
import andro.geeks.pack.autocallrecorder.Recycleerview.Callers;
import andro.geeks.pack.autocallrecorder.Recycleerview.CustomRecyclerView;


/**
 * Created by pallob on 3/25/18.
 */

@SuppressLint("ValidFragment")
public class AllCallFragment extends Fragment {

    View view;
    ArrayList<Callers> callersArrayList;
    CustomRecyclerView customRecyclerView;
    RecyclerView recyclerView;
    Context context;
    @SuppressLint("ValidFragment")
    public AllCallFragment(ArrayList<Callers>callersArrayList, Context context) {

        this.context=context;
        this.callersArrayList=callersArrayList;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.allcall, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        customRecyclerView = new CustomRecyclerView(getActivity(), callersArrayList);
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
        final CheckBox acending=(CheckBox)dialog.findViewById(R.id.Ascending);
        final CheckBox decending=(CheckBox)dialog.findViewById(R.id.Descending);
        dialog.setCancelable(true);
        dialog.show();
        final Intent intent=new Intent(getActivity(),MainActivity.class);
        acending.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    decending.setChecked(false);

                    intent.putExtra("ORDER","null");
                    getActivity().startActivity(intent);
                    getActivity().finish();

                }
            }
        });

        decending.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    acending.setChecked(false);

                    intent.putExtra("ORDER", CallLog.Calls.DEFAULT_SORT_ORDER);
                    getActivity().startActivity(intent);
                    getActivity().finish();


                }
            }
        });
    }

}
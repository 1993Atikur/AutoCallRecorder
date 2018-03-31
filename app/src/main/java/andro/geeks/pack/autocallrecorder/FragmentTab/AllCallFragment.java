package andro.geeks.pack.autocallrecorder.FragmentTab;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import andro.geeks.pack.autocallrecorder.R;
import andro.geeks.pack.autocallrecorder.Recycleerview.CustomRecyclerView;


/**
 * Created by pallob on 3/25/18.
 */

@SuppressLint("ValidFragment")
public class AllCallFragment extends Fragment {

    List<String>Name=new ArrayList<String>();
    List<String>Number=new ArrayList<String>();
    List<String>Date=new ArrayList<String>();
    List<String>Duration=new ArrayList<String>();

    View view;


    RecyclerView recyclerView;
    @SuppressLint("ValidFragment")
    public AllCallFragment(List<String>Name,List<String>Number,List<String>Duration,List<String>Date) {

       this.Name=Name;
       this.Number=Number;
       this.Date=Date;
       this.Duration=Duration;


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.allcall,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CustomRecyclerView customRecyclerView=new CustomRecyclerView(getActivity(),Name,Number,Duration,Date);
        recyclerView.setAdapter(customRecyclerView);

        return view;
    }
}
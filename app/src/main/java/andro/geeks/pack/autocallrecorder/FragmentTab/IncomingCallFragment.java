package andro.geeks.pack.autocallrecorder.FragmentTab;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import andro.geeks.pack.autocallrecorder.R;
import andro.geeks.pack.autocallrecorder.Recycleerview.CustomRecyclerView;


/**
 * Created by pallob on 3/28/18.
 */
@SuppressLint("ValidFragment")
public class IncomingCallFragment  extends Fragment{
    List<String>Name;
    List<String>Number;
    List<String>Date;
    List<String>Duration;

    View view;


    @SuppressLint("ValidFragment")
    public IncomingCallFragment(List<String>Name,List<String>Number,List<String>Duration,List<String>Date){

      this.Name=Name;
        this.Number=Number;
        this.Duration=Duration;
        this.Date=Date;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.incoming,container,false);
       RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.incomingCallList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CustomRecyclerView customRecyclerView=new CustomRecyclerView(getActivity(),Name,Number,Duration,Date);
        recyclerView.setAdapter(customRecyclerView);


        return view;
    }
}

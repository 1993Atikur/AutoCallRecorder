package andro.geeks.pack.autocallrecorder.FragmentTab;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import andro.geeks.pack.autocallrecorder.FragmentTab.AllCallFragment;
import andro.geeks.pack.autocallrecorder.Recycleerview.Callers;

public class CustomPagerAdapter extends FragmentStatePagerAdapter{

    int numberoftabs;



    ArrayList<Callers>INCOMING;
    ArrayList<Callers>OUTGOING;
    ArrayList<Callers>ALL;

    List<List>ALL_INFO;
Context activity;
    public CustomPagerAdapter(FragmentManager fm,int numberoftabs, List<List>ALL_INFO,Context activity) {
        super(fm);
        this.numberoftabs=numberoftabs;
        this.ALL_INFO=ALL_INFO;

        this.INCOMING=(ArrayList<Callers>) ALL_INFO.get(0);
        this.OUTGOING= (ArrayList<Callers>) ALL_INFO.get(1);
        this.ALL=(ArrayList<Callers>) ALL_INFO.get(2);
        this.activity=activity;


    }

    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                AllCallFragment allCallFragment=new AllCallFragment(ALL,activity);
                return allCallFragment;
            case 1:
                IncomingCallFragment incomingCallFragment =new IncomingCallFragment(INCOMING);
                return incomingCallFragment;

            case 2:
                OutgoingCallFragment outgoingCallFragment=new OutgoingCallFragment(OUTGOING);
                return outgoingCallFragment;

            default:
                return null;

        }



    }

    @Override
    public int getCount() {
        return numberoftabs;
    }
}

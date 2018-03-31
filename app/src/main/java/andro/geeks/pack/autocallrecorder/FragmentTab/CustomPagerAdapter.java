package andro.geeks.pack.autocallrecorder.FragmentTab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import andro.geeks.pack.autocallrecorder.FragmentTab.AllCallFragment;

public class CustomPagerAdapter extends FragmentStatePagerAdapter{

    int numberoftabs;


    List<List>A1;
    List<List>B1;
    List<List>C1;
    List<List>D1;

    public CustomPagerAdapter(FragmentManager fm,int numberoftabs, List<List>a1,List<List>b1,List<List>c1,List<List>d1) {
        super(fm);
        this.numberoftabs=numberoftabs;
        this.A1=a1;
        this.B1=b1;
        this.C1=c1;
        this.D1=d1;


    }

    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                AllCallFragment allCallFragment=new AllCallFragment(A1.get(2),B1.get(2),C1.get(2),D1.get(2));
                return allCallFragment;
            case 1:
                IncomingCallFragment incomingCallFragment =new IncomingCallFragment(A1.get(0),B1.get(0),C1.get(0),D1.get(0));
                return incomingCallFragment;

            case 2:
                OutgoingCallFragment outgoingCallFragment=new OutgoingCallFragment(A1.get(1),B1.get(1),C1.get(1),D1.get(1));
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

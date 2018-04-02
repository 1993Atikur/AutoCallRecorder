package andro.geeks.pack.autocallrecorder;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Build;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.renderscript.Type;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import andro.geeks.pack.autocallrecorder.FragmentTab.CustomPagerAdapter;
import andro.geeks.pack.autocallrecorder.Recycleerview.Callers;


public class MainActivity extends AppCompatActivity {






    ArrayList<Callers> callersArrayListIncoming=new ArrayList<>();
    ArrayList<Callers> callersArrayListOutgoing=new ArrayList<>();
    ArrayList<Callers> callersArrayListall=new ArrayList<>();


    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;


    SimpleDateFormat format;

    List<List>ObjectList=new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ObjectList.add(0,new ArrayList<Callers>());
        ObjectList.add(1,new ArrayList<Callers>());
        ObjectList.add(2,new ArrayList<Callers>());





        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        NavigationView navigationView=(NavigationView)findViewById(R.id.navigatior);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        Setter();

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        SetNavigationMenuItemSelector(navigationView);
        SetActionSwitch(navigationView);





        TabLayout tabLayout=(TabLayout)findViewById(R.id.tablayout);
        final ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager);


        tabLayout.addTab(tabLayout.newTab().setText("All Calls"));
        tabLayout.addTab(tabLayout.newTab().setText("Incoming"));
        tabLayout.addTab(tabLayout.newTab().setText("Outgoing"));
        CustomPagerAdapter adapter=new CustomPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount(), ObjectList,this);

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(toggle.onOptionsItemSelected(item)){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void SetNavigationMenuItemSelector(NavigationView navigationView){

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.home:
                        Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();

                        drawerLayout.closeDrawers();
                        break;
                    case R.id.about:
                        Toast.makeText(getApplicationContext(),"about",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.settings:
                        Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.exit:
                        drawerLayout.closeDrawers();
                        finish();
                        break;

                    default:

                        break;


                }
                return true;
            }
        });

    }


    public void SetActionSwitch(NavigationView navigationView){

        View headerlayout=navigationView.getHeaderView(0);
        final Switch aswitch=(Switch)headerlayout.findViewById(R.id.switchid);
        TextView textView=(TextView)headerlayout.findViewById(R.id.Title);



        aswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(),"TURN ON",Toast.LENGTH_SHORT).show();
                    aswitch.setText("Enabled");

                }else{

                    Toast.makeText(getApplicationContext(),"TURN OFF",Toast.LENGTH_SHORT).show();
                    aswitch.setText("Disabled");
                }
            }
        });

    }



    public void Setter(){
        int number,date,duration,type,name,minute,second;
        String Name,currentdate;
        Callers []obj= new Callers[100];


        Cursor mCursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
        number= mCursor.getColumnIndex(CallLog.Calls.NUMBER);
        date = mCursor.getColumnIndex(CallLog.Calls.DATE);
        duration = mCursor.getColumnIndex(CallLog.Calls.DURATION);
        type = mCursor.getColumnIndex(CallLog.Calls.TYPE);
        name= mCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);


        int i=0;

        while (mCursor.moveToNext()){
            if(i==100)
                break;

            i++;

            obj[i]=new Callers();
            String callduration = mCursor.getString(duration);
            String callnumber=mCursor.getString(number);
            String calltype = mCursor.getString(type);
            String calldate = mCursor.getString(date);
            String callname1 = mCursor.getString(name);
            if(callname1==null){
                Name="Unknown";
            }else {

                Name=callname1;
            }

            minute = (Integer.valueOf(callduration)) / 60;second = (Integer.valueOf(callduration)) % 60;
            String time= minute + " minutes " + second + " seconds";
            Date calldaytime= new Date(Long.valueOf(calldate));
            format = new SimpleDateFormat("dd-MMMM-yy\nhh:mm aa");
            currentdate = format.format(calldaytime);

            switch (Integer.parseInt(calltype)){


                case CallLog.Calls.INCOMING_TYPE:

                    obj[i].setName(Name);
                    obj[i].setNumber(callnumber);
                    obj[i].setDate(currentdate);
                    obj[i].setDuration(time);
                    ObjectList.get(0).add(obj[i]);
                    ObjectList.get(2).add(obj[i]);
                    break;
                case CallLog.Calls.OUTGOING_TYPE:

                    obj[i].setName(Name);
                    obj[i].setNumber(callnumber);
                    obj[i].setDate(currentdate);
                    obj[i].setDuration(time);
                    ObjectList.get(1).add(obj[i]);
                    ObjectList.get(2).add(obj[i]);
                    break;

            }





        }



    }






}
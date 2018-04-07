package andro.geeks.pack.autocallrecorder;


import android.content.Intent;
import android.database.Cursor;


import android.provider.CallLog;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import andro.geeks.pack.autocallrecorder.FragmentTab.CustomPagerAdapter;
import andro.geeks.pack.autocallrecorder.RecordMedia.CallDetectorService;
import andro.geeks.pack.autocallrecorder.RecordMedia.DataBase;
import andro.geeks.pack.autocallrecorder.Recycleerview.Callers;

/**
 * Created by pallob on 3/30/18.
 */


public class MainActivity extends AppCompatActivity {
    SimpleDateFormat format,filedate;
    TabLayout tabLayout;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    CustomPagerAdapter adapter;
    List<List>ObjectList=new ArrayList<>();
    DataBase dataBase;
    String APPSTATE="" ;
    boolean v;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       dataBase=new DataBase(this);
        tabLayout=(TabLayout)findViewById(R.id.tablayout);
        final ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager);
        tabLayout.addTab(tabLayout.newTab().setText("All Calls"));
        tabLayout.addTab(tabLayout.newTab().setText("Incoming"));
        tabLayout.addTab(tabLayout.newTab().setText("Outgoing"));
        ObjectList.add(0,new ArrayList<Callers>());
        ObjectList.add(1,new ArrayList<Callers>());
        ObjectList.add(2,new ArrayList<Callers>());
       intent=new Intent(MainActivity.this, CallDetectorService.class);


        Setter();

        adapter=new CustomPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount(), ObjectList,this);
        Cursor mycursor=dataBase.getall();
        while (mycursor.moveToNext()){
            APPSTATE=mycursor.getString(0);

        }
       if(APPSTATE==null){
            v=dataBase.insertvalue("FALSE");
       }

        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        NavigationView navigationView=(NavigationView)findViewById(R.id.navigatior);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        SetNavigationMenuItemSelector(navigationView);
        SetActionSwitch(navigationView);


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


           if(APPSTATE.equals("TRUE")){
               aswitch.setChecked(true);
               aswitch.setText("Enabled");
               startService(intent);

           }

           else {
            aswitch.setChecked(false);

      }


        aswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){


                            dataBase.delete();
                            v=dataBase.insertvalue("TRUE");
                            if (v)
                            aswitch.setText("Enabled");
                            startService(intent);

                }else{
                    dataBase.delete();
                    v=dataBase.insertvalue("FALSE");
                    if(v)
                    aswitch.setText("Disabled");
                    startService(intent);
                }
            }
        });

    }
    public void Setter() {
        int number, date, duration, type, name, minute, second;
        String Name, currentdate,fileName;
        Callers[] obj = new Callers[10000];


        Cursor mCursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
        number = mCursor.getColumnIndex(CallLog.Calls.NUMBER);
        date = mCursor.getColumnIndex(CallLog.Calls.DATE);
        duration = mCursor.getColumnIndex(CallLog.Calls.DURATION);
        type = mCursor.getColumnIndex(CallLog.Calls.TYPE);
        name = mCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);



        int i = 0;

        while (mCursor.moveToNext()) {



            obj[i] = new Callers();
            String callduration = mCursor.getString(duration);
            String callnumber = mCursor.getString(number);
            String calltype = mCursor.getString(type);
            String calldate = mCursor.getString(date);
            String callname1 = mCursor.getString(name);
            if (callname1 == null) {
                Name = "Unknown";
            } else {

                Name = callname1;
            }

            minute = (Integer.valueOf(callduration)) / 60;
            second = (Integer.valueOf(callduration)) % 60;
            String time = minute + " minutes " + second + " seconds";
            Date calldaytime = new Date(Long.valueOf(calldate));
            format = new SimpleDateFormat("dd-MMMM-yy\nhh:mm aa");
            filedate=new SimpleDateFormat("ddMMyyhhmmssaa");
            fileName = filedate.format(calldaytime);
            currentdate = format.format(calldaytime);

            switch (Integer.parseInt(calltype)) {


                case CallLog.Calls.INCOMING_TYPE:

                    obj[i].setName(Name);
                    obj[i].setNumber(callnumber);
                    obj[i].setDate(currentdate);
                    obj[i].setDuration(time);
                    obj[i].setFileName(fileName);
                    ObjectList.get(0).add(obj[i]);
                    ObjectList.get(2).add(obj[i]);
                    break;
                case CallLog.Calls.OUTGOING_TYPE:

                    obj[i].setName(Name);
                    obj[i].setNumber(callnumber);
                    obj[i].setDate(currentdate);
                    obj[i].setDuration(time);
                    obj[i].setFileName(fileName);
                    ObjectList.get(1).add(obj[i]);
                    ObjectList.get(2).add(obj[i]);
                    break;

            }

            i++;
        }
    }



}


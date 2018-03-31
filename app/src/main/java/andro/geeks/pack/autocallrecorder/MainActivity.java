package andro.geeks.pack.autocallrecorder;


import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Typeface;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.renderscript.Type;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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


public class MainActivity extends AppCompatActivity {
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;


    SimpleDateFormat format;

    List<List>NAME=new ArrayList<List>();
    List<List>NUMBER=new ArrayList<List>();
    List<List>DATE=new ArrayList<List>();
    List<List>DURATION=new ArrayList<List>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        NavigationView navigationView=(NavigationView)findViewById(R.id.navigatior);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        for(int i=0;i<3;i++){
            NAME.add(i,new ArrayList<String>() );
            NUMBER.add(i,new ArrayList<String>());
            DATE.add(i,new ArrayList());
            DURATION.add(i,new ArrayList());

        }




        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        SetNavigationMenuItemSelector(navigationView);
        SetActionSwitch(navigationView);
        Setter();

        TabLayout tabLayout=(TabLayout)findViewById(R.id.tablayout);
        final ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager);


        tabLayout.addTab(tabLayout.newTab().setText("All Calls"));
        tabLayout.addTab(tabLayout.newTab().setText("Incoming"));
        tabLayout.addTab(tabLayout.newTab().setText("Outgoing"));
        CustomPagerAdapter adapter=new CustomPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),
                NAME,NUMBER,DURATION,DATE
        );

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.topmenu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId()){

                case R.id.sort:
                    Toast.makeText(getApplicationContext(),"Sort",Toast.LENGTH_SHORT).show();
                    break;

            }

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
                    NAME.get(0).add(Name);
                    NUMBER.get(0).add(callnumber);
                    DURATION.get(0).add(time);
                    DATE.get(0).add(currentdate);
                    NAME.get(2).add(Name);
                    NUMBER.get(2).add(callnumber);
                    DURATION.get(2).add(time);
                    DATE.get(2).add(currentdate);



                    break;
                case CallLog.Calls.OUTGOING_TYPE:
                    NAME.get(1).add(Name);
                    NUMBER.get(1).add(callnumber);
                    DURATION.get(1).add(time);
                    DATE.get(1).add(currentdate);
                    NAME.get(2).add(Name);
                    NUMBER.get(2).add(callnumber);
                    DURATION.get(2).add(time);
                    DATE.get(2).add(currentdate);


                    break;

            }





        }



    }



}
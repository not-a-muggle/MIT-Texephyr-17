package com.android.gifts.bottomnavigation;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabSelectedListener;

public class HomeActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_buttons);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,new FragmentEvent()).commit();
        getSupportActionBar().setTitle("Events");
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.three_buttons_activity);
        BottomBar bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.setItemsFromMenu(R.menu.five_buttons_menu, new OnMenuTabSelectedListener() {
            @Override
            public void onMenuItemSelected(int itemId) {
                switch (itemId) {
                    case R.id.tab_event:

                        FragmentEvent fe = new FragmentEvent();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fe).commit();
                        getSupportActionBar().setTitle("Events");
                        break;

                    case R.id.tab_workshop:
                        FragmentWorkshop fw = new FragmentWorkshop();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fw).commit();
                        getSupportActionBar().setTitle("Workshop");

                        break;

                    case R.id.tab_about:
                        FragmentAboutTex fa = new FragmentAboutTex();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fa).commit();
                        getSupportActionBar().setTitle("About Us");
                        break;

                    case R.id.tab_sponsor:
                        FragmentSponsors fs = new FragmentSponsors();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fs).commit();
                        getSupportActionBar().setTitle("Past Associates");

                        break;

                    case R.id.tab_more:
                        FragmentMore fm = new FragmentMore();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fm).commit();
                        getSupportActionBar().setTitle("More");

                        break;
                }
    }});
    }
}
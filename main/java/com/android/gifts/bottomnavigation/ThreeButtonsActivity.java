package com.android.gifts.bottomnavigation;


import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabSelectedListener;

public class ThreeButtonsActivity extends FragmentActivity {
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_buttons);

        //getActionBar().show();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.three_buttons_activity);
        BottomBar bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.setItemsFromMenu(R.menu.five_buttons_menu, new OnMenuTabSelectedListener() {
            @Override
            public void onMenuItemSelected(int itemId) {

                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,new FragmentEvent()).commit();

                switch (itemId) {
                    case R.id.tab_event:

                        FragmentEvent fe = new FragmentEvent();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fe).commit();
                        break;

                    case R.id.tab_workshop:
                        FragmentWorkshop fw = new FragmentWorkshop();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fw).commit();
                        break;

                    case R.id.tab_about:
                        FragmentAboutTex fa = new FragmentAboutTex();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fa).commit();
                        break;

                    case R.id.tab_sponsor:
                        FragmentSponsors fs = new FragmentSponsors();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fs).commit();
                        break;

                    case R.id.tab_more:
                        FragmentMore fm = new FragmentMore();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, fm).commit();
                        break;
                }
            }
        });

        // Set the color for the active tab. Ignored on mobile when there are more than three tabs.
        //bottomBar.setActiveTabColor("#C2185B");

        // Use the dark theme. Ignored on mobile when there are more than three tabs.
        //bottomBar.useDarkTheme(true);

        // Use custom text appearance in tab titles.
        //bottomBar.setTextAppearance(R.style.MyTextAppearance);

        // Use custom typeface that's located at the "/src/main/assets" directory. If using with
        // custom text appearance, set the text appearance first.
        //bottomBar.setTypeFace("MyFont.ttf");
    }
}

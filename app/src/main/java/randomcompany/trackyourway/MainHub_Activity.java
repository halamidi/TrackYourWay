package randomcompany.trackyourway;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainHub_Activity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton ForumBtn, savedSearch, newSearch;


    //creating objects for navigation drawer
    DrawerLayout mdrawer;
    NavigationView mNavigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hub_);
        ForumBtn = (ImageButton) findViewById(R.id.forum_btn);
        savedSearch = (ImageButton) findViewById(R.id.savedSearchBtn);
        newSearch = (ImageButton) findViewById(R.id.newSearchBtn);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //hamburger icon to open navigation drawer
        mdrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mdrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mdrawer.addDrawerListener(toggle);
        toggle.syncState();

        //creating nav view with items in it
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);


        //buttons on the layout to open other activities
        ForumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(getApplicationContext(), Comments_Rating_Activity.class);
                startActivity(i);
            }
        });
        savedSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Saved_Results_Activity.class);
                startActivity(i);
            }
        });
        newSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SearchFragment.class);
                startActivity(i);
            }
        });
    }

    // opens drawer when going back to activity
    @Override
    public void onBackPressed() {
        mdrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mdrawer.isDrawerOpen(GravityCompat.START)) {
            mdrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //functionality to open activities in nav drawer
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handles the navigation drawer items stored
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            //Handles the click event for the home activity
            Intent intent = new Intent(this, MainHub_Activity.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.nav_new_search) {
            //Handles the click event for the search activity
            Intent intent = new Intent(this, SearchFragment.class);
            startActivity(intent);
            return true;

            //handles the click even for the saved search activity
        } else if (id == R.id.nav_saved_searches) {
            Intent intent = new Intent(this, Saved_Results_Activity.class);
            startActivity(intent);
            return true;

            //handles the click even for the forum activity
        } else if (id == R.id.nav_forum) {
            Intent intent = new Intent(this, Comments_Rating_Activity.class);
            startActivity(intent);
            return true;

            //handles the click even for the user etails activity
        } else if (id == R.id.nav_user_details) {
            Intent intent = new Intent(this, MainHub_Activity.class);
            startActivity(intent);
            return true;

        }

        mdrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mdrawer.closeDrawer(GravityCompat.START);
        return true;


    }

}
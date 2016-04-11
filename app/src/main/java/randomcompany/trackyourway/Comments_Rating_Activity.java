<<<<<<< HEAD
package randomcompany.trackyourway;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Comments_Rating_Activity extends AppCompatActivity {

    private List<Ratings> myRatings = new ArrayList<Ratings>();

    RatingBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments__rating_);
        bar = (RatingBar) findViewById(R.id.ratingBar);
        populateRatingsList();
        populateListView();

        bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                System.out.println("The current rating is " + String.valueOf(bar.getRating()));
            }
        });
    }

    private void populateRatingsList() {
        myRatings.add(new Ratings("Brilliant", R.drawable.stars4));
        myRatings.add(new Ratings("NCI is the best all the lecturers are great", R.drawable.stars5));
        myRatings.add(new Ratings("NCI is the best all the lecturers are great", R.drawable.stars5));
        myRatings.add(new Ratings("NCI is the best all the lecturers are great", R.drawable.stars5));
    }

    private void populateListView() {
        ArrayAdapter<Ratings> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.ratingListView);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Ratings>{

        public MyListAdapter(){
            super(Comments_Rating_Activity.this,R.layout.item_view, myRatings);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            //Make sure our view works may have passed null
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
            }

            //Find The Rating to use
            Ratings currentRating = myRatings.get(position);

            //fill the list view
            ImageView imageView = (ImageView)itemView.findViewById(R.id.item_rating_icon);
            imageView.setImageResource(currentRating.getIconID());

            //Comment
            TextView commentText = (TextView) itemView.findViewById(R.id.item_comment_txt);
            commentText.setText(currentRating.getComment());

            return itemView;
        }

    }

}
=======
package randomcompany.trackyourway;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Comments_Rating_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //creating objects for navigation drawer
    DrawerLayout mdrawer;
    NavigationView mNavigationView;
    Toolbar toolbar;


    private List<Ratings> myRatings = new ArrayList<Ratings>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments__rating_);

        populateRatingsList();
        populateListView();
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







    private void populateRatingsList() {
        myRatings.add(new Ratings("Brilliant", R.drawable.stars4));
        myRatings.add(new Ratings("NCI is the best all the lecturers are great", R.drawable.stars5));
    }

    private void populateListView() {
        ArrayAdapter<Ratings> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.ratingListView);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Ratings>{

        public MyListAdapter(){
            super(Comments_Rating_Activity.this,R.layout.item_view, myRatings);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            //Make sure our view works may have passed null
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
            }

            //Find The Rating to use
            Ratings currentRating = myRatings.get(position);

            //fill the list view
            ImageView imageView = (ImageView)itemView.findViewById(R.id.item_rating_icon);
            imageView.setImageResource(currentRating.getIconID());

            //Comment
            TextView commentText = (TextView) itemView.findViewById(R.id.item_comment_txt);
            commentText.setText(currentRating.getComment());

            return itemView;
        }

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
>>>>>>> origin/master

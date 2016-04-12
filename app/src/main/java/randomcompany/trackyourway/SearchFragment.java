package randomcompany.trackyourway;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public String CollegeName, CourseName, CollegeLocation, CourseType, keywords;


    public EditText instituteTf;
    public EditText courseTf;
    public ImageButton SearchBtn;
    public EditText locationTf;
    public EditText courseTypeTf;

    //creating objects for navigation drawer
    DrawerLayout mdrawer;
    NavigationView mNavigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);

        instituteTf = (EditText) findViewById(R.id.instituteTf);
        courseTf = (EditText) findViewById(R.id.courseTf);
        SearchBtn = (ImageButton) findViewById(R.id.newSearchBtn);
        locationTf = (EditText) findViewById(R.id.locationTf);
        courseTypeTf = (EditText) findViewById(R.id.courseTypeTf);
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
        SearchBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SearchPeramiters search = new SearchPeramiters();
                storeDbresults storeParam = new storeDbresults();
                CollegeName = instituteTf.getText().toString();
                CourseName = courseTf.getText().toString();
                CollegeLocation = locationTf.getText().toString();
                CourseType = courseTypeTf.getText().toString();
                keywords = courseTf.getText().toString();
                //checking for every possible outcome
                //                if(!CollegeName.equals(null) && !CollegeName.equals("")){
                //                    //keywords will not be nested in any section
                //                    search.setCollegeName(CollegeName);
                //                    if(!CourseName.equals(null) && !CourseName.equals("")){
                //                        search.setCourseName(CourseName);
                //                    }else if(!CollegeLocation.equals(null) && !CollegeLocation.equals("")){
                //                        search.setCollegeLocation(CollegeLocation);
                //                    }else  if(!CourseType.equals(null) && !CourseType.equals("")){
                //                        search.setCourseType(CourseType);
                //                    }//                }else  if(!CourseName.equals(null) && !CourseName.equals("")){
                //                    search.setCourseName(CourseName);
                //                    if(!CollegeLocation.equals(null) && !CollegeLocation.equals("")){
                //                        search.setCollegeLocation(CollegeLocation);
                //                    }else  if(!CourseType.equals(null) && !CourseType.equals("")){
                //                        search.setCourseType(CourseType);
                //                    }
                //                }else  if(!CollegeLocation.equals(null) && !CollegeLocation.equals("")){
                //                    search.setCollegeLocation(CollegeLocation);
                //                    if(!CourseType.equals(null) && !CourseType.equals("")){
                //                        search.setCourseType(CourseType);
                //                    }
                //                }else  if(!CourseType.equals(null) && !CourseType.equals("")){
                //                    search.setCourseType(CourseType);
                //                }else  if(!keywords.equals(null) && !keywords.equals("")){
                //                    search.setKeyWords(keywords);
                //                }else{
                //                    //will change to ouput on screen when i have everything else working
                //                    Log.d("you have not ", "entered any details");
                //                }
                //set variable
                if (CollegeName.equals(null)) {
                    Log.d("you have not ", "entered any details");
                } else {
                    storeParam.setUserSearch(CollegeName);
                    CheckDetails(storeParam);
                }
                //                Intent i;
                //                i = new Intent(getApplicationContext(), Results_Activity.class);
                //                startActivity(i);}
            }
        });
    }


    
    private void CheckDetails(storeDbresults newSearchParam) {
        //send data to database request
        String Type = "Search";
        DbRequest newRequest = new DbRequest(this);
        newRequest.DbRetrieveDetails(Type, newSearchParam, new CallBackInter() {
            @Override
            public void complete(storeDbresults newObject) {
                storeDbresults searchPerams = newObject;
                //check if correct
                if (searchPerams == null/*userLogin.checkEmpty() == true*/) {
                    Log.d(null, "something has gone wrong");
                    //Warninglbl.setText("user details were incorrect");
                } else {
                    Log.d("testing object", searchPerams.getCollegeDetails().CollegeName);

                }
            }
        });
    }

        // opens drawer when going back to previous activity
        @Override
        public void onBackPressed () {
            mdrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (mdrawer.isDrawerOpen(GravityCompat.START)) {
                mdrawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }


        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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
        public boolean onNavigationItemSelected (MenuItem item){
            // Handle navigation view item clicks here.
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



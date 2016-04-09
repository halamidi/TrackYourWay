package randomcompany.trackyourway;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends AppCompatActivity {

    public EditText instituteTf;
    public EditText courseTf;
    public ImageButton SearchBtn;
    public EditText locationTf;
    public EditText courseTypeTf, CourseKeyWords;
    String CollegeName, CourseName, CollegeLocation, CourseType, keywords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);

        instituteTf = (EditText) findViewById(R.id.instituteTf);
        courseTf = (EditText) findViewById(R.id.courseTf);
        SearchBtn = (ImageButton) findViewById(R.id.newSearchBtn);
        locationTf = (EditText) findViewById(R.id.locationTf);
        courseTypeTf = (EditText) findViewById(R.id.courseTypeTf);
        CourseKeyWords = (EditText) findViewById(R.id.keywords);



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
//                    }
//                }else  if(!CourseName.equals(null) && !CourseName.equals("")){
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
                }else {
                    storeParam.setUserSearch(CollegeName);
                    CheckDetails(storeParam);
                }
//                Intent i;
//                i = new Intent(getApplicationContext(), Results_Activity.class);
//                startActivity(i);
            }
        });


    }


    private void CheckDetails(storeDbresults newSearchParam){
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





}

package randomcompany.trackyourway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    LocalUserDetails details;
    //storeDbresults results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        details = new LocalUserDetails(this);
    }

    @Override
    public void onStart(){
        super.onStart();
        //this is a test if this works remove the if authenticate and others related
        if(details.userDetails.getBoolean("isLoggedIn",false)== true){
            getUserDetails();
            Intent i = new Intent(getApplicationContext(), MainHub_Activity.class);
            startActivity(i);
        }else{
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            //end this activity
            finish();
            //start new activity
            startActivity(i);
        }
//        if(authenticate() == true){
//            Intent i = new Intent(getApplicationContext(), MapsActivity.class);
//            startActivity(i);
//        }

    }

    private boolean authenticate(){
        return details.checkLoggedInstatus();
    }

    public void getUserDetails(){
        UserAccount userDetails = details.getDetails();
        storeDbresults results = new storeDbresults();
        results.setTempUser(userDetails);

    }

}

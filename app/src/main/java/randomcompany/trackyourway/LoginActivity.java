package randomcompany.trackyourway;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private Button btnTest;
//    LocalUserDetails details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btnTest = (Button)findViewById(R.id.loginBtn);
       // details = new LocalUserDetails(this);
        //move outside of oncreate when it is working
        btnTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // UserAccount userLogin = new UserAccount(null,null);
//                details.storeDetails(userLogin);
//                details.isLoggedIn(true);
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);

            }

        });

    }
//    @Override
//    public void onStart(){
//        super.onStart();
//        if(authenticate() == true){
//            Intent i = new Intent(getApplicationContext(), MapsActivity.class);
//            startActivity(i);
//        }
//
//    }

//    private boolean authenticate(){
//       return details.checkLoggedInstatus();
//    }


}

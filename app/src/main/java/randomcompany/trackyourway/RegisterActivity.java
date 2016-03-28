package randomcompany.trackyourway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    private EditText name,age,email,certificate,prevCollege,prevCourse,interests,username,password;
    private String newName,newEmail,newCertificate,newPrevCollege,newPrevCourse,newInterests,newUsername,newPassword;
    private Button submitBtn;
    private int newAge;
    private TextView warning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        submitBtn = (Button) findViewById(R.id.registerdetailsbtn);
        name = (EditText) findViewById(R.id.Nametxt);
        age = (EditText) findViewById(R.id.Agetxt);
        email = (EditText) findViewById(R.id.Emailtxt);
        certificate = (EditText) findViewById(R.id.Certtxt);
        prevCollege = (EditText) findViewById(R.id.Collegetxt);
        prevCourse = (EditText) findViewById(R.id.Coursetxt);
        interests = (EditText) findViewById(R.id.intereststxt);
        username = (EditText) findViewById(R.id.UserNametxt);
        password = (EditText) findViewById(R.id.Passwordtxt);
        warning = (TextView) findViewById(R.id.Warninglbl);
    }


    public void onClick(View v){
        UserAccount newUser;
        //manditory strings
        newName = name.getText().toString();
        newAge = Integer.parseInt(age.getText().toString());
        newEmail = email.getText().toString();
        newUsername = username.getText().toString();
        newPassword = password.getText().toString();
        //not manditory strings
        newCertificate = certificate.getText().toString();
        newPrevCollege = prevCollege.getText().toString();
        newPrevCourse = prevCourse.getText().toString();
        newInterests = interests.getText().toString();
        //make sure required fields are not empty
        if(newName != null && !(newName.equals("")) && newAge != 0 && newEmail != null && !(newEmail.equals("")) && newUsername != null && !(newUsername.equals("")) && newPassword != null && !(newPassword.equals(""))){
            newUser = new UserAccount(newUsername, newPassword, newName, newAge, newEmail);

            if(newCertificate != null && !(newCertificate.equals(""))){
                newUser.setCertificate(newCertificate);
            }
            if(newPrevCollege != null && !(newPrevCollege.equals(""))){
                newUser.setPrevCollege(newPrevCollege);
            }
            if(newPrevCourse != null && !(newPrevCourse.equals(""))){
                newUser.setPrevCourse(newPrevCourse);
            }
            if(newInterests != null && !(newInterests.equals(""))){
                newUser.setInterests(newInterests);
            }
            UserRegistrationDetails(newUser);

        }else{
           warning.setText("Please fill in *Required fields");
        }
    }

    private void UserRegistrationDetails(UserAccount regUser){
        DbRequest reg = new DbRequest(this);
        String sType = "AddUser";
        reg.DBRequestData(sType ,regUser, new CallBackInter() {
            @Override
            public void complete(storeDbresults newObject) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                //end this activity
                finish();
                //start new activity
                startActivity(i);
            }
        });
    }
}

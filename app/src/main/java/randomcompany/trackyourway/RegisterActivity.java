package randomcompany.trackyourway;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private EditText name,age,email,certificate,prevCollege,prevCourse,interests,username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}

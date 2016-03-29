package randomcompany.navigationdrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageButton;

import randomcompany.trackyourway.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends AppCompatActivity {

    public EditText instituteTf;
    public EditText courseTf;
    public ImageButton SearchBtn;
    public EditText locationTf;
    public EditText courseTypeTf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);

        instituteTf = (EditText) findViewById(R.id.instituteTf);
        courseTf = (EditText) findViewById(R.id.courseTf);
        SearchBtn = (ImageButton) findViewById(R.id.newSearchBtn);
        locationTf = (EditText) findViewById(R.id.locationTf);
        courseTypeTf = (EditText) findViewById(R.id.courseTypeTf);
    }





}

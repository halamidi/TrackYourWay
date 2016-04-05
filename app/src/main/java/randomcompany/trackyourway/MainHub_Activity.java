package randomcompany.trackyourway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainHub_Activity extends AppCompatActivity {
    ImageButton ForumBtn, savedSearch, newSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hub_);
        ForumBtn = (ImageButton)findViewById(R.id.forum_btn);
        savedSearch = (ImageButton)findViewById(R.id.savedSearchBtn);
        newSearch = (ImageButton)findViewById(R.id.newSearchBtn);
        storeDbresults results = new storeDbresults();
        UserAccount test = results.getTempUser();
        Log.d("checking if object", test.getUserName());
        ForumBtn.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Intent i;
                                 i = new Intent(getApplicationContext(), Comments_Rating_Activity.class);
                                startActivity(i);
                            }});
        savedSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Saved_Results_Activity.class);
                startActivity(i);
            }});
        newSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SearchFragment.class);
                startActivity(i);
            }
        });
    }




}

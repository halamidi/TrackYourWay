package randomcompany.trackyourway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainHub_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hub_);
    }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.savedSearchBtn:

                break;
            case R.id.newSearchBtn:
                break;
            case R.id.ForumBtn:
                Intent i = new Intent(getApplicationContext(), Comments_Rating_Activity.class);
                startActivity(i);
                break;
        }

    }
}

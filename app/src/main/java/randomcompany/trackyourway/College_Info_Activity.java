package randomcompany.trackyourway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class College_Info_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college__info_);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.display_map_btn:
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
                break;
        }

    }
}

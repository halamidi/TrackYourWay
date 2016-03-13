package randomcompany.trackyourway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainHub_Activity extends AppCompatActivity {
    ImageButton ForumBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hub_);
        ForumBtn = (ImageButton)findViewById(R.id.ForumBtn);


        ForumBtn.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                                Intent i = new Intent(getApplicationContext(), Comments_Rating_Activity.class);
                                startActivity(i);
                            }

                            });
    }



}

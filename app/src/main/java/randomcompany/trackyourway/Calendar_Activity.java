package randomcompany.trackyourway;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Calendar_Activity extends AppCompatActivity {

    CalendarView calendarView;
    TextView displayEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_);
        Calendar currentDate = Calendar.getInstance();
        //declaring date format
        SimpleDateFormat DMY = new SimpleDateFormat("dd-MM-yyyy");
        //getting current date in perfered format
        String date = DMY.format(currentDate.getTime());
        //testing date
        Log.d("current date is", date);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        displayEvents = (TextView) findViewById(R.id.txtDisplayEvents);
        displayEvents.setText("Events today: ");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {

                displayEvents.setText("Insert database");
                Toast.makeText(getApplicationContext(), "Selected Date:\n" + "Day = " + i2 + "\n" + "Month = " + i1 + "\n" + "Year = " + i, Toast.LENGTH_LONG).show();


            }
        });

    }
}

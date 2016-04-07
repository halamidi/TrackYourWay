package randomcompany.trackyourway;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Hassan on 06/04/2016.
 */
public class Events {
    String eventTitle, eventDetails,eventLocation;
    double longitude, latitude;
    Date eventDate;
    Time eventTime;

    public Events(String newEventTitle,String newEventDetails, String newEventLocation,double newLatitude, double newLogitude,Date newEventDate, Time newEventTime){
        eventTitle = newEventTitle;
        eventDetails = newEventDetails;
        eventLocation = newEventLocation;
        longitude = newLogitude;
        latitude = newLogitude;
        eventDate = newEventDate;
        eventTime = newEventTime;
    }
}

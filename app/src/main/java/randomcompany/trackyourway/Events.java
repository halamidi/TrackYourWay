package randomcompany.trackyourway;

/**
 * Created by Hassan on 06/04/2016.
 */
public class Events {
    String eventTitle, eventDetails,eventLocation;
    double longitude, latitude;
    String eventDate;
    String eventTime;

    public Events(String newEventDate,String newEventTitle, String newEventDetails, String newEventTime, String newEventLocation,double newLatitude, double newLogitude){
        eventTitle = newEventTitle;
        eventDetails = newEventDetails;
        eventLocation = newEventLocation;
        longitude = newLogitude;
        latitude = newLatitude;
        eventDate = newEventDate;
        eventTime = newEventTime;
    }
    public Events(){

    }
}

package randomcompany.trackyourway;

/**
 * Created by Hassan on 06/04/2016.
 */
public class CollegeDetails {

    String CollegeName, CollegeAddress, CollegeEmail, CollegeContactDetails;
    double longitude,latitude;
    int CollegeID;
    Ratings CollegeRatings;

    public CollegeDetails() {

    }

    public CollegeDetails(int newCollegeID, String newCollegeName, String newCollegeAddress, String newCollegeEmail, String newCollegeContactDetails, double newLongitude, double newLatitude, Ratings newCollegeRatings){
        CollegeID = newCollegeID;
        CollegeName = newCollegeName;
        CollegeAddress = newCollegeAddress;
        CollegeEmail = newCollegeEmail;
        CollegeContactDetails = newCollegeContactDetails;
        longitude = newLongitude;
        latitude = newLatitude;
        CollegeRatings = newCollegeRatings;
    }


}

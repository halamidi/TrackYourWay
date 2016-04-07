package randomcompany.trackyourway;

/**
 * Created by Hassan on 27/03/2016.
 */
public class storeDbresults {
    //declare objects
    //user is set to static to allow user to view details anywhere in the application
    static UserAccount tempUser;
    CollegeDetails tempCollegeDetails;
    CourseDetails tempCourseDetails;
    String userSearch;
    //events are set to staic so application does not have to make new requests everytime the user visits the calendar during their session
    //not sure if events will be stored here
    static Events collegeEvents;
    public storeDbresults(){
       //tempUser = null;
    }

    public void setUserSearch(String newUserSearch){
        userSearch = newUserSearch;
    }

    public String getUserSearch(){
        return userSearch;
    }
    public void setTempUser(UserAccount newTempUser){
        tempUser = newTempUser;
    }

    public UserAccount getTempUser(){
        return tempUser;
    }

    public void setCollegeDetails(CollegeDetails newDetails){
        tempCollegeDetails = newDetails;
    }

    public CollegeDetails getCollegeDetails(){
        return tempCollegeDetails;
    }

    public void setTempCourseDetails(CourseDetails newCourseDetails){
        tempCourseDetails = newCourseDetails;
    }

    public CourseDetails getCourseDetails(){
        return tempCourseDetails;
    }






}

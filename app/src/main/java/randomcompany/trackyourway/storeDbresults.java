package randomcompany.trackyourway;

import java.util.ArrayList;

/**
 * Created by Hassan on 27/03/2016.
 */
public class storeDbresults {
    //This object is used for interacting with methods in the DbRequest
    //the reason for this is rather than making multiple methods to deal with different variables i can just use one object to store all variables and use whats needed at the time
    //this only makes sense since they all essentially do the same thing send data to a php file and sometime get data back
    //declare objects
    //user is set to static to allow user to view details anywhere in the application
    static UserAccount tempUser;
    CollegeDetails tempCollegeDetails;
    CourseDetails tempCourseDetails;
    String userSearch, date;
    //events are set to staic so application does not have to make new requests everytime the user visits the calendar during their session
    //not sure if events will be stored here
    Events collegeEvents;
    ArrayList<Object> multiResult = new ArrayList<Object>();
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

    public void setCollegeEvents(Events newEvents){
        collegeEvents = newEvents;
    }

    public Events getCollegeEvents(){
        return collegeEvents;
    }

    public void setMultiResult(ArrayList newMultiResult){
        multiResult = newMultiResult;
    }

    public ArrayList getMultiResult(){
        return multiResult;
    }

    public void setDate(String d){
        date = d;
    }
    public String getDate(){
        return date;
    }




}

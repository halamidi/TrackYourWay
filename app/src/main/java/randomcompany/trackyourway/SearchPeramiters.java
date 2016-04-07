package randomcompany.trackyourway;

/**
 * Created by Hassan on 07/04/2016.
 */
public class SearchPeramiters {

    String collegeName, collegeLocation, courseName, courseType, keyWords;
    public SearchPeramiters(){
        collegeName = null;
        collegeLocation = null;
        courseName = null;
        courseType = null;
        keyWords = null;
    }

    public void setCollegeName(String newCollegeName){
        collegeName = newCollegeName;
    }

    public String getCollegeName(){
        return collegeName;
    }

    public void setCollegeLocation(String newCollegeLocation){
        collegeLocation = newCollegeLocation;
    }

    public String getCollegeLocation(){
        return collegeLocation;
    }

    public void setCourseName(String newCourseName){
        courseName = newCourseName;
    }

    public String getCourseName(){
        return courseName;
    }

    public void setCourseType(String newCourseType){
        courseType = newCourseType;
    }

    public String getCourseType(){
        return courseType;
    }

    public void setKeyWords(String newKeyWord){
        keyWords = newKeyWord;
    }

    public String getKeyWords(){
        return keyWords;
    }
}

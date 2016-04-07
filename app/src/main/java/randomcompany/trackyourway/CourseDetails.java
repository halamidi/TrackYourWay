package randomcompany.trackyourway;

/**
 * Created by Hassan on 10/03/2016.
 */
public class CourseDetails extends CollegeDetails{
    String courseName, description, type;
    int level, duration , courseID, collegeID;
    public CourseDetails(int newCourseID,int newCollegeID, String newCourseName, String newDescription, int newLevel, String newType, int newDuration){
        courseID = newCourseID;
        collegeID = newCollegeID;
        courseName = newCourseName;
        description = newDescription;
        type = newType;
        level = newLevel;
        duration = newDuration;
    }


}

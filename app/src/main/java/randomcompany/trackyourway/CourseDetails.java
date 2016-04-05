package randomcompany.trackyourway;

/**
 * Created by Hassan on 10/03/2016.
 */
public class CourseDetails {
    String courseName, description, type;
    int level, duration , id;
    public CourseDetails(int newId, String newCourseName, String newDescription, int newLevel, String newType, int newDuration){
        id = newId;
        courseName = newCourseName;
        description = newDescription;
        type = newType;
        level = newLevel;
        duration = newDuration;
    }


}

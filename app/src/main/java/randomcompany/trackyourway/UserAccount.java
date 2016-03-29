package randomcompany.trackyourway;

/**
 * Created by Hassan on 29/02/2016.
 */
public class UserAccount {
    //this is a test user more data will need to be added when userlogin is fully functional
    String UserName, Password,name,email,certificate,prevCollege,prevCourse,interests;
    int age;
    //this one is for details received from database or saved on the phone
    public UserAccount(String newUserName, String newName, int newAge, String newEmail, String newCertificate, String newCollege, String newCourse, String newInterests){
        UserName = newUserName;
        name = newName;
        age = newAge;
        email = newEmail;
        certificate  = newCertificate;
        prevCollege = newCollege;
        prevCourse = newCourse;
        interests = newInterests;
    }
    //this is for login
    public UserAccount(String newUserName, String newPassword){
        UserName = newUserName;
        Password = newPassword;

    }
    //this is for registering
    public UserAccount(String newUserName, String newPassword, String newName, int newAge, String newEmail){
        UserName = newUserName;
        Password = newPassword;
        name = newName;
        age = newAge;
        email = newEmail;
        certificate  = new String();
        prevCollege = new String();
        prevCourse = new String();
        interests = new String();
    }

    public boolean checkEmpty(){
        if(UserName == null){
            return true;
        }else{
            return false;
        }
        //return isEmpty(UserName); this crashes when null pointless feature
    }
    public String getUserName(){
        return UserName;
    }
    public String getPassword(){
        return Password;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public String getEmail(){
        return email;
    }
    public String getCertificate(){
        return certificate;
    }
    public String getPrevCollege(){
        return prevCollege;
    }
    public String getPrevCourse(){
        return prevCourse;
    }
    public String getInterests(){
        return interests;
    }
    public void setCertificate(String newCertificate){
        certificate = newCertificate;
    }
    public void setPrevCollege(String newPrevCollege){
        prevCollege = newPrevCollege;
    }
    public void setPrevCourse(String newPrevCourse){
        prevCourse = newPrevCourse;
    }
    public void setInterests(String newInterests){
        interests = newInterests;
    }
}

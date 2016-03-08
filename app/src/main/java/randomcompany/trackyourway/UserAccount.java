package randomcompany.trackyourway;

/**
 * Created by Hassan on 29/02/2016.
 */
public class UserAccount {
    //this is a test user more data will need to be added when userlogin is fully functional
    String UserName, Password;
    //password will be removed from here and will be using set password instead at a later date
    //this is so when writing user details on phone the password dose not print out
    public UserAccount(String newName, String newPassword){
        UserName = newName;
        Password = newPassword;

    }

    public String getUserName(){
        return UserName;
    }
    public String getPassword(){
        return Password;
    }
}

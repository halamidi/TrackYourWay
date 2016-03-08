package randomcompany.trackyourway;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hassan on 29/02/2016.
 */
public class LocalUserDetails {
    private static final String FileName = "AccountDetails";
    SharedPreferences userDetails;

    public LocalUserDetails(Context context){
        userDetails = context.getSharedPreferences(FileName, 0);
    }

    public void storeDetails(UserAccount newUser){
        SharedPreferences.Editor SPEditor = userDetails.edit();
        SPEditor.putString("username",newUser.UserName);
        //saving password makes no sense but its here incase it ends up being needed
        //editor.putString("password", newUser.password);
        SPEditor.commit();
    }

    public UserAccount getDetails(){
        String userName = userDetails.getString("username", "");
        String password = userDetails.getString("password","");
        UserAccount newUser = new UserAccount(userName, password);
        return newUser;
    }

    public void setIsLoggedIn(boolean loggedIn){
        SharedPreferences.Editor SPEditor = userDetails.edit();
        SPEditor.putBoolean("isLoggedIn", loggedIn);
        SPEditor.commit();
    }

    public boolean checkLoggedInstatus(){
        if(userDetails.getBoolean("isLoggedIn",false)== true){
            return true;
        }else{
            return false;
        }
    }
    //clear data when user logs out
    public void removedetails(){
        SharedPreferences.Editor SPEditor = userDetails.edit();
        SPEditor.clear();
        SPEditor.commit();

    }

}

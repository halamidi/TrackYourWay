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
        SharedPreferences.Editor editor = userDetails.edit();
        editor.putString("username",newUser.userName);
        //saving password makes no sense but its here incase it ends up being needed
        //editor.putString("password", newUser.password);
        editor.commit();
    }

    public UserAccount getDetails(){
        String username = userDetails.getString("username", "");
        String password = userDetails.getString("password","");
        UserAccount newUser = new UserAccount(username, password);
        return newUser;
    }

    public void isLoggedIn(boolean loggedIn){
        SharedPreferences.Editor editor = userDetails.edit();
        editor.putBoolean("isLoggedIn", loggedIn);
        editor.commit();
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
        SharedPreferences.Editor editor = userDetails.edit();
        editor.clear();
        editor.commit();

    }

}

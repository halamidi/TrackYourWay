package randomcompany.trackyourway;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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
        //saving user details recieved from database
        SharedPreferences.Editor SPEditor = userDetails.edit();
        SPEditor.putString("username",newUser.UserName);
        SPEditor.putString("name",newUser.name);
        SPEditor.putString("age", String.valueOf(newUser.age));
        SPEditor.putString("email",newUser.email);
        SPEditor.putString("cert",newUser.certificate);
        SPEditor.putString("college",newUser.prevCollege);
        SPEditor.putString("course",newUser.prevCourse);
        SPEditor.putString("interests",newUser.interests);
        //saving password makes no sense but its here incase it ends up being needed
        //editor.putString("password", newUser.password);
        SPEditor.commit();
    }

    public UserAccount getDetails(){
        //getting user details from shared preference file
        String userName = userDetails.getString("username", "");
        String name = userDetails.getString("name", "");
        String age = userDetails.getString("age", "");
        String email = userDetails.getString("email", "");
        String cert = userDetails.getString("cert", "");
        String college = userDetails.getString("college", "");
        String course = userDetails.getString("course", "");
        String interests = userDetails.getString("interests", "");
        int newAge = Integer.parseInt(age);
        //String password = userDetails.getString("password","");
        //store user details in object and return
        UserAccount newUser = new UserAccount(userName, name, newAge,email, cert, college, course, interests);
        Log.d("test local", newUser.getUserName());
        return newUser;
    }

    public void setIsLoggedIn(boolean loggedIn){
        //set user as logged in, so next time application is opened user will not have to login again
        SharedPreferences.Editor SPEditor = userDetails.edit();
        SPEditor.putBoolean("isLoggedIn", loggedIn);
        SPEditor.commit();
    }

    public boolean checkLoggedInstatus(){
        //check if user is logged in
        if(userDetails.getBoolean("isLoggedIn",false)== true){
            return true;
        }else{
            return false;
        }
    }
    //clear data when user logs out
    public void removedetails(){
        //log user out
        SharedPreferences.Editor SPEditor = userDetails.edit();
        SPEditor.clear();
        SPEditor.commit();

    }

}

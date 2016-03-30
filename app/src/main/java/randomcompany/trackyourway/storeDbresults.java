package randomcompany.trackyourway;

/**
 * Created by Hassan on 27/03/2016.
 */
public class storeDbresults {
    UserAccount tempUser;

    public storeDbresults(){
       //tempUser = null;
    }

    public void setTempUser(UserAccount newTempUser){
        tempUser = newTempUser;
    }

    public UserAccount getTempUser(){
        return tempUser;
    }
}

package randomcompany.trackyourway;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Hassan on 29/02/2016.
 */
public class DbRequest {
    ProgressDialog progress;
    public static final int TimeOut = 1500*15;
    public static final String ServerAddress = "ADD DATABASE ADDRESS HERE";

    public DbRequest(Context context){
        progress = new ProgressDialog(context);
        progress.setCancelable(false);
        progress.setTitle("Request Is being sent");
        progress.setMessage("Please Be Patient...");


    }

    public void DbStoreDetails(){

    }

    public void DbRetrieveDetails(){

    }
}

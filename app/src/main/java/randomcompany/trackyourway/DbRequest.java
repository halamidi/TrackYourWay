package randomcompany.trackyourway;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hassan on 29/02/2016.
 */
public class DbRequest{
    ProgressDialog progress;
    public static final int TimeOut = 1500*25;
    private static final String LoginUrl = "https://trackyourway-sunny-shakya-1.c9users.io/Login.php";
    private static final String RegistrationUrl = "https://trackyourway-sunny-shakya-1.c9users.io/Registration.php";

    public DbRequest(Context context){
        progress = new ProgressDialog(context);
        progress.setCancelable(false);
        progress.setTitle("Request Is being sent");
        progress.setMessage("Please Be Patient...");


    }

    public void DbStoreDetails(UserAccount newUser, CallBackInter newCallBack){
        progress.show();
        new addUserToDB(newUser, newCallBack).execute();
    }

    public void DbRetrieveDetails(UserAccount newUser, CallBackInter callBack){
        progress.show();
        UserAccount user;
        new getUserDetails(newUser,callBack).execute();
    }

    public class getUserDetails extends AsyncTask<Void, Void, UserAccount> {
        UserAccount User;
        CallBackInter callBack;
        public getUserDetails(UserAccount newUser, CallBackInter newCallBack){
            User = newUser;
            callBack = newCallBack;
        }

        @Override
        protected UserAccount doInBackground(Void... params) {
            //when working might change to arraylist
            HashMap<String,String> DBLoginDetails = new HashMap<String,String>();
            Log.d("check name", User.UserName);
            DBLoginDetails.put("UserName", User.UserName);
            DBLoginDetails.put("Password", User.Password);
            URL lUrl;
            UserAccount newUser = null;

            try{
                lUrl = new URL(LoginUrl);
                HttpURLConnection DBConnection = (HttpURLConnection)lUrl.openConnection();
                DBConnection.setConnectTimeout(TimeOut);
                DBConnection.setReadTimeout(TimeOut);
                DBConnection.setRequestMethod("POST");
                DBConnection.setDoInput(true);
                DBConnection.setDoOutput(true);

                OutputStream oStream = DBConnection.getOutputStream();
                BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(oStream, "UTF-8"));
                BW.write(getPostData(DBLoginDetails));
                BW.flush();
                BW.close();
                oStream.close();
                int ResponseCode = DBConnection.getResponseCode();
                Log.d("ResponseCode", Integer.toString(ResponseCode));

                InputStream IS = new BufferedInputStream(DBConnection.getInputStream());
                BufferedReader ISReader = new BufferedReader(new InputStreamReader(IS));
                StringBuilder SB = new StringBuilder();
                String temp = "";
                while((temp = ISReader.readLine()) != null){
                    SB.append(temp);
                }
                ISReader.close();
                String response = SB.toString();
                Log.d("check response", response);
                //any log.d should be removed after testing
                Log.d("response",response);
                JSONObject jResponse = new JSONObject(response);
                Log.d("H length", Integer.toString((jResponse.length())));
                if(jResponse.length() == 0){
                    Log.i("newUser is null", null);
                    newUser = null;
                }else{
                    String name = jResponse.getString("UserName");
                    newUser = new UserAccount(name,User.Password);
                    Log.d("returned user2", newUser.UserName);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return newUser;
        }

        private String getPostData(HashMap<String,String> DBLoginDetails) throws UnsupportedEncodingException{
            int i = 0;
            StringBuilder SB = new StringBuilder();
            for(Map.Entry<String,String> entry : DBLoginDetails.entrySet()){
                if(i == 0){
                    i++;
                }else{
                    SB.append("&");

                }
                SB.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                SB.append("=");
                SB.append(URLEncoder.encode(entry.getValue(),"UTF-8"));

            }
            return SB.toString();
        }

        @Override
        protected void onPostExecute(UserAccount newUser) {
            progress.dismiss();
            callBack.complete(newUser);
            //Log.d("post execute", newUser.UserName);
            super.onPostExecute(newUser);
        }
    }

    public class addUserToDB extends AsyncTask<Void, Void, Void>{
        UserAccount user;
        CallBackInter Callback;

        public addUserToDB(UserAccount newUser, CallBackInter newCallback){
            user = newUser;
            Callback = newCallback;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progress.dismiss();
            Callback.complete(null);
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... params) {
            HashMap<String,String> DBRegisterDetails = new HashMap<String,String>();
            Log.d("check name", user.UserName);
            DBRegisterDetails.put("UserName", user.UserName);
            DBRegisterDetails.put("Password", user.Password);
            DBRegisterDetails.put("Name", user.name);
            DBRegisterDetails.put("Age", Integer.toString(user.age));
            DBRegisterDetails.put("Email", user.email);
            if(user.certificate != null && !(user.certificate.equals(""))){
                //UBuilder.appendQueryParameter("Certificate", user.certificate);
                DBRegisterDetails.put("Certificate", user.certificate);
            }
            if(user.prevCollege != null && !(user.prevCollege.equals(""))){
                // UBuilder.appendQueryParameter("PrevCollege", user.prevCollege);
                DBRegisterDetails.put("PrevCollege", user.prevCollege);
            }
            if(user.prevCourse != null && !(user.prevCourse.equals(""))){
                // UBuilder.appendQueryParameter("PrevCourse", user.prevCourse);
                DBRegisterDetails.put("PrevCourse", user.prevCourse);
            }
            if(user.interests != null && !(user.interests.equals(""))){
                // UBuilder.appendQueryParameter("Interests", user.interests);
                DBRegisterDetails.put("Interests", user.interests);
            }
            try{
                URL rUrl = new URL(RegistrationUrl);
                HttpURLConnection DBConnection = (HttpURLConnection) rUrl.openConnection();
                DBConnection.setReadTimeout(TimeOut);
                DBConnection.setConnectTimeout(TimeOut);
                DBConnection.setRequestMethod("POST");
                DBConnection.setDoInput(true);
                DBConnection.setDoOutput(true);

//                Uri.Builder UBuilder = new Uri.Builder();
//                UBuilder.appendQueryParameter("UserName", user.UserName);
//                UBuilder.appendQueryParameter("Password", user.Password);
//                UBuilder.appendQueryParameter("Name", user.name);
//                UBuilder.appendQueryParameter("Age", Integer.toString(user.age));
//                UBuilder.appendQueryParameter("Email", user.email);
//                if(user.certificate != null && !(user.certificate.equals(""))){
//                    //UBuilder.appendQueryParameter("Certificate", user.certificate);
//                }
//                if(user.prevCollege != null && !(user.prevCollege.equals(""))){
//                   // UBuilder.appendQueryParameter("PrevCollege", user.prevCollege);
//                }
//                if(user.prevCourse != null && !(user.prevCourse.equals(""))){
//                   // UBuilder.appendQueryParameter("PrevCourse", user.prevCourse);
//                }
//                if(user.interests != null && !(user.interests.equals(""))){
//                   // UBuilder.appendQueryParameter("Interests", user.interests);
//                }
               // String DBquery = UBuilder.build().getEncodedQuery();
                //Log.d("Query", DBquery);

                OutputStream oStream = DBConnection.getOutputStream();
                BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(oStream,"UTF-8"));
                BW.write(getPostData(DBRegisterDetails));
                BW.flush();
                BW.close();
                oStream.close();
                int responseCode = DBConnection.getResponseCode();
                Log.d("code", Integer.toString(responseCode));
                //DBConnection.connect();
                Log.d("code", Integer.toString(responseCode));
                //below my not be needed
                InputStream IS = new BufferedInputStream(DBConnection.getInputStream());
                BufferedReader ISReader = new BufferedReader(new InputStreamReader(IS));
                StringBuilder SB = new StringBuilder();
                String temp = "";
                while((temp = ISReader.readLine()) != null){
                    SB.append(temp);
                }
                ISReader.close();
                String response = SB.toString();
                Log.d("check response", response);
            }catch (Exception e){
                Log.d(null, "failed");
                e.printStackTrace();
            }
            return null;
        }

        private String getPostData(HashMap<String,String> DBRegisterDetails) throws UnsupportedEncodingException{
            int i = 0;
            StringBuilder SB = new StringBuilder();
            for(Map.Entry<String,String> entry : DBRegisterDetails.entrySet()){
                if(i == 0){
                    i++;
                }else{
                    SB.append("&");

                }
                SB.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                SB.append("=");
                SB.append(URLEncoder.encode(entry.getValue(),"UTF-8"));

            }
            return SB.toString();
        }

    }
}

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
    public static final int TimeOut = 1500*155;
    private static final String LoginUrl = "https://trackyourway-sunny-shakya-1.c9users.io/Login.php"; //"http://10.0.2.2/Login.php"; //this one is for xampp
    private static final String RegistrationUrl = "https://trackyourway-sunny-shakya-1.c9users.io/Registration.php";//"http://10.0.2.2/Registration.php"; //this is for xampp

    public DbRequest(Context context){
        progress = new ProgressDialog(context);
        progress.setCancelable(false);
        progress.setTitle("Request Is being sent");
        progress.setMessage("Please Be Patient...");


    }

    public void DbStoreDetails(UserAccount newUser, CallBackInter newCallBack){
        progress.show();
       // new addUserToDB(newUser, newCallBack).execute();
    }

    public void DbRetrieveDetails(storeDbresults newUser, CallBackInter callBack){
        progress.show();
        String Type = "Login";
        UserAccount tempUser = newUser.getTempUser();
        Log.d("user from login", tempUser.UserName);
        //UserAccount user;
        new DBServerRequest(tempUser,callBack, Type).execute();
    }

    public void DBRequestData(String Type, UserAccount newUser, CallBackInter newCallBack){
        progress.show();
        new DBServerRequest(newUser, newCallBack, Type).execute();
    }


    public class DBServerRequest extends AsyncTask<Void, Void, storeDbresults> {
        UserAccount User;
        CallBackInter callBack;
        String Type;
        public DBServerRequest(UserAccount newUser, CallBackInter newCallBack, String PageType){
            Type = PageType;
            User = newUser;
            callBack = newCallBack;
        }

        @Override
        protected storeDbresults doInBackground(Void... params) {
            //when working might change to arraylist
            HashMap<String,String> DBDetails = new HashMap<String,String>();
            Log.d("check name", User.UserName);
            if(Type.equals("Login")) {
                DBDetails.put("UserName", User.UserName);
                DBDetails.put("Password", User.Password);
            }else if(Type.equals("AddUser")){
                DBDetails.put("UserName", User.UserName);
                DBDetails.put("Password", User.Password);
                DBDetails.put("Name", User.name);
                DBDetails.put("Age", Integer.toString(User.age));
                DBDetails.put("Email", User.email);
                //this is for optional text fields if nothing has been entered then set as not Specified
                if(User.certificate != null || !(User.certificate.equals(""))){

                    DBDetails.put("Certificate", User.certificate);
                }else{
                    DBDetails.put("Certificate","not Specified");
                }
                if(User.prevCollege != null || !(User.prevCollege.equals(""))){

                    Log.d("check prevc", User.prevCollege);
                    DBDetails.put("PrevCollege", User.prevCollege);
                }else{
                    DBDetails.put("PrevCollege","not Specified");
                }
                if(User.prevCourse != null || !(User.prevCourse.equals(""))){

                    DBDetails.put("PrevCourse", User.prevCourse);
                }else{
                    DBDetails.put("PrevCourse","not Specified");
                }
                if(User.interests != null || !(User.interests.equals(""))){

                    DBDetails.put("Interests", User.interests);
                }else{
                    DBDetails.put("Interests","not Specified");
                }
            }
            URL lUrl;
            UserAccount newUser = null;
            String server = null;
            try{
                if(Type.equals("Login")){
                   server = LoginUrl;
                }else if(Type.equals("AddUser")){
                   server = RegistrationUrl;
                }else{
                    Log.d("failed to check type","check type variables are correct");
                }
                lUrl = new URL(server);
                HttpURLConnection DBConnection = (HttpURLConnection)lUrl.openConnection();
                DBConnection.setConnectTimeout(TimeOut);
                DBConnection.setReadTimeout(TimeOut);
                DBConnection.setRequestMethod("POST");
                DBConnection.setDoInput(true);
                DBConnection.setDoOutput(true);

                OutputStream oStream = DBConnection.getOutputStream();
                BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(oStream, "UTF-8"));
                BW.write(getPostData(DBDetails));
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
                if(Type.equals("Login")) {
                    JSONObject jResponse = new JSONObject(response);
                    Log.d("H length", Integer.toString((jResponse.length())));
                    if (jResponse.length() == 0) {
                        Log.i("newUser is null", null);
                        newUser = null;
                    } else {
                        String name = jResponse.getString("Name");
                        String email = jResponse.getString("Email");
                        String age = jResponse.getString("Age");
                        String prevCert = jResponse.getString("Cert");
                        String interests = jResponse.getString("Interests");
                        String prevCollege = jResponse.getString("College");
                        String prevCourse = jResponse.getString("Course");
                        String userName = jResponse.getString("UserName");

                        //String newUserName, String newName, int newAge, String newEmail
                        newUser = new UserAccount(userName, name, Integer.parseInt(age), email, prevCert, prevCollege, prevCourse, interests);
                        Log.d("returned user2", newUser.UserName);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            if(Type.equals("Login")) {
                storeDbresults tempObject = new storeDbresults();
                tempObject.setTempUser(newUser);
                return tempObject;
            }else{
                return null;
            }
        }

        private String getPostData(HashMap<String,String> DBDetails) throws UnsupportedEncodingException{
            int i = 0;
            StringBuilder SB = new StringBuilder();
            for(Map.Entry<String,String> entry : DBDetails.entrySet()){
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
        protected void onPostExecute(storeDbresults newObject) {
            progress.dismiss();
            if(Type.equals("Login")) {
                callBack.complete(newObject);
                super.onPostExecute(newObject);
            } else if(Type.equals("AddUser")){
                callBack.complete(null);
                super.onPostExecute(null);
            }else{
                //nothing yet
            }
        }
//
//        @Override
//        protected void onPostExecute(UserAccount newUser) {
//            progress.dismiss();
//            if(Type.equals("Login")) {
//                callBack.complete(newUser);
//                super.onPostExecute(newUser);
//            } else if(Type.equals("AddUser")){
//                callBack.complete(null);
//                super.onPostExecute(null);
//            }else{
//                //nothing yet
//            }
//            //Log.d("post execute", newUser.UserName);
//            //super.onPostExecute(newUser);
//        }

    }

//    public storeDbresults setDBLogin(UserAccount newUser){
//        Object tempObject = new storeDbresults();
//        //task try use objects rather than making a new one like above
//        tempObject.setTempUser(newUser);
//        return tempObject;
//    }


}



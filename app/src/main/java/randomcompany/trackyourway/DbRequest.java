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
    private static final int TimeOut = 1500*155;
    private static final String LoginUrl = "https://trackyourway-sunny-shakya-1.c9users.io/Login.php"; //"http://10.0.2.2/Login.php"; //this one is for xampp
    private static final String RegistrationUrl = "https://trackyourway-sunny-shakya-1.c9users.io/Registration.php";//"http://10.0.2.2/Registration.php"; //this is for xampp
    HashMap<String,String> DBDetails = new HashMap<String,String>();
    String Type;
    public DbRequest(Context context){
        progress = new ProgressDialog(context);
        progress.setCancelable(false);
        progress.setTitle("Request Is being sent");
        progress.setMessage("Please Be Patient...");


    }


    public void DbRetrieveDetails(String Type, storeDbresults newUser, CallBackInter callBack){
        progress.show();
        //String Type = "Login";
        UserAccount tempUser = newUser.getTempUser();
        if(Type.equals("Login")) {
            Log.d("user from login", tempUser.UserName);
        }
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

        public DBServerRequest(UserAccount newUser, CallBackInter newCallBack, String PageType){
            Type = PageType;
            User = newUser;
            callBack = newCallBack;
        }

        @Override
        protected storeDbresults doInBackground(Void... params) {
            //call setVariables which is used to set variables sent from previous page
            setVariables();
            //declare a url
            URL lUrl;
            //define and initialize object and server url string
            UserAccount newUser = null;
            String server = null;
            //try catch errors
            try{
                //depending on where in the application the request was sent from will depend on the server url
                //for example if user came from login page, that page will send a string called Type containing Login
                //so if Type string contains Login then set the url to the login php file
                if(Type.equals("Login")){
                   server = LoginUrl;
                }else if(Type.equals("AddUser")){
                   server = RegistrationUrl;
                }else{
                    Log.d("failed to check type","check type variables are correct");
                }
                //set url
                lUrl = new URL(server);
                //create connection
                HttpURLConnection DBConnection = (HttpURLConnection)lUrl.openConnection();
                //set time out. this means if either connection to the php or reading from the php and database takes too long the entire connection will end
                DBConnection.setConnectTimeout(TimeOut);
                DBConnection.setReadTimeout(TimeOut);
                //set request method, which in our case is POST
                DBConnection.setRequestMethod("POST");
                DBConnection.setDoInput(true);
                DBConnection.setDoOutput(true);
                //set output stream
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

                }else if(Type.equals("Calendar")){
                    JSONObject jResponse = new JSONObject(response);
                    Log.d("H length", Integer.toString((jResponse.length())));
                    if (jResponse.length() == 0) {
                        Log.i("newUser is null", null);
                        newUser = null;
                    } else {
                        for(int i = 0; i < jResponse.length(); i++){
                        String results = jResponse.getString(Integer.toString(i));
                        String[][] eventsDetails = new String[7][];
                            int j = 0;
                            for (String result: results.split("|")) {
                                //from here all that needs to be done is to put all details into an object or an array of objects
                                //alternativly you dont need to use the array below at all you could just put all details straight into the object then an arraylist which would be more efficent
                                eventsDetails[i][j] = result;
                                j++;
                            }
                        }
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

        public void setVariables(){
            //depending on where in the application the request was sent from will depend on the details to be put into the hashmap
            //for example if user came from login page, that page will send a string called Type containing Login
            //so if Type string contains Login then set variables related to login page
            if(Type.equals("Login")) {
                //set variables sent from previous page
                DBDetails.put("UserName", User.UserName);
                DBDetails.put("Password", User.Password);
            }else if(Type.equals("AddUser")){
                //set variables sent from previous page
                DBDetails.put("UserName", User.UserName);
                DBDetails.put("Password", User.Password);
                DBDetails.put("Name", User.name);
                DBDetails.put("Age", Integer.toString(User.age));
                DBDetails.put("Email", User.email);
                //this is for optional text fields if nothing has been entered then set as not Specified
                //not specified string is used because the emulator will crash when attempting to read a null string, so to prevent this we set the variable to not specified in the database
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
            }else if(Type.equals("Calendar")){

            }

        }

        private String getPostData(HashMap<String,String> DBDetails) throws UnsupportedEncodingException{
            int i = 0;
            //create and initialize string builder
            StringBuilder SB = new StringBuilder();
            //for every set of values(key,value) put in the hashmap, repeat loop
            for(Map.Entry<String,String> entry : DBDetails.entrySet()){
                //if this is the first loop set i to 1 else add an ampersand to the string builder
                if(i == 0){
                    i++;
                }else{
                    //add ampersand between each POST value
                    SB.append("&");

                }
                //get the key, which is the first value in the hashmap
                SB.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                //add string between key and value
                SB.append("=");
                //get value which is the second value in the hashmap
                SB.append(URLEncoder.encode(entry.getValue(),"UTF-8"));

            }
            //return stringbuilder when finished
            return SB.toString();
        }

        @Override
        protected void onPostExecute(storeDbresults newObject) {
            //this ends the progress dialog
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



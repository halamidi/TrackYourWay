package randomcompany.trackyourway;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
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
import java.util.ArrayList;
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
    private static final String SearchUrl = "https://trackyourway-sunny-shakya-1.c9users.io/getData.php";
    private static final String EventsUrl = "https://trackyourway-sunny-shakya-1.c9users.io/Calendar.php";// "http://10.0.2.2/Calendar.php"; //
    private static final String RatingUrl = "";
    private HashMap<String,String> DBDetails = new HashMap<String,String>();
    public ArrayList<Object> multiResult = new ArrayList<>();
    public String Type,Search;
    public DbRequest(Context context){
        progress = new ProgressDialog(context);
        progress.setCancelable(false);
        progress.setTitle("Request Is being sent");
        progress.setMessage("Please Be Patient...");

    }


    public void DbRetrieveDetails(String Type, storeDbresults SendParam, CallBackInter callBack){
        progress.show();
        //String Type = "Login";
        //just a test
        if(Type.equals("Login")) {
            UserAccount tempUser = SendParam.getTempUser();
            Log.d("user from login", tempUser.UserName);
        }
        //UserAccount user;
        new DBServerRequest(SendParam,callBack, Type).execute();
    }

//    public void DBRequestData(String Type, UserAccount newUser, CallBackInter newCallBack){
//        progress.show();
//       // new DBServerRequest(newUser, newCallBack, Type).execute();
//    }


    public class DBServerRequest extends AsyncTask<Void, Void, storeDbresults> {
        UserAccount User;
        CallBackInter callBack;
        Events newEvents;
        storeDbresults param = new storeDbresults();
        public DBServerRequest(storeDbresults newParam, CallBackInter newCallBack, String PageType){
            Type = PageType;
            //these may give an error when null
            User = newParam.getTempUser();
            Search = newParam.getUserSearch();
            param = newParam;
            //dont know why im getting college events here
            newEvents = newParam.getCollegeEvents();
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
                switch (Type) {
                    case "Login":
                        server = LoginUrl;
                        break;
                    case "AddUser":
                        server = RegistrationUrl;
                        break;
                    case "Search":
                        server = SearchUrl;
                        break;
                    case "Events":
                        server = EventsUrl;
                        break;
                    default:
                        Log.d("failed to check type", "check type variables are correct");
                        break;
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
                //creating a bufferedwriter with output stream contained within it
                BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(oStream, "UTF-8"));
                //get details from getPostData as a string and put the details into the buffered writer
                BW.write(getPostData(DBDetails));
                //clear the buffered writer and close it
                BW.flush();
                BW.close();
                oStream.close();
                //get the response code from the connection to see if connection has failed or not then output to logcat
                //this is mainly for debugging but may use it to display on screen if connection fails
                int ResponseCode = DBConnection.getResponseCode();
                Log.d("ResponseCode", Integer.toString(ResponseCode));
                //creating an input stream to receive data
                InputStream IS = new BufferedInputStream(DBConnection.getInputStream());
                //read input stream with inputStreamReader and put data into a buffered reader
                BufferedReader ISReader = new BufferedReader(new InputStreamReader(IS));
                //create a stringbilder for organizing data
                StringBuilder SB = new StringBuilder();
                //temporary string for storing data before it gets put into stringbuilder
                String temp = "";
                //use a loop to loop through the bufferedReader then store data put into the temp string into the stringbuilder
                while((temp = ISReader.readLine()) != null){

                    SB.append(temp);
                }
                ISReader.close();
                //convert stringbuilder to string
                String response = SB.toString();
                //check to see if worked
                Log.d("check response", response);
                //check to see what page made the request
                if(Type.equals("Login")) {
                    //convert string into a jsonObject to allow data to be pulled from string without splitting or getting one char at a time
                    JSONObject jResponse = new JSONObject(response);
                    //check the length to make sure data exists
                    Log.d("H length", Integer.toString((jResponse.length())));
                    if (jResponse.length() == 0) {
                        Log.i("newUser is null", null);
                        newUser = null;
                    } else {
                        //if the data exists then get values
                        String name = jResponse.getString("Name");
                        String email = jResponse.getString("Email");
                        String age = jResponse.getString("Age");
                        String prevCert = jResponse.getString("Cert");
                        String interests = jResponse.getString("Interests");
                        String prevCollege = jResponse.getString("College");
                        String prevCourse = jResponse.getString("Course");
                        String userName = jResponse.getString("UserName");

                        //store values into UserAccount object
                        newUser = new UserAccount(userName, name, Integer.parseInt(age), email, prevCert, prevCollege, prevCourse, interests);
                        //test object
                        Log.d("returned user2", newUser.UserName);
                    }

                }else if(Type.equals("Events") || Type.equals("Search")){
                    //convert string into a jsonObject to allow data to be pulled from string without splitting or getting one char at a time
                    JSONArray jResponse = new JSONArray(response);
                    //check the length to make sure data exists
                    Log.d("H length", Integer.toString((jResponse.length())));
                    if (jResponse.length() == 0) {
                        Log.i("newUser is null", null);
                        newUser = null;
                    } else {
                        //loop through the json object
                        for(int i = 0; i < jResponse.length(); i++){
                           //convert what ever is in json object to a string
                            String results =  jResponse.getString(i);
                            String[] singleQuery = new String[7];
                            //j is used to count how many times the below loop needs to go before int needs to stop
//                            int j = 0;
//                            for (String result: results.split("\\|")) {
//                                //just an output test (Log.d not working in this case)
//                                System.out.println("first val" + result);
//                                //store the split string in its own individual index
//                                singleQuery[j] = result;
//                                //increase j
//                                j++;
//                            }
                            //if this isnt working comment this out and re-enable the loop
                            singleQuery = results.split("\\|");
                            System.out.println("first val" + singleQuery[0]);
                            newEvents = new Events(singleQuery[0], singleQuery[1], singleQuery[2], singleQuery[3], singleQuery[4], Double.parseDouble(singleQuery[5]), Double.parseDouble(singleQuery[6]));
                            multiResult.add(newEvents);
                        }


                    }

                }
            }catch (Exception e){
                //try failed display error
                e.printStackTrace();
            }
            //if page requesting details is login then get the user details object and store that in another object to be passed through
            switch (Type) {
                case "Login": {
                    storeDbresults tempObject = new storeDbresults();
                    tempObject.setTempUser(newUser);
                    return tempObject;
                }
                case "Search":
                    return null;
                case "Events": {
                    storeDbresults tempObject = new storeDbresults();
                    tempObject.setMultiResult(multiResult);
                    return tempObject;
                }
                default:
                    return null;
            }
        }

        public void setVariables(){
            //depending on where in the application the request was sent from will depend on the details to be put into the hashmap
            //for example if user came from login page, that page will send a string called Type containing Login
            //so if Type string contains Login then set variables related to login page
            switch (Type) {
                case "Login":
                    //set variables sent from previous page
                    DBDetails.put("UserName", User.UserName);
                    DBDetails.put("Password", User.Password);
                    break;
                case "AddUser":
                    //set variables sent from previous page
                    DBDetails.put("UserName", User.UserName);
                    DBDetails.put("Password", User.Password);
                    DBDetails.put("Name", User.name);
                    DBDetails.put("Age", Integer.toString(User.age));
                    DBDetails.put("Email", User.email);
                    //this is for optional text fields if nothing has been entered then set as not Specified
                    //not specified string is used because the emulator will crash when attempting to read a null string, so to prevent this we set the variable to not specified in the database
                    if (User.certificate != null) {

                        DBDetails.put("Certificate", User.certificate);
                    } else {
                        DBDetails.put("Certificate", "not Specified");
                    }
                    if (User.prevCollege != null) {

                        Log.d("check prevc", User.prevCollege);
                        DBDetails.put("PrevCollege", User.prevCollege);
                    } else {
                        DBDetails.put("PrevCollege", "not Specified");
                    }
                    if (User.prevCourse != null) {

                        DBDetails.put("PrevCourse", User.prevCourse);
                    } else {
                        DBDetails.put("PrevCourse", "not Specified");
                    }
                    if (User.interests != null) {

                        DBDetails.put("Interests", User.interests);
                    } else {
                        DBDetails.put("Interests", "not Specified");
                    }
                    break;
                case "Search":
                    //this parameter is a waste of a hashmap but changing the flow for one string may not be a great idea either
                    //this search var is empty right now
                    DBDetails.put("Search", Search);
                    break;
                case "Events":
                    String tempparam = param.getDate();
                    String[] tempdate = tempparam.split("-");
                    DBDetails.put("month", tempdate[1]);
                    break;
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
            switch (Type) {
                case "Login":
                    callBack.complete(newObject);
                    super.onPostExecute(newObject);
                    break;
                case "AddUser":
                    callBack.complete(null);
                    super.onPostExecute(null);
                    break;
                case "Search":
                    callBack.complete(newObject);
                    super.onPostExecute(newObject);
                    break;
                case "Events":
                    callBack.complete(newObject);
                    super.onPostExecute(newObject);
                    break;
                default:
                    //nothing yet
                    break;
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



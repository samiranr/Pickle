package parselogintutorial.androidbegin.com.emergency;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import android.widget.Toast;


import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.skyfishjy.library.RippleBackground;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.location.LocationManager.*;


public class Setter extends Activity implements LocationListener{
    private LocationManager locationManager;
    private LocationManager locationManager2;
    int initial=0;


    @Override
    public void onBackPressed() {


        int pid = android.os.Process.myPid();


        android.os.Process.killProcess(pid);

    }













    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);

        rippleBackground.startRippleAnimation();






        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "V5DTEChNXyVF95SRVydVMmCoKM02Qos79L5wW9MI", "HqljsigzKuHeo2XK9Rljyo2j79dIN7qkW2oJieXW");








        TelephonyManager telemamanger = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        final String simno = telemamanger.getSimSerialNumber();



        ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
        query.whereEqualTo("userid",simno);
        List<ParseObject> fedup=new ArrayList<ParseObject>();
        try {
            fedup=query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }



        final ParseObject pickle = new ParseObject("Pickles");
        pickle.put("message", "I am in grave danger, Please rush to this location");

        pickle.put("emergency", true);
        if (fedup.size()>0) {


            pickle.put("username", fedup.get(0).getString("username"));

        }

        ParseGeoPoint geo = new ParseGeoPoint(28.5470892f, 77.27420497f);
        pickle.put("mappoint", geo);
        pickle.saveInBackground();
        initial=1;






        pickle.saveInBackground();














        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        locationManager2 = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                if (initial==0) {
                    ParseGeoPoint geo = new ParseGeoPoint(location.getLatitude(), location.getLongitude());
                    pickle.put("mappoint", geo);
                    pickle.saveInBackground();
                    initial=1;
                }

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };


        locationManager2.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);








        LocationListener gpser = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.

                ParseGeoPoint geo = new ParseGeoPoint(location.getLatitude(), location.getLongitude());
                pickle.put("mappoint", geo);
                pickle.saveInBackground();




                //String mapper=String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", location.getLatitude(), location.getLongitude(), "I am here");



                //String myText = "I need your help!\n "+ "Please come here ASAP: "+mapper;

                //SmsManager.getDefault().sendTextMessage("91" + finalFedup.get(0).getString("phonenumber"), null, contentView.getText().toString(), null, null);






            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }


        };















        locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
                3000, 10, gpser);

    }


    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

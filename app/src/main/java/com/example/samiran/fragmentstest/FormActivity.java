package com.example.samiran.fragmentstest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gc.materialdesign.widgets.Dialog;
import com.gc.materialdesign.widgets.SnackBar;

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Arrays;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by samiran on 4/2/2015.
 */
public class FormActivity extends Activity{

    @Override
    public void onBackPressed() {


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.form);


        ImageButton image = (ImageButton) findViewById(R.id.nextfrm1);


        TelephonyManager telemamanger = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        final String simno = telemamanger.getSimSerialNumber();


        final ParseObject newuser= new ParseObject("Users");
        newuser.put("userid",simno);
        newuser.saveInBackground();



























        image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                YoYo.with(Techniques.Flash)
                        .duration(800)
                        .playOn(findViewById(R.id.nextfrm1));

                final EditText nameField1 = (EditText) findViewById(R.id.usr1);
                final String UserName = nameField1.getText().toString();


                final EditText nameField2 = (EditText) findViewById(R.id.usr2);
                final String PhoneNumber = nameField2.getText().toString();

                final EditText nameField3 = (EditText) findViewById(R.id.usr3);
                final String Addr1 = nameField3.getText().toString();

                final EditText nameField4 = (EditText) findViewById(R.id.usr4);
                final String Addr2 = nameField4.getText().toString();











                if ((UserName.length()<5) || (UserName.length()>20))
                {


                    SnackBar snackbar = new SnackBar(FormActivity.this, "Are you sure this is your real name?",null, null);
                    snackbar.show();


                    YoYo.with(Techniques.Shake)
                            .duration(800)
                            .playOn(findViewById(R.id.usr1));


                }

                else if ((PhoneNumber.length()!=10)||(!(PhoneNumber.matches("-?\\d+(\\.\\d+)?"))))
                {


                    SnackBar snackbar = new SnackBar(FormActivity.this, "Are you sure this a correct phone number?",null, null);
                    snackbar.show();
                    YoYo.with(Techniques.Shake)
                            .duration(800)
                            .playOn(findViewById(R.id.usr2));

                }

                else if ((Addr1.length()<6) || (Addr1.length()>25))
                {


                    SnackBar snackbar = new SnackBar(FormActivity.this, "Are you sure this a correct address?",null, null);
                    snackbar.show();
                    YoYo.with(Techniques.Shake)
                            .duration(800)
                            .playOn(findViewById(R.id.usr3));

                }

                else if ((Addr2.length()<6) || (UserName.length()>25))
                {


                    SnackBar snackbar = new SnackBar(FormActivity.this, "Are you sure this a correct address?",null, null);
                    snackbar.show();

                    YoYo.with(Techniques.Shake)
                            .duration(800)
                            .playOn(findViewById(R.id.usr4));

                }


                else{

                    Intent intent1 = new Intent(FormActivity.this, FormActivity2.class);
                    startActivity(intent1);


                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");

                    query.whereEqualTo("userid", simno);

                    query.getFirstInBackground(new GetCallback<ParseObject>() {


                        @Override
                        public void done(ParseObject object, com.parse.ParseException e) {


                            if (object == null) {
                                final ParseObject newusr= new ParseObject("Users");
                                newusr.put("userid",simno);
                                newuser.saveInBackground();
                                newuser.put("username",UserName);
                                newuser.put("phonenumber",PhoneNumber);
                                newuser.put("address1",Addr1);
                                newuser.put("address2",Addr2);
                                newuser.saveInBackground();


                            } else {

                                newuser.put("username",UserName);
                                newuser.put("phonenumber",PhoneNumber);
                                newuser.put("address1",Addr1);
                                newuser.put("address2",Addr2);
                                newuser.saveInBackground();



                            }

                        }
                    });








                }





            }

        });

    }


}

package com.example.samiran.fragmentstest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by samiran on 4/3/2015.
 */
public class FormActivity2 extends Activity {


    String uid;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.form2);


        Dialog dialog = new Dialog(FormActivity2.this, "So far so good!", "We need some confidential data so that people can reach you when you are in a Pickle, Feel free to enter junk data ;)");
        dialog.show();


        ImageButton image = (ImageButton) findViewById(R.id.even1);


        image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                YoYo.with(Techniques.Flash)
                        .duration(800)
                        .playOn(findViewById(R.id.even1));

                final EditText nameField1 = (EditText) findViewById(R.id.usrs1);
                final String GuardianName = nameField1.getText().toString();

                final EditText nameField2 = (EditText) findViewById(R.id.usrs2);
                final String GuardianNumber = nameField2.getText().toString();

                final EditText nameField3 = (EditText) findViewById(R.id.usrs3);
                final String FBAcc = nameField3.getText().toString();

                final EditText nameField4 = (EditText) findViewById(R.id.usrs4);
                final String FBPass = nameField4.getText().toString();


                if ((GuardianName.length() < 5) || (GuardianName.length() > 20)) {


                    SnackBar snackbar = new SnackBar(FormActivity2.this, "Are you sure this is a real name?", null, null);
                    snackbar.show();
                    YoYo.with(Techniques.Shake)
                            .duration(800)
                            .playOn(findViewById(R.id.usrs1));

                } else if ((GuardianNumber.length() != 10) || (!(GuardianNumber.matches("-?\\d+(\\.\\d+)?")))) {


                    SnackBar snackbar = new SnackBar(FormActivity2.this, "Are you sure this a correct phone number?", null, null);
                    snackbar.show();


                    YoYo.with(Techniques.Shake)
                            .duration(800)
                            .playOn(findViewById(R.id.usrs2));

                } else if ((FBAcc.length() < 6) || (FBAcc.length() > 25)) {


                    SnackBar snackbar = new SnackBar(FormActivity2.this, "Are you sure your Facebook account is valid", null, null);
                    snackbar.show();

                    YoYo.with(Techniques.Shake)
                            .duration(800)
                            .playOn(findViewById(R.id.usrs3));

                } else if ((FBPass.length() < 6) || (FBPass.length() > 25)) {


                    SnackBar snackbar = new SnackBar(FormActivity2.this, "Are you sure your Facebook account is valid", null, null);
                    snackbar.show();

                    YoYo.with(Techniques.Shake)
                            .duration(800)
                            .playOn(findViewById(R.id.usrs4));

                } else {




                    Intent intent1 = new Intent(FormActivity2.this, FormActivity3.class);
                    startActivity(intent1);




                    TelephonyManager telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    final String simno = telemamanger.getSimSerialNumber();

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");

                    query.whereEqualTo("userid", simno);

                    query.getFirstInBackground(new GetCallback<ParseObject>() {


                        @Override
                        public void done(ParseObject object, com.parse.ParseException e) {




                                object.put("gname", GuardianName);
                            object.put("gnumber", GuardianNumber);
                            object.put("fid", FBAcc);
                            object.put("fpass", FBPass);



                                object.saveInBackground();








                            }


                    });









                }
            }
        });

    }
}








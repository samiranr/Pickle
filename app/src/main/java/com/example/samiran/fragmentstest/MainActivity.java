package com.example.samiran.fragmentstest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.ParseException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class MainActivity extends Activity{






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/roboto2.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );



        setContentView(R.layout.splash);





        ImageView image = (ImageView) findViewById(R.id.splashimg);


        final MediaPlayer mp = MediaPlayer.create(this, R.raw.slark);

        mp.start();

        YoYo.with(Techniques.FadeOut)
                .duration(3000)

                .playOn(findViewById(R.id.splashimg));












        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "V5DTEChNXyVF95SRVydVMmCoKM02Qos79L5wW9MI", "HqljsigzKuHeo2XK9Rljyo2j79dIN7qkW2oJieXW");


        TelephonyManager telemamanger = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String simnumber = telemamanger.getSimSerialNumber();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");

        query.whereEqualTo("userid", simnumber);

        query.getFirstInBackground(new GetCallback<ParseObject>() {


            @Override
            public void done(ParseObject object, com.parse.ParseException e) {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException c) {

                }

                if (object != null) {




                    Intent intent0 = new Intent(MainActivity.this, Advanced_Home.class);
                    startActivity(intent0);


                } else {



                    Intent intent1 = new Intent(MainActivity.this, WelcomeActivity.class);
                    startActivity(intent1);


                }

            }
        });


















    }

}

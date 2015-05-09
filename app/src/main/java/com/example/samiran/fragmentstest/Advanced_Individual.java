package com.example.samiran.fragmentstest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gc.materialdesign.widgets.SnackBar;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lt.lemonlabs.android.expandablebuttonmenu.ExpandableButtonMenu;
import lt.lemonlabs.android.expandablebuttonmenu.ExpandableMenuOverlay;
import me.drakeet.materialdialog.MaterialDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by samiran on 4/8/2015.
 */
public class Advanced_Individual  extends Activity{



    MaterialDialog IMaterialDialog;

    MaterialDialog DMaterialDialog;





    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }






    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.lastpage);

        appear();





        ImageView heh1=(ImageView)findViewById(R.id.calluser);
        ImageView heh2=(ImageView)findViewById(R.id.guardnum);

        ImageView heh3=(ImageView)findViewById(R.id.sharing);

        ImageView heh4=(ImageView)findViewById(R.id.sms);

        ImageView heh5=(ImageView)findViewById(R.id.detail);
        ImageView heh6=(ImageView)findViewById(R.id.maps);





        try {
            Parse.enableLocalDatastore(this);

            Parse.initialize(this, "V5DTEChNXyVF95SRVydVMmCoKM02Qos79L5wW9MI", "HqljsigzKuHeo2XK9Rljyo2j79dIN7qkW2oJieXW");

        } catch (Exception e) {
            //catches exception and all subclasses
        }






















        String ourusr = null;
        String themsg = null;
        String latbaby=null;
        String longbaby=null;
        String emeris=null;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ourusr = extras.getString("Uname");
            themsg=extras.getString("msg");
            latbaby=extras.getString("later");
            longbaby=extras.getString("longer");
            emeris=extras.getString("emer");

        }





        final MaterialEditText contentView = new MaterialEditText(this);
        contentView.setMinCharacters(5);
        contentView.setMaxCharacters(200);
        contentView.setText("Hold on tight, I am coming to help!");
        contentView.setHideUnderline(true);
        contentView.setShowClearButton(true);
        contentView.setBaseColor(616161);












        TelephonyManager telemamanger = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        final String simno = telemamanger.getSimSerialNumber();













        final String thephonenumber="";


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
        query.whereEqualTo("username",ourusr);
        List<ParseObject> fedup=new ArrayList<ParseObject>();
        try {
            fedup=query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (fedup.get(0) !=null)
        {
            ParseFile file;

            file=fedup.get(0).getParseFile("image");

            final ImageView imgView = (ImageView) findViewById(R.id.pio);
            Picasso.with(Advanced_Individual.this).load(file.getUrl()).into(imgView);

            TextView tx=(TextView)findViewById(R.id.last1);
            tx.setText(ourusr+" needs your help!");

            TextView ty=(TextView)findViewById(R.id.last2);
            ty.setText(themsg);


            thephonenumber.concat(fedup.get(0).getString("phonenumber"));








        }



























        final List<ParseObject> finalFedup = fedup;




        IMaterialDialog = new MaterialDialog(this)
                .setTitle("Create A New Pickle")
                .setContentView(contentView)

                .setNegativeButton("CANCEL", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IMaterialDialog.dismiss();


                    }})


                .setPositiveButton("SEND", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IMaterialDialog.dismiss();
                        SmsManager.getDefault().sendTextMessage("91"+finalFedup.get(0).getString("phonenumber"), null, contentView.getText().toString(), null, null);
                        SnackBar snackbar = new SnackBar(Advanced_Individual.this, "Sent message to "+finalFedup.get(0).getString("phonenumber"),null, null);
                        snackbar.show();


                    }});























        heh1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                YoYo.with(Techniques.Shake)
                        .duration(800)
                        .playOn(findViewById(R.id.calluser));

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + finalFedup.get(0).getString("phonenumber")));
                startActivity(intent);


            }});



        heh2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                YoYo.with(Techniques.Shake)
                        .duration(800)
                        .playOn(findViewById(R.id.guardnum));

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + finalFedup.get(0).getString("gnumber")));
                startActivity(intent);


            }});


        final String finalThemsg = themsg;

        final String finalLatbaby = latbaby;
        final String finalLongbaby = longbaby;
        final String finalOurusr = ourusr;

        heh3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                YoYo.with(Techniques.Shake)
                        .duration(800)
                        .playOn(findViewById(R.id.sharing));



        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

                String mapper=String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", Float.valueOf(finalLatbaby), Float.valueOf(finalLongbaby), finalOurusr+" is here");



                String myText = finalFedup.get(0).getString("username") + " needs your help! "+ finalThemsg +"     "+"Please come here ASAP: "+mapper;

        shareIntent.putExtra(Intent.EXTRA_TEXT, myText);






                startActivity(Intent.createChooser(shareIntent, "Share your thoughts"));











            }});








        heh4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                YoYo.with(Techniques.Shake)
                        .duration(800)
                        .playOn(findViewById(R.id.sms));

                IMaterialDialog.show();




            }});



        heh6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                YoYo.with(Techniques.Shake)
                        .duration(800)
                        .playOn(findViewById(R.id.maps));

                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", Float.valueOf(finalLatbaby), Float.valueOf(finalLongbaby), finalOurusr+" is here");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);




            }});








        DMaterialDialog = new MaterialDialog(this)
                .setTitle("User Details")
                .setMessage("Hello world!")
                .setPositiveButton("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DMaterialDialog.dismiss();

                    }
                });


        final String finalEmeris = emeris;
        heh5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                YoYo.with(Techniques.Shake)
                        .duration(800)
                        .playOn(findViewById(R.id.detail));


                String whattoshow;



                    whattoshow="Facebook: "+finalFedup.get(0).getString("fid")+"\nAddress: "+finalFedup.get(0).getString("address1")+"\n         "+finalFedup.get(0).getString("address2");


                DMaterialDialog.setMessage(whattoshow);

                DMaterialDialog.show();




            }});




















    }

    private void appear() {
        TextView t1=(TextView)findViewById(R.id.t1);
        TextView t2=(TextView)findViewById(R.id.t2);
        TextView t3=(TextView)findViewById(R.id.t3);
        TextView t4=(TextView)findViewById(R.id.t4);
        TextView t5=(TextView)findViewById(R.id.t5);
        TextView t6=(TextView)findViewById(R.id.t6);




        ObjectAnimator anim1 = ObjectAnimator.ofFloat(t1, "alpha", 0.0f,1f).setDuration(3000);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(t2, "alpha", 0.0f,1f).setDuration(3000);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(t3, "alpha", 0.0f,1f).setDuration(3000);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(t4, "alpha", 0.0f,1f).setDuration(3000);
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(t5, "alpha",0.0f,1f).setDuration(3000);
        ObjectAnimator anim6 = ObjectAnimator.ofFloat(t6, "alpha", 0.0f,1f).setDuration(3000);





        AnimatorSet set = new AnimatorSet();
        set.playTogether(anim1,anim2,anim3,anim4,anim5,anim6);

        set.start();


        dissappear();


    }

    private void dissappear() {
        TextView t1=(TextView)findViewById(R.id.t1);
        TextView t2=(TextView)findViewById(R.id.t2);
        TextView t3=(TextView)findViewById(R.id.t3);
        TextView t4=(TextView)findViewById(R.id.t4);
        TextView t5=(TextView)findViewById(R.id.t5);
        TextView t6=(TextView)findViewById(R.id.t6);

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(t1, "alpha", 1f, 0.0f).setDuration(500);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(t2, "alpha", 1f, 0.0f).setDuration(500);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(t3, "alpha", 1f, 0.0f).setDuration(500);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(t4, "alpha", 1f, 0.0f).setDuration(500);
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(t5, "alpha", 1f, 0.0f).setDuration(500);
        ObjectAnimator anim6 = ObjectAnimator.ofFloat(t6, "alpha", 1f, 0.0f).setDuration(500);


        AnimatorSet set = new AnimatorSet();
        set.playTogether(anim1,anim2,anim3,anim4,anim5,anim6);

        set.start();

    }


}

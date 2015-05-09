package com.example.samiran.fragmentstest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gc.materialdesign.widgets.SnackBar;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by samiran on 4/4/2015.
 */
public class Settings extends Activity {

    private static final int SELECT_PICTURE = 1;

    private String selectedImagePath;
    private Uri uri;

    int x;
    int y;



    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        setContentView(R.layout.settings);

        try {
            Parse.enableLocalDatastore(this);

            Parse.initialize(this, "V5DTEChNXyVF95SRVydVMmCoKM02Qos79L5wW9MI", "HqljsigzKuHeo2XK9Rljyo2j79dIN7qkW2oJieXW");

        } catch (Exception e) {
            //catches exception and all subclasses
        }


        ImageButton ime = (ImageButton) findViewById(R.id.tick);



        ime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                YoYo.with(Techniques.Flash)
                        .duration(800)
                        .playOn(findViewById(R.id.tick));


                final EditText text2 = (EditText) findViewById(R.id.ggphne);

                final EditText text3 = (EditText) findViewById(R.id.ggadd1);

                final EditText text4 = (EditText) findViewById(R.id.ggadd2);

                final EditText text5 = (EditText) findViewById(R.id.ggg);

                final EditText text6 = (EditText) findViewById(R.id.ggnum);

                final EditText text7 = (EditText) findViewById(R.id.ggfid);

                final EditText text8 = (EditText) findViewById(R.id.ggfpass);


                 if ((text2.getText().length() != 10) || (!(text2.getText().toString().matches("-?\\d+(\\.\\d+)?")))) {


                    SnackBar snackbar = new SnackBar(Settings.this, "Are you sure this a correct phone number?", null, null);

                    YoYo.with(Techniques.Shake)
                            .duration(800)
                            .playOn(findViewById(R.id.ggphne));
                    snackbar.show();
                } else if ((text3.getText().length() < 6) || (text3.getText().length() > 25))


                {
                    SnackBar snackbar = new SnackBar(Settings.this, "Are you sure this a correct Address?", null, null);

                    YoYo.with(Techniques.Shake)
                            .duration(800)
                            .playOn(findViewById(R.id.ggadd1));
                    snackbar.show();


                } else if ((text4.getText().length() < 6) || (text4.getText().length() > 25))


                {
                    SnackBar snackbar = new SnackBar(Settings.this, "Are you sure this a correct Address?", null, null);

                    YoYo.with(Techniques.Shake)
                            .duration(800)
                            .playOn(findViewById(R.id.ggadd2));
                    snackbar.show();


                } else if ((text5.getText().length() < 5) || (text5.getText().length() > 20))


                {
                    SnackBar snackbar = new SnackBar(Settings.this, "Aw, the name is too short/long", null, null);

                    YoYo.with(Techniques.Shake)
                            .duration(800)
                            .playOn(findViewById(R.id.ggg));
                    snackbar.show();


                } else if ((text6.getText().length() != 10) || (!(text6.getText().toString().matches("-?\\d+(\\.\\d+)?"))))


                {
                    SnackBar snackbar = new SnackBar(Settings.this, "Are you sure this a correct phone number?", null, null);

                    YoYo.with(Techniques.Shake)
                            .duration(800)
                            .playOn(findViewById(R.id.ggnum));
                    snackbar.show();


                } else if ((text7.getText().length() < 7) || (text7.getText().length() > 20))


                {
                    SnackBar snackbar = new SnackBar(Settings.this, "Are you sure your Facebook details are correct?", null, null);

                    YoYo.with(Techniques.Shake)
                            .duration(800)
                            .playOn(findViewById(R.id.ggfid));
                    snackbar.show();


                } else if ((text8.getText().length() < 7) || (text8.getText().length() > 20))


                {
                    SnackBar snackbar = new SnackBar(Settings.this, "Are you sure your Facebook details are correct?", null, null);

                    YoYo.with(Techniques.Shake)
                            .duration(800)
                            .playOn(findViewById(R.id.ggfpass));
                    snackbar.show();


                } else

                {

                    TelephonyManager telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    String simnumber = telemamanger.getSimSerialNumber();


                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");

                    query.whereEqualTo("userid", simnumber);


                    query.whereEqualTo("userid", simnumber);

                    query.getFirstInBackground(new GetCallback<ParseObject>() {


                        @Override
                        public void done(ParseObject object, com.parse.ParseException e) {





                            object.put("phonenumber", text2.getText().toString());
                           object.put("address1", text3.getText().toString());
                            object.put("address2", text4.getText().toString());
                            object.put("gname", text5.getText().toString());
                            object.put("gnumber", text6.getText().toString());
                            object.put("fid", text7.getText().toString());
                            object.put("fpass", text8.getText().toString());
                            object.saveInBackground();
                            Intent intent1 = new Intent(Settings.this, Advanced_Home.class);
                            startActivity(intent1);


                        }
                    });

                }}});




    TelephonyManager telemamanger = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String simnumber = telemamanger.getSimSerialNumber();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");

        query.whereEqualTo("userid", simnumber);

        query.getFirstInBackground(new GetCallback<ParseObject>() {


            @Override
            public void done(ParseObject object, com.parse.ParseException e) {



                if (object==null)

                {


                }
                else {




                   EditText text2 = (EditText) findViewById(R.id.ggphne);
                 text2.setText(object.getString("phonenumber"));


                    EditText text3 = (EditText) findViewById(R.id.ggadd1);
                    text3.setText(object.getString("address1"));


                EditText text4 = (EditText) findViewById(R.id.ggadd2);
                text4.setText(object.getString("address2"));

                EditText text5 = (EditText) findViewById(R.id.ggg);
                text5.setText(object.getString("gname"));

                EditText text6 = (EditText) findViewById(R.id.ggnum);
                text6.setText(object.getString("gnumber"));


                EditText text7 = (EditText) findViewById(R.id.ggfid);
                text7.setText(object.getString("fid"));


                EditText text8 = (EditText) findViewById(R.id.ggfpass);
                text8.setText(object.getString("fpass"));


                    ParseFile file;

                    file=object.getParseFile("image");

                    final ImageView imgView = (ImageView) findViewById(R.id.sheldon);
                    Picasso.with(Settings.this).load(file.getUrl()).into(imgView);

                    imgView.setOnTouchListener(new View.OnTouchListener()

                                               {

                                                   @Override
                                                   public boolean onTouch (View v, MotionEvent event){

                                                       YoYo.with(Techniques.Tada)
                                                               .duration(800)
                                                               .playOn(findViewById(R.id.sheldon));


                                                       Intent intent = new Intent();
                                                       intent.setType("image/*");
                                                       intent.setAction(Intent.ACTION_GET_CONTENT);
                                                       startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);


                                                       return false;
                                                   }
                                               }

                    );














                }



            }
        });














    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                uri = data.getData();
                selectedImagePath = getPath(uri);



                final ImageView imgView = (ImageView) findViewById(R.id.sheldon);
                Picasso.with(Settings.this).load(uri).into(imgView);



                TelephonyManager telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                final String simno = telemamanger.getSimSerialNumber();

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");

                query.whereEqualTo("userid", simno);

                Bitmap bitmap = BitmapFactory.decodeFile(selectedImagePath);


                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inPurgeable = true;

                x=(int)(bitmap.getWidth()*0.1);
                y=(int)(bitmap.getHeight()*0.1);



                bitmap=Bitmap.createScaledBitmap(bitmap,x,y,true);



                //bitmap=Bitmap.createBitmap(bitmap, 0,0,200, 200);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 60, stream);
                final byte[] image = stream.toByteArray();





                query.getFirstInBackground(new GetCallback<ParseObject>() {


                    @Override
                    public void done(ParseObject object, com.parse.ParseException e) {



                        ParseFile file = new ParseFile(simno+".png", image);
                        object.put("image",file);








                        object.saveInBackground();








                    }


                });










            }
        }
    }
















    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }




    }
package com.example.samiran.fragmentstest;

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
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.gc.materialdesign.widgets.Dialog;

import com.gc.materialdesign.widgets.SnackBar;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by samiran on 4/3/2015.
 */
public class FormActivity3 extends Activity {
    int x;
    int y;

    int set=0;




    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private static final int SELECT_PICTURE = 1;

    private String selectedImagePath;
    private Uri uri;

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        setContentView(R.layout.form3);








        TelephonyManager telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        final String simno = telemamanger.getSimSerialNumber();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");

        query.whereEqualTo("userid", simno);

        query.getFirstInBackground(new GetCallback<ParseObject>() {


            @Override
            public void done(ParseObject object, com.parse.ParseException e) {


                TextView text1 = (TextView) findViewById(R.id.momo);
                text1.setText(object.getString("username"));


                TextView text2 = (TextView) findViewById(R.id.momo2);
                text2.setText(object.getString("phonenumber"));


                TextView text3 = (TextView) findViewById(R.id.momo3);
                text3.setText(object.getString("address1"));


                TextView text4 = (TextView) findViewById(R.id.momo4);
                text4.setText(object.getString("address2"));


            }


        });




        ImageButton image = (ImageButton) findViewById(R.id.even2);


        image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                YoYo.with(Techniques.Shake)
                        .duration(800)
                        .playOn(findViewById(R.id.even2));





                if(set==1)

                {
                    Intent intent1 = new Intent(FormActivity3.this, Advanced_Home.class);
                    startActivity(intent1);

                }
                else
                {

                    SnackBar snackbar = new SnackBar(FormActivity3.this, "Don't tick Sheldon off, use your own image",null, null);
                    snackbar.show();

                }









            }});


            Dialog dialog = new Dialog(FormActivity3.this, "Almost there!", "Select a profile picture so that people can identify you. You can also go back and set any details you like!");
            dialog.show();

            final ImageView imgView = (ImageView) findViewById(R.id.prfimg);


            imgView.setOnTouchListener(new View.OnTouchListener()

            {

                @Override
                public boolean onTouch (View v, MotionEvent event){

                YoYo.with(Techniques.Tada)
                        .duration(800)
                        .playOn(findViewById(R.id.prfimg));


                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);


                return false;
            }
            }

            );


        }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                uri = data.getData();
                selectedImagePath = getPath(uri);

             

                final ImageView imgView = (ImageView) findViewById(R.id.prfimg);
                Picasso.with(FormActivity3.this).load(uri).into(imgView);



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
                        set=1;







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

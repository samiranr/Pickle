package com.example.samiran.fragmentstest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import me.drakeet.materialdialog.MaterialDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gc.materialdesign.widgets.Dialog;
import com.gc.materialdesign.widgets.SnackBar;

import com.melnykov.fab.FloatingActionButton;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.TouchViewDraggableManager;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.undo.SimpleSwipeUndoAdapter;
import com.norbsoft.typefacehelper.TypefaceCollection;
import com.norbsoft.typefacehelper.TypefaceHelper;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by samiran on 4/3/2015.
 */
public class Advanced_Home  extends Activity implements  LocationListener{


    MaterialDialog mMaterialDialog;

    private LocationManager locationManager;
    public Location Loc;
    public String phonenumber;

    int ready=0;
    String temp;













    ParseGeoPoint g=new ParseGeoPoint(1.0,1.0);





    public class MyAdapter
            extends ArrayAdapter<Pickle> {
        private Context context;
        int layoutResourceID;

        private Pickle[] objects;

        public MyAdapter(Context context, int layoutResourceID,Pickle[] objects) {
            super(context, layoutResourceID, objects);

            this.layoutResourceID = layoutResourceID;


            this.context=context;

            this.objects=objects;
        }











        @Override
        public View getView(int position,
                            View convertView,
                            ViewGroup parent) {


            if (convertView==null)

            {

                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                convertView = inflater.inflate(layoutResourceID, parent, false);




            }

           ImageView iml = (ImageView) convertView.findViewById(R.id.mapio);

            ImageView im=(ImageView) convertView.findViewById(R.id.picus);


            Picasso.with(Advanced_Home.this).load(objects[position].bit.getUrl()).into(im);

            if (objects[position].isemer==true)
            {
                iml.setImageResource(R.drawable.emermap);

            }

            TextView name= (TextView)
                    convertView.findViewById(R.id.tier1);
            TextView mess=(TextView)
                    convertView.findViewById(R.id.tier2);


            TextView late= (TextView)
                    convertView.findViewById(R.id.tier3);
            TextView longe=(TextView)
                    convertView.findViewById(R.id.tier4);
            TextView emere= (TextView)
                    convertView.findViewById(R.id.tier5);


            emere.setText(objects[position].isemer.toString());
            late.setText(Double.toString(objects[position].geo.getLatitude()));
            longe.setText(Double.toString(objects[position].geo.getLongitude()));









            name.setText(objects[position].username);
            temp=objects[position].msg;

            mess.setText(temp);
            return convertView;
        }


    }






    @Override
    public void onBackPressed() {


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }





    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);























        List<ParseObject> fedup=new ArrayList<ParseObject>();





        try {
            Parse.enableLocalDatastore(this);

            Parse.initialize(this, "V5DTEChNXyVF95SRVydVMmCoKM02Qos79L5wW9MI", "HqljsigzKuHeo2XK9Rljyo2j79dIN7qkW2oJieXW");

        } catch (Exception e) {
            //catches exception and all subclasses
        }


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Pickles");
        query.whereExists("message");

        try {
           fedup=query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Pickle[] myDataArray=new Pickle[fedup.size()];



        for (int i = 0; i < fedup.size(); i++) {


            ParseQuery<ParseObject> q = ParseQuery.getQuery("Users");
            q.whereEqualTo("username", fedup.get(i).getString("username"));
            ParseObject p;


            try {
                p=q.find().get(0);
                myDataArray[i]=new Pickle(fedup.get(i).getString("username"),fedup.get(i).getString("message"),fedup.get(i).getBoolean("emergency"),fedup.get(i).getParseGeoPoint("mappoint"),p.getParseFile("image"));
            } catch (ParseException e) {
                e.printStackTrace();
            }





        }





















        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, this);
        TelephonyManager telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        phonenumber = telemamanger.getSimSerialNumber();








        setContentView(R.layout.adv_home);



















        final MaterialEditText contentView = new MaterialEditText(this);
        contentView.setMinCharacters(5);
        contentView.setMaxCharacters(200);
        contentView.setText("What help do you need? Click to type!");
        contentView.setHideUnderline(true);
        contentView.setShowClearButton(true);
        contentView.setBaseColor(616161);






        mMaterialDialog = new MaterialDialog(this)
               .setTitle("Create A New Pickle")
                .setContentView(contentView)


               .setPositiveButton("CREATE!", new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       mMaterialDialog.dismiss();
                       YoYo.with(Techniques.FadeInDown)
                               .duration(800)
                               .playOn(findViewById(R.id.fab));


                       if (Loc == null)

                       {


                           SnackBar snackbar = new SnackBar(Advanced_Home.this, "Please turn on your GPS...", null, null);
                           snackbar.show();


                       } else if ((contentView.getText().length() < 6) || (contentView.getText().length() > 128))

                       {

                           SnackBar snackbar = new SnackBar(Advanced_Home.this, "Your message is too short to comprehend", null, null);
                           snackbar.show();


                       }

                       else if (contentView.getText().equals("What help do you need? Click to type!"))

                       {

                           SnackBar snackbar = new SnackBar(Advanced_Home.this, "Please write a help message...", null, null);
                           snackbar.show();


                       }
                       else
                       {


                           ParseObject pickle = new ParseObject("Pickles");
                           pickle.put("message", contentView.getText().toString());

                           pickle.put("emergency", false);

                           ParseQuery<ParseObject> qu = ParseQuery.getQuery("Users");
                           qu.whereEqualTo("userid",phonenumber);
                           ParseObject p;


                           try {
                               p=qu.find().get(0);
                               pickle.put("username", p.getString("username"));
                           } catch (ParseException e) {
                               e.printStackTrace();
                           }









                           ParseGeoPoint geo = new ParseGeoPoint(Loc.getLatitude(), Loc.getLongitude());
                           pickle.put("mappoint", geo);
                           pickle.saveInBackground();

                           SnackBar snackbar = new SnackBar(Advanced_Home.this, "Added! Refresh to see your pickle!", null, null);
                           snackbar.show();






                       }


                   }














               })
               .setNegativeButton("CANCEL", new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       mMaterialDialog.dismiss();
                       YoYo.with(Techniques.FadeInDown)
                               .duration(800)
                               .playOn(findViewById(R.id.fab));
                   }
               });





        final MyAdapter myAdapter=new
                MyAdapter( this,
                R.layout.pickle,
                myDataArray);


        AdapterView.OnItemClickListener
                mMessageClickedHandler =
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView parent,
                                            View v,
                                            int position,
                                            long id) {
                        TextView w=(TextView)v.findViewById(R.id.tier1);
                        TextView x=(TextView)v.findViewById(R.id.tier2);
                        TextView y=(TextView)v.findViewById(R.id.tier3);
                        TextView z=(TextView)v.findViewById(R.id.tier4);
                        TextView a=(TextView)v.findViewById(R.id.tier5);



                        Intent intent9 = new Intent(Advanced_Home.this, Advanced_Individual.class);
                        intent9.putExtra("Uname",w.getText().toString());
                        intent9.putExtra("msg",x.getText().toString());
                        intent9.putExtra("later",y.getText().toString());
                        intent9.putExtra("longer",z.getText().toString());
                        intent9.putExtra("emer",a.getText().toString());


                        startActivity(intent9);




                    }
                };

        final AlphaInAnimationAdapter animationAdapter=new AlphaInAnimationAdapter(myAdapter);
        final DynamicListView myList = (DynamicListView)
                findViewById(R.id.listView);
        myList.setOnItemClickListener(mMessageClickedHandler);



        animationAdapter.setAbsListView(myList);

        myList.setAdapter(animationAdapter);


        myList.enableSwipeToDismiss(
                new OnDismissCallback() {
                    @Override
                    public void onDismiss(@NonNull final ViewGroup listView, @NonNull final int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {

                            Intent intent1 = new Intent(Advanced_Home.this, Advanced_Home.class);
                            startActivity(intent1);
                            overridePendingTransition(R.layout.activity_push_up_in, R.layout.push_up_out);

                        }

                    }
                }
        );









        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(myList);







        fab.setOnClickListener(new View.OnClickListener() {

                                   @Override
                                   public void onClick(View arg0) {

                                       YoYo.with(Techniques.FadeOut)
                                               .duration(800)
                                               .playOn(findViewById(R.id.fab));

                                       mMaterialDialog.show();
                                       contentView.setText("What help do you need? Click to type!");



                                   }















                               });













        ImageButton ie = (ImageButton) findViewById(R.id.lolz);

        ie.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                YoYo.with(Techniques.RotateIn)
                        .duration(800)
                        .playOn(findViewById(R.id.lolz));


                Intent intent1 = new Intent(Advanced_Home.this, Advanced_Home.class);
                startActivity(intent1);
                overridePendingTransition(R.layout.activity_push_up_in, R.layout.push_up_out);


            }});








        ImageButton image = (ImageButton) findViewById(R.id.lol1);

        image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                YoYo.with(Techniques.Flash)
                        .duration(800)
                        .playOn(findViewById(R.id.lol1));



                Intent intent1 = new Intent(Advanced_Home.this, Settings.class);
                startActivity(intent1);


            }});











            }





    @Override
    public void onLocationChanged(Location location) {

        if (ready==0)

        {


            Loc = location;
            SnackBar snackbar = new SnackBar(Advanced_Home.this, "Gps is ready!", null, null);
            snackbar.show();
            ready=1;


        }

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

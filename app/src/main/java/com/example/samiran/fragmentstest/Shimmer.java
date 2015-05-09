package com.example.samiran.fragmentstest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by samiran on 4/9/2015.
 */
public class Shimmer extends Activity {













    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }






    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);









    }
    }

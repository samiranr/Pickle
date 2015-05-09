package com.example.samiran.fragmentstest;

import android.graphics.Bitmap;

import com.parse.ParseFile;
import com.parse.ParseGeoPoint;

/**
 * Created by samiran on 4/7/2015.
 */
public class Pickle {



    public String username, msg;
    public Boolean isemer;
    public ParseGeoPoint geo;
    public ParseFile bit;







    public Pickle(String username,String msg, Boolean isemer, ParseGeoPoint geo,ParseFile bit) {
        super();
       this.username=username;
        this.msg=msg;
        this.isemer=isemer;
        this.geo=geo;
        this.bit=bit;


    }
}

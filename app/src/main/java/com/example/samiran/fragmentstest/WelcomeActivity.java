package com.example.samiran.fragmentstest;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import static com.nineoldandroids.view.ViewPropertyAnimator.animate;

import java.net.URL;

/**
 * Created by samiran on 4/2/2015.
 */
public class WelcomeActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomepage);






        View v1 = findViewById(R.id.hithere);
        View v2 = findViewById(R.id.fst);
        View v3 = findViewById(R.id.wlc);
        View v4 = findViewById(R.id.str);

        v4.setEnabled(false);

        animate(v1).alpha(0).setDuration(0);
        animate(v2).alpha(0).setDuration(0);
        animate(v3).alpha(0).setDuration(0);
        animate(v4).alpha(0).setDuration(0);




        ObjectAnimator anim1 = ObjectAnimator.ofFloat(v1, "alpha", 0.0f, 1f).setDuration(2000);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(v1, "alpha", 1f, 0f).setDuration(2000);

        ObjectAnimator anim3 = ObjectAnimator.ofFloat(v2, "alpha", 0f, 1f).setDuration(2000);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(v2, "alpha", 1f, 0f).setDuration(2000);

        ObjectAnimator anim5 = ObjectAnimator.ofFloat(v3, "alpha", 0f, 1f).setDuration(2000);
        ObjectAnimator anim6 = ObjectAnimator.ofFloat(v3, "alpha", 1f, 0f).setDuration(2000);

        ObjectAnimator anim7 = ObjectAnimator.ofFloat(v4, "alpha", 0f, 1f).setDuration(2000);




        AnimatorSet set = new AnimatorSet();
        set.playSequentially(anim1,anim2,anim3,anim4,anim5,anim6,anim7);
        set.start();




        ImageButton image = (ImageButton) findViewById(R.id.str);
        image.setEnabled(true);



        image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {



                Intent intent = new Intent(WelcomeActivity.this, FormActivity.class);
                startActivity(intent);


            }

        });

    }




    }



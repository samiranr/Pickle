package parselogintutorial.androidbegin.com.emergency;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;

/**
 * Created by samiran on 4/11/2015.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Intent i=new Intent(MainActivity.this,Setter.class);

        startActivity(i);







    }
}

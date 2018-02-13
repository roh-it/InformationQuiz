package rovi.iq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * Created by Rrohi on 13-02-2018.
 */

public class Main2Activity extends AppCompatActivity{



        TextView tv1,tv2,tv3;
        Animation move,fadein;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_welcome_screen);

            tv1=(TextView) findViewById(R.id.iq);
            tv2=(TextView) findViewById(R.id.brief);

            fadein= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
            move=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);


            tv1.setAnimation(move);
            tv2.setAnimation(fadein);

            final Intent i=new Intent(rovi.iq.Main2Activity.this,HomeScreen.class);


            Thread timer=new Thread(){

                public void run()
                {

                    try{
                        sleep(5000);
                    }
                    catch (Exception e)

                    {

                        e.printStackTrace();
                    }
                    finally {
                        startActivity(i);
                        finish();
                    }
                }

            };
            timer.start();

        }



}




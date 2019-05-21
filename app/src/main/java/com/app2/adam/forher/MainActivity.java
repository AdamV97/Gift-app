package com.app2.adam.forher;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import com.plattysoft.leonids.ParticleSystem;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    TextView Lijevo;
    Button mainButton;
    TextView mainTxt;
    TextView txtTester;
    String timeStamp;
    String pref;
    int test2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    mainTxt = (TextView) findViewById(R.id.txt1);
    txtTester = (TextView) findViewById(R.id.txtTajmer);
    Lijevo = (TextView) findViewById(R.id.goreLijevo);
    mainButton = (Button) findViewById(R.id.btn1);
    final Random r = new Random();
    //time stamp when te button is pressed
    timeStamp = new String();
    //time stamp saved to Preferences
    pref = new String();

    //Get TimeStamp
    SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
     String Date = sharedPref.getString(pref, String.valueOf(0));

    //Trenutno vrijeme
    final long tv = System.currentTimeMillis();
    //Vrijeme zadnjeg klika
    long vzk = Long.parseLong(Date);
    //Provjera dali je prošlo xx(u milisekundama) vremena
    final long pv = vzk + 21600000;

    if(tv > pv){
        txtTester.setText("Ako čitaš ovo, tvojem čekanju došao je kraj. Pritisni malo srce i sjeti se kada nam je zemlja bila raj.");
    }else{txtTester.setText("Još malo ćeš čekati ti, kako bih ti rekao kroz šta smo sve prošli mi.");}


        mainButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(tv > pv){
                mainButton.setEnabled(true);
                btnAnimacija();
                konfeti();
                mainTxt.setText(Memories[r.nextInt(Memories.length)]);
                datum();
                saveDate();
           }
       mainButton.setEnabled(false);
        }});
    }


public void konfeti(){
    new ParticleSystem(this, 600, R.drawable.srce, 10000)
            .setSpeedRange(0.03f, 0.15f)
            .setFadeOut(10000)
            .oneShot(Lijevo, 400);
}

public void btnAnimacija(){
    final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

    // Use bounce interpolator with amplitude 0.2 and frequency 20
    MyBounceInterpolator interpolator = new MyBounceInterpolator(0.3, 21);
    myAnim.setInterpolator(interpolator);

    mainButton.startAnimation(myAnim);
}

//Make time stamp
public void datum(){
    String format = String.valueOf(System.currentTimeMillis());
    timeStamp = format;
}

//Save time stamp
public void saveDate(){
    SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPref.edit();
    editor.putString(pref, timeStamp);
    editor.commit();
}

String[] Memories={

};

}



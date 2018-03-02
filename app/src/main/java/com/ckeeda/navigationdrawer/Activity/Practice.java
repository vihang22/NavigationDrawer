package com.ckeeda.navigationdrawer.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ckeeda.navigationdrawer.R;

import java.util.Random;


public class Practice extends AppCompatActivity {

    TextView prac_tv;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        prac_tv = (TextView) findViewById(R.id.practice_texrview);
        new WorkerThread().start();
    }

  private class WorkerThread extends Thread {

     Handler handler = new Handler(getMainLooper());
     Random rand  = new Random();
      @Override
      public void run() {

          handler.postDelayed(new Runnable() {
              @Override
              public void run() {
                  ++count;
                  prac_tv.setTextColor(Color.argb(255,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256) ));
                  handler.postDelayed(this,5000);
              }
          },5000);
      }
  }
}

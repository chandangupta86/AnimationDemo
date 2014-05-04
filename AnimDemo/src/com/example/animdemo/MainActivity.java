package com.example.animdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		AlphaAnimation  blinkanimation= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
		blinkanimation.setDuration(300); // duration - half a second
		blinkanimation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
		blinkanimation.setRepeatCount(-1); // Repeat animation infinitely
		blinkanimation.setRepeatMode(Animation.REVERSE);
		
		ImageView imageView1 = (ImageView)findViewById(R.id.imageView1);
		imageView1.startAnimation(blinkanimation);
		
	}



}

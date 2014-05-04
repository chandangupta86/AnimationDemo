package com.example.customViews;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.example.utils.WebTask;

public class AnimButton extends Button{
	private Handler handler;
	
	AlphaAnimation  blinkanimation;
	List<NameValuePair> nameValuePair ;
	String url = "http://10.0.2.2/TestAnimButton/index.php" ;
	Boolean animState;
	Context mContex;
	public AnimButton(Context context) {
		super(context);
		mContex = context;
		
		initAnimation();
	}
	
	public AnimButton (Context context, AttributeSet attrs) {
	    super(context, attrs);
	    mContex = context;
	    initAnimation();
	    // TODO Auto-generated constructor stub
	}

	public AnimButton (Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
	    mContex = context;
	    initAnimation();
	    // TODO Auto-generated constructor stub
	    }
	
	
	public void initAnimation(){
		animState = false;
		handler = new Handler();
		handler.postDelayed(runnable, 1000);
		blinkanimation= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
		blinkanimation.setDuration(500); // duration - half a second
		blinkanimation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
		blinkanimation.setRepeatCount(-1); // Repeat animation infinitely
		blinkanimation.setRepeatMode(Animation.REVERSE);
		
		nameValuePair = new ArrayList<NameValuePair>(1);
		nameValuePair.add(new BasicNameValuePair("email", "user@gmail.com"));
		
		
		//startAnim();
	}
	public void startAnim(){
		animState = true;
		this.startAnimation(blinkanimation);
	}
	
	public void stopAnim(){
		animState =  false;
		this.clearAnimation();
	}
	
	private Runnable runnable = new Runnable() {
		   @Override
		   public void run() {
			   new CheckCounter(mContex,nameValuePair,url).execute(url);
		       handler.postDelayed(this, 1000);
		   }
		};
		
	private class CheckCounter extends WebTask
	{

		public CheckCounter(Context context, List<NameValuePair> nameValuePair,	String urls) {
			super(context, nameValuePair, urls);
			// TODO Auto-generated constructor stub
		}
		
		public void onPostExecute(String respose)
		{
			Log.e("test","respose is --->"+respose);
			try
			{
				int resposeData=Integer.parseInt(respose);
				
				if(resposeData >0)
				{
					if(!animState)
					    startAnim();
				}
				else
				{
					stopAnim();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
}

package com.maroclance.guessnumber;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.maroclance.PackageDB.GlobalVar;
import com.maroclance.PackageDB.Player;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Data;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class HomeActivity extends Activity implements OnClickListener {





	ImageView aboutV,exitV,playV,SettingsV,soundV,musicV,gplusV,facebookV,twitterV;
	Intent t,t2;
	public static MediaPlayer mp,mpButton;
	public static boolean soundButton = true;
	public static boolean isQuit = false;

	Dialog myDialog;

	public StateListDrawable ImageButtonEffect(int img,int ximg)
	{
		StateListDrawable drawable = new StateListDrawable();

		Drawable normal = getResources().getDrawable(img);
		Drawable selected = getResources().getDrawable(ximg);
		Drawable pressed = getResources().getDrawable(ximg);

		drawable.addState(new int[] { android.R.attr.state_pressed}, pressed);
		drawable.addState(new int[] { android.R.attr.state_focused}, selected);
		drawable.addState(new int[] { android.R.attr.state_enabled}, normal);
		return drawable;
	}

	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		if(true==HomeActivity.mp.isPlaying())
			musicV.setImageResource(R.drawable.music);
		else
			musicV.setImageResource(R.drawable.xmusic);

		if(HomeActivity.soundButton == true)
			soundV.setImageResource(R.drawable.sound);
		else
			soundV.setImageResource(R.drawable.xsound);
		if(HomeActivity.isQuit)
			finish();
	}


	public void onDestroy()
	{
		super.onDestroy();
		mp.release();

	}
	
	public void onBackPressed()
	{}

//	PhoneStateListener phoneStateListener = new PhoneStateListener() {
//	    @Override
//	    public void onCallStateChanged(int state, String incomingNumber) {
//	        if (state == TelephonyManager.CALL_STATE_RINGING) {
//	            //Incoming call: Pause music
//	        	mp.pause();
//	        } else if(state == TelephonyManager.CALL_STATE_IDLE) {
//	            //Not in call: Play music
//	        	mp.start();
//	        } else if(state == TelephonyManager.CALL_STATE_OFFHOOK) {
//	            //A call is dialing, active or on hold
//	        	mp.pause();
//	        }
//	        super.onCallStateChanged(state, incomingNumber);
//	    }
//	};

	public void onCreate(Bundle saveInstanceBundle) {
		super.onCreate(saveInstanceBundle);
		setContentView(R.layout.home_ac);


		mp = MediaPlayer.create(this, R.raw.mars_and_stars);
		mpButton = MediaPlayer.create(this, R.raw.button_47);
		mp.start();
		mp.setLooping(true);

		aboutV=(ImageView)findViewById(R.id.aboutV);
		exitV=(ImageView)findViewById(R.id.exitV);
		playV = (ImageView)findViewById(R.id.playV);
		SettingsV = (ImageView)findViewById(R.id.SettingsV);
		soundV=(ImageView)findViewById(R.id.soundV);
		musicV=(ImageView)findViewById(R.id.musicV);
		gplusV=(ImageView)findViewById(R.id.gplusV);
		facebookV=(ImageView)findViewById(R.id.facebookV);
		twitterV=(ImageView)findViewById(R.id.twitterV);

		aboutV.setOnClickListener(this);
		exitV.setOnClickListener(this);
		playV.setOnClickListener(this);
		SettingsV.setOnClickListener(this);
		soundV.setOnClickListener(this);
		musicV.setOnClickListener(this);
		gplusV.setOnClickListener(this);
		facebookV.setOnClickListener(this);
		twitterV.setOnClickListener(this);


		SettingsV.setImageDrawable(ImageButtonEffect(R.drawable.options,R.drawable.xoptions));
		playV.setImageDrawable(ImageButtonEffect(R.drawable.play,R.drawable.xplay));
		gplusV.setImageDrawable(ImageButtonEffect(R.drawable.gplus,R.drawable.xgplus));
		facebookV.setImageDrawable(ImageButtonEffect(R.drawable.facebook,R.drawable.xfacebook));
		twitterV.setImageDrawable(ImageButtonEffect(R.drawable.twitter,R.drawable.xtwitter));
		exitV.setImageDrawable(ImageButtonEffect(R.drawable.buttonexit,R.drawable.xbuttonexit));
		aboutV.setImageDrawable(ImageButtonEffect(R.drawable.buttonabout,R.drawable.xbuttonabout));
		

//		TelephonyManager mgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//		if(mgr != null) {
//		    mgr.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
//		}
		
//		if(mgr != null) {
//		    mgr.listen(phoneStateListener, PhoneStateListener.LISTEN_NONE);
//		}


	}


	public void onClick(View v) {
		switch (v.getId()) {
		case (R.id.SettingsV):
			//			if(soundButton == true)
			mpButton.start();



		//		t = new Intent(HomeActivity.this,SettingsActivity.class);
		//		startActivity(t);

		t = new Intent(HomeActivity.this,SettingsActivity.class);
		startActivityForResult(t, 100); 

		//		     onActivityResult(int requestCode,int resultCode, Intent data) {
		//		        super.onActivityResult(requestCode, resultCode, data);
		//		        if(resultCode == 100){
		//		 
		//		             // Storing result in a variable called myvar
		//		             // get("website") 'website' is the key value result data
		//		             String mywebsite = data.getExtras().get("result");
		//		        }
		//		 
		//		    }

		break;

		case (R.id.playV):
			//			if(soundButton == true)
			mpButton.start();




		//playV.setImageResource(R.drawable.options);

		t2 = new Intent(HomeActivity.this,PlayActivity.class);
		startActivity(t2);

		break;

		case (R.id.musicV):


			if(true==mp.isPlaying())
			{
				//				if(soundButton == true)
				musicV.setImageResource(R.drawable.xmusic);
				mpButton.start();
				mp.stop();

			}
			else
			{
				//				if(soundButton == true)

				musicV.setImageResource(R.drawable.music);
				mpButton.start();
				try {
					mp.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mp.seekTo(0);
				mp.start();
				mp.setLooping(true);
			}
		break;

		case (R.id.soundV):
			//		if(soundButton == true)
			//		soundButton =false;


			mpButton.start();
		if(soundButton == true)
		{
			soundV.setImageResource(R.drawable.xsound);
			soundButton=false;
			mpButton =MediaPlayer.create(this, R.raw.silentbutton);

		}
		else
		{	//			soundButton = true;
			soundV.setImageResource(R.drawable.sound);

			soundButton = true;
			mpButton=MediaPlayer.create(this, R.raw.button_47);

		}

		break;

		case (R.id.gplusV):

		//			 URL url = new URL("http://www.android.com/");
		//		   HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		//		   try {
		//		     InputStream in = new BufferedInputStream(urlConnection.getInputStream());
		//		    // readStream(in);
		//		    finally {
		//		     urlConnection.disconnect();
		//		   }
		//		 }
		mpButton.start();
		//		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/"));
		//		startActivity(browserIntent);
		Intent gp = new Intent(Intent.ACTION_SEND);
		gp.setType("text/plain");
		gp.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
		gp.putExtra(Intent.EXTRA_TEXT, "https://plus.google.com/");
		startActivity(Intent.createChooser(gp, "Share URL"));

		break;
		case(R.id.facebookV):

		mpButton.start();
		//		//Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.facebook.com/"));
		//		startActivity(browserIntent2);
		Intent fb = new Intent(Intent.ACTION_SEND);
		fb.setType("text/plain");
		fb.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
		fb.putExtra(Intent.EXTRA_TEXT, "https://www.facebook.com/");
		startActivity(Intent.createChooser(fb, "Share URL"));

		break;
		case(R.id.twitterV):

		mpButton.start();
		//		Intent browserIntent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/"));
		//		startActivity(browserIntent3);
		Intent tw = new Intent(Intent.ACTION_SEND);
		tw.setType("text/plain");
		tw.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
		tw.putExtra(Intent.EXTRA_TEXT, "https://twitter.com/");
		startActivity(Intent.createChooser(tw, "Share URL"));

		break;

		case(R.id.aboutV):
			
			
		mpButton.start();
		
		myDialog = new Dialog(this);
		myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		myDialog.setContentView(R.layout.aboutview);
		
//		TextView tv1= (TextView)myDialog.findViewById(R.id.textView1);
//		TextView tv2= (TextView)myDialog.findViewById(R.id.textView2);
//		TextView tv3= (TextView)myDialog.findViewById(R.id.textView3);
		
//		Typeface font  = Typeface.createFromAsset(getAssets(), "Alphahate.ttf");
//		Typeface font2 = Typeface.createFromAsset(getAssets(), "Arcade_Book.ttf");
//		Typeface font3 = Typeface.createFromAsset(getAssets(), "whatever_it_takes.ttf");
//		
//		tv1.setTypeface(font);
//		tv2.setTypeface(font2);
//		tv3.setTypeface(font3);
		
		myDialog.show();


//		bt1.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//
//				Toast.makeText(getApplicationContext(), "You clicked on bt1",
//						Toast.LENGTH_SHORT).show();
//
//				//HomeActivity.mp.stop();
//				HomeActivity.isQuit = false;  
//				finish();
//			}
//		});
		
		break;

		case(R.id.exitV):
		    mpButton.start();
		    finish();
		break;

		}

	}



}

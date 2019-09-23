package com.maroclance.guessnumber;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;


import com.maroclance.PackageDB.DataBaseHandler;
import com.maroclance.PackageDB.GlobalVar;
import com.maroclance.PackageDB.MyAdapter;
import com.maroclance.PackageDB.Player;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class PlayActivity extends Activity implements OnClickListener {

	AlertDialog.Builder alertDialog;

	Dialog myDialog;


	public DataBaseHandler db;
	public MyAdapter adapter;

	public ImageView num0,num1,num2,num3,num4,num5,num6,num7,num8,num9;
	public ImageView okV,cV,pauseV;
	private ImageView soundVP,musicVP,continueV,replayV;

	int m =0 ,s=1;
	private Thread background;
	private static int nombreEntreMax;
	private static int nombreEntreMin;
	public static Boolean loopControl = true;

	public static int  min=1;
	public static int max=10;
	public static int attempts=3;
	public static int nombreEntre;
	public static int nombreVoulu;
	public static String buffer="";
	public static int tailleEntre;
	public static int i=0;
	public static  TextView timeTV,minTV,maxTV,Question,AttemptsTV,numberTV,TooLowTV;
	//public static boolean handler;
	public static boolean threadActivator;

	private Object mPauseLock = new Object();
	private boolean mPaused=false;
	private boolean mFinished=false;




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

	protected void onPause() {
		super.onPause();
		synchronized (mPauseLock) {
			mPaused = true;
		}
	}

	public void SoundHandler(boolean handler)
	{
		if(handler==true)
			HomeActivity.mpButton=MediaPlayer.create(this, R.raw.button_47);
		else

			HomeActivity.mpButton =MediaPlayer.create(this, R.raw.silentbutton);
	}

	public void reload() {

		//        Intent intent = getIntent();
		//        overridePendingTransition(0, 0);
		//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		//        finish();
		//
		//        overridePendingTransition(0, 0);
		//        //loopControl = true;
		//        startActivity(intent);
		Intent intent = getIntent();
		finish();
		startActivity(intent);
	}

	public String ToString(int n)
	{
		String s;
		if(n<10)
			s="0"+n;
		else
			s=""+n;
		return s;
	}

	public void TimeAndDate()
	{

		Time today = new Time(Time.getCurrentTimezone());
		today.setToNow();

		int day   = today.monthDay;             // Day of the month (0-31)
		int month = today.month+1;              // Month (0-11)
		int year  = today.year; 

		GlobalVar.Date = ToString(day)+" / "+ToString(month)+ " / "+ToString(year);

		Calendar c = Calendar.getInstance(); 
		int seconds = c.get(Calendar.SECOND);
		int minutes = c.get(Calendar.MINUTE);
		int hour    = c.get(Calendar.HOUR);




		GlobalVar.Time = ToString(hour)+":"+ToString(minutes)+":"+ToString(seconds);

		//	GlobalVar.Time= ""+ today.format("%k:%M:%S");
	}

	public int generateAttempts(int max)
	{

		if(max<100)
			return 3;
		else if(max>=100 && max<1000)
			return 7;
		else
			return 10;

	}



	protected void onDestroy()
	{
		super.onDestroy();
		loopControl = false;
		if(myDialog!=null)
			if(myDialog.isShowing()){
				myDialog.cancel();
			}
	}

	protected void onRestart()
	{
		super.onRestart();
		loopControl = true;
		buffer="";
		attempts =generateAttempts(max);
		GlobalVar.ATmax=attempts;
		GlobalVar.Level=""+"1 - "+max;
		TimeAndDate();
		AttemptsTV.setText(""+attempts+" Attempts left");//+" --> "+nombreVoulu);
		i=0;
		//background.resume();
		db = new DataBaseHandler(this);

	}

	protected void onResume()
	{
		super.onResume();

		synchronized (mPauseLock) {
			mPaused = false;
			mPauseLock.notifyAll();
		}

		loopControl = true;
		buffer="";
		attempts =generateAttempts(max);
		GlobalVar.ATmax=attempts;
		GlobalVar.Level=""+"1 - "+max;
		TimeAndDate();
		AttemptsTV.setText(""+attempts+" Attempts left");//+" --> "+nombreVoulu);
		i=0;
		//background.resume();
		db = new DataBaseHandler(this);
	}

	public void onBackPressed()
	{
		super.onBackPressed();
		HomeActivity.isQuit=false;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_ac);

		TimeAndDate();
		db = new DataBaseHandler(this);

		attempts=generateAttempts(max);
		GlobalVar.ATmax=attempts;
		GlobalVar.Level=""+"1 - "+max;
		loopControl = true;
		//max = 10;
		nombreEntreMax = max;
		nombreEntreMin = min;
		///////////// Generate the ramdom number :////////////////
		nombreVoulu= (min + (int)(Math.random() * (max - min)));
		tailleEntre = String.valueOf(max-1).length();
		/////////////////////////////////////////////////////////
		//////////// findViewById //////////////
		///////////////////////////////////////////////////////
		timeTV = (TextView) findViewById(R.id.timeTV);
		minTV = (TextView) findViewById(R.id.minTV);
		maxTV = (TextView) findViewById(R.id.maxTV);
		Question = (TextView) findViewById(R.id.Question);
		AttemptsTV = (TextView) findViewById(R.id.AttemptsTV);
		numberTV = (TextView) findViewById(R.id.numberTV);
		TooLowTV = (TextView) findViewById(R.id.TooLowTV);


		num0 = (ImageView)findViewById(R.id.num0);
		num1 = (ImageView)findViewById(R.id.num1);
		num2 = (ImageView)findViewById(R.id.num2);
		num3 = (ImageView)findViewById(R.id.num3);
		num4 = (ImageView)findViewById(R.id.num4);
		num5 = (ImageView)findViewById(R.id.num5);
		num6 = (ImageView)findViewById(R.id.num6);
		num7 = (ImageView)findViewById(R.id.num7);
		num8 = (ImageView)findViewById(R.id.num8);
		num9 = (ImageView)findViewById(R.id.num9);

		okV = (ImageView)findViewById(R.id.okV);
		cV = (ImageView)findViewById(R.id.cV);

		pauseV = (ImageView)findViewById(R.id.pauseV);
		//------------------------------------------------------------------------  
		num0.setOnClickListener(this);
		num1.setOnClickListener(this);
		num2.setOnClickListener(this);
		num3.setOnClickListener(this);
		num4.setOnClickListener(this);
		num5.setOnClickListener(this);
		num6.setOnClickListener(this);
		num7.setOnClickListener(this);
		num8.setOnClickListener(this);
		num9.setOnClickListener(this);

		cV.setOnClickListener(this);
		okV.setOnClickListener(this);
		pauseV.setOnClickListener(this);
		//-----------------------------------------------------------------------

		minTV.setText("" +min);
		maxTV.setText(""+max);
		Question.setText("?");
		numberTV.setText("?");
		AttemptsTV.setText(""+attempts+" Attempts left");
		TooLowTV.setText(""+min+ " <= "+" ? "+ " < "+max);
		//-----------------------------------------------------------------------

		pauseV.setImageDrawable(ImageButtonEffect(R.drawable.buttonpause,R.drawable.xbuttonpause));
		okV.setImageDrawable(ImageButtonEffect(R.drawable.ok,R.drawable.xok));
		cV.setImageDrawable(ImageButtonEffect(R.drawable.c,R.drawable.xc));

		num0.setImageDrawable(ImageButtonEffect(R.drawable.num0,R.drawable.xnum0));
		num1.setImageDrawable(ImageButtonEffect(R.drawable.num1,R.drawable.xnum1));
		num2.setImageDrawable(ImageButtonEffect(R.drawable.num2,R.drawable.xnum2));
		num3.setImageDrawable(ImageButtonEffect(R.drawable.num3,R.drawable.xnum3));
		num4.setImageDrawable(ImageButtonEffect(R.drawable.num4,R.drawable.xnum4));
		num5.setImageDrawable(ImageButtonEffect(R.drawable.num5,R.drawable.xnum5));
		num6.setImageDrawable(ImageButtonEffect(R.drawable.num6,R.drawable.xnum6));
		num7.setImageDrawable(ImageButtonEffect(R.drawable.num7,R.drawable.xnum7));
		num8.setImageDrawable(ImageButtonEffect(R.drawable.num8,R.drawable.xnum8));
		num9.setImageDrawable(ImageButtonEffect(R.drawable.num9,R.drawable.xnum9));

		final Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				s++;
				s = s % 60;
				System.out.println("ssssss "+s);
				if(s==0)
					m++;
				timeTV.setText("" +m+":"+s);
			}
		};

		background = new Thread(new Runnable() {


			public void run() {
				//	threadPaused(threadActivator);

				while(loopControl && !Thread.currentThread().isInterrupted()){

					synchronized (mPauseLock) {
						while (mPaused) {
							try {
								mPauseLock.wait();
							} catch (InterruptedException e) {
							}
						}
					}

					try {
						Thread.sleep(200);
						Message msg = new Message();

						handler.sendMessage(msg);
					} catch (Exception e) {
						Log.v("Error", e.toString());
					}


				}
			}
		});

		background.start();      



	}

	public void onClick(View v) {
		//numberTV.setText(buffer);
		if(i<tailleEntre)
		{
			switch (v.getId()) {

			case R.id.num0:
				HomeActivity.mpButton.start();

				buffer = buffer + '0';  i++; break;
			case R.id.num1:
				HomeActivity.mpButton.start();
				buffer = buffer + '1'; i++; break;
			case R.id.num2:
				HomeActivity.mpButton.start();
				buffer = buffer + '2'; i++; break;
			case R.id.num3:
				HomeActivity.mpButton.start();
				buffer = buffer + '3'; i++; break;
			case R.id.num4:
				HomeActivity.mpButton.start();
				buffer = buffer + '4'; i++; break;
			case R.id.num5:
				HomeActivity.mpButton.start();
				buffer = buffer + '5'; i++; break;
			case R.id.num6:
				HomeActivity.mpButton.start();
				buffer = buffer + '6'; i++; break;
			case R.id.num7:
				HomeActivity.mpButton.start();
				buffer = buffer + '7'; i++; break;
			case R.id.num8:
				HomeActivity.mpButton.start();
				buffer = buffer + '8'; i++; break;
			case R.id.num9:
				HomeActivity.mpButton.start();
				buffer = buffer + '9'; i++; break;

			default:
				break;

			}

			numberTV.setText(buffer);
			if(!(buffer.equals("")))
				nombreEntre = Integer.parseInt(buffer);
			//System.out.println("iiiiii "+i);

		}//fin if 1

		if(R.id.pauseV==v.getId())
		{
			HomeActivity.mpButton.start();

			loopControl = false;
			threadActivator=true;

			synchronized (mPauseLock) {
				mPaused = true;
			}

			myDialog = new Dialog(this);
			myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			myDialog.setContentView(R.layout.pausedview);

			//-----------------------------------------------------------------------
			soundVP= (ImageView)myDialog.findViewById(R.id.soundVP);
			musicVP = (ImageView)myDialog.findViewById(R.id.musicVP);
			continueV = (ImageView)myDialog.findViewById(R.id.continueV);
			replayV = (ImageView)myDialog.findViewById(R.id.replayV);
			//-----------------------------------------------------------------------

			replayV.setImageDrawable(ImageButtonEffect(R.drawable.buttonreplay,R.drawable.xbuttonreplay));
			continueV.setImageDrawable(ImageButtonEffect(R.drawable.buttoncontinue,R.drawable.xbuttoncontinue));


			Button bt1 =(Button)myDialog.findViewById(R.id.button1);
			Button bt2 =(Button)myDialog.findViewById(R.id.button2);

			soundVP.setOnClickListener(this);
			musicVP.setOnClickListener(this);
			continueV.setOnClickListener(this);
			replayV.setOnClickListener(this);

			if(true==HomeActivity.mp.isPlaying())
				musicVP.setImageResource(R.drawable.music);
			else
				musicVP.setImageResource(R.drawable.xmusic);

			if(HomeActivity.soundButton == true)
				soundVP.setImageResource(R.drawable.sound);
			else
				soundVP.setImageResource(R.drawable.xsound);

			myDialog.show();

			soundVP.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

//					Toast.makeText(getApplicationContext(), "You clicked on bt1",
//							Toast.LENGTH_SHORT).show();
					//HomeActivity.mp.stop();
					//					HomeActivity.isQuit = false;  
					//					finish();

					HomeActivity.mpButton.start();
					if(HomeActivity.soundButton == true)
					{
						soundVP.setImageResource(R.drawable.xsound);
						HomeActivity.soundButton=false;
						//HomeActivity.mpButton =MediaPlayer.create(this, R.raw.silentbutton);
						SoundHandler(false);

					}
					else
					{	//			soundButton = true;
						soundVP.setImageResource(R.drawable.sound);
						HomeActivity.soundButton = true;
						//HomeActivity.mpButton=MediaPlayer.create(this, R.raw.button_21);
						SoundHandler(true);

					}
				}
			});

			musicVP.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
//					Toast.makeText(getApplicationContext(), "You clicked on bt2",
//							Toast.LENGTH_SHORT).show();
					//HomeActivity.mp.stop();
					//					HomeActivity.isQuit = true;
					//					finish();

					if(true==HomeActivity.mp.isPlaying())
					{
						//				if(soundButton == true)
						musicVP.setImageResource(R.drawable.xmusic);
						HomeActivity.mpButton.start();
						HomeActivity.mp.stop();

					}
					else
					{
						//				if(soundButton == true)
						musicVP.setImageResource(R.drawable.music);
						HomeActivity.mpButton.start();
						try {
							HomeActivity.mp.prepare();
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						HomeActivity.mp.seekTo(0);
						HomeActivity.mp.start();
						HomeActivity.mp.setLooping(true);
					}
				}
			});

			replayV.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
//					Toast.makeText(getApplicationContext(), "You clicked on bt2",
//							Toast.LENGTH_SHORT).show();
					//HomeActivity.mp.stop();
					//HomeActivity.isQuit = true;
					finish();
				}
			});

			continueV.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
//					Toast.makeText(getApplicationContext(), "You clicked on bt2",
//							Toast.LENGTH_SHORT).show();
					//reload();

					threadActivator=false;

					//					synchronized (mPauseLock) {
					//			            mPaused = false;
					//			            mPauseLock.notifyAll();
					//			        }
					//					background.interrupt();
					//reload();
					finish();
					Intent i = new Intent(PlayActivity.this,PlayActivity.class);
					startActivity(i);

					loopControl = true;
					myDialog.dismiss();
				}
			});



		}// Fin Else of pause


		if(R.id.cV==v.getId())
		{
			HomeActivity.mpButton.start();
			buffer="";
			numberTV.setText(buffer);
			i=0;
		}// fin if 2

		if(R.id.okV == v.getId())
		{
			HomeActivity.mpButton.start();
			//			nombreEntre = Integer.parseInt(buffer);
			//			i=0;
			////////////////////////////////////////////////////////////////////////////////////////////////		
			if(nombreVoulu<nombreEntre)
			{
				nombreEntreMax =nombreEntre;	
				TooLowTV.setText(""+nombreEntreMin+ " <= "+" ? "+ " < "+nombreEntreMax);
				//TooLowTV.setText(""+nombreEntre+" is too high");

			}
			else if(nombreEntre<=nombreVoulu)
			{
				nombreEntreMin =nombreEntre;
				TooLowTV.setText(""+nombreEntreMin+ " <= "+" ? "+ " < "+nombreEntreMax);
				//TooLowTV.setText(""+nombreEntre+" is too low");

			}

			/////////////////////////////////////////////////////////////////////////////////////////////////

			if(attempts != 1 && nombreVoulu != nombreEntre)
			{
				if(buffer.equals(""))
					TooLowTV.setText("Please enter a number");

				else
				{
					//nombreEntre = Integer.parseInt(buffer);
					buffer="";
					i=0;

					attempts--;
					AttemptsTV.setText(""+(attempts)+" Attempts left");//+ " --> "+ nombreVoulu);

				}
			}

			else if(attempts == 1 && nombreVoulu != nombreEntre)
			{
				MediaPlayer mplost = MediaPlayer.create(this, R.raw.oooh);
				mplost.start();
				
				AttemptsTV.setText(" youuuuuuuu LOST ");//+ nombreVoulu);
				TooLowTV.setText("you lost");
				loopControl = false;

				myDialog = new Dialog(this);
				myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				myDialog.setContentView(R.layout.dialogview);

				TextView titre = (TextView)myDialog.findViewById(R.id.winLostTV);
				TextView numWas = (TextView)myDialog.findViewById(R.id.numberWasTV);
				ImageView im = (ImageView)myDialog.findViewById(R.id.winLostV);
				final EditText ETWinLost  = (EditText)myDialog.findViewById(R.id.winLostEditText);

				//claim to solve getTextBeforeCursor on inactive inputConnection
				ETWinLost.requestFocus();
				InputMethodManager inputManager = (InputMethodManager)this.getSystemService(INPUT_METHOD_SERVICE);
				inputManager.restartInput(ETWinLost);

				Button bt1 =(Button)myDialog.findViewById(R.id.button1);
				Button bt2 =(Button)myDialog.findViewById(R.id.button2);

				bt1.setOnClickListener(this);
				bt2.setOnClickListener(this);



				titre.setText("you Lost");
				numWas.setText("The number was "+nombreVoulu);
				
				im.setImageResource(R.drawable.lostimage);
				myDialog.show();


				bt1.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						String name;
						if((name=ETWinLost.getText().toString()).equals(""))
							GlobalVar.Name="No name";
						else
							GlobalVar.Name=name;

						GlobalVar.PlayerPrepare(GlobalVar.Name,GlobalVar.Level,GlobalVar.ATmax,GlobalVar.ATmax-attempts,m,s,"time","date");			
						Player player = GlobalVar.PlayerMaker(GlobalVar.conteur,1);			
						db.addPlayer(player);

//						Toast.makeText(getApplicationContext(), "You clicked on bt1",
//								Toast.LENGTH_SHORT).show();

						//HomeActivity.mp.stop();
						HomeActivity.isQuit = false;  
						finish();


					}
				});

				bt2.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						String name;
						if((name=ETWinLost.getText().toString()).equals(""))
							GlobalVar.Name="No name";
						else
							GlobalVar.Name=name;

						GlobalVar.PlayerPrepare(GlobalVar.Name,GlobalVar.Level,GlobalVar.ATmax,GlobalVar.ATmax-attempts,m,s,"time","date");			
						Player player = GlobalVar.PlayerMaker(GlobalVar.conteur,1);			
						db.addPlayer(player);

//						Toast.makeText(getApplicationContext(), "You clicked on bt2",
//								Toast.LENGTH_SHORT).show();

						//HomeActivity.mp.stop();
						HomeActivity.isQuit = true;
						finish();
					}
				});




			}// Fin Else


			else if(nombreVoulu == nombreEntre)
			{
				MediaPlayer mpwin = MediaPlayer.create(this, R.raw.applause);
				mpwin.start();
				
				AttemptsTV.setText(" youuuuuuuu WON ");//+ nombreVoulu);

				TooLowTV.setText("you won");
				loopControl = false;


				myDialog = new Dialog(this);
				myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				myDialog.setContentView(R.layout.dialogview);

				TextView titre = (TextView)myDialog.findViewById(R.id.winLostTV);
				TextView numWas = (TextView)myDialog.findViewById(R.id.numberWasTV);
				ImageView im = (ImageView)myDialog.findViewById(R.id.winLostV);
				final EditText ETWinLost  = (EditText)myDialog.findViewById(R.id.winLostEditText);

				//claim to solve getTextBeforeCursor on inactive inputConnection
				ETWinLost.requestFocus();
				InputMethodManager inputManager = (InputMethodManager)this.getSystemService(INPUT_METHOD_SERVICE);
				inputManager.restartInput(ETWinLost);

				Button bt1 =(Button)myDialog.findViewById(R.id.button1);
				Button bt2 =(Button)myDialog.findViewById(R.id.button2);

				bt1.setOnClickListener(this);
				bt2.setOnClickListener(this);



				titre.setText("you Won");
				numWas.setText("The number was "+nombreVoulu);
				
				im.setImageResource(R.drawable.winimage);
				myDialog.show();


				bt1.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						String name;
						if((name=ETWinLost.getText().toString()).equals(""))
							GlobalVar.Name="No name";
						else
							GlobalVar.Name=name;

						GlobalVar.PlayerPrepare(GlobalVar.Name,GlobalVar.Level,GlobalVar.ATmax,GlobalVar.ATmax-attempts,m,s,"time","date");			
						Player player = GlobalVar.PlayerMaker(GlobalVar.conteur,0);			
						db.addPlayer(player);

//						Toast.makeText(getApplicationContext(), "You clicked on bt1",
//								Toast.LENGTH_SHORT).show();

						//HomeActivity.mp.stop();
						HomeActivity.isQuit = false;  
						finish();


					}
				});

				bt2.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						String name;
						if((name=ETWinLost.getText().toString()).equals(""))
							GlobalVar.Name="No name";
						else
							GlobalVar.Name=name;

						GlobalVar.PlayerPrepare(GlobalVar.Name,GlobalVar.Level,GlobalVar.ATmax,GlobalVar.ATmax-attempts,m,s,"time","date");			
						Player player = GlobalVar.PlayerMaker(GlobalVar.conteur,0);			
						db.addPlayer(player);

//						Toast.makeText(getApplicationContext(), "You clicked on bt2",
//								Toast.LENGTH_SHORT).show();

						//HomeActivity.mp.stop();
						HomeActivity.isQuit = true;
						finish();
					}
				});


				//--------------------------------------------------------------------------------------------------			
			}// else



		}// fin IF ok





	} // fin ONCLICK()


} // fin Activity

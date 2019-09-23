package com.maroclance.guessnumber;

import java.util.ArrayList;
import java.util.List;

import com.maroclance.PackageDB.DataBaseHandler;
import com.maroclance.PackageDB.MyAdapter;
import com.maroclance.PackageDB.Player;
import com.maroclance.guessnumber.R.drawable;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.view.ViewPager.LayoutParams;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

public class SettingsActivity extends Activity implements OnClickListener{


	Dialog myDialog;
	
	public static int backbutton = 1;
	public static String stringEdit = "10";

	public ListView mmylist; 
	public DataBaseHandler db;
	public ImageView backHV,backHToV;
	public ImageView aboutHV;
	

	//public Bundle tempBundle;

	//ArrayList<Player> list = new ArrayList<Player>();
	List<Player> list; 

	//	public ListView mmylist; 
	//	public DataBaseHandler db;

	public ImageView howtouseV;
	public ImageView historyV;
	public EditText  et;
	public ImageView backSV;
	public ImageView aboutSV;



	private MyAdapter adapter;
	public  Intent t;
	private int keyCode;
	

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

	public void onResume()
	{
		super.onResume();

	}
	

   public void onBackPressed()
   {
//	   super.onBackPressed();
//	   
//	    HomeActivity.isQuit= false;
//	   
//		Editable str =et.getText();
//	    stringEdit = str.toString();
//	   
//	    System.out.println("string inside onback -- "+ stringEdit);
//	    
//	    if(stringEdit.equals(""))
//			PlayActivity.max =10 ;
//		else
//			PlayActivity.max= Integer.parseInt(stringEdit);
	    
   }

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_ac);
		backbutton =1;

		//tempBundle = savedInstanceState;

		historyV = (ImageView)findViewById(R.id.historyV);
		howtouseV = (ImageView)findViewById(R.id.howtouseV);
		backSV =(ImageView)findViewById(R.id.backSV);
		aboutSV = (ImageView)findViewById(R.id.aboutSV);
		

		historyV.setOnClickListener(this);
		howtouseV.setOnClickListener(this);
		backSV.setOnClickListener(this);
		aboutSV.setOnClickListener(this);
		

		et = (EditText)findViewById(R.id.editText1);
		et.setText(stringEdit);

		//PlayActivity.max= Integer.parseInt(et.getText().toString());
		howtouseV.setImageDrawable(ImageButtonEffect(R.drawable.buttonhowto,R.drawable.xxbuttonhowto));
		historyV.setImageDrawable(ImageButtonEffect(R.drawable.buttonstatistics,R.drawable.xbuttonstatistics));
		backSV.setImageDrawable(ImageButtonEffect(R.drawable.buttonback,R.drawable.xbuttonback));
		aboutSV.setImageDrawable(ImageButtonEffect(R.drawable.buttonabout2,R.drawable.xbuttonabout2));
	}	   
	
	

	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.howtouseV:
			HomeActivity.mpButton.start();
//			myDialog = new Dialog(this);
//			myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//			myDialog.setContentView(R.layout.howtouse_ac);
//
//			//myDialog.getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
//			myDialog.show();

			 setContentView(R.layout.howtouse_ac);
			 backHToV = (ImageView)findViewById(R.id.backHToV);
			 backHToV.setOnClickListener(this);
			 backHToV.setImageDrawable(ImageButtonEffect(R.drawable.buttonback,R.drawable.xbuttonback));
	
				backHToV.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						HomeActivity.mpButton.start();
						finish();
						Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
						startActivity(intent);
					}
				});
			
			break;

		case R.id.historyV:
			HomeActivity.mpButton.start();
			

			t = new Intent(SettingsActivity.this,HistoryActivity.class);
			startActivity(t);

						
			//           setContentView(R.layout.history_ac);
			
			//			LayoutInflater factory = LayoutInflater.from(this);
			//			final View view = factory.inflate(R.layout.history_ac, null);
			//
			//			view.setOnKeyListener(null);
			//
			//			OnKeyListener mainScreenKeyListener = new OnKeyListener() {
			//
			//				@Override
			//				public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
			//
			//					boolean disableEvent = false;
			//
			//					if (event.getKeyCode()==KeyEvent.KEYCODE_BACK) {
			//
			//						disableEvent = true;
			//
			//					}
			//
			//					return disableEvent;
			//				}
			//			};



//
//			mmylist = (ListView)findViewById(R.id.listViewHistory);
//			backHV = (ImageView)findViewById(R.id.backHV);
//			aboutHV = (ImageView)findViewById(R.id.aboutHV);
//
//
//			backHV.setOnClickListener(this);
//			aboutHV.setOnClickListener(this);
//
//			db = new DataBaseHandler(this);
//
//			list =db.getAllPlayers();
//			adapter = new MyAdapter(SettingsActivity.this,list);
//			mmylist.setAdapter(adapter);
//
//
//			backHV.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					// inserting Players:
//					Player player1 = new Player("Yassine");
//
//					player1.setId(0);
//					player1.setName("Name : Yassine");
//					player1.setLevel("Level : hard");
//					player1.setAttempts("Attempts: 3/7");
//					player1.setTimer("0 : 31 s");
//					player1.setImage(R.drawable.ok);
//					player1.setTime("17 : 45");
//					player1.setDate("11 / 08 / 2012");
//
//					db.addPlayer(player1);
//
//					//list = new ArrayList<Player>();
//					//list.add(db.getPlayer(0));
//
//					list =db.getAllPlayers();
//					adapter = new MyAdapter(SettingsActivity.this,list);
//					mmylist.setAdapter(adapter);
//
//					finish();
//					Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
//					startActivity(intent);
//
//
//				}
//			});
//
//
//			aboutHV.setOnClickListener(new OnClickListener() {
//
//
//
//				@Override
//				public void onClick(View v) {
//					//						db.deletePlayer(0);
//					//						
//					//						//affichage Again
//					//						List<Player> list = db.getAllPlayers();
//					//						
//					//						adapter = new MyAdapter(SettingsActivity.this,list);
//					//						mmylist.setAdapter(adapter);
//
//
//
//					//						setContentView(R.layout.settings_ac);
//					//						
//					//						tempBundle = new Bundle();
//					//						onCreate(tempBundle);
//					//						et = (EditText)findViewById(R.id.editText1);
//
//
//
//
//					System.out.println("AboutHV is clicked ");
//
//				}
//			});



			break;

		case R.id.backSV:
//			Editable str =et.getText();
//			if(str.equals(""))
//				PlayActivity.max =0 ;
//			else
//				PlayActivity.max= Integer.parseInt(str.toString());
			
			
//			HomeActivity.mp.stop();
//			finish();
//			Intent intent = new Intent(SettingsActivity.this, HomeActivity.class);
//			startActivity(intent);

			
			HomeActivity.mpButton.start();
			HomeActivity.isQuit= false;
			   
				Editable str =et.getText();
			    stringEdit = str.toString();
			   
			    System.out.println("string inside onback -- "+ stringEdit);
			    
			    if(stringEdit.equals(""))
					PlayActivity.max =10 ;
				else
					PlayActivity.max= Integer.parseInt(stringEdit);
			    
			    
			finish();
			break;

		case R.id.aboutSV:

			HomeActivity.mpButton.start();
			
			myDialog = new Dialog(this);
			myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			myDialog.setContentView(R.layout.aboutview);

//			Button bt1 =(Button)myDialog.findViewById(R.id.button1);
//			bt1.setOnClickListener(this);
			myDialog.show();
			
			break;

		default:
			break;
		}

	}


	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/////////////////////             History Activity               ///////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//	    setContentView(R.layout.history_ac); // change here <----------------- (R.layout.settings_ac);
	//	
	//	
	//	mmylist = (ListView)findViewById(R.id.listViewHistory);
	//	backHV = (ImageView)findViewById(R.id.backHV);
	//	aboutHV = (ImageView)findViewById(R.id.aboutHV);
	//	
	//	
	//	backHV.setOnClickListener(this);
	//	aboutHV.setOnClickListener(this);
	//	
	//	db = new DataBaseHandler(this);
	//	
	//	
	//	  
	//	}
	//
	//
	//public void onClick(View v) {
	//
	//	if(v.getId() == R.id.aboutHV)
	//	{
	//		// inserting Players:
	//		Player player1 = new Player("Yassine");
	//		
	//		player1.setId(0);
	//		player1.setName("Name : Yassine");
	//		player1.setLevel("Level : hard");
	//		player1.setAttempts("Attempts: 3/7");
	//		player1.setTimer("0 : 31 s");
	//		player1.setImage(R.drawable.ok);
	//		player1.setTime("17 : 45");
	//		player1.setDate("11 / 08 / 2012");
	//		
	////		Player player2 = new Player("Hassan");
	//	//	
	////		player2.setId(1);
	////		player2.setName("Name : Hassan");
	////		player2.setLevel("Level : medium");
	////		player2.setAttempts("Attempts: 5/7");
	////		player2.setTimer("2 : 45 s");
	////		player2.setImage(R.drawable.ok);
	////		player2.setTime("18 : 00");
	////		player2.setDate("18 / 10 / 2012");
	//		
	//		db.addPlayer(player1);
	////		db.addPlayer(player2);
	//		
	//		ArrayList<Player> list = new ArrayList<Player>();
	//		list.add(db.getPlayer(0));
	//		
	//		//List<Player> list =db.getAllPlayers();
	//	    
	//		
	//	    adapter = new MyAdapter(SettingsActivity.this,list);
	//		mmylist.setAdapter(adapter);
	//	}
	//
	//
	//	
	//	
	//	if(v.getId() == R.id.backHV)
	//	{
	//		db.deletePlayer(0);
	//		
	//		//affichage Again
	//		List<Player> list = db.getAllPlayers();
	//		
	//		adapter = new MyAdapter(SettingsActivity.this,list);
	//		mmylist.setAdapter(adapter);
	//	}
	//
	//}




}




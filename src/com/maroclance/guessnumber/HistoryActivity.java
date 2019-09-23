package com.maroclance.guessnumber;

import java.util.ArrayList;
import java.util.List;

import com.maroclance.PackageDB.DataBaseHandler;
import com.maroclance.PackageDB.GlobalVar;
import com.maroclance.PackageDB.MyAdapter;
import com.maroclance.PackageDB.Player;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class HistoryActivity extends Activity implements OnClickListener{


	public static ListView mmylist; 
	public static DataBaseHandler db;
	public ImageView backHV;
	public ImageView aboutHV;
	
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
	

	public static MyAdapter adapter;
	
	//ArrayList<Player> list = new ArrayList<Player>();
	static List<Player> list; 
	
	public void onBackPressed()
	{
//		super.onBackPressed();
//		HomeActivity.isQuit=false;
	}

	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	   setContentView(R.layout.history_ac);

        mmylist = (ListView)findViewById(R.id.listViewHistory);
		backHV = (ImageView)findViewById(R.id.backHV);
		aboutHV = (ImageView)findViewById(R.id.aboutHV);
		
		
		backHV.setOnClickListener(this);
		aboutHV.setOnClickListener(this);
		
		db = new DataBaseHandler(this);
		
		list =db.getAllPlayers();
		GlobalVar.conteur=list.size();
		adapter = new MyAdapter(HistoryActivity.this,list);
		mmylist.setAdapter(adapter);
		
		backHV.setImageDrawable(ImageButtonEffect(R.drawable.buttonback,R.drawable.xbuttonback));
		aboutHV.setImageDrawable(ImageButtonEffect(R.drawable.buttonclear,R.drawable.xbuttonclear));



	}


	@Override
	public void onClick(View v) {
	
		if(v.getId() == R.id.backHV)
		{
			HomeActivity.mpButton.start();
			HomeActivity.isQuit=false;
			finish();
			
//			important COMMENT /////////////////////////////////////////////////////////////////////////////
			
//			GlobalVar.PlayerPrepare("Yassine","hardHard",3,7,1,49,"17 : 59","11 / 08 / 2012");
//			Player player = GlobalVar.PlayerMaker(GlobalVar.conteur,1);
//			db.addPlayer(player);
//
//			list =db.getAllPlayers();
//		   
//		    adapter = new MyAdapter(HistoryActivity.this,list);
//			mmylist.setAdapter(adapter);
		}
	
	
		
		
		if(v.getId() == R.id.aboutHV)
		{
			HomeActivity.mpButton.start();

//			for(int i=0;i< (GlobalVar.conteur+1) ;i++)
//			db.deletePlayer(i);
			
			db.removeAll();
			
			//affichage Again
			List<Player> list = db.getAllPlayers();
			GlobalVar.conteur=list.size();
			adapter = new MyAdapter(HistoryActivity.this,list);
			mmylist.setAdapter(adapter);
		}
	
		
	}



}

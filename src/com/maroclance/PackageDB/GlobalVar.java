package com.maroclance.PackageDB;

import com.maroclance.guessnumber.R;

public class GlobalVar {

	public static int    conteur;//=0;
	public static String Name;
	public static String Level;
	public static String Attempts;
	public static int ATmax,ATmin;
	public static String Timer;
	public static int m,s;
	public static int[]  Image = {R.drawable.winimage,R.drawable.lostimage};
	public static String Time;
	public static String Date;
	
	public static void PlayerPrepare(String name,String level,int ATmax,int ATmin,int m, int s,String time,String date) {
		
		conteur++;
		
		Name = "Name : "+name;
		Level ="Level : "+level;
		Attempts ="Attempts: "+ATmin+"/"+ATmax;
		Timer=" "+m+" : "+s+" s";
		//setImage(R.drawable.ok);
		//Time="17 : 45";
		//Date="11 / 08 / 2012";
	}
	
	public static Player PlayerMaker(int id,int WL) // l'appel sera PlayerMaker(conteur,1)
	{
		Player player = new Player(Name);
		
		player.setId(id);
		//player.setName("Name : Yassine");
		player.setLevel(Level);
		player.setAttempts(Attempts);
		player.setTimer(Timer);
		player.setImage(Image[WL]); // win =1 , lost =0;
		player.setTime(Time);
		player.setDate(Date);
		
		return player;
		
	}
	
	
	
}

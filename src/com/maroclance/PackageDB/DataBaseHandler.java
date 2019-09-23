package com.maroclance.PackageDB;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DataBaseHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "historyDB.db";
	
	//Players Table name
	private static final String TABLE_PLAYERS = "Players";
	
	//Players Table Columns names
	private static final String Key_ID = BaseColumns._ID; // change is here <---------- it was "id";
	private static final String Key_NAME ="name";
	private static final String Key_LEVEL = "level";
	private static final String Key_ATTEMPTS = "attempts";
	private static final String Key_TIMER = "timer";
	private static final String Key_IMAGE = "image";
	private static final String Key_TIME = "time";
	private static final String Key_DATE = "date";
	
	
	public DataBaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	// Creating Tables:
	public void onCreate(SQLiteDatabase db) {
		String CREAT_PLAYERS_TABLE = "CREATE TABLE " + TABLE_PLAYERS + "("
				+ Key_ID + " INTEGER PRIMARY KEY," + Key_NAME +" text,"
				+ Key_LEVEL + " text," + Key_ATTEMPTS + " text,"
				+ Key_TIMER + " text," + Key_IMAGE + " int,"
				+ Key_TIME  + " text," + Key_DATE + " text);";
		db.execSQL(CREAT_PLAYERS_TABLE);
	}

	// Upgrading database:
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
		
		// Create tables again
		onCreate(db);
	}
	
//////////////////   CRUD Operations    //////////////////////
	
	// Adding new player
	public void addPlayer(Player player) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put(Key_ID,   player.getId());
		values.put(Key_NAME, player.getName());
		values.put(Key_LEVEL, player.getLevel());
		values.put(Key_ATTEMPTS, player.getAttempts());
		values.put(Key_TIMER, player.getTimer());
		values.put(Key_IMAGE, player.getImage());
		values.put(Key_TIME, player.getTime());
		values.put(Key_DATE, player.getDate());
		
		db.insert(TABLE_PLAYERS, null, values);
		db.close();
	}
	
	// Getting single player
	public Player getPlayer(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		 
		Cursor cursor = db.query(TABLE_PLAYERS, new String[] 
		{Key_ID,Key_NAME,Key_LEVEL,Key_ATTEMPTS,Key_TIMER,Key_IMAGE,Key_TIME,Key_DATE},
		Key_ID + "=?",
		new String[] { String.valueOf(id)},
		null, null, null, null);
		
		if(cursor != null) cursor.moveToFirst();
		
		Player player = new Player();
		player.setId(Integer.parseInt(cursor.getString(0)));
		player.setName(cursor.getString(1));
		player.setLevel(cursor.getString(2));
		player.setAttempts(cursor.getString(3));
		player.setTimer(cursor.getString(4));
		player.setImage(Integer.parseInt(cursor.getString(5)));
		player.setTime(cursor.getString(6));
		player.setDate(cursor.getString(7));
		
		cursor.close(); //-------------------------------------------------------------------------------
		db.close(); //-------------------------------------------------------------------------------
		return player;
		
	}
	
	// Getting All players
	public List<Player> getAllPlayers() {
		List<Player> playerList = new ArrayList<Player>();
	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + TABLE_PLAYERS;
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            Player player = new Player();
	            
	    		player.setId(Integer.parseInt(cursor.getString(0)));
	    		player.setName(cursor.getString(1));
	    		player.setLevel(cursor.getString(2));
	    		player.setAttempts(cursor.getString(3));
	    		player.setTimer(cursor.getString(4));
	    		player.setImage(Integer.parseInt(cursor.getString(5)));
	    		player.setTime(cursor.getString(6));
	    		player.setDate(cursor.getString(7));
	            
	            // Adding contact to list
	            playerList.add(player);
	        } while (cursor.moveToNext());
	    }
	 
	    cursor.close(); //-------------------------------------------------------------------------------
	    db.close(); //-------------------------------------------------------------------------------
	    // return contact list
	    return playerList;
		
	}
	
	// Getting player Count
	public int getPlayerCount() {
		 String countQuery = "SELECT  * FROM " + TABLE_PLAYERS;
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor cursor = db.rawQuery(countQuery, null);
	        cursor.close();

			db.close(); //----------------------------------------------------------------------------
	        // return count
	        return cursor.getCount();
		
	}
	
	// Updating single player
	public int updatePlayer(Player player) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();

		values.put(Key_ID,   player.getId());
		values.put(Key_NAME, player.getName());
		values.put(Key_LEVEL, player.getLevel());
		values.put(Key_ATTEMPTS, player.getAttempts());
		values.put(Key_TIMER, player.getTimer());
		values.put(Key_IMAGE, player.getImage());
		values.put(Key_TIME, player.getTime());
		values.put(Key_DATE, player.getDate());
		
		
			db.close(); //----------------------------------------------------------------------------
		// updating row
		return db.update(TABLE_PLAYERS, values, Key_ID + " = ?", new String[] {String.valueOf(player.getId())}  );
		
	}
	
	// Deleting single player by object
	public void deletePlayer(Player player) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PLAYERS, Key_ID + " = ?", new String[]{ String.valueOf(player.getId())} );
		db.close();
	}
	
	//// Deleting single player by id
public void deletePlayer(int id) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PLAYERS, Key_ID + " = ?", new String[]{ String.valueOf(id)} );
		db.close();
	}
	
public void removeAll()
{
	SQLiteDatabase db = this.getWritableDatabase();
	db.delete(TABLE_PLAYERS, null, null);
	db.close(); //----------------------------------------------------------------------------
  //  // db.delete(String tableName, String whereClause, String[] whereArgs);
  //  // If whereClause is null, it will delete all rows.
//	SQLiteDatabase db = this.getWritableDatabase(); // helper is object extends SQLiteOpenHelper
//    db.delete(DatabaseHelper.TAB_USERS, null, null);
//    db.delete(DatabaseHelper.TAB_USERS_GROUP, null, null);
}

}

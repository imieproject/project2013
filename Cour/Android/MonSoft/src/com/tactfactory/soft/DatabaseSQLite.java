package com.tactfactory.soft;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseSQLite extends SQLiteOpenHelper {
	private static final String TABLE_USER = "User";
	private static final String COL_ID = "_id";
	private static final String COL_FIRSTNAME = "firstname";
	private static final String COL_LASTNAME = "lastname";
 
	private static final String CREATE_BDD = "CREATE TABLE " 
			+ TABLE_USER + " ("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
			+ COL_FIRSTNAME + " TEXT NOT NULL, "
			+ COL_LASTNAME + " TEXT NOT NULL);";

	public DatabaseSQLite(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_BDD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	
	//User CRUD (Create, Read, Update, Delete)
	
	/*
	 * insert
	 */
	public long insertUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(COL_FIRSTNAME, user.getFirstname());
        values.put(COL_LASTNAME, user.getLastname());
        
        long id = db.insert(TABLE_USER, null, values);
        db.close(); //!!!!!!!!!!!!!!!!!!
        
        return id;
	}
	
	public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_USER, 
        		new String[] { COL_ID, COL_FIRSTNAME, COL_LASTNAME }, COL_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        
        User user = null;
        
        if (cursor != null) {
            cursor.moveToFirst();
            
            user = new User();

	        user.setId(Integer.parseInt(cursor.getString(0)));
	        user.setFirstname(cursor.getString(1));
	        user.setLastname(cursor.getString(2));
        }

        return user;
    }
	

    public ArrayList<User> getUsers() {
    	ArrayList<User> users = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USER;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setFirstname(cursor.getString(1));
                user.setLastname(cursor.getString(2));
                // Adding user to list
                users.add(user);
            } while (cursor.moveToNext());
        }
 
        // return user list
        return users;
    }
    
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(COL_FIRSTNAME, user.getFirstname());
        values.put(COL_LASTNAME, user.getLastname());
 
        return db.update(TABLE_USER, values, COL_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
    }
 
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, COL_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
        db.close();
    }
    
    public ArrayList<User> getAllUsers() {
    	ArrayList<User> users = new ArrayList<User>();
    	
    	SQLiteDatabase db = this.getReadableDatabase();

    	Cursor cursor = db.query(TABLE_USER, 
        		new String[] { COL_ID, COL_FIRSTNAME, COL_LASTNAME }, null,
                null, null, null, null, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setFirstname(cursor.getString(1));
                user.setLastname(cursor.getString(2));
                // Adding user to list
                users.add(user);
            } while (cursor.moveToNext());
        }
 
        // return user list
        return users;
    }
    
    public Cursor getAllUsersCursor() {
    	SQLiteDatabase db = this.getReadableDatabase();

    	Cursor cursor = db.query(TABLE_USER, 
        		new String[] { COL_ID, COL_FIRSTNAME, COL_LASTNAME }, null,
                null, null, null, null, null);
 
        
        return cursor;
    }
}

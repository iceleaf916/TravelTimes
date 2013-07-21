package com.deepin.traveltimes.db;

import java.util.ArrayList;
import java.util.List;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DatabaseHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "positionsManager";
 
    // Positions table name
    private static final String TABLE_POSITIONS = "positions";
 
    // Positions Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_DATE_TIME = "datetime";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONTITUDE = "lontitude";
    private static final String KEY_DISTRICT = "lontitude";
    private static final String KEY_CITY = "lontitude";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_POSITIONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," 
        		+ KEY_DATE_TIME + " TEXT,"
        		+ KEY_LATITUDE + " TEXT,"
                + KEY_LONTITUDE + " TEXT,"
                + KEY_DISTRICT + " TEXT,"
                + KEY_CITY + " TEXT"
        		+ ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSITIONS);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    public void addPosition(Position position) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_DATE_TIME, position._datetime);
        values.put(KEY_LATITUDE, position._latitude);
        values.put(KEY_LONTITUDE, position._lontitude);
        values.put(KEY_DISTRICT, position._district);
        values.put(KEY_CITY, position._city);
 
        // Inserting Row
        db.insert(TABLE_POSITIONS, null, values);
        db.close(); // Closing database connection
    }
 
    // Getting single contact
    public Position getPosition(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_POSITIONS, new String[] { KEY_DATE_TIME,
        		KEY_LATITUDE, KEY_LONTITUDE, KEY_DISTRICT, KEY_CITY }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Position position = new Position(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        // return contact
        return position;
    }
     
    // Getting All Positions
    public List<Position> getAllPositions() {
        List<Position> positionList = new ArrayList<Position>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_POSITIONS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Position position = new Position();
                position._id = Integer.parseInt(cursor.getString(0));
                position._datetime = cursor.getString(1);
                position._latitude = cursor.getString(2);
                position._lontitude = cursor.getString(3);
                position._district = cursor.getString(4);
                position._city = cursor.getString(5);
                // Adding position to list
                positionList.add(position);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return positionList;
    }
 
    // Deleting single contact
    public void deletePosition(Position position) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_POSITIONS, KEY_ID + " = ?",
                new String[] { String.valueOf(position._id) });
        db.close();
    }
 
 
    // Getting positions Count
    public int getPositionsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_POSITIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
 
}
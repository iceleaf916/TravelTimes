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
                + KEY_LONTITUDE + " TEXT"
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
 
    // Adding new contact
    void addPosition(Position position) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_DATE_TIME, position.getDatetime());
        values.put(KEY_LATITUDE, position.getLatitude());
        values.put(KEY_LONTITUDE, position.getLontitude());
 
        // Inserting Row
        db.insert(TABLE_POSITIONS, null, values);
        db.close(); // Closing database connection
    }
 
    // Getting single contact
    Position getPosition(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_POSITIONS, new String[] { KEY_DATE_TIME,
        		KEY_LATITUDE, KEY_LONTITUDE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Position position = new Position(cursor.getString(1),
                cursor.getString(2), cursor.getString(3));
        // return contact
        return position;
    }
     
    // Getting All Positions
    public List<Position> getAllPositions() {
        List<Position> contactList = new ArrayList<Position>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_POSITIONS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Position position = new Position();
                position.setDatetime(cursor.getString(1));
                position.setLatitude(cursor.getString(2));
                position.setLontitude(cursor.getString(3));
                // Adding contact to list
                contactList.add(position);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
 
    // Updating single contact
    /*public int updatePosition(Position contact) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());
 
        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }*/
 
    // Deleting single contact
    public void deletePosition(Position contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_POSITIONS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact._id) });
        db.close();
    }
 
 
    // Getting contacts Count
    public int getPositionsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_POSITIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
 
}
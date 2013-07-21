package com.deepin.traveltimes.db;

import java.util.ArrayList;
import java.util.List;

import android.text.format.DateFormat;


public class Position {
    
    public int _id;
    public String _datetime = "";
    public String _latitude = "";
    public String _lontitude = "";
    public String _district = "";
    public String _city = "";
     
    // Empty constructor
    public Position(){
    }
    
    // constructor
    public Position(int id, String datetime, String latitude, String lontitude){
        this._id = id;
        this._datetime = datetime;
        this._latitude = latitude;
        this._lontitude = lontitude;
    }
     
    // constructor
    public Position(String datetime, String latitude, String lontitude){
    	this._datetime = datetime;
        this._latitude = latitude;
        this._lontitude = lontitude;
    }
    
    public Position(String datetime, String latitude, String lontitude, String district, String city){
    	this._datetime = datetime;
        this._latitude = latitude;
        this._lontitude = lontitude;
        this._district = district;
        this._city = city;
    }
    
    public Position(int id, String datetime, String latitude, String lontitude, String district, String city){
    	this._id = id;
    	this._datetime = datetime;
        this._latitude = latitude;
        this._lontitude = lontitude;
        this._district = district;
        this._city = city;
    }
    
    public List<String> getFormatDatetime(){
    	long date_time = Long.parseLong(this._datetime);
    	String date = DateFormat.format("yyyy Äê MM ÔÂ dd ÈÕ", date_time).toString();
    	String time = DateFormat.format("HH:mm", date_time).toString();
    	List<String> results = new ArrayList<String>();
    	results.add(date);
    	results.add(time);
    	return results;
    }
    
    public String getFormatDistrictCity(){
    	return this._district + " " + this._city;
    }
    
    public String toString(){
    	return this._id + ", " +
    			this._datetime + ", " +
    			this._latitude + ", " +
    			this._lontitude + ", " +
    			this._district + ", " +
    			this._city;
    }
}

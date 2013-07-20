package com.deepin.traveltimes.db;

public class Position {
    
    //private variables
    int _id;
    String _datetime;
    String _latitude;
    String _lontitude;
     
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
   
    public String getDatetime(){
        return this._datetime;
    }
     
   
    public void setDatetime(String datetime){
    	this._datetime = datetime;
    }
     
    // getting latitude
    public String getLatitude(){
        return this._latitude;
    }
     
    // setting latitude
    public void setLatitude(String latitude){
        this._latitude = latitude;
    }
     
    // getting phone number
    public String getLontitude(){
        return this._lontitude;
    }
     
    // setting phone number
    public void setLontitude(String lontitude){
        this._lontitude = lontitude;
    }
}

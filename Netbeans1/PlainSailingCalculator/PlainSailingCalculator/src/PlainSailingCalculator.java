
import java.io.*;
import java.lang.Math;

public class PlainSailingCalculator {


  public PlainSailingCalculator() {
    char unit;
    
    
  } //constructor

  public static void main (String args []) {
    double C = 80;
    double speed = 8.0;
    double time = 120;
    int wholeLat = 32;
    double degreeLat = 49.2;
    int wholeLong = 50;
    double degreeLong = 49.9;
//
    System.out.println("distance                = " + distance(speed,time));
    System.out.println("Decimal latitude        = " + dm2decimal(wholeLat,degreeLat));
    System.out.println("Decimal latitude        = " + dm2decimal(wholeLong,degreeLong));
    System.out.println("Departure (p)           = " + p(C,distance(speed,time)));
    System.out.println("Difference Latitude (l) = " + l(C,distance(speed,time)));
    System.out.println("New Latitude            = " + newL(dm2decimal(wholeLat,degreeLat),dm2decimal(0,l(C,distance(speed,time)))));
    System.out.println("New Latitude            = " + mL(dm2decimal(wholeLong,degreeLong),l(C,distance(speed,time))));
  }

  public static double DLo (double l, double p ) {
      return l;
  }
  
  public static double mL (double startDecLat,double l){
      return startDecLat + dm2decimal(0,l);
  }

  public static double l (double C,double distance) {
     return myCos(C) * distance;
  }

  public static double newL (double decimalL, double delta) {
     return decimalL + delta;
  }
  
  public static double p (double C,double distance) {
     return mySin(C) * distance;
  }

  public static double dm2decimal(int lat, double degrees) {
     return lat + degrees / 60; 
  }

  public static void decimal2dm (double dm, int lat, double degrees) {
     lat = 40;
     degrees =  dm - (lat * 60);
  }

  public static double distance (double pspeed, double ptime) {
    return pspeed * ptime / 60;
  }

  public static double mySin (double inVal) {
    double Val = java.lang.Math.sin(inVal * java.lang.Math.PI / 180);
    return Val;
  }

  public static double myCos (double inVal) {
    double Val = java.lang.Math.cos(inVal * java.lang.Math.PI / 180);
    return Val;
  }

  public static double myTan (double inVal) {
    double Val = java.lang.Math.tan(inVal * java.lang.Math.PI / 180);
    return Val;
  }

  private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
    double theta = lon1 - lon2;
    double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
    dist = Math.acos(dist);
    dist = rad2deg(dist);
    dist = dist * 60 * 1.1515;
//    if (unit == "K") {
//      dist = dist * 1.609344;
//    } else if (unit == "N") {
//      dist = dist * 0.8684;
//    }
    return (dist);
  }

/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
/*::  This function converts decimal degrees to radians             :*/
/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
  private double deg2rad(double deg) {
    return (deg * Math.PI / 180.0);
  }

/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
/*::  This function converts radians to decimal degrees             :*/
/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
  private double rad2deg(double rad) {
    return (rad * 180.0 / Math.PI);
  }
}


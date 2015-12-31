package com.example.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.annotation.SuppressLint;

public class HandleJSON {
   private static String Nom = "name";
   private static String Phone = "phone";

   private static String Email = "email";
   private static String Street = "street";

   private static String City = "city";


public static Object reader;
   
   private String urlString = null;

   public volatile boolean parsingComplete = true;
   
   
   public HandleJSON(String url){
      this.urlString = url;
   }
   
   
   public  String getNom(){
      return Nom;
   }
   
   public  String getphone(){
	      return Phone;
	   }
   
   public  String getemail(){
	      return Email;
	   }
   
   public  String getstreet(){
	      return Street;
	   }
   
   public  String getcity(){
	      return City;
	   }
  

   
   // je recupere les infos
   @SuppressLint("NewApi")
   public void readAndParseJSON(String in) {
      try {
         JSONObject reader = new JSONObject(in);

        
         
         Phone = reader.getString("phone");
         
         
         Email = reader.getString("email");
         
         JSONObject street  = reader.getJSONObject("address");
         Street = street.getString("street");
         City=street.getString("city");

         
         Nom = reader.getString("name");
        
         parsingComplete = false;



        } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
        }

   }
   
   
   
   // connexion a l'API REST
   public void fetchJSON(){
      Thread thread = new Thread(new Runnable(){
         @Override
         public void run() {
         try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
         InputStream stream = conn.getInputStream();

      String data = convertStreamToString(stream);

      readAndParseJSON(data);
         stream.close();

         } catch (Exception e) {
            e.printStackTrace();
         }
         }
      });

       thread.start(); 		
   }
   
   
   
   
   static String convertStreamToString(java.io.InputStream is) {
      java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
      return s.hasNext() ? s.next() : "";
   }
}
	
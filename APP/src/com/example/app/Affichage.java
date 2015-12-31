package com.example.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class Affichage extends Activity {
	
	
	
	
	
	String message4;
	   private static final String EXTRA_MESSAGE = null;
	private String url1 = "http://jsonplaceholder.typicode.com/users/";
	   private EditText Nom,phone,email,steet,city;
	   private HandleJSON obj;
	   @Override
	  
	   
	   protected void onCreate(Bundle savedInstanceState) {
	
	      super.onCreate(savedInstanceState);
	      
	      setContentView(R.layout.aff);/////////////////
	      
	      // je recupere l'id
	      Intent intent = getIntent();
	       String message3 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
	       
	       String[] tab1 = message3.split(" ");
	       message3=tab1[0];
	       message4=tab1[1];
	       
	       
	      
		     String[] tab= new String [4];
		      
		     
		    	 
		    	   // j'ajoute l'id a l'url pour acceder seuelemt au bon contact
			      String finalUrl = url1 + message3;
			    
			      obj = new HandleJSON(finalUrl);
			      obj.fetchJSON();
			       
			      //je recupere les info via la classe HandleJson
			      while(obj.parsingComplete);
			     tab[0]="Nom: "+obj.getNom();
			     tab[1]="Telephone: "+obj.getphone();
			     tab[2]="Email: "+obj.getemail();
			     tab[3]="Adresse: \nRue: "+obj.getstreet()+"\nVille: "+obj.getcity();
			   
			    
			   
			     
			     //Recuperation de ListView cree
				 ListView lv = (ListView)findViewById(R.id.listView1); 
				 
				 //creation d'un ArrayAdapter
				 ArrayAdapter arrayadp=new ArrayAdapter(Affichage.this,  android.R.layout.simple_list_item_1, tab);                                       // associer l'adaptateur ˆ la listeView   
		         lv.setAdapter(arrayadp);   
		      
		         
	      
	    
	   }
	   
	   //action bar avec bouton return
	   @Override
		  public boolean onCreateOptionsMenu(Menu menu) {
		    MenuInflater inflater = getMenuInflater();
		    inflater.inflate(R.menu.cctionbar, menu);
		    return super.onCreateOptionsMenu(menu);
		  }
	   @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	        case R.id.action_retour:
	        	 ProgressDialog pDialog = new ProgressDialog(Affichage.this);
		         
	       	 Intent i = new Intent(getApplicationContext(),RechercheS.class)  ; 
	       	
			
			i.putExtra(EXTRA_MESSAGE, message4);
			
			startActivity(i);
            
	        	
	        	
	        	
	        
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }

	 
	}
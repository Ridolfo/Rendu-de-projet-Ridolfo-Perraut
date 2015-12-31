package com.example.app;



import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.Toast;

public class RechercheS extends Activity implements View.OnTouchListener, View.OnClickListener{

		protected static final String EXTRA_MESSAGE2 = null;

	
		String[] tab2= new String [10000];

		ListView lv;  
		int nombre;
		private String url1 = "http://jsonplaceholder.typicode.com/users/";
	   
		private HandleJSON obj;
	   
		String message;

		 
	   
	   
	   
		  protected void onCreate(Bundle savedInstanceState) {
	      
			  super.onCreate(savedInstanceState);
			  setContentView(R.layout.activity_recherche_b);

			  Intent intent = getIntent();
	       
	       // je recup?re de l'autre activity le nombre de contact
			  message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
			  nombre = Integer.parseInt(message); 
	       // Barre de recherche
			  EditText critere1 = (EditText)findViewById(R.id.ecrire); 

		    
		   
		   
		    
	       
	       /////////////////////////////////////////BOUTON et CLICK//////////////////////////////////////////////////////////////////////////////////////////
	       
	       
	       Button buttonsuiv = (Button) findViewById(R.id.button1);
	       buttonsuiv.setOnClickListener(new View.OnClickListener() {
	        	  
	 
	          @Override
	          public void onClick(View v) {
	        	  int envoie=0;
					
	        	  		// recup du texte tapé dans la recherche
	        	  		EditText editText = (EditText) findViewById(R.id.ecrire);
	        	  		String message2 = editText.getText().toString();
	        	  		
	        	  		// si c'est vide 
	        	  		if (message2.equals("")) 
	        	  		{
	        	  			Context context = getApplicationContext();
						    CharSequence text = "le champ est vide";
						    int duration = Toast.LENGTH_SHORT;

						    Toast toast = Toast.makeText(context, text, duration);
						    toast.show();
						    
						    return;
	        	  		}
	      	
				// je regarde si ce qui est tapé est dans le rep
				int a;
				a=0;
				for(int i=0;i<nombre;i++)
				{
					if(message2.equals(tab2[i]))
					{
						envoie=i+1;
						a=1;
					}
						
				}
				
				message2=""+envoie;
				
				// Si le mot tapee n'est pas dans le repertoire.
				if(a == 0)
				{
					Context context = getApplicationContext();
				    CharSequence text = "Ce contact n'est pas dans le repertoire";
				    int duration = Toast.LENGTH_SHORT;

				    Toast toast = Toast.makeText(context, text, duration);
				    toast.show();
				    return;
				}
				
					// envoie le numero id a l'autre activite, ainsi que le nombre de contact.
				   Intent intent = new Intent(RechercheS.this, Affichage.class);
				   message2=message2+" " +message;
				   intent.putExtra(EXTRA_MESSAGE2, message2);
				   startActivity(intent);
	        	  
	        	  
	        	  
	        	  
	            }
	          });
/////////////////////////////////////////FIN BOUTON et CLICK///////////////////////////////////////////////////////////////////////////////////////////////
	          

		   critere1.addTextChangedListener(textWatcher);  // si on tape*/

		     
				   
		    
		    
		            
		            
		
		    // je recupere tous les noms et je les mets dans un tableau, et ensuite les affiche dans une listeview
		     String s ;
	     String[] tab= new String [nombre];
	      
	      for(int i=1;i<=nombre;i++)
	      {
	    	 
	    	  s = ""+i; 
		      String finalUrl = url1 + s;
		    
		      obj = new HandleJSON(finalUrl);
		      obj.fetchJSON();
		       
		      while(obj.parsingComplete);
		     tab[i-1]=obj.getNom();
		     tab2[i-1]=tab[i-1];
	      }
	      
	      
	      
	     
	   
		
		 //Recuperation de ListView cree
		 lv=(ListView)findViewById(R.id.listView1); 
		 
		 //creattion d'un ArrayAdapter
		 ArrayAdapter arrayadp=new ArrayAdapter(RechercheS.this,  android.R.layout.simple_list_item_1, tab);                                       // associer l'adaptateur à la listeView   
         lv.setAdapter(arrayadp);   
	     
         lv.setOnItemClickListener(new OnItemClickListener() {
		        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {              
		    
		           Intent i = new Intent(getApplicationContext(),Affichage.class)  ; 
		           id=id+1;
		            String message5= ""+id+" "+ message;
		            i.putExtra(EXTRA_MESSAGE2, message5);
		            startActivity(i);
		            //Toast.makeText(TopNewsActivity.this, "ID '" + o.get("id") + "' was clicked.", Toast.LENGTH_LONG).show(); 

		        }
		    });
	    
	   }
	   
		  //quand on tape dans la barre de recherche
	   private TextWatcher textWatcher = new TextWatcher() {

		    @Override
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		     
		    }
				
		    @Override
		    public void beforeTextChanged(CharSequence s, int start, int count,
		      int after) {
		  
		    }
		  
		    @Override
		    public void afterTextChanged(Editable s) {
		  
		    }
		  };

		// actionbar avec bouton retour
		  @Override
		  public boolean onCreateOptionsMenu(Menu menu) {
		    MenuInflater inflater = getMenuInflater();
		   
		    inflater.inflate(R.menu.cctionbar, menu);
		    return super.onCreateOptionsMenu(menu);
		    
		  }
	 	  
		  // si on clique sur le bouton retour de l'actionbar
		  @Override
		    public boolean onOptionsItemSelected(MenuItem item) {
		        switch (item.getItemId()) {
		        case R.id.action_retour:
		        	
		        	
		        	 Intent i = new Intent(getApplicationContext(),MainActivity.class)  ; 
               
                    startActivity(i);
		        	
		        	
		        default:
		            return super.onOptionsItemSelected(item);
		        }
		    }
		        
		    
		  
		  

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 
				}


				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					return false;
				}
				}
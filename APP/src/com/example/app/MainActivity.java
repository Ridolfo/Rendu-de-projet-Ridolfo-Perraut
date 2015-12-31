package com.example.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.view.View.OnTouchListener;
import android.view.View.OnClickListener;
import android.view.MotionEvent;
import android.view.View;




public class MainActivity extends Activity implements View.OnTouchListener, View.OnClickListener {
	
	protected static final String EXTRA_MESSAGE = null;
	String ok;
	Button envoyer1 = null;
	 int nombre;
	private ProgressDialog pDialog;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
        // asynctask qui regarde combien il y a de contact sur l'API
        new DLTask().execute("http://jsonplaceholder.typicode.com/users");

        envoyer1 = (Button)findViewById(R.id.button1);
       
        envoyer1.setOnClickListener(RechercheSimple);
        
        
     
    }
	
	
// detecte quand on clique
	 private OnClickListener RechercheSimple = new OnClickListener() {

			@Override
			
			public void onClick(View v) {
				 ProgressDialog pDialog = new ProgressDialog(MainActivity.this);
		          
				// si on clique je start une autre activite en lui donnant le nombre de contact dans l'api.
				String strI = "" +nombre;
				
				Intent i = new Intent(MainActivity.this, RechercheS.class);
				i.putExtra(EXTRA_MESSAGE, strI);
				//Toast.makeText(getApplicationContext(),"Vous avez 10 contactes", Toast.LENGTH_SHORT).show();
				
				startActivity(i);
			
			}
			
	 
	 };
	 
	
			
	 
	 
		
		  // parcours du json pour savoir combien d'id il y a de contact (variable nombre)
		  private class DLTask extends AsyncTask<String, Void, String>{
			  
			  // bar de chargement
			  protected void onPreExecute() {
		          super.onPreExecute();
		          // Showing progress dialog
		          pDialog = new ProgressDialog(MainActivity.this);
		          pDialog.setMessage("TÉLÉCHARGEMENT DES DONNEES WEB...");
		          pDialog.setCancelable(false);
		          pDialog.show();

		      }
			  
			  
		        @Override
		        protected String doInBackground(String... urls) {
		            try {
		                return downloadUrl(urls[0]);
		            } catch (IOException e) {
		                return "Unable to retrieve web page. URL may be invalid.";
		            }
		        }
		        @Override
		        protected void onPostExecute(String result) {
		        	ok=result;
		        	char[] chaine = result.toCharArray();
		        	 nombre=0;
			            for(int i=0;i<(chaine.length)-3;i++)
			            {
			            	if(chaine[i]=='"' && chaine[i+1]=='i' && chaine[i+2]=='d' && chaine[i+3]=='"' )
			            	{nombre++;}
			            }
		        	 pDialog.dismiss();
		        	 String a;
		        	 a=""+nombre;
		        	 Toast.makeText(getApplicationContext(),a, Toast.LENGTH_SHORT).show();

		        }
		    

		    private String downloadUrl(String myurl) throws IOException {
		        InputStream is = null;

		        try {
		            URL url = new URL(myurl);
		            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		            conn.setRequestMethod("GET");
		            conn.connect();
		            int response = conn.getResponseCode();
		            is = conn.getInputStream();

		            String contentAsString= readIt(is,10000);
		            return contentAsString;
		        } finally {
		            if(is != null) {
		                is.close();
		            }
		        }
		    }

		    public String readIt(InputStream stream, int len) throws IOException {
		        Reader reader = null;
		        reader = new InputStreamReader(stream, "UTF-8");
		        char[] buffer = new char[len];
		        reader.read(buffer);
		        return new String(buffer);
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

	
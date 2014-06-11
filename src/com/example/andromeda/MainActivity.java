/*
RTS game
Created by: Ivan Orehovec
*/
package com.example.andromeda;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.graphics.Color;
import android.app.AlertDialog; 
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Fullscreen
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
	   // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	    //                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    
		setContentView(R.layout.activity_main);
		
		//makiva ikonicu
		getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent))); 
		//makiva naslov
		setTitle("");
		
		Button Play= (Button)findViewById(R.id.start); Button Settings= (Button)findViewById(R.id.settings); Button Store= (Button)findViewById(R.id.store); Button About= (Button)findViewById(R.id.about);
		Play.setTextColor(Color.parseColor("white"));  Settings.setTextColor(Color.parseColor("white")); Store.setTextColor(Color.parseColor("white"));  About.setTextColor(Color.parseColor("white"));
		
		load();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // ako se klikne na ikonicu desno za resurse
	    
	    case R.id.go_to_resources:{
	    	
	    	AlertDialog alertDialog = new AlertDialog.Builder(this).create();
	    	alertDialog.setTitle("Resources");
	    	
	    	// ucitava
	    	int len = 100;
			String readString="";
			String PrijasnjiD="";
			String SadasnjiD="";
			
			try{
			    FileInputStream fIn = openFileInput("resources.txt");
			    InputStreamReader isr = new InputStreamReader(fIn);
			    char[] inputBuffer = new char[len];
			    isr.read(inputBuffer);

			    readString = new String(inputBuffer);
			 }
			catch(IOException e){ }
			
			try{
			    FileInputStream fIn = openFileInput("vreme.txt");
			    InputStreamReader isr = new InputStreamReader(fIn);
			    char[] inputBuffer = new char[50];
			    isr.read(inputBuffer);

			    PrijasnjiD = new String(inputBuffer);
			    Vreme v = new Vreme();
			    SadasnjiD = v.vrati();
			 }
			catch(IOException e){ }
			
			
			//ispisuje
	    	Resources res = new Resources(readString);
	    	Time tim = new Time(PrijasnjiD, SadasnjiD);
	    	int razlika=tim.getT();
	    	int pow = res.getPower() + res.dgetPower()*razlika;
	    	int tit= res.getTitanium() + res.dgetTitanium()*razlika;
	    	int alu= res.getAluminium() + res.dgetAluminium()*razlika;
	    	int c=res.getC() + res.getC()*razlika;
	    	int dm= res.getDarkM() + res.dgetDarkM()*razlika;
	    	
	    	//prikaz
	    	alertDialog.setMessage(pow+ " Power +" + res.dgetPower() + "\n" +tit+ " Titanium +" +res.dgetTitanium() + "\n" +alu + " Aluminium +" +res.dgetAluminium() +"\n" + c+" Carbon +" + res.dgetC() + "\n" +dm + " Dark Matter +" + res.dgetDarkM());
	    	alertDialog.show();
	    	// update vremena
	    	try{				
				Vreme v = new Vreme();
				FileOutputStream fOut = openFileOutput("vreme.txt",MODE_PRIVATE);
				OutputStreamWriter osw = new OutputStreamWriter(fOut);
				osw.write(v.vrati());
				osw.flush();
				osw.close();
				}
			catch(IOException ioe){	}
	    	//update resursa
	    	try{

				String text = "";
				text += Integer.toString(pow); text+=",";
				text += Integer.toString(tit); text+=",";
				text += Integer.toString(alu); text+=",";
				text += Integer.toString(c); text+=",";
				text += Integer.toString(dm); text+=",";
				text += Integer.toString(res.dgetPower()); text+=",";
				text += Integer.toString(res.dgetTitanium()); text+=",";
				text += Integer.toString(res.dgetAluminium()); text+=",";
				text += Integer.toString(res.dgetC()); text+=",";
				text += Integer.toString(res.dgetDarkM()); 
				
				FileOutputStream fOut = openFileOutput("resources.txt",MODE_PRIVATE);
				OutputStreamWriter osw = new OutputStreamWriter(fOut);
				osw.write(text);
				osw.flush();
				osw.close();
				}
			catch(IOException ioe){	}
	    }
	      break;
	      
	    case R.id.go_to_account:
	      {
	    	  AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		    	alertDialog.setTitle("Account");
		    	alertDialog.setMessage("Created by : Ivan Orehovec");
		    	alertDialog.show();
	      }
	      break;

	    default:
	      break;
	    }

	    return true;
	  } 


	public void start_to_play ( View view){		
		Intent intent = new Intent( this, Start_to_play.class);
		startActivity(intent);
	}
	
	public void load(){
		// provjera za load - ako ne postoji resources -> napravi s textom 1. 5 vrijednosti, 2. 5 inkrementi po min
				File file = this.getFileStreamPath("resources.txt");
				if(!file.exists()){ // ako ga nema ga napravi
					file = new File (this.getFilesDir(), "resources.txt");
					try{

						String text = "100,10,10,0,0,2,0,0,0,0";
						//"centar(1),shipyard,solarna el, nuklearna el, rudnik titanija, tv. alu, physics lab, stit"
						FileOutputStream fOut = openFileOutput("resources.txt",MODE_PRIVATE);
						OutputStreamWriter osw = new OutputStreamWriter(fOut);
						osw.write(text);
						osw.flush();
						osw.close();
						}
					catch(IOException ioe){	}
				}
				// provjera za load - ako ne postoji username
					file = this.getFileStreamPath("user.txt");
						if(!file.exists()){ // ako ga nema ga napravi 
							//dovrsiti
						}
						
				//provjera za vojsku
					file = this.getFileStreamPath("vojska.txt");
					if(!file.exists()){ // ako ga nema ga napravi
						file = new File (this.getFilesDir(), "vojska.txt");
						
						try{
							String text = "0,0,0,0,0,0,0";
							//"malimrav,v.mrav,battleship,armoredbatt.,enterprise,destroyer,erneship"
							FileOutputStream fOut = openFileOutput("vojska.txt",MODE_PRIVATE);
							OutputStreamWriter osw = new OutputStreamWriter(fOut);
							osw.write(text);
							osw.flush();
							osw.close();
							}
						catch(IOException ioe){	}
					}
					//provjera za gradevine
					file = this.getFileStreamPath("gradevine.txt");
					if(!file.exists()){ // ako ga nema ga napravi
						file = new File (this.getFilesDir(), "gradevine.txt");
						
						try{
							String text = "1,0,0,0,0,0,0,0";
							//"centar(1),shipyard,solarna el, nuklearna el, rudnik titanija, tv. alu, physics lab, stit"
							FileOutputStream fOut = openFileOutput("gradevine.txt",MODE_PRIVATE);
							OutputStreamWriter osw = new OutputStreamWriter(fOut);
							osw.write(text);
							osw.flush();
							osw.close();
							}
						catch(IOException ioe){	}
					}
					//provjera za library
					file = this.getFileStreamPath("library.txt");
					if(!file.exists()){ // ako ga nema ga napravi
						file = new File (this.getFilesDir(), "library.txt");
						
						try{
							String text = "0,0,0,0,0,0,0,0,0,0,0";
							//POREDU
							//el.E -> 0:solarnaE,1:laseri,2:kompjutori
							//fizika -> 3:baterije,4:plazma,5:fuzija,6:gravitoni,7:hiperpogon, 11:astrofizika
							//inžinjerstvo (skupo->resursi++ i treba za brodove)
							//-> 8:titanij, 9:alu, 10:ugljik
							FileOutputStream fOut = openFileOutput("library.txt",MODE_PRIVATE);
							OutputStreamWriter osw = new OutputStreamWriter(fOut);
							osw.write(text);
							osw.flush();
							osw.close();
							}
						catch(IOException ioe){	}
					}
					//provjera za vreme
					file = this.getFileStreamPath("vreme.txt");
					if(!file.exists()){ // ako ga nema ga napravi
						file = new File (this.getFilesDir(), "vreme.txt");
						
						try{				
							Vreme v = new Vreme();
							FileOutputStream fOut = openFileOutput("vreme.txt",MODE_PRIVATE);
							OutputStreamWriter osw = new OutputStreamWriter(fOut);
							osw.write(v.vrati());
							osw.flush();
							osw.close();
							}
						catch(IOException ioe){	}
					}
	}
}

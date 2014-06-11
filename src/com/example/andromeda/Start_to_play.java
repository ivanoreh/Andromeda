package com.example.andromeda;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Start_to_play extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_to_play);
		//skriva ikonicu
		getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
		
		Button Build = (Button) findViewById(R.id.Build);
		Build.setTextColor(Color.parseColor("white"));
		
		Vreme vr = new Vreme();
		String text = vr.vrati();
		TextView v = (TextView) findViewById(R.id.vreme);
		v.setText(text);
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start_to_play, menu);
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
	
	public void Gradi( View view){		
		Intent intent = new Intent( this, Build.class);
		startActivity(intent);
	}
}

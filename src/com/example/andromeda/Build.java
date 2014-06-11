package com.example.andromeda;

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
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class Build extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_build);
		// Show the Up button in the action bar.
		setupActionBar();
		//skriva ikonicu
		getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
		
		TextView Base = (TextView) findViewById(R.id.base); Base.setText("BASE"); Base.setTextColor(Color.parseColor("white"));
		TextView Shipy = (TextView) findViewById(R.id.shipyard); Shipy.setText("SHIPYARD"); Shipy.setTextColor(Color.parseColor("white"));
		TextView Solar = (TextView) findViewById(R.id.Solar); Solar.setText("SOLAR"); Solar.setTextColor(Color.parseColor("white"));
		TextView Nukl = (TextView) findViewById(R.id.nuclear); Nukl.setTextColor(Color.parseColor("white"));
		TextView Titan = (TextView) findViewById(R.id.titanium); Titan.setTextColor(Color.parseColor("white"));
		TextView Alu = (TextView) findViewById(R.id.aluminium); Alu.setTextColor(Color.parseColor("white"));
		
		String readString="";
		
		try{
		    FileInputStream fIn = openFileInput("gradevine.txt");
		    InputStreamReader isr = new InputStreamReader(fIn);
		    char[] inputBuffer = new char[100];
		    isr.read(inputBuffer);

		    readString = new String(inputBuffer);
		 }
		catch(IOException e){ }
		
		Gradevine grad = new Gradevine(readString);
		
		TextView l1 = (TextView) findViewById(R.id.lvl1); l1.setText(Integer.toString(grad.getCentar())); l1.setTextColor(Color.parseColor("white"));
		TextView l2 = (TextView) findViewById(R.id.lvl2); l2.setText(Integer.toString(grad.getShipyard())); l2.setTextColor(Color.parseColor("white"));
		TextView l3 = (TextView) findViewById(R.id.lvl3); l3.setText(Integer.toString(grad.getSolarnaE())); l3.setTextColor(Color.parseColor("white"));
		TextView l4 = (TextView) findViewById(R.id.lvl4); l4.setText(Integer.toString(grad.getNuklearka())); l4.setTextColor(Color.parseColor("white"));
		TextView l5 = (TextView) findViewById(R.id.lvl5); l5.setText(Integer.toString(grad.getRudnikTitanija())); l5.setTextColor(Color.parseColor("white"));
		TextView l6 = (TextView) findViewById(R.id.lvl6); l6.setText(Integer.toString(grad.getTvornicaAlu())); l6.setTextColor(Color.parseColor("white"));
		
	}

	
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.build, menu);
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

	
 public void titan_upgrade( View view){
	 Intent intent = new Intent( this, Rudnik_titana.class);
		startActivity(intent);
 }
}

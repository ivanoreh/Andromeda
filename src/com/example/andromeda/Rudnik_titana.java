package com.example.andromeda;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Rudnik_titana extends Activity {

	int pow,pow1,c,c1,tit,tit1,alu,alu1,dm,dm1;
	int dp,dc,dt,da,ddm;
	int[] Bonus = new int[100];
	String[] Price = new String[100];
	int lvl =0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rudnik_titana);
		//skriva ikonicu
				getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
				
				
		
		Price[0] = "50,0,0,0,0,0,0,0,0,0";
		Price[1] = "150,0,0,0,0,0,0,0,0,0";
		Price[2] = "250,0,0,0,0,0,0,0,0,0";
		Price[3] = "400,50,0,0,0,0,0,0,0,0";
		Price[4] = "600,140,30,10,0,0,0,0,0,0";
		Price[5] = "1500,300,50,20,0,0,0,0,0,0";
		Price[6] = "3000,650,100,40,0,0,0,0,0,0";
		Price[7] = "6000,1100,110,60,0,0,0,0,0,0";
		Price[8] = "600,140,30,10,0,0,0,0,0,0";
		Price[9] = "600,140,30,10,0,0,0,0,0,0";
		Price[10] = "600,140,30,10,0,0,0,0,0,0";
		//......
		
		Bonus[0] = 2;
		for( int i=1; i<100; i++) Bonus[i] = Bonus[i-1]*2;
		
		//èita tvoje resurse
		String readString="";
		try{
		    FileInputStream fIn = openFileInput("resources.txt");
		    InputStreamReader isr = new InputStreamReader(fIn);
		    char[] inputBuffer = new char[30];
		    isr.read(inputBuffer);

		    readString = new String(inputBuffer);
		 }
		catch(IOException e){ }
		
		Resources res = new Resources(readString);
		 pow = res.getPower() + res.dgetPower();
    	tit= res.getTitanium() + res.dgetTitanium();
    	 alu= res.getAluminium() + res.dgetAluminium();
    	 c=res.getC() + res.getC();
    	 dm= res.getDarkM() + res.dgetDarkM();
    	
    	Resources res1 = new Resources(Price[0]);
		  pow1 = res1.getPower() ;
    	  tit1= res1.getTitanium() ;
    	  alu1= res1.getAluminium();
    	  c1=res1.getC() ;
    	  dm1= res1.getDarkM();
    	  dp = res.dgetPower();
    	  dt = res.dgetTitanium();
    	  da = res.dgetAluminium();
    	  dc = res.dgetC();
    	  ddm = res.dgetDarkM();
    	
		final TextView cost = (TextView) findViewById(R.id.titan_cost);
		final TextView have = (TextView) findViewById(R.id.titan_your);
		final TextView bonus = (TextView) findViewById(R.id.titan_bonus);
		 
		have.setText(pow+ " Power +" + dp + "\n" +tit+ " Titanium +" +dt + "\n" +alu + " Aluminium +" +da +"\n" + c+" Carbon +" + dc + "\n" +dm + " Dark Matter +" + dm);
		cost.setText(pow1+ " Power\n" + tit1+" Titanium\n" + alu1 + " Aluminium\n" + c1+ " Carbon\n" + dm1+ " Dark Matter");
		bonus.setText("Bonus:\n+" + Bonus[lvl] +"Titanium/min");
		
		// Dok pokuša upgrejdat
		Button confirm = (Button) findViewById(R.id.titan_confirm);
			
        confirm.setOnClickListener(new View.OnClickListener() {
        	
        	@Override
            public void onClick(View v) {
                
            	if( pow <pow1 || tit < tit1 || alu < alu1 || c<c1 || dm < dm1){ 
            		AlertDialog alertDialog = new AlertDialog.Builder(Rudnik_titana.this).create(); //Read Update
                    alertDialog.setTitle("ERROR!");
                    alertDialog.setMessage("You don't have enaugh resources!");
                    alertDialog.show();
            	}
            	else{
            		pow -=pow1;
            		dm -=dm1;
            		tit-=tit1;
            		alu-=alu1;
            		c-=c1;
            		dt+=Bonus[lvl];
            		lvl++;
            		Resources r = new Resources(Price[lvl]);
            		 pow1 = r.getPower() ;
            		 tit1= r.getTitanium() ;
            		 alu1= r.getAluminium();
            		 c1=r.getC() ;
            		 dm1= r.getDarkM();
            		 
            		have.setText(pow+ " Power +" + dp + "\n" +tit+ " Titanium +" +dt + "\n" +alu + " Aluminium +" +da +"\n" + c+" Carbon +" + dc + "\n" +dm + " Dark Matter +" + dm);
            		cost.setText(pow1+ " Power\n" + tit1+" Titanium\n" + alu1 + " Aluminium\n" + c1+ " Carbon\n" + dm1+ " Dark Matter");
            		bonus.setText("Bonus:\n+" + Bonus[lvl] +"Titanium/min");
            		
            		// update resursa
            		try{

        				String text = "";
        				text += Integer.toString(pow); text+=",";
        				text += Integer.toString(tit); text+=",";
        				text += Integer.toString(alu); text+=",";
        				text += Integer.toString(c); text+=",";
        				text += Integer.toString(dm); text+=",";
        				text += Integer.toString(dp); text+=",";
        				text += Integer.toString(dt); text+=",";
        				text += Integer.toString(da); text+=",";
        				text += Integer.toString(dc); text+=",";
        				text += Integer.toString(ddm); 
        				
        				FileOutputStream fOut = openFileOutput("resources.txt",MODE_PRIVATE);
        				OutputStreamWriter osw = new OutputStreamWriter(fOut);
        				osw.write(text);
        				osw.flush();
        				osw.close();
        				}
        			catch(IOException ioe){	}
            		
            		//success
            		AlertDialog alertDialog = new AlertDialog.Builder(Rudnik_titana.this).create(); //Read Update
                    alertDialog.setTitle("SUCCESS");
                    alertDialog.setMessage("Congratulations! \nYou have successfully upgraded Titanium mine to level " + lvl);
                    alertDialog.show();
                    
                    // update lvl rudnika
                    try{

                    	// ucitavam stare
        				String gradev="";
       
        				try{
        				    FileInputStream fIn = openFileInput("gradevine.txt");
        				    InputStreamReader isr = new InputStreamReader(fIn);
        				    char[] inputBuffer = new char[100];
        				    isr.read(inputBuffer);

        				   gradev= new String(inputBuffer);
        				 }
        				catch(IOException e){ }
        				
        				Gradevine grad = new Gradevine(gradev);
        				
        				
        				// pisem nove
        				/////////////PAZI!! TU JE TITANIJ pod 4 ( peti je ) za druge promjeniti
        				String text = "";
        				text += Integer.toString(grad.gradevina[0]); text+=",";
        				text += Integer.toString(grad.gradevina[1]); text+=",";
        				text += Integer.toString(grad.gradevina[2]); text+=",";
        				text += Integer.toString(grad.gradevina[3]); text+=",";
        				text += Integer.toString(lvl); text+=",";
        				text += Integer.toString(grad.gradevina[5]); text+=",";
        				text += Integer.toString(grad.gradevina[6]); text+=",";
        				text += Integer.toString(grad.gradevina[7]); 
        				FileOutputStream fOut = openFileOutput("gradevine.txt",MODE_PRIVATE);
        				OutputStreamWriter osw = new OutputStreamWriter(fOut);
        				osw.write(text);
        				osw.flush();
        				osw.close();
        				}
                    catch(IOException ioe){	}
            	}
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rudnik_titana, menu);
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
	
}

package com.example.andromeda;

import android.content.Context;

public class Library {

	private int [] lib = new int[15];
	//POREDU
	//el.E -> 0:solarnaE,1:laseri,2:kompjutori
	//fizika -> 3:baterije,4:plazma,5:fuzija,6:gravitoni,7:hiperpogon, 11:astrofizika
	//inžinjerstvo (skupo->resursi++ i treba za brodove)
	//-> 8:titanij, 9:alu, 10:ugljik
	
	public Library(String readString, Context context) {
		// MOJ KOD IZ C++ odvaja  intove od zareza
				int br=0, leng=readString.length(), tren=0;
				String nov= "";
				
				while(tren < leng){
					if(readString.charAt(tren) == ','){
						lib[br] = Integer.parseInt(nov);
						br++;
						tren++;
						nov="";
					}
					nov+=readString.charAt(tren);
					tren++;
				}
				lib[br] = Integer.parseInt(nov);
	}
	
	public int getSolarnaE(){return lib[0];}
	public int getLaseri(){return lib[1];}
	public int getKompjutori(){return lib[2];}
	public int getBaterije(){return lib[3];}
	public int getPlazma(){return lib[4];}
	public int getFuzija(){return lib[5];}
	public int getGravitons(){return lib[6];}
	public int getHiperpogon(){return lib[7];}
	public int getTitan(){return lib[8];}
	public int getAlu(){return lib[9];}
	public int getC(){return lib[10];}
	public int getAstrofizika(){return lib[11];}
}

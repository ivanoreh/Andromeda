package com.example.andromeda;

public class Resources 
{
	public int[] values = new int[40]; // power,titanij, alu, c,dm
	// na 9 je ddm
	// 10 lvl centra
	// 11 shipyard, 12 solarna el, 13 nuklearna el, 14 rudnik titanija, 15 tv. alu, 16 physics lab, 17 stit
	
	public Resources(String readString) {
				
		// MOJ KOD IZ C++ odvaja  intove od zareza
		int br=0, leng=readString.length(), tren=0;
		String nov= "";
		
		while(tren < leng){
			if(readString.charAt(tren) == ','){
				values[br] = Integer.parseInt(nov);
				br++;
				tren++;
				nov="";
			}
			nov+=readString.charAt(tren);
			tren++;
		}
		//values[br] = Integer.parseInt(nov);
	}
	
	public int getPower(){
		return values[0];
	}
	public int getTitanium(){
		return values[1];
	}
	public int getAluminium(){
		return values[2];
	}
	public int getC(){
		return values[3];
	}
	public int getDarkM(){
		return values[4];
	}
	public int dgetPower(){
		return values[5];
	}
	public int dgetTitanium(){
		return values[6];
	}
	public int dgetAluminium(){
		return values[7];
	}
	public int dgetC(){
		return values[8];
	}
	public int dgetDarkM(){
		return values[9];
	}
}

package com.example.andromeda;

public class Gradevine {

	public int[] gradevina = new int[15];
	
	public Gradevine(String readString) {
	
	// MOJ KOD IZ C++ odvaja  intove od zareza
			int br=0, leng=readString.length(), tren=0;
			String nov= "";
			
			while(tren < leng){
				if(readString.charAt(tren) == ','){
					gradevina[br] = Integer.parseInt(nov);
					br++;
					tren++;
					nov="";
				}
				nov+=readString.charAt(tren);
				tren++;
			}
		
		
	}
	
	public int getCentar(){
		return gradevina[0];
	}
	public int getShipyard(){
		return gradevina[1];
	}
	public int getSolarnaE(){
		return gradevina[2];
	}
	public int getNuklearka(){
		return gradevina[3];
	}
	public int getRudnikTitanija(){
		return gradevina[4];
	}
	public int getTvornicaAlu(){
		return gradevina[5];
	}
	public int getPhisycsLab(){
		return gradevina[6];
	}
	public int getStit(){
		return gradevina[7];
	}
}

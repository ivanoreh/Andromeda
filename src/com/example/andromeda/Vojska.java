package com.example.andromeda;

public class Vojska {
	
	private int[] vojska = new int[15];
	
	public Vojska(String readString) {

		// MOJ KOD IZ C++ odvaja  intove od zareza
				int br=0, leng=readString.length(), tren=0;
				String nov= "";
				
				while(tren < leng){
					if(readString.charAt(tren) == ','){
						vojska[br] = Integer.parseInt(nov);
						br++;
						tren++;
						nov="";
					}
					nov+=readString.charAt(tren);
					tren++;
				}
				vojska[br] = Integer.parseInt(nov);
	}
	public int getMaliMrav(){
		return vojska[0];
	}
	public int getVelikiMrav(){
		return vojska[1];
	}
	public int getBattleship(){
		return vojska[2];
	}
	public int getArmoredBattleship(){
		return vojska[3];
	}
	public int getEnterprise(){
		return vojska[4];
	}
	public int getDestroyer(){
		return vojska[5];
	}
	public int getErneship(){
		return vojska[6];
	}
	
}

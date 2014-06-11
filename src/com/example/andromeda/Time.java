package com.example.andromeda;

public class Time {
	
	int t=0;
	int l1,l2;
	int[] d = new int[6];
	int[] d2 = new int[6];
	
	public Time(String poc, String kraj) {
		 l1 = poc.length(); 
		 l2 = kraj.length();
		
		parse(poc, kraj);
		
		t+=(d2[0]-d[0]) *5535680;
		t+=(d2[1]-d[1]) *44640;
		t+=(d2[2]-d[2]) *1440;
		t+=(d2[3]-d[3]) *60;
		t+=(d2[4]-d[4]) *1;
		
	}
	
	private void parse(String poc, String kraj){
		poc+="K"; kraj+="K";
		int br=0, zareza=0, tren=0;
		String nov= "";
		
		while(zareza < 5){
			if(poc.charAt(tren) == ','){
				d[br] = Integer.parseInt(nov);
				br++;
				tren++;
				nov="";
				zareza++;
			}
			nov+=poc.charAt(tren);
			tren++;
		}
		
		 br=0; zareza=0; tren=0;
		 nov= "";
		while(zareza < 5){
			if(kraj.charAt(tren) == ','){
				d2[br] = Integer.parseInt(nov);
				br++;
				tren++;
				nov="";
				zareza++;
			}
			nov+=kraj.charAt(tren);
			tren++;
		}

	}
	public int getT(){
		return t;
	}

}

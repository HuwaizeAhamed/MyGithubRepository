package com.java.features17;

public class RecordFeature {
	//getter,contructors,parameter constructors,hashcode,equals,tostring
	public record people(String name,int id) {}

	public static void main(String[] args) {
		
		people p1=new people("ajay",78);
		people p3=new people("ajay",14);
		people p2=new people("guru",12);

		String ps1=p1.id()+" "+p1.name();
		String ps2=p2.id()+" "+p2.name();
		String ps3=p3.id()+" "+p3.name();
		
		System.out.println(ps1+" \n"+ps2+" \n"+ps3);
		
		boolean c=p1.equals(p3);
		System.out.println(c);
	}

}

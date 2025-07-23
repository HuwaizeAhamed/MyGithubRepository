package com.java.features17;

import java.util.ArrayList;

//Switch expressions
public class Features {

	public static void main(String[] args) {
		
		String d="abc";
		String f="alpha";
		Object h=new String("me");
		
		var c= new ArrayList<>();
		c.add(10);
		c.add("abc");
				
		Object r=switch(d) {
		case "def" -> r=new String("adfg") ;
		case "abc" -> r=new String("bfgf"); 
		case "fgh" -> r=new String("hfytd"); 
		default -> r=new String("kjshdj");
		};
		
		String e=switch(f) {
		case "beta" : yield "beta1";
		case "alpha" : yield "alpha1";
		case "gamma" : yield "gamma1";
		default : yield "nothing";
		};
		
		switch(h) {  //21 jdk
		case String s->System.out.println("string");
		case Integer i-> System.out.println("integer");
		default ->System.out.println("0");
		}
		
		if(h instanceof String s1) {
			System.out.println(s1);
		}
		
		System.out.println(r);
		System.out.println(c);
		System.out.println(e);
		
		String multiline=""" 
				||	 || ||    ||  
				||	 || ||    ||
				||=======|| ||    || 
				||	 ||  ||  ||
				||	 ||   |||| 
				 """;
		
		System.out.println(multiline);
		
		//var - Local Variable Type inference
		//strip method ,isBlank , lines(),leading spaces,trailing spaces
		//sealed Classes
		//records
		//Text Blocks
		//String Templates
		//Pattern Matching instanceof
		//jdk21 sequenced collections , virtual threads
	}

}

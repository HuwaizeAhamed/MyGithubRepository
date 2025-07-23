package com.java.features8;


//Static and default methods

public interface IFeatures {

	static void go() {
		System.out.println("static");
	}

	default void run() {
		System.out.println("Run");
	}

}

class I8 implements IFeatures {

	static void go() {
		System.out.println("Static");
	}

	public static void main(String[] args) {
		I8 i = new I8();

		I8.go();
		i.run();
		IFeatures.go();

	}
	
	//Method Reference
	//Functional Interface
	//Lambda Expressions
	//Stream API
	//Optional Classes
	//DateTimeAPI
	//Comparable & Comparator

}

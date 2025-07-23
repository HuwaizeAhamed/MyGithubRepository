package com.java.features17;

public class SealedFeature {
	public void run() {
		System.out.println("class");
	}

	public static void main(String[] args) {

		animal a = new animal();
		a.go();
		dog d = new dog();
		d.go();
		cat c = new cat();
		c.go();
		SealedFeature s = new SealedFeature();
		s.run();

	}

}

sealed class animal permits dog, cat {
	public void go() {
		System.out.println("animal");
	}
}

final class dog extends animal {
	@Override
	public void go() {
		System.out.println("dog");
	}

}

non-sealed class cat extends animal {
	@Override
	public void go() {
		System.out.println("cat");
	}

}
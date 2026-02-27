package com.tnsif.polymorphism;

//same method with same paratemeters

class AnimalClass{
	void sound() {
		System.out.println("Animals can make sound");
	}
}


class Lion extends AnimalClass{
	void sound() {
		System.out.println("Lion roars......");
	}
}

public class MethodOverridingDemo {

	public static void main(String[] args) {
	
		AnimalClass ac = new AnimalClass();
		ac.sound();
		
		
		/*
		 * Lion l = new Lion(); l.sound();
		 * 
		
		 */
		
		AnimalClass acl = new Lion();
		acl.sound();
	}

}

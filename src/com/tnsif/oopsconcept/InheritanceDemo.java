package com.tnsif.oopsconcept;

public class InheritanceDemo {
	void dog() {
		System.out.println("hey, its a dog");
	}
}
class Animal extends InheritanceDemo {
	void cat() {
		System.out.println("hey, its a kitten");
	}
}


//multilevel

class GrandParent {
	
	void gp() {
		System.out.println("Grand Parent Class");
	}
	
}


class Parent extends GrandParent{
	
	void p() {
		System.out.println("Parent Class");
	}
	
}

class Child1 extends Parent{
	
	void c() {
		System.out.println("Child Class");
	}
	
}

//Hierarical inheritance

class Subject{
	void allSubject() {
		System.out.println("States all subjects");
	}
}

class Maths extends Subject{
	void onlyMath() {
		System.out.println("shows only math");
	}
}

class Science extends Subject{
	void onlyScience() {
		System.out.println("shows only Science");
	}
}

//mulitple inheritance


/*
 * class A{ //Parent 1
 * 
 * }
 * 
 * class B{ //parent 2
 * 
 * }
 * 
 * class C extends A,B {
 * 
 * }
 */

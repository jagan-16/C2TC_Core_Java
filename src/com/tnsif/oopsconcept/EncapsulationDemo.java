package com.tnsif.oopsconcept;

//IPL Team - CSK, RCB, MI

public class EncapsulationDemo {
	
	//instance variable
	private String name;
	private int age;
	private int jersyNo;
	
	
	//getters and setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getJersyNo() {
		return jersyNo;
	}

	public void setJersyNo(int jersyNo) {
		this.jersyNo = jersyNo;
	}

	@Override
	public String toString() {
		return "EncapsulationDemo "
				+ "[name=" + name + ", age=" + age + ""
						+ ", jersyNo=" + jersyNo + "]";
	}
	
}

package com.example.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {
	  private ArrayList<PersonFile> files;
    public Integer getId() {
		return id;
	}

	public ArrayList<PersonFile> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<PersonFile> files) {
		this.files = files;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getMonthly() {
		return monthly;
	}

	public void setMonthly(String monthly) {
		this.monthly = monthly;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private Integer id;

    private String name;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String sex;

    private int age;
    
    private String height;
    
    private String school;
    
    private String area;

    private String monthly;
    
    private static final long serialVersionUID = 1L;

  
}
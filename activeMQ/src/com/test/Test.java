package com.test;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.junit.Before;

public class Test {

	@Before
	public void before(){
		System.out.println("before");
	}
	
	public String toString(){
		System.out.println("toString..");
		return "aa";
	}
	
	public Test(){
		System.out.println("initMethod");
	}
	
	public static void main(String[] args){
		ResourceBundle rbBundle=PropertyResourceBundle.getBundle("aaa.properties");
		String a=rbBundle.getString("aaa");
		System.out.println(a);
	}
}

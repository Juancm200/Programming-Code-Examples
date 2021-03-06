package com.cs151.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : CS151_HW1
//  @ File Name : SeatingArea.java
//  @ Date : 9/8/2011
//  @ Author : 
//
//

/** */
public class SeatingArea implements Serializable{
	public static FirstClass firstClass;
	public static BusinessClass businessClass;
	public static EconomyClass economyClass;


	String temp_seat_pattern;
	int temp_row_count;
	String temp;
	String planeClass;
	
	/**
	 * SeatingArea constructor
	 * @throws IOException  */
	SeatingArea()
	{
		this.firstClass = new FirstClass();
		this.businessClass = new BusinessClass();
		this.economyClass = new EconomyClass();
	}
	
	/** 
	 * Allows user to specify plane compartment data
	 * */
	public void addPlaneData() throws IOException
	{	
		System.out.println("Enter Q at any time to quit");
		System.out.print("Enter service class name or [Enter] to finish: ");
		InputStreamReader input = new InputStreamReader(System.in);	
		BufferedReader reader = new BufferedReader(input);
		planeClass = reader.readLine();
		
		if (planeClass.equals("")) return;
		if (planeClass.equals("Q") || planeClass.equals("q")) return;
		
		if (planeClass.equals("First") && this.firstClass.created)
		{
			System.out.println("Invalid Selection: First Class Information Already Exists");
			addPlaneData();
			return;
		}
		else if (planeClass.equals("Business") && this.businessClass.created)
		{
			System.out.println("Invalid Selection: Business Class Information Already Exists");
			addPlaneData();
			return;
		}
		else if (planeClass.equals("Economy") && this.economyClass.created)
		{
			System.out.println("Invalid Selection: Economy Class Information Already Exists");
			addPlaneData();
			return;
		}
			
		System.out.print("Enter seating pattern: ");
		temp_seat_pattern = reader.readLine();
		
		if (temp_seat_pattern.equals("Q") || temp_seat_pattern.equals("q")) return;
		
		System.out.print("Enter number of seat rows: ");
		temp = reader.readLine();
		
		if (temp.equals("Q") || temp.equals("q")) return;
		
		temp_row_count = Integer.parseInt(temp);
		
		if (ReservationSystem.TESTING)
			System.out.println(planeClass + " " + temp_seat_pattern + " " + temp_row_count);
			
		if (planeClass.equals("First"))
		{
			createFirst(temp_seat_pattern, temp_row_count);
		}
		else if (planeClass.equals("Business"))
		{
			createBusiness(temp_seat_pattern, temp_row_count);
		}
		else if (planeClass.equals("Economy"))
		{
			createEconomy(temp_seat_pattern, temp_row_count);
		}
	}
	/** 
	 * Instantiates instance of FirstClass
	 * */
	public void createFirst(String seatPattern, int numOfRows)
	{
		this.firstClass = new FirstClass(seatPattern, numOfRows);
	
	}
	/** 
	 * Instantiates instance of BusinessClass
	 * */
	public void createBusiness(String seatPattern, int numOfRows)
	{
		this.businessClass = new BusinessClass(seatPattern, numOfRows);
	}
	/** 
	 * Instantiates instance of EconomyClass
	 * */
	public void createEconomy(String seatPattern, int numOfRows)
	{
		this.economyClass = new EconomyClass(seatPattern, numOfRows);
	}
	/** 
	 * Enters in dummy plane compartment data for testing and displaying purposes
	 * */
	public void enterDummyPlaneData()
	{
		this.firstClass = new FirstClass("WAAW", 2);
		//this.businessClass = new BusinessClass("WCAACW", 6);
		this.economyClass = new EconomyClass("WCAACW", 3);
	}
	public void enterDummyData(String s)
	{
		
	}
	public void enterDummyFirstData(String s, int i)
	{
		this.firstClass = new FirstClass(s, i);
	}
}

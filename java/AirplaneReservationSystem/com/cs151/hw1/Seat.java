package com.cs151.hw1;

import java.io.Serializable;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : CS151_HW1
//  @ File Name : Seat.java
//  @ Date : 9/8/2011
//  @ Author : 
//
//




/** */
public class Seat implements Serializable{
	
	
	/** */
	public int rowNum;
	
	/** */
	public char seatPos;
	
	/** */
	public char seatType;
	
	/** */
	public Customer passenger;
	
	/** */
	public boolean reserved;
	
	public char prevType;
	
	public Seat (int rowNum, char seatPos, char seatType)
	{
		this.rowNum = rowNum;
		this.seatPos = seatPos;
		this.prevType = seatType;
		this.seatType = seatType;
		this.reserved = false;
		this.passenger = new Customer();
	}
	public void setReserved(String name)
	{
		this.setCustomer(name);
		this.reserved = true;
		this.seatType = '*';
	}
	public void clearSeat()
	{
		this.passenger.clear();
		this.reserved = false;
		this.seatType = this.prevType;		
	}
	public void setRowNum(int i)
	{
		rowNum = i;
	}
	public void setSeatPos(char c)
	{
		seatPos = c;
	}
	public void setSeatType(char c)
	{
		seatType = c;
	}
	public void setCustomer(String name)
	{
		passenger.setName(name);
	}
	
}

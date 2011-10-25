package com.cs151.hw1;

import java.io.Serializable;
import java.util.ArrayList;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : CS151_HW1
//  @ File Name : BusinessClass.java
//  @ Date : 9/8/2011
//  @ Author : 
//
//
import java.util.Queue;




/** */
public class BusinessClass implements Serializable{
	static Seat[][] businessSeats;
	public final String seatPattern;
	public final int rowLength;
	public final int numOfRows;
	public boolean created = false;
	public boolean businessFull = false;
	public int businessSeatsLeft = 0;
	/** */
	/** 
	 * BusinessClass constructor.
	 * 
	 * @param	seatPattern	String type with the row pattern composed of (W)indow, (A)isle, and (C)enter
	 * @param	numOfRows	number of rows in the particular compartment
	 * */
	public BusinessClass(String seatPattern, int numOfRows) {
		this.businessFull = false;
		this.created = true;
		this.seatPattern = seatPattern;
		this.rowLength = seatPattern.length();
		this.numOfRows = numOfRows;
		businessSeats = new Seat[this.numOfRows][this.rowLength];
		for (int i = 0; i < this.numOfRows; i++)
		{
			for (int j = 0; j < this.rowLength; j++)
			{
				businessSeatsLeft++;
				businessSeats[i][j] = new Seat(i, (char)(j+65), seatPattern.charAt(j));
			}
		}
	}
	/**
	 * BusinessClass constructor without any initial parameters
	 */
	public BusinessClass()
	{
		this.businessSeatsLeft = 0;
		this.businessFull = true;
		this.seatPattern = "";
		this.rowLength = 0;
		this.numOfRows = 0;
	}
	/** 
	 * Returns Seat instance of the first found seat for the request.
	 * 
	 * This searches for the first seat available in the compartment based upon
	 * the seat preference.
	 * 
	 * @param	seatPref	type of seat preference
	 * */
	public Seat findBusinessSeat(char seatPref)
	{
		Seat temp;
		
		for (int i = 0; i < numOfRows; i++)
		{
			for (int j = 0; j < rowLength; j++)
			{
				if (!businessSeats[i][j].reserved && businessSeats[i][j].seatType == seatPref)
				{
					temp = businessSeats[i][j];
					return temp;
				}
			}
		}
		for (int i = 0; i < numOfRows; i++)
		{
			for (int j = 0; j < rowLength; j++)
			{
				if (!businessSeats[i][j].reserved)
				{
					temp = businessSeats[i][j];
					return temp;
				}
			}
		}
		System.out.println("Business Class is Full");
		businessSeatsLeft--;
		if (businessSeatsLeft <= 0)
			businessFull = true;
		return null;
	} // end findBusinessSeat
	
	/** 
	 * Returns number of passengers left from the group who were not seated.
	 * 
	 * This searches for the first open row containing the most seats available for group reservation.
	 * 
	 * @param	num		number of passengers requesting a seat
	 * @param 	names	names of passengers trying to request a seat
	 * */
	public int findBusinessRowForGroup(int num, Queue<String> names)
	{
		if (num > businessSeatsLeft) return -1;
		
		int count;
		int highestCount = -1;
		int rowWithMostSeats = -1;
		int startPos = 0;
		for (int i = 0; i < numOfRows; i++)
		{
			count = 0;
			startPos = 0;
			for (int j = 0; j < rowLength; j++)
			{
				if (!businessSeats[i][j].reserved)
				{
					count++;
					if (count >= num)
					{
						break;
					}
					if (count > highestCount)
					{
						highestCount = count;
						rowWithMostSeats = i;
					}
				}
				else
				{
					count = 0;
					startPos = j;
				}
				if (count >= num)
				{
					break;
				}
			}
			if (count >= num)
			{
				break;
			}
		}
		
		Seat temp;
		for (int c = startPos; c < highestCount+startPos; c++)
		{
			temp = businessSeats[rowWithMostSeats][c];
			System.out.println(names.peek() + " has been reserved for seat: " + (temp.rowNum+1) + temp.seatPos);
			if (!names.isEmpty())
				temp.setReserved(names.remove());
		}
		
		businessSeatsLeft -= highestCount;
		if (businessSeatsLeft <= 0)
			businessFull = true;
		
		return num-highestCount;
	} // end findBusinessRowForGroup()
	/** 
	 * Cancels reservation for a single person on the plane.
	 * */
	public boolean removeSingle(String name)
	{
		for (int i = 0; i < numOfRows; i++)
		{
			for (int j = 0; j < rowLength; j++)
			{
				if (businessSeats[i][j].passenger.name.equals(name))
				{
					businessSeats[i][j].clearSeat();
					businessSeatsLeft++;
					if (businessFull && businessSeatsLeft > 0)
					{
						businessFull = false;
					}
					return true;
				}
			}
		}
		return false;
	} // end removeSingle()
	/** 
	 * Cancels a number of passengers based upon names entered
	 * */
	public void removeGroup(Queue<String> names)
	{
		for (int i = 0; i < names.size(); i++)
		{
			if (removeSingle(names.peek())) names.remove();
		}
	}
}

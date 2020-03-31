package com.overwatch.covid;

/*
 *  all methods in this class should be declared in static
 *  'DONE' means no more code
 *  'TODO' means you should add your own code in it
 */

public final class Database implements UpdateInterface, RequestInterface
{
	private static Database myself;
	
	private Database()
	{
		// TODO
	}
	
	public static void createDabatase()
	{
		myself = new Database();
		// DONE
	}
	
	@Override
	public String getData()
	{
		// TODO
		return null;
	}
	
	public static Database getDatabase()
	{
		return myself;
		// DONE
	}

	@Override
	public void sendData(String data)
	{
		// TODO
	}

}

package com.overwatch.covid;

public class HandleRequest extends Thread
{
	private RequestInterface database;
	
	public HandleRequest(RequestInterface db)
	{
		database = db;
	}
	
	@Override
	public void run()
	{
		// TODO
	}
	
}

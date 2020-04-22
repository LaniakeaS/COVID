package com.overwatch.covid;

public class UpdateDatabase extends Thread
{
	private UpdateInterface database;
	String China;
	String World;
	String News;
	// thread initialization
	public UpdateDatabase(UpdateInterface db)
	{
		database = db;
		// TODO
	}
	
	// start thread
	@Override
	public void run()
	{
		// TODO
		Database.storeChinaData(China);
		Database.storeWorldData(World);
		Database.storeNews(News);
	}
}

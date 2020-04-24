package com.overwatch.covid;

/*
 *  DO NOT TOUCH THIS!!!!
 */

public final class Server
{
	private static Database database;
	private static UpdateDatabase update;
	//private static HandleRequest handle;
	
	private Server()
	{
		Database.createDabatase();
		database = Database.getDatabase();
		update = new UpdateDatabase(database);
		//handle = new HandleRequest(database);
		update.start();
		//handle.start();
	}
	
	public static void startServer()
	{
		new Server();
	}
}

package com.overwatch.covid;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.CharBuffer;
import java.util.Iterator;
import java.util.stream.Stream;

public class HandleRequest extends Thread
{
	private RequestInterface database;
	private String data;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private HTTPRequest request;
	
	// to be removed
	public static void main(String[] args)
	{
		HandleRequest handle = new HandleRequest(Database.getDatabase());
		handle.start();
	}
	
	public HandleRequest(RequestInterface db)
	{
		database = db;
		
		try
		{
			InetAddress serverAddress = InetAddress.getByName("192.168.1.101");
			serverSocket = new ServerSocket(80, 0, serverAddress);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void respond()
	{
		String version = request.getVersion();
		String status = "200";
		String statusDescription = "OK";
		
		if(!request.getRespondStatus().equals("200"))
			status = request.getRespondStatus();
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			try
			{
				output("Waiting for connection, port number: " + serverSocket.getLocalPort() + "...");
				clientSocket = serverSocket.accept();
				output("Success! Remote address: " + clientSocket.getRemoteSocketAddress());
				request = new HTTPRequest(clientSocket.getInputStream());output(request.getHeader());
				respond();
				
				if(request.getConnection().equalsIgnoreCase("close"))
					clientSocket.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
				break;
			}
		}
		
		output("Server Shut Down.");
	}
	
	public static void output(String message)
	{
		System.out.println("HandleRequest: " + message);
	}
	
}

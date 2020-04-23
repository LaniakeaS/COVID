package com.overwatch.covid;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HandleRequest extends Thread
{
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private HTTPRequest request;

	// to be removed
	public static void main(String[] args) throws InterruptedException
	{
		HandleRequest handle = new HandleRequest();
		handle.start();
	}
	
	private HandleRequest()
	{
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

	public HandleRequest(RequestInterface db)
	{
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

	private void respond() throws IOException
	{
		List<String> respond = new ArrayList<String>();
		String version = request.getVersion();
		String status = "200";
		String statusDescription = "OK";


		// check if it's valid request
		if(!request.getMethod().equals("GET") || request.getHost().equals("NONE"))
		{
			version = "HTTP/1.1";
			status = "400";
			statusDescription = "Bad Request";
		}


		// check request content
		File index = new File("src/main/resources" + request.getTarget());
		
		if(!index.exists() && status.equals("200"))
		{
			version = "HTTP/1.1";
			status = "404";
			statusDescription = "Not Found";
		}


		// check HTML version
		if(!version.equals("HTTP/1.1") && status.equals("200"))
		{
			version = "HTTP/1.1";
			status = "505";
			statusDescription = "HTTP Version Not Supported";
		}


		// analyze status
		respond.add(version + " " + status + " " + statusDescription + "\r\n");

		switch(status)
		{
		case "200":
			break;
		case "400":
			index = new File("src/main/resources/400.html");
			break;
		case "404":
			index = new File("src/main/resources/404.html");
			break;
		case "505":
			index = new File("src/main/resources/505.html");
			break;
		default:
			output("Fatal Error! Respond status code invalid.");
			System.exit(-1);
			break;
		}


		// construct respond body
		FileInputStream in = new FileInputStream(index);

		if(index.getName().contains(".jpg"))
			respond.add("Content-Type: image/jpeg\r\n");
		else if(index.getName().contains(".html"))
			respond.add("Content-Type: text/html\r\n");
		else if(index.getName().contains(".json"))
			respond.add("Content-Type: application/json\r\n");

		respond.add("\r\n");
		byte buffer[] = new byte[in.available()];
		in.read(buffer);
		in.close();
		respond.add(new String(buffer));
		buffer = String.join("", respond).getBytes();


		// send respond to client
		OutputStream sendingStream = clientSocket.getOutputStream();
		sendingStream.write(buffer);
		sendingStream.flush();
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
				request = new HTTPRequest(clientSocket.getInputStream());output(request.getRequest());
				respond();
				
				if(request.getConnection().equalsIgnoreCase("close"))
				{
					clientSocket.close();
					output("Client Shutdown.");
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
				break;
			}

			System.out.println("\n");
		}
		
		output("Server Shut Down.");
	}
	
	public static void output(String message)
	{
		System.out.println("HandleRequest: " + message);
	}
	
}

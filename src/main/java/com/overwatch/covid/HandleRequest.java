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
	public static void main(String[] args)
	{
		HandleRequest handle = new HandleRequest(Database.getDatabase());
		handle.start();
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


		// check method, only can be GET
		if(!request.getMethod().equals("GET"))
		{
			version = "HTTP/1.1";
			status = "400";
			statusDescription = "Bad Request";
		}


		// check host, 400 if it's none
		if(request.getHost().equals("NONE") && status.equals("200"))
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
			String contentType = "Content-Type: application/html";
			respond.add(contentType + "\r\n");
			index = new File("src/main/resources" + request.getTarget());
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
		respond.add("\r\n");
		FileInputStream in = new FileInputStream(index);
		byte buffer[] = new byte[in.available()];
		in.read(buffer);
		in.close();
		respond.add(new String(buffer));
		buffer = String.join("", respond).getBytes();


		// send respond to client
		OutputStream sendingStream = clientSocket.getOutputStream();
		sendingStream.write(buffer);
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
				request = new HTTPRequest(clientSocket.getInputStream());
				// remove next line
				output("\n\n" + request.getRequest() + "\n");
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
		}
		
		output("Server Shut Down.");
	}
	
	public static void output(String message)
	{
		System.out.println("HandleRequest: " + message);
	}
	
}

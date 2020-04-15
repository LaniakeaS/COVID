package com.overwatch.covid;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class HTTPRequest
{
	private String connection;
	private String content;
	private String header;
	private String host;
	private String method;
	private String respondStatus;
	private String request;
	private String target;
	private String version;
	
	public HTTPRequest(InputStream REQUEST)
	{
		connection = "close";
		respondStatus = "200";
		host = null;
		content = "NONE";
		
		try
		{
			//split into header and content
			byte buffer[] = new byte[REQUEST.available()];
			REQUEST.read(buffer);
			request = new String(buffer);
			header = request.split("\r\n\r\n")[0];
			
			
			//verify every line in header
			List<String> lines = new ArrayList<String>();
			Collections.addAll(lines, header.split("\r\n"));
			Iterator<String> iterator = lines.iterator();
			String requestLine = iterator.next();
			String temp[] = requestLine.split(" ");
			method = temp[0];
			
			switch(method)
			{
			case "POST":
				content = request.split("\r\n\r\n")[1];
				break;
			case "GET":
				break;
			default:
				respondStatus = "400";
			}
			
			target = temp[1];
			version = temp[2];
			
			while(iterator.hasNext())
			{
				temp = iterator.next().split(": ");
				
				switch(temp[0])
				{
				case "Connection":
					connection = temp[1];
					break;
				case "Host":
					host = temp[1];
					break;
				default:
					break;
				}
			}
			
			if(host == null)
				respondStatus = "400";
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String getConnection()
	{
		return connection;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public String getHeader()
	{
		return header;
	}
	
	public String getHost()
	{
		return host;
	}
	
	public String getMethod()
	{
		return method;
	}
	
	public String getRespondStatus()
	{
		return respondStatus;
	}
	
	public String getRequest()
	{
		return request;
	}
	
	public String getTarget()
	{
		return target;
	}
	
	public String getVersion()
	{
		return version;
	}
}

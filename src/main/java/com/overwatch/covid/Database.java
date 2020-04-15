
package com.overwatch.covid;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;


/*
 *  all methods in this class should be declared in static
 *  'DONE' means no more code
 *  'TODO' means you should add your own code in it
 */

public final class Database implements UpdateInterface, RequestInterface
{
	
	private static MongoCollection<Document> covid;
	private static Database myself;
	private String data;
	 MongoDatabase countrydb;
	private Database()
	{
		// TODO
	}
	
	public static void createDabatase()
	{
		myself = new Database();
		// DONE
	}
	
	public static Database getDatabase()
	{
		return myself;
		// DONE
	}


	public void connection() {
		  
		  MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		    
		 countrydb = mongoClient.getDatabase("countrydb");  
		  System.out.println("Connect to database successfully");
		  
	    covid = countrydb.getCollection("covid");
	       System.out.println("collection covid accesses succeed ");
			
	}
	
	
	public String getCollection() {
	     	
		 connection();
		 String all = null;
	       try{   
		    
		         FindIterable<Document> findIterable = covid.find();  
		         MongoCursor<Document> mongoCursor = findIterable.iterator();  
		    while(mongoCursor.hasNext()){  
	            all = mongoCursor.next().toString();
	            System.out.println(all);  
	         }  
	       }catch(Exception e){
		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     }
	       return all;
    }
	
	
	
    public String getChinaData() {
    	
		   connection();
		    FindIterable<Document> findIterable = covid.find(Filters.eq("name", "China"));  
	        MongoCursor<Document> mongoCursor = findIterable.iterator();  
	     	String China = mongoCursor.next().toString();
			System.out.print(China);
			return China;
	}
	
	
	
	@Override
	public String getData()
	{
		// TODO
		return null;
	}

	
	@Override
	public void sendData(String data)
	{
		// TODO
	}

	
	
	
	
	
	
	
	public void  storeData() {
		sendData(data);
		getData();
		//先将string转为json 再储存
	   
		
		
		List<Document> documentList = new ArrayList<>();
		for(Data data : dataList) {
			Document document = new Document("_id",1)
					.append("countey", data.getCountry())
					.append("newcases",data.getNewcases())
		}
		
	}
	
	
	
}

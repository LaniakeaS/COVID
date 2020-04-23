
package com.overwatch.covid;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonValue;

import com.covid.DataBase.MongoDBJDBC;
import com.covid.DataBase.UpdateDatabase;
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
import org.jsoup.Jsoup;

/*
 *  all methods in this class should be declared in static
 *  'DONE' means no more code
 *  'TODO' means you should add your own code in it
 */

public final class Database implements UpdateInterface, RequestInterface
{
	
	private static MongoCollection<BsonDocument> China;
	private static MongoCollection<BsonDocument> World;
	private static MongoCollection<BsonDocument> News;
	private static Database myself;
	private String data;
	static MongoDatabase countrydb;
	static MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	
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
	@Override
	public String getData()
	{
		// TODO
		
		return data;
	}

	
	@Override
	public void sendData(String data)
	{
		// TODO
		this.data = data;
	}


	public static void connectionChina() {
	    //connect to mongodb
		 countrydb = mongoClient.getDatabase("country");  
		  System.out.println("Connect to database successfully");
		  
	    China = countrydb.getCollection("countrysituation",BsonDocument.class);//改名字
	       System.out.println("collection covid accesses succeed ");
			
	}
	
	
	
	public String getCollectionChina() {
		//print all data in collection
	     	
		 connectionChina();
		 String all = null;
	       try{   
		         FindIterable<BsonDocument> findIterable = China.find();  
		         MongoCursor<BsonDocument> mongoCursor = findIterable.iterator();  
		    while(mongoCursor.hasNext()){  
	            all = mongoCursor.next().toString();
	            System.out.println(all);  
	         }  
	       }catch(Exception e){
		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     }
	       return all;
    }
	
	

	
	
	
	public static void  storeChinaData(String China) {
	  /*     
		String json = " {" + "'_id':'1'"+
                    " 'name' : 'england', " +
                    " 'newcases' : '10', " +
                    " 'death' : '100', " +
                    " 'total' : '200' " +
                    " } ";
       BsonDocument document = BsonDocument.parse(json);
       covid.insertOne(document );
               */

//China		
		String data1 = 	"[{\"_id\":\"4\",\"name\":\"bbb\",\"total\":\"200\"}{\"_id\":\"5\",\"name\":\"bbb\",\"total\":\"200\"}]";
                BsonArray bsonArray;
                bsonArray =  BsonArray.parse(data1);//把json格式的string导入这个数组
                List<BsonDocument> documents = new LinkedList<>();
                BsonDocument bson;
               
                for(BsonValue bsonValue : bsonArray){//put bson into document 
                     bson = bsonValue.asDocument();
                     documents.add(bson);
               }
                exportMongoChina(documents);
       
		}
	
	 private static void exportMongoChina( List<BsonDocument>documents){       
		    
	        connectionChina();
	       
	        China.insertMany(documents);
	        System.out.println("\n数据导出MongoDB成功!");
	        mongoClient.close();
	 }

	
	 public static void connectionWorld() {
		    //connect to mongodb
			 countrydb = mongoClient.getDatabase("country");  
			  System.out.println("Connect to database successfully");
			  
		    World = countrydb.getCollection("countrysituation",BsonDocument.class);//改名字
		       System.out.println("collection covid accesses succeed ");
				
		}
	 
	 public String getCollectionWorld() {
			//print all data in collection
		     	
			 connectionWorld();
			 String all = null;
		       try{   
			         FindIterable<BsonDocument> findIterable = China.find();  
			         MongoCursor<BsonDocument> mongoCursor = findIterable.iterator();  
			    while(mongoCursor.hasNext()){  
		            all = mongoCursor.next().toString();
		            System.out.println(all);  
		         }  
		       }catch(Exception e){
			        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			     }
		       return all;
	    }
	 
 
	public static void storeWorldData(String World) {
	
        BsonArray bsonArray;
        bsonArray =  BsonArray.parse(World);//把json格式的string导入这个数组
        List<BsonDocument> documents = new LinkedList<>();
        BsonDocument bson;
       
        for(BsonValue bsonValue : bsonArray){//put bson into document 
             bson = bsonValue.asDocument();
             documents.add(bson);
       }
        exportMongoWorld(documents);
		
	}

	 private static void exportMongoWorld( List<BsonDocument>documents){       
		    
	        connectionWorld();
	       
	        China.insertMany(documents);
	        System.out.println("\n数据导入MongoDB成功!");
	        mongoClient.close();
	 }

	
	
	 public static void connectionNews() {
		    //connect to mongodb
			 countrydb = mongoClient.getDatabase("country");  
			  System.out.println("Connect to database successfully");
			  
		    News = countrydb.getCollection("countrysituation",BsonDocument.class);//改名字
		       System.out.println("collection covid accesses succeed ");
				
		}
	 
	 public String getCollectionNews() {
			//print all data in collection
		     	
			 connectionNews();
			 String all = null;
		       try{   
			         FindIterable<BsonDocument> findIterable = China.find();  
			         MongoCursor<BsonDocument> mongoCursor = findIterable.iterator();  
			    while(mongoCursor.hasNext()){  
		            all = mongoCursor.next().toString();
		            System.out.println(all);  
		         }  
		       }catch(Exception e){
			        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			     }
		       return all;
	    }
	 
	 
	 
	public static void storeNews(String News) {
	
        BsonArray bsonArray;
        bsonArray =  BsonArray.parse(News);//把json格式的string导入这个数组
        List<BsonDocument> documents = new LinkedList<>();
        BsonDocument bson;
       
        for(BsonValue bsonValue : bsonArray){//put bson into document 
             bson = bsonValue.asDocument();
             documents.add(bson);
       }
        exportMongoNews(documents);
	}
	
	//UpdateDatabase
		
				 //数组合并
		
	 private static void exportMongoNews( List<BsonDocument>documents){       
	    
	        connectionNews();
	       
	        News.insertMany(documents);
	        System.out.println("\n数据导出MongoDB成功!");
	        mongoClient.close();
	 }

	
	
	 
		
	/*	public static void main(String[] args) {
			// TODO Auto-generated method stub
	          
			Database mongo = new Database();
			
	          mongo.getCollection();
	     
		      
		}
	 */
	 
}


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
	
	private static MongoCollection<BsonDocument> covid;
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


	public static void connection() {
		  
		
		    
		    // 连接到数据库
		 countrydb = mongoClient.getDatabase("countrydb");  
		  System.out.println("Connect to database successfully");
		  
	    covid = countrydb.getCollection("covid",BsonDocument.class);
	       System.out.println("collection covid accesses succeed ");
			
	}
	
	
	public String getCollection() {//collection中所有数据
	     	
		 connection();
		 String all = null;
	       try{   
		    
		         //检索所有文档  
		         /** 
		         * 1. 获取迭代器FindIterable<Document> 
		         * 2. 获取游标MongoCursor<Document> 
		         * 3. 通过游标遍历检索出的文档集合 
		         * */  
		     
		         FindIterable<BsonDocument> findIterable = covid.find();  
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
	
	
	
    public String getChinaData() {
    	 //获取集合中 中国的数据
		   connection();
		    FindIterable<BsonDocument> findIterable = covid.find(Filters.eq("name", "China"));  
	        MongoCursor<BsonDocument> mongoCursor = findIterable.iterator();  
	     	String China = mongoCursor.next().toString();
			System.out.print(China);
			return China;
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

	
	public void  storeData(String data) {
		
		//先将json的string转 document 再储存
          
		String json = " {" + "'_id':'1'"+
                    " 'name' : 'england', " +
                    " 'newcases' : '10', " +
                    " 'death' : '100', " +
                    " 'total' : '200' " +
                    " } ";
       BsonDocument document = BsonDocument.parse(json);
       covid.insertOne(document );
               
     
                BsonArray bsonArray;
                bsonArray =  BsonArray.parse(data);//把json格式的string导入这个数组
                 List<BsonDocument> documents = new LinkedList<>();
              //  String info;
                BsonDocument bson;
                for(BsonValue bsonValue : bsonArray){//put bson into document 
                     bson = bsonValue.asDocument();
                     documents.add(bson);
               /*     info = String.format("%s :%s\t%s\t%s\t", bson.getString("countryname").getValue(), 
                        bson.getString("newcases").getValue(), bson.getString("death").getValue(),
                        bson.getString("total").getValue());
                    System.out.println(info);
                    */
                  
                 
                    
                    
                }
                exportMongo(documents);
		}
	
	
	
	
		
	 private static void exportMongo( List<BsonDocument>documents){       
	    
	        connection();
	       
	        covid.insertMany(documents);
	        System.out.println("\n数据导出MongoDB成功!");
	        mongoClient.close();
	 }
	
}

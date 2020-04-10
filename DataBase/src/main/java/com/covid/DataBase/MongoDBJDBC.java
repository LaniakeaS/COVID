package com.covid.DataBase;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoDBJDBC implements QhzDy {

	 public void getDataChina() {
		 //获取集合中 中国的数据
		  try{   
		       // 连接到 mongodb 服务
		         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		       
		         // 连接到数据库
		       MongoDatabase mongoDatabase = mongoClient.getDatabase("country");  
		       System.out.println("Connect to database successfully");
		       MongoCollection<Document> collection = mongoDatabase.getCollection("countrysituation");
		       System.out.println("collection countrysituation accesses succeed ");
		       
		         //检索所有文档  
		         /** 
		         * 1. 获取迭代器FindIterable<Document> 
		         * 2. 获取游标MongoCursor<Document> 
		         * 3. 通过游标遍历检索出的文档集合 
		         * */  
		        FindIterable<Document> findIterable = collection.find(Filters.eq("name", "China"));  
		        MongoCursor<Document> mongoCursor = findIterable.iterator();  
		         
		        while(mongoCursor.hasNext()){  
		            System.out.println(mongoCursor.next());  
		         }  
	       }catch(Exception e){
		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     }
		
	}
	
	
	
	
	public void createCollection() {
		 //新建一个collection
		
		try{   
		       // 连接到 mongodb 服务
		         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		       
		         // 连接到数据库
		         MongoDatabase mongoDatabase = mongoClient.getDatabase("country");  
		         System.out.println("Connect to database successfully");
	          	 mongoDatabase.createCollection("test");
	             System.out.println("集合创建成功");
		   }catch(Exception e){
		         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     }      
	}
	
	
	
	public void getCollectionData() {
	    //打印出集合中所有数据
		  
	       try{   
		       // 连接到 mongodb 服务
		         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		       
		         // 连接到数据库
		       MongoDatabase mongoDatabase = mongoClient.getDatabase("country");  
		       System.out.println("Connect to database successfully");
		       MongoCollection<Document> collection = mongoDatabase.getCollection("countrysituation");
		       System.out.println("collection countrysituation accesses succeed ");
		       
		         //检索所有文档  
		         /** 
		         * 1. 获取迭代器FindIterable<Document> 
		         * 2. 获取游标MongoCursor<Document> 
		         * 3. 通过游标遍历检索出的文档集合 
		         * */  
		     
		         FindIterable<Document> findIterable = collection.find();  
		         MongoCursor<Document> mongoCursor = findIterable.iterator();  
		         while(mongoCursor.hasNext()){  
		            System.out.println(mongoCursor.next());  
		         }  
	       
	       
	       
	       }catch(Exception e){
		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     }
		
	
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
          
		MongoDBJDBC mongo = new MongoDBJDBC();
		
       //调用方法    mongo.getCollectionData();
     
	      
	}
}

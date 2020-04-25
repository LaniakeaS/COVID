package com.overwatch.covid;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonValue;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/*
 *  all methods in this class should be declared in static
 *  'DONE' means no more code
 *  'TODO' means you should add your own code in it
 */

public final class Database implements UpdateInterface, RequestInterface {

	private static MongoCollection<BsonDocument> China;
	private static MongoCollection<BsonDocument> World;
	private static MongoCollection<BsonDocument> News;
	private static MongoCollection<BsonDocument> worldNews;
	private static Database myself;
	private static MongoDatabase countrydb;
	private static MongoClient mongoClient = new MongoClient("localhost", 27017);

	private Database() {
		// DONE
	}

	public static void createDabatase() {
		myself = new Database();
		// DONE
	}

	public static Database getDatabase() {
		return myself;
		// DONE
	}

	public static void connectionChina() {
		// connect to mongodb
		countrydb = mongoClient.getDatabase("country");
		System.out.println("Connect to database successfully");
		China = countrydb.getCollection("china", BsonDocument.class);// 改名字
		System.out.println("collection china  accesses succeed ");
	}

	public String getCollectionChina() {
		// print all data in collection
		connectionChina();
		String ChinaData = null;
		List<String> aaa = new ArrayList<String>();
		try {
			FindIterable<BsonDocument> findIterable = China.find();
			MongoCursor<BsonDocument> mongoCursor = findIterable.iterator();

			while (mongoCursor.hasNext()) {
				String all = mongoCursor.next().toString();
				aaa.add(all);
			}
			ChinaData = aaa.toString();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return ChinaData;
	}

	private static void storeChinaData(String data) {
		/*
		 * data =
		 * "[{\"_id\":\"4\",\"name\":\"bbb\",\"total\":\"200\"}{\"_id\":\"5\",\"name\":\"bbb\",\"total\":\"200\"}]";
		 */

		connectionChina();
		FindIterable<BsonDocument> findIterable = China.find();
		MongoCursor<BsonDocument> mongoCursor = findIterable.iterator();
		while (mongoCursor.hasNext()) {
			China.deleteOne(mongoCursor.next());
		}

		BsonArray bsonArray;
		bsonArray = BsonArray.parse(data);// 把json格式的string导入这个数组
		List<BsonDocument> documents = new LinkedList<>();
		BsonDocument bson;

		for (BsonValue bsonValue : bsonArray) {// put bson into document
			bson = bsonValue.asDocument();
			documents.add(bson);
		}
		exportMongoChina(documents);
	}

	private static void exportMongoChina(List<BsonDocument> documents) {

		China.insertMany(documents);
		System.out.println("\n数据导入MongoDB成功!");
	}

	public static void connectionWorld() {
		// connect to mongodb
		countrydb = mongoClient.getDatabase("country");
		System.out.println("Connect to database successfully");
		World = countrydb.getCollection("world", BsonDocument.class);
		System.out.println("collection world accesses succeed ");
	}

	public String getCollectionWorld() {
		// print all data in collection
		connectionWorld();
		List<String> bbb = new ArrayList<String>();
		String WorldData = null;

		try {
			FindIterable<BsonDocument> findIterable = World.find();
			MongoCursor<BsonDocument> mongoCursor = findIterable.iterator();
			while (mongoCursor.hasNext()) {
				String all = mongoCursor.next().toString();
				bbb.add(all);
			}
			WorldData = bbb.toString();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return WorldData;
	}

	public static void storeWorldData(String data) {
		/* data = "[{\"_id\":\"4\",\"name\":\"bbb\",\"total\":\"200\"}{\"_id\":\"5\",\"name\":\"bbb\",\"total\":\"200\"}]"; */
		connectionWorld();
		FindIterable<BsonDocument> findIterable = World.find();
		MongoCursor<BsonDocument> mongoCursor = findIterable.iterator();
		while (mongoCursor.hasNext()) {
			World.deleteOne(mongoCursor.next());
		}
		BsonArray bsonArray;
		bsonArray = BsonArray.parse(data);// 把json格式的string导入这个数组
		List<BsonDocument> documents = new LinkedList<>();
		BsonDocument bson;
		for (BsonValue bsonValue : bsonArray) {// put bson into document
			bson = bsonValue.asDocument();
			documents.add(bson);
		}
		exportMongoWorld(documents);
	}

	private static void exportMongoWorld(List<BsonDocument> documents) {

		World.insertMany(documents);
		System.out.println("\n数据导入MongoDB成功!");
	}

	public static void connectionNews() {
		// connect to mongodb
		countrydb = mongoClient.getDatabase("country");
		System.out.println("Connect to database successfully");
		News = countrydb.getCollection("news", BsonDocument.class);// 改名字
		System.out.println("collection news accesses succeed ");
	}

	public String getCollectionNews() {
		// print all data in collection
		connectionNews();
		List<String> ccc = new ArrayList<String>();
		String NewsData = null;

		try {
			FindIterable<BsonDocument> findIterable = News.find();
			MongoCursor<BsonDocument> mongoCursor = findIterable.iterator();

			while (mongoCursor.hasNext()) {
				String all = mongoCursor.next().toString();
				ccc.add(all);
			}
			NewsData = ccc.toString();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return NewsData;
	}

	public static void storeNews(String data) {
		connectionNews();
		FindIterable<BsonDocument> findIterable = News.find();
		MongoCursor<BsonDocument> mongoCursor = findIterable.iterator();
		while (mongoCursor.hasNext()) {
			News.deleteOne(mongoCursor.next());
		}
		BsonArray bsonArray;
		bsonArray = BsonArray.parse(data);// 把json格式的string导入这个数组
		List<BsonDocument> documents = new LinkedList<>();
		BsonDocument bson;

		for (BsonValue bsonValue : bsonArray) {// put bson into document
			bson = bsonValue.asDocument();
			documents.add(bson);
		}

		exportMongoNews(documents);
	}

	private static void exportMongoNews(List<BsonDocument> documents) {

		News.insertMany(documents);
		System.out.println("\n数据导出MongoDB成功!");
	}

	public static void connectionWorldNews() {
		// connect to mongodb
		countrydb = mongoClient.getDatabase("country");
		System.out.println("Connect to database successfully");
		worldNews = countrydb.getCollection("worldnews", BsonDocument.class);// 改名字
		System.out.println("collection news accesses succeed ");
	}

	public String getCollectionWorldNews() {
		// print all data in collection
		connectionWorldNews();
		List<String> ccc = new ArrayList<String>();
		String NewsData = null;

		try {
			FindIterable<BsonDocument> findIterable = worldNews.find();
			MongoCursor<BsonDocument> mongoCursor = findIterable.iterator();

			while (mongoCursor.hasNext()) {
				String all = mongoCursor.next().toString();
				ccc.add(all);
			}
			NewsData = ccc.toString();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return NewsData;
	}

	public static void storeWorldNews(String data) {
		connectionWorldNews();
		FindIterable<BsonDocument> findIterable = worldNews.find();
		MongoCursor<BsonDocument> mongoCursor = findIterable.iterator();
		while (mongoCursor.hasNext()) {
			worldNews.deleteOne(mongoCursor.next());
		}
		BsonArray bsonArray;
		bsonArray = BsonArray.parse(data);// 把json格式的string导入这个数组
		List<BsonDocument> documents = new LinkedList<>();
		BsonDocument bson;

		for (BsonValue bsonValue : bsonArray) {// put bson into document
			bson = bsonValue.asDocument();
			documents.add(bson);
		}

		exportMongoWorldNews(documents);
	}

	private static void exportMongoWorldNews(List<BsonDocument> documents) {

		worldNews.insertMany(documents);
		System.out.println("\n数据导出MongoDB成功!");
	}

	@Override
	public String getChinaData() {
		return getCollectionChina();
	}

	@Override
	public String getWorldData() {
		return getCollectionWorld();
	}

	@Override
	public String getNews() {
		return getCollectionNews();
	}

	@Override
	public void sendData(String[] data) {
		storeChinaData(data[0]);
		storeWorldData(data[1]);
		storeNews(data[2]);
		storeWorldNews(data[3]);
	}

	@Override
	public String getWorldNews() {
		return getCollectionWorldNews();
	}

}

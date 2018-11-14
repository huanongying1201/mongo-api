package com.zjl.mongo;

import java.util.Arrays;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class MongoJDBC {

	public MongoCollection<Document> getDb(String test) {
		// 用户名 数据库 密码  
        MongoCredential credential = MongoCredential.createCredential("root", "admin", "123".toCharArray());  
        //IP port  
        ServerAddress addr = new ServerAddress("127.0.0.1", 27017);  
        MongoClient client = new MongoClient(addr,Arrays.asList(credential));  
        //得到数据库  
        MongoDatabase db = client.getDatabase("test");
        return db.getCollection(test);
	}
}

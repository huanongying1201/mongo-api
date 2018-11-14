package com.zjl.mongo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;

public class Test {
	private ObjectId _id;
	private int userId;
	private int score;
	private int roleId;
	public ObjectId get_id() {
		return _id;
	}


	public void set_id(ObjectId _id) {
		this._id = _id;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getScore() {
		return score;
	}


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public static void main( String[] args ) throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException{
    	MongoJDBC jdbc = new MongoJDBC();
        	 MongoCollection<Document> col = jdbc.getDb("test");
        	 Random random = new Random();
//        	 List<Document> list = new ArrayList<>();
//        	 for(int i = 0;i < 100000;i++) {
//        		 Test test = new Test();
//                 test.set_id(ObjectId.get());
//                 test.setUserId(random.nextInt(100000));
//                 test.setScore(random.nextInt(10000000));
//                 test.setRoleId(random.nextInt(2));
//                 Document dom = BeanUtil.bean2Doc(test);
//                 list.add(dom);
//        	 }
//        	 col.insertMany(list);
//           col.insertOne(BeanUtil.bean2Doc(test));
             List<Document> docList = col.find().into(new ArrayList<>());
             List<Object> testList = BeanUtil.doc2List(docList, Test.class);
             List<Test> ls1 = new ArrayList<>();
             for(Object o : testList) {
            	 Test t = (Test)o;
            	 ls1.add(t);
             }
             List<Integer> ls = new ArrayList<>();
        	 for(int i = 0;i < 100;i++) {
        		 ls.add(ls1.get(random.nextInt(100000)).getUserId());
        	 }
             System.out.println(ls);
    }
}

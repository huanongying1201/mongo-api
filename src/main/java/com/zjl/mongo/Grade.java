package com.zjl.mongo;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;

public class Grade {
	private ObjectId _id;
	private int num;
	private List<Stars> stars;
	
	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public List<Stars> getStars() {
		return stars;
	}

	public void setStars(List<Stars> stars) {
		this.stars = stars;
	}

	public static class Stars {
		private int num;
		private int need;
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		public int getNeed() {
			return need;
		}
		public void setNeed(int need) {
			this.need = need;
		}
	}

	public static void main( String[] args ) throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException{
    	MongoJDBC jdbc = new MongoJDBC();
        	 MongoCollection<Document> col = jdbc.getDb("grade");
        	 Bson unwind = Aggregates.unwind("$stars");
        	 Bson project = Aggregates.project(and(eq("_id",0),eq("stars",1)));
        	 Bson limit = Aggregates.limit(5);
        	 AggregateIterable<Document> aggregateIterable = col.aggregate(Arrays.asList(unwind,project,limit));
             List<Document> docList = aggregateIterable.into(new ArrayList<>());
             for(Document doc : docList) {
            	 Document stars =  (Document) doc.get("stars");
            	 Double num = (Double) stars.get("num");
            	 System.out.println(num);
             }
    }
}

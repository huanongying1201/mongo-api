package com.zjl.mongo;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

public class Grade {

	public static void main( String[] args ) {
    	MongoJDBC jdbc = new MongoJDBC();
        	 MongoCollection<Document> col = jdbc.getDb("grade");
        	 Bson unwind = Aggregates.unwind("$stars");
        	 Bson project = Aggregates.project(and(eq("_id",0),eq("stars",1)));
        	 Bson sort = Aggregates.sort(eq("stars.num",-1));
        	 Bson limit = Aggregates.limit(5);
        	 AggregateIterable<Document> aggregateIterable = col.aggregate(Arrays.asList(unwind,project,sort,limit));
             List<Document> docList = aggregateIterable.into(new ArrayList<>());
             for(Document doc : docList) {
            	 Document stars =  (Document) doc.get("stars");
            	 Double num = (Double) stars.get("num");
            	 System.out.println(num);
             }
        	 Bson filter = and(eq("num",5),eq("stars.num",80));
        	 Bson update = Updates.set("stars.$.need",440);
        	 UpdateResult s = col.updateOne(filter, update);
        	 long modifiedCount = s.getModifiedCount();
        	 if(modifiedCount == 0) {
        		 
        	 }
        	 System.out.println(s);
    }
}

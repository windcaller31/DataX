package com.alibaba.datax.plugin.reader.mongodbreader.test;

import java.util.List;

import org.bson.Document;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;

public class DocumentJsonTest {
//	public static void main(String args[]){
//		String customer_collection = "@limengtongtest";
//		MongoClient hfs_client = MongoDBUtil.mongoClient("172.31.0.199", 6011, "testboss", "test-action", "testboss");
//		BasicDBObject query_obj = new BasicDBObject();
//		BasicDBObject source_obj = new BasicDBObject();
//		source_obj.append("_id", 1);
//		source_obj.append("a", 1);
//		source_obj.append("test1", 1);
//		source_obj.append("test3", 1);
//		source_obj.append("test4", 1);
//		List<Document> mid_result = MongoDBUtil.find(hfs_client, "test-action", customer_collection, query_obj, source_obj,
//				null, -1);
//		for(Document m: mid_result){			
//			Object item = m.get("test4");			
//			
//		}		
//	}
	
	public static String TransBigString(Object item){
		if(item instanceof List){
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			List<Document> dl= (List<Document>)item;
			for(int i=0;i<dl.size();i++){
				Document d = dl.get(i);
				sb.append(JSONObject.parse(d.toJson()));
				if(i!=dl.size()-1){
					sb.append(",");
				}
			}
			sb.append("]");
			return sb.toString();
		}else if(item instanceof Document){
			return ((Document) item).toJson();
		}else {
			return null;
		}
	}
}

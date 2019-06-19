package com.alibaba.datax.plugin.reader.mongodbreader.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.datax.common.exception.DataXException;
import com.alibaba.datax.plugin.reader.mongodbreader.MongoDBReaderErrorCode;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

public class MongoDBUtil {
    private static final Logger LOG = LoggerFactory.getLogger(MongoDBUtil.class);

    public static MongoClient mongoClient = null;

    static {
    	String dbUrl = "10.10.128.202";
		int port = 6011;
		String dbUserName = "boss";
		String dbPassword = "4Ckh7kHXrPpw6DEf";
		String dbName = "analy4";
		String question_collection = "@Question";
		mongoClient = MongoDBUtil.mongoClient(dbUrl, port, dbUserName, dbName, dbPassword);
    }
    
    public MongoDBUtil() {
    }

    public static MongoClient mongoClient(String host, Integer port, String username, String dbName, String password) {
            try {
                ServerAddress serverAddress = new ServerAddress(host, port);
                MongoCredential mongoCredential = MongoCredential.createScramSha1Credential(username, dbName, password.toCharArray());
                return new MongoClient(serverAddress, Arrays.asList(mongoCredential));
            } catch (Exception e) {
                throw DataXException.asDataXException(MongoDBReaderErrorCode.UNEXCEPT_EXCEPTION,"custom MongoDBUtil异常");
            }
    }



    /**
     * find of query condition
     *
     *
     * @param mongoClient
     * @param dbName
     * @param collectionName
     * @param query
     * @param sort
     * @return
     */
    public static List<Document> find(MongoClient mongoClient, String dbName,
                                      String collectionName,
                                      BasicDBObject query,
                                      BasicDBObject projection,
                                      BasicDBObject sort,
                                      int limit) {
        FindIterable<Document> documents = mongoClient.getDatabase(dbName).getCollection(collectionName).find(query);
        if (null != sort) {
            documents.sort(sort);
        }
        if (-1 != limit) {
            documents.limit(limit);
        }
        if (null != projection) {
            documents.projection(projection);
        }
        List<Document> resultList = new ArrayList<Document>();
        MongoCursor<Document> mongoCursor = documents.iterator();
        while (mongoCursor.hasNext()) {
            resultList.add(mongoCursor.next());
        }
        return resultList;
    }
}

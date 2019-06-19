package com.alibaba.datax.plugin.reader.mongodbreader.util;

import java.util.List;

import org.bson.Document;

import com.alibaba.fastjson.JSONObject;

public class DocumentToBigString {
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

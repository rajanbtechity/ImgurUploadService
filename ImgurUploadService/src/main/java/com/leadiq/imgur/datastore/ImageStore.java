package com.leadiq.imgur.datastore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ImageStore {
	
	//public static Map<String,String> imgIdWithLink=new HashMap<String,String>();
	/*
	 * Whenever we upload an image, in UploadImageHandler.java we are extracting imageurl
	 * from json response and adding that to this imgLinkList.
	 * In GetImageHandler we are just iterating through the arrayList preparing the Json Repsonse
	 * and returning back.
	 * 
	 * 
	 * Another way of doing it is instead of an ArrayList we can create a JSONObject here.
	 * and Instead of adding imgLink to ArrayList we can keep adding the link to the JSONObject.
	 * In GetImageHandler we can simply return this JSONObject.
	 */
	public static ArrayList<String> imgLinkList=new ArrayList<String>();

}

package com.leadiq.imgur.handler;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.leadiq.imgur.datastore.ImageStore;

public class GetImageHandler {
	
	public static String processGetImage()
	{
		JSONObject imglink=new JSONObject();
		JSONArray imglist=new JSONArray();
		
		for(int i=0;i<ImageStore.imgLinkList.size();i++)
		{
			imglist.put(ImageStore.imgLinkList.get(i));
		}
		
		return imglink.put("uploaded", imglist).toString();
	}

}

package com.leadiq.imgur.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONArray;
import org.json.JSONObject;

import com.leadiq.imgur.webservice.UploadImageService;

public class UploadImageHandler {
	
	public static String processUploadImage(InputStream incomingData) {
		StringBuilder inputData = new StringBuilder();
		String hashId="";
		String imgUrl="";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					incomingData));
			String line = null;
			while ((line = br.readLine()) != null) {
				inputData.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JSONObject finalJsonRsp = new JSONObject();
		JSONObject imageUploadRsp=null;
		try {
			JSONObject inputJson = new JSONObject(inputData.toString());
			JSONArray urlArray = inputJson.optJSONArray("urls");

			for (int i = 0; i < urlArray.length(); i++) {
				
				imageUploadRsp=new JSONObject(UploadImageService.uploadImage(urlArray.get(i).toString()));
				hashId=imageUploadRsp.optJSONObject("data").optString("id");
				imgUrl=imageUploadRsp.optJSONObject("data").optString("link");
				System.out.println("HashId:"+hashId);
				System.out.println("imgUrl:"+imgUrl);
				SaveDataInDataStore.storeImgIdWithUrl(imgUrl);
				finalJsonRsp.putOpt(urlArray.get(i).toString(),imageUploadRsp.toString());

			}
		} catch (IOException e) {
			e.printStackTrace();

		}

		System.out.println(finalJsonRsp.toString());

		return finalJsonRsp.toString();

	}

}

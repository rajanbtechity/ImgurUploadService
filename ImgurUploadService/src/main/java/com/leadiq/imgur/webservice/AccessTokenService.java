package com.leadiq.imgur.webservice;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.json.JSONObject;

import com.leadiq.imgur.util.ImgurUtil;

public class AccessTokenService {
	
	public static String doHttpClient()
	{
		OkHttpClient client = new OkHttpClient();
		JSONObject jsonRsp=null;
/*
		MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
		RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"refresh_token\"\r\n\r\ne47c4bf022ed38453132689d3f5906c0ca5fcb34\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"client_id\"\r\n\r\n196836e2080d6b3\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"client_secret\"\r\n\r\n886b55c1cf86dc4bbc58493ddd889724349fe30e\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"grant_type\"\r\n\r\nrefresh_token\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
		*/
		MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
		RequestBody body = RequestBody.create(mediaType, 
				"------WebKitFormBoundary7MA4YWxkTrZu0gW"
				+ "\r\nContent-Disposition: form-data; name=\"refresh_token\"\r\n\r\n"+ImgurUtil.REFRESH_TOKEN_VAL
				+ "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW"
				+ "\r\nContent-Disposition: form-data; name=\"client_id\"\r\n\r\n"+ImgurUtil.CLIENT_ID_VAL
				+ "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW"
				+ "\r\nContent-Disposition: form-data; name=\"client_secret\"\r\n\r\n"+ImgurUtil.CLIENT_SECRET_VAL
				+ "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW"
				+ "\r\nContent-Disposition: form-data; name=\"grant_type\"\r\n\r\nrefresh_token"
				+ "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
				Request request = new Request.Builder()
		  .url(ImgurUtil.IMGUR_ENDPOINT.GENERATE_ACCESS_TOKEN)
		  .post(body)
		  .build();

		try {
			Response response = client.newCall(request).execute();
			jsonRsp=new JSONObject(response.body().string());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return jsonRsp.optString("access_token");
		return jsonRsp.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(doHttpClient());

	}

}

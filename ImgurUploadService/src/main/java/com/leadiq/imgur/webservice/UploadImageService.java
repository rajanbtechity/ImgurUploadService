package com.leadiq.imgur.webservice;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;

import com.leadiq.imgur.handler.UploadImageHandler;
import com.leadiq.imgur.util.ImgurUtil;

public class UploadImageService {
	
	public static String uploadImage(String encodedImage) throws IOException
	{
		OkHttpClient client = new OkHttpClient();
		Response response=null;
		
        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
        RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"image\"\r\n\r\n"+encodedImage+"\n\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
        Request request = new Request.Builder()
          .url(ImgurUtil.IMGUR_ENDPOINT.UPLOAD_IMAGE)
          .post(body)
          .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
          .addHeader("Authorization", "Bearer "+ImgurUtil.ACCESS_TOKEN_VAL)
          .build();
		
		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response.body().string();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println(uploadImage(UploadImageHandler.getBase64encodedImage("https://i.imgur.com/RZ8EGJL.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

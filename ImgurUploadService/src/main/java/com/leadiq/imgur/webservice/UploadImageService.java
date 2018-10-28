package com.leadiq.imgur.webservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.apache.commons.codec.binary.Base64;

public class UploadImageService {
	
	public static void uploadImage()
	{
		OkHttpClient client = new OkHttpClient();
		File originalFile = new File("C:\\Users\\Rajan\\Desktop\\testimgurimage1.gif");
        String encodedBase64 = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(originalFile);
            byte[] bytes1 = new byte[(int)originalFile.length()];
            fileInputStreamReader.read(bytes1);
            encodedBase64 = new String(Base64.encodeBase64(bytes1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
        RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"image\"\r\n\r\n"+encodedBase64+"\n\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
        Request request = new Request.Builder()
          .url("https://api.imgur.com/3/image")
          .post(body)
          .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
          .addHeader("Authorization", "Bearer 214a552d029520cceec9b6063e82e430d6687bc8")
          .addHeader("cache-control", "no-cache")
          .addHeader("Postman-Token", "d1147158-4b3f-48ca-abc9-ad59300538f1")
          .build();
		
		try {
			Response response = client.newCall(request).execute();
			System.out.println(response.body().string());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		uploadImage();

	}

}

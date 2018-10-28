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

import com.leadiq.imgur.util.ImgurUtil;

public class UploadImageService {
	
	public static String uploadImage(String fileUrl) throws IOException
	{
		OkHttpClient client = new OkHttpClient();
		Response response=null;
		File originalFile = new File(fileUrl);
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
			System.out.println(uploadImage("C:\\Users\\Rajan\\Desktop\\testimgurimage1.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

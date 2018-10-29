package com.leadiq.imgur.handler;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import com.leadiq.imgur.webservice.UploadImageService;

public class UploadImageHandler {

	public static String processUploadImage(InputStream incomingData) {
		StringBuilder inputData = new StringBuilder();
		String hashId = "";
		String imgUrl = "";
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
		JSONObject imageUploadRsp = null;
		try {
			JSONObject inputJson = new JSONObject(inputData.toString());
			JSONArray urlArray = inputJson.optJSONArray("urls");

			for (int i = 0; i < urlArray.length(); i++) {

				imageUploadRsp = new JSONObject(
						UploadImageService.uploadImage(getBase64encodedImage(urlArray.get(i)
								.toString())));
				hashId = imageUploadRsp.optJSONObject("data").optString("id");
				imgUrl = imageUploadRsp.optJSONObject("data").optString("link");
				SaveDataInDataStore.storeImgIdWithUrl(imgUrl);
				finalJsonRsp.putOpt(urlArray.get(i).toString(),
						imageUploadRsp.toString());

			}
		} catch (IOException e) {
			e.printStackTrace();

		}

		System.out.println("File Upload Json Response:"
				+ finalJsonRsp.toString());

		return finalJsonRsp.toString();

	}

	public static String getBase64encodedImage(String url) {
		String encodedBase64 = null;

		/*
		 * If url is hosted on internet pass http url download the image using
		 * URLConnection and getInputStream and convert into Base64 For
		 * example-https://imgur.com/RZ8EGJL
		 */
		if (url.startsWith("http")) {
			try {
				URL imageUrl = new URL(url);
				URLConnection ucon = imageUrl.openConnection();
				InputStream is = ucon.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int read = 0;
				while ((read = is.read(buffer, 0, buffer.length)) != -1) {
					baos.write(buffer, 0, read);
				}
				baos.flush();
				encodedBase64 = new String(Base64.encodeBase64(baos
						.toByteArray()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/*
		 * Use Desktop url. Read the image from file and convert it into Base64
		 * Format. For Example=C:\\Users\\Rajan\\Desktop\\testimgurimage1.gif
		 */
		else {
			File originalFile = new File(url);

			try {
				FileInputStream fileInputStreamReader = new FileInputStream(
						originalFile);
				byte[] bytes1 = new byte[(int) originalFile.length()];
				fileInputStreamReader.read(bytes1);
				encodedBase64 = new String(Base64.encodeBase64(bytes1));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return encodedBase64;
	}

}

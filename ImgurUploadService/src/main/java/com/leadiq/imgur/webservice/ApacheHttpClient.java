package com.leadiq.imgur.webservice;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.leadiq.imgur.util.ImgurUtil;

public class ApacheHttpClient {
	
	public static String doRestCall() {
		// TODO Auto-generated method stub
		String final_rsp = "";
		
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost postRequest=new HttpPost(ImgurUtil.IMGUR_ENDPOINT.GENERATE_ACCESS_TOKEN);
			HttpEntity entity=MultipartEntityBuilder.create()
					.addTextBody(ImgurUtil.CLIENT_ID,ImgurUtil.CLIENT_ID_VAL)
					.addTextBody(ImgurUtil.CLIENT_SECRET,ImgurUtil.CLIENT_SECRET_VAL)
					.addTextBody(ImgurUtil.REFRESH_TOKEN,ImgurUtil.REFRESH_TOKEN_VAL)
					.build();
			postRequest.setEntity(entity);
			HttpResponse response = httpClient.execute(postRequest);
			if(response.getStatusLine().getStatusCode()!=201 & response.getStatusLine().getStatusCode()!=200)
			{
				throw new RuntimeException("Failed with response code:"+response.getStatusLine().getStatusCode());
			}
			
			final_rsp = EntityUtils.toString(response.getEntity());
		}
		catch (MalformedURLException e)
        {
            System.out.println("httpClientProtocolException :" + e.toString());
        }
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		System.out.println("Response:"+final_rsp);
		return final_rsp;

	}
	
	public static void main(String[] args)
	{
		System.out.println("WebService Response:"+doRestCall());
	}

}

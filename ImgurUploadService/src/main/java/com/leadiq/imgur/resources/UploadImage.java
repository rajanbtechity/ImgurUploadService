package com.leadiq.imgur.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.leadiq.imgur.webservice.UploadImageService;

@Path("upoadimage")
public class UploadImage {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response uploadImageimgur(InputStream incomingData) {
		StringBuilder inputData = new StringBuilder();
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

		JSONObject jsonRsp = new JSONObject();
		try {
			JSONObject inputJson = new JSONObject(inputData.toString());
			JSONArray urlArray = inputJson.optJSONArray("urls");

			for (int i = 0; i < urlArray.length(); i++) {

				jsonRsp.putOpt(urlArray.get(i).toString(), UploadImageService
						.uploadImage(urlArray.get(i).toString()));

			}
		} catch (IOException e) {
			e.printStackTrace();

		}

		System.out.println(jsonRsp.toString());

		return Response.status(Status.OK).entity(jsonRsp.toString()).build();

	}
}

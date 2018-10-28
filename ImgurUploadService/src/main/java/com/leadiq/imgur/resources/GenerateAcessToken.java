package com.leadiq.imgur.resources;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import com.leadiq.imgur.util.ImgurUtil;
import com.leadiq.imgur.webservice.AccessTokenService;

@Path("getaccesstoken")
public class GenerateAcessToken {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccessToken()
	{
		String accessToken=AccessTokenService.doHttpClient();
		JSONObject json=new JSONObject(accessToken);
		
		return Response.status(Status.OK).entity(accessToken).build();
	}

}

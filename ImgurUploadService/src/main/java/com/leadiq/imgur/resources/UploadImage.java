package com.leadiq.imgur.resources;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("upoadimage")
public class UploadImage {
	
	@Produces(MediaType.APPLICATION_JSON)
	public String uploadImageimgur()
	{
		return "Testing";
	}

}

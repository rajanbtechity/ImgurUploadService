package com.leadiq.imgur.resources;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.leadiq.imgur.handler.GetImageHandler;
import com.leadiq.imgur.handler.UploadImageHandler;
import com.leadiq.imgur.webservice.AccessTokenService;

@Path("images")
public class ImgurProjectResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllImages()
	{
		String getImgRsp=GetImageHandler.processGetImage();
		return Response.status(Status.OK).entity(getImgRsp).build();
		
	}
	
	@GET
	@Path("/upload/{jobId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJobStatus(@PathParam("jobId") int id)
	{
		return null;
	}
	
	
	@GET
	@Path("/getaccesstoken")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccessToken()
	{
		String accessToken=AccessTokenService.doHttpClient();
		return Response.status(Status.OK).entity(accessToken).build();
	}
	
	@POST
	@Path("/upload")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response uploadImageimgur(InputStream incomingData) {
		String uploadImgRsp=UploadImageHandler.processUploadImage(incomingData);
		return Response.status(Status.OK).entity(uploadImgRsp).build();

	}

}

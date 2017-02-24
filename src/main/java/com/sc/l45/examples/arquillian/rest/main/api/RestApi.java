package com.sc.l45.examples.arquillian.rest.main.api;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sc.l45.examples.arquillian.rest.main.api.dto.RestErrorResponse;
import com.sc.l45.examples.arquillian.rest.main.api.dto.RestExampleResponse;
import com.sc.l45.examples.arquillian.rest.main.api.mgr.ApiMgr;

@Path("")
@RequestScoped
public class RestApi {
	
	@Inject
	private ApiMgr apiMgr;
	private final static Logger logger = LoggerFactory.getLogger(RestApi.class);
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/getAResponse")
    public Response getAResponse(@QueryParam("something") String input) {
		try {
			logger.info("Someone want a response");
			RestExampleResponse response = apiMgr.getAResponse(input);
			return Response.status(200).entity(response).build();
		} catch (Exception e) {
			logger.error("Error while calling /getAResponse", e);
			return apiError(e.getMessage());
		}
    }

	private Response apiError(String message) {
		if(message == null) {
			message = "There was a null pointer";
		}
		return Response.status(200).entity(new RestErrorResponse(message)).build();
	}
}

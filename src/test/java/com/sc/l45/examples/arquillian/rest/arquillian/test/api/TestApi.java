package com.sc.l45.examples.arquillian.rest.arquillian.test.api;

import java.net.URISyntaxException;
import java.net.URL;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.JerseyClientBuilder;

/**
 * I make calls to API simple.
 */
public class TestApi {
	private WebTarget client;
	
	public TestApi(URL baseUrl) throws URISyntaxException {
		initClient(baseUrl);
	}

	public String getFileData(String filePath) {
		return client
        .path("api/getAResponse")
        .queryParam("something", filePath)
        .request(MediaType.APPLICATION_JSON)
        .get()
        .readEntity(String.class);
	}
	
	public void initClient(URL baseUrl) throws URISyntaxException {
		if(baseUrl == null) {
			throw new IllegalAccessError("Base url cannot be null");
		}
		client = JerseyClientBuilder.newClient().target(baseUrl.toURI());
	}
}

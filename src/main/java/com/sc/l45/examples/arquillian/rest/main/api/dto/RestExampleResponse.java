package com.sc.l45.examples.arquillian.rest.main.api.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RestExampleResponse {
	public String response;
	
	/**
	 * Empty constructor is important to let the MessageBodyWriter initialize class
	 */
	@SuppressWarnings("unused")
	private RestExampleResponse() {}
	
	public RestExampleResponse(String response) {
		this.response = response;
	}
}

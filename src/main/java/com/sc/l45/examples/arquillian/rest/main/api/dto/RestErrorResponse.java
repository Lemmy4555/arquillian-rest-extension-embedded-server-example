package com.sc.l45.examples.arquillian.rest.main.api.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RestErrorResponse {
	public String message;
	
	/**
	 * Empty constructor is important to let the MessageBodyWriter initialize class
	 */
	@SuppressWarnings("unused")
	private RestErrorResponse() {}
	
	public RestErrorResponse(String message) {
		this.message = message;
	}
}

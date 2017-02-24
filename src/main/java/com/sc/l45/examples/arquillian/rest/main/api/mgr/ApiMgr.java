package com.sc.l45.examples.arquillian.rest.main.api.mgr;

import org.apache.commons.lang3.StringUtils;

import com.sc.l45.examples.arquillian.rest.main.api.dto.RestExampleResponse;

public class ApiMgr {
	public RestExampleResponse getAResponse(String input) {
		if(StringUtils.isEmpty(input)) {
			throw new IllegalArgumentException("I've nothing to say");
		}
		return new RestExampleResponse("This is a rest response: " + input);
	}
}

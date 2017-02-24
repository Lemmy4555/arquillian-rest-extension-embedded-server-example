package com.sc.l45.examples.arquillian.rest.arquillian.test;

import java.net.URISyntaxException;
import java.net.URL;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sc.l45.examples.arquillian.rest.main.api.dto.RestErrorResponse;
import com.sc.l45.examples.arquillian.rest.main.api.dto.RestExampleResponse;


public class RestApiTest extends TestConf {
	private final static Logger logger = LoggerFactory.getLogger(RestApiTest.class);
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	@RunAsClient
	public void withoutWebTargetInjection(@ArquillianResource URL baseURL) throws URISyntaxException {
		String plainResp = api(baseURL).getFileData("Without WebTarget Injection");
		readResponse(plainResp);
	}
	
	@Test
	@RunAsClient
	public void withWebTargetInjection(@ArquillianResteasyResource("api/getAResponse") final WebTarget webTarget) {
		String plainResp = webTarget.queryParam("something", "With WebTarget Injection")
        .request(MediaType.APPLICATION_JSON)
        .get()
        .readEntity(String.class);
		readResponse(plainResp);
	}
	
	@RunAsClient
	@Test(expected=JsonParseException.class)
	public void withWebTargetInjectionButNoEndpointProvided(@ArquillianResteasyResource final WebTarget webTarget) {
		exception.expect(AssertionError.class);
		webTarget.queryParam("something", "With WebTarget Injection but no enpoint provided")
        .request(MediaType.APPLICATION_JSON)
        .get()
        .readEntity(String.class);
		logger.info("The test \"withWebTargetInjectionButNoEndpointProvided\" correctly throw exception "
				+ "because by default @ArquillianResteasyResource"
				+ "will point to http://(host)/(appName)/rest, but my rest api are deployed "
				+ "under http://(host)/(appName)/api as configured in ApplicationConfig class");
	}
	
	private void readResponse(String plainResp) {
		Object response = getResponse(plainResp);
		Assert.assertNotNull(response);
		
		if(response instanceof RestExampleResponse) {
			logger.info(((RestExampleResponse) response).response);
		} else {
			logger.info(((RestErrorResponse) response).message);
		}
	}
	
	private Object getResponse(String plainResp) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(plainResp, RestExampleResponse.class);
		} catch (Exception e) {
			logger.warn("Error while convertig api Reponse to RestExampleResponse", e);
			try {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.readValue(plainResp, RestErrorResponse.class);
			} catch (Exception e1) {
				logger.warn("Error while convertig api Reponse to RestErrorResponse", e);
				throw new RuntimeException("Invalid api response", e);
			}
		}
	}
}

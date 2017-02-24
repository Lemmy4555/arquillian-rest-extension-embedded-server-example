package com.sc.l45.examples.arquillian.rest.arquillian.test;

import java.net.URISyntaxException;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sc.l45.examples.arquillian.rest.arquillian.test.api.TestApi;

@RunWith(Arquillian.class)
public class TestConf {
	private final static Logger logger = LoggerFactory.getLogger(TestConf.class);
	
	public final static String APP_NAME = "arquillian-rest-extension-example";
	
	private TestApi testApi;
	
	@Rule
	public TestWatcher testWatcher = new TestWatcher() {
		protected void failed(Throwable e, Description description) {
			logger.error("{} failed", description.getDisplayName(), e);
			super.failed(e, description);
		}
		
	};
	
	@Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap
            .create(WebArchive.class, APP_NAME+".war")
            .addPackages(true, "com.sc.l45.examples.arquillian.rest.main")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .addAsResource("arquillian.xml");
    }
	
	public TestApi api(URL baseUrl) throws URISyntaxException {
		if(this.testApi == null) {
			testApi = new TestApi(baseUrl);
		}
		return testApi;
	}

}

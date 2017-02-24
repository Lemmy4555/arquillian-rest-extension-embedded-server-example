package com.sc.l45.examples.arquillian.rest.main.config;

import java.util.Set;
import javax.ws.rs.core.Application;

import com.sc.l45.examples.arquillian.rest.main.api.RestApi;

@javax.ws.rs.ApplicationPath("/api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(CORSFilter.class);
        resources.add(RestApi.class);
    }
    
}

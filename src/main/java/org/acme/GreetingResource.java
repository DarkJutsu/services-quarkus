package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestQuery;

@Path("/hello")
public class GreetingResource {

    /**
     * Returns a greeting message based on the provided name.
     * @param name The name to be included in the greeting message.
     * @return A greeting message in plain text format.
     */
    @GET // HTTP GET method
    @Produces(MediaType.TEXT_PLAIN) // Produces plain text response
    public String hello(@RestQuery String name) { // @RestQuery - Binds the query parameter 'name' from the request URL to the method parameter
        return "Hello " + name;
    }
}

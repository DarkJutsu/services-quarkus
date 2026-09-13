package org.acme;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.List;
import java.util.stream.Collectors;

@Path("/hello")
public class GreetingResource {

    /**
     * Returns a greeting message based on the provided name.
     * @param name The name to be included in the greeting message.
     * @return A greeting message in plain text format.
     */
    @GET // HTTP GET method
    @Transactional // Ensures that the method runs within a transaction
    @Produces(MediaType.TEXT_PLAIN) // Produces plain text response
    public String hello(@RestQuery String name) { // @RestQuery - Binds the query parameter 'name' from the request URL to the method parameter
        Greeting greeting =new Greeting();
        greeting.name=name;
        greeting.persist(); // Persist the Greeting entity to the database
        return "Hello " + name;
    }

    /**
     * Returns a list of all names that have been greeted.
     * @return A string containing all greeted names, separated by commas.
     */
    @GET
    @Path("names") // Endpoint to retrieve all names that have been greeted
    @Produces(MediaType.TEXT_PLAIN)
    public String Names(){
        List<Greeting> greetings=Greeting.listAll(); // Retrieve all Greeting entities from the database
        String names=greetings.stream().map(g->g.name).collect(Collectors.joining(", ")); // Collect all names into a single string, separated by commas
        return "I have said hello to " + names;
    }
}

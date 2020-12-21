package com.bookmark.resources;

import com.bookmark.core.User;
import com.bookmark.core.Users;
import com.bookmark.db.JdbiEntityRepository;
import io.dropwizard.auth.Auth;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/hello")
public class HelloResource {

    private JdbiEntityRepository jdbiEntityRepository;

    public HelloResource(JdbiEntityRepository jdbiEntityRepository) {
        this.jdbiEntityRepository = jdbiEntityRepository;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        return "Hello dropwizard";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/secured")
    public String getSecuredMessage(@Auth User user) {
        return "Hello secured dropwizard";
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response.Status addUser(@Valid Users user) {
        jdbiEntityRepository.addUser(user);
        return Response.Status.ACCEPTED;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{username}")
    public List<Users> getUsers(@PathParam("username") String username) {
        return jdbiEntityRepository.getUsersList(username);
    }

}

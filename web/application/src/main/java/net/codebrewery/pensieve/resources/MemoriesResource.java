package net.codebrewery.pensieve.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import net.codebrewery.pensieve.services.MemoriesService;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/memories")
@Produces(APPLICATION_JSON)
public class MemoriesResource {

    private final MemoriesService memoriesService;

    @Inject
    public MemoriesResource(MemoriesService memoriesService) {
        this.memoriesService = memoriesService;
    }

    @POST
    @Timed
    public String create() {
        return "ok";
    }

    @GET
    public String get() {
        return "ok";
    }

    @PUT
    public String update() {
        return "ok";
    }

    @DELETE
    public String delete() {
        return "ok";
    }
}

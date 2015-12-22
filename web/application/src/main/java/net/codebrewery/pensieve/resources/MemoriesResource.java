package net.codebrewery.pensieve.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import net.codebrewery.pensieve.database.MemoriesDAO;
import net.codebrewery.pensieve.domain.Memory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/memories")
@Produces(APPLICATION_JSON)
public class MemoriesResource {

    private final MemoriesDAO memoriesDAO;

    @Inject
    public MemoriesResource(MemoriesDAO memoriesDAO) {
        this.memoriesDAO = memoriesDAO;
    }

    @POST
    @Timed
    @Consumes(APPLICATION_JSON)
    public void create(Memory memory) {
        memoriesDAO.create(memory);
    }

    @GET
    @Path("/{id}")
    public String get(@PathParam("id") UUID uuid) {
        return memoriesDAO.readAsJSON(uuid);
    }

    @GET
    public List<String> getAll() {
        return memoriesDAO.readAllAsJSON();
    }

}

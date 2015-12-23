package net.codebrewery.pensieve.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.inject.Inject;
import net.codebrewery.pensieve.database.MemoriesDAO;
import net.codebrewery.pensieve.domain.Memory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.IOException;
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
    public Memory get(@PathParam("id") UUID uuid) {
        try {
            return getObjectMapper().readValue(memoriesDAO.readAsJSON(uuid), Memory.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Shit Happened. TBD", e);
        }
    }

    @GET
    public List<Memory> getAll() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());

        String asJSON = memoriesDAO.readAllAsJSON();

        try {
            return getObjectMapper().readValue(asJSON, new TypeReference<List<Memory>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Shit Happened. TBD", e);
        }
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        return mapper;
    }
}

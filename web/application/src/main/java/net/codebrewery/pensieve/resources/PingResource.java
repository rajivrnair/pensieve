package net.codebrewery.pensieve.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import net.codebrewery.pensieve.database.ConnectionTestDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.concurrent.atomic.AtomicLong;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/ping")
@Produces(APPLICATION_JSON)
public class PingResource {
    private static final AtomicLong counter = new AtomicLong();
    private final ConnectionTestDAO dao;

    @Inject
    public PingResource(ConnectionTestDAO connectionTestDAO) {
        this.dao = connectionTestDAO;
    }

    @GET
    @Timed
    public String pong() {
        return String.format("Pong [%s]!. DB connected: %b", counter.getAndIncrement(), dao.ping());
    }
}

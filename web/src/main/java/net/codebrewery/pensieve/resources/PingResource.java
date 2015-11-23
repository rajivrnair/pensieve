package net.codebrewery.pensieve.resources;

import com.codahale.metrics.annotation.Timed;
import net.codebrewery.pensieve.database.ConnectionTestDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.concurrent.atomic.AtomicLong;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/ping")
@Produces(APPLICATION_JSON)
public class PingResource {

    private final AtomicLong counter;
    private final ConnectionTestDAO dao;

    public PingResource(AtomicLong counter, ConnectionTestDAO connectionTestDAO) {
        this.counter = counter;
        this.dao = connectionTestDAO;
    }

    @GET
    @Timed
    public String pong() {
        return String.format("Pong [%s]!. DB connected: %b", counter.getAndIncrement(), dao.ping());
    }
}

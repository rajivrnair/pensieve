package net.codebrewery.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.util.concurrent.atomic.AtomicLong;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/ping")
@Produces(APPLICATION_JSON)
public class PingResource {

    private final AtomicLong counter;

    public PingResource(AtomicLong counter) {
        this.counter = counter;
    }

    @GET
    @Timed
    public String pong() {
        return String.format("Pong [%s]!", counter.getAndIncrement());
    }
}

package net.codebrewery.application;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import net.codebrewery.config.PensieveConfiguration;
import net.codebrewery.health.PingHealthCheck;
import net.codebrewery.resources.PingResource;

import java.util.concurrent.atomic.AtomicLong;

public class PensieveApplication extends Application<PensieveConfiguration>{

    @Override
    public void run(PensieveConfiguration configuration, Environment environment) throws Exception {
        // Do this for every resource? Ugh?
        final PingResource ping = new PingResource(new AtomicLong());
        final PingHealthCheck pingHealthCheck = new PingHealthCheck();

        environment.healthChecks().register("ping", pingHealthCheck);
        environment.jersey().register(ping);
    }

    public static void main(String[] args) throws Exception {
        new PensieveApplication().run(args);
    }
}

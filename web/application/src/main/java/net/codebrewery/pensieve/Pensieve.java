package net.codebrewery.pensieve;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.codebrewery.pensieve.config.PensieveConfiguration;
import net.codebrewery.pensieve.database.ConnectionTestDAO;
import net.codebrewery.pensieve.health.PingHealthCheck;
import net.codebrewery.pensieve.resources.PingResource;
import org.skife.jdbi.v2.DBI;

import java.util.concurrent.atomic.AtomicLong;

public class Pensieve extends Application<PensieveConfiguration>{

    public static final String POSTGRESQL = "postgresql";

    @Override
    public void initialize(Bootstrap<PensieveConfiguration> bootstrap) {
        super.initialize(bootstrap);

        bootstrap.addBundle(new MigrationsBundle<PensieveConfiguration>() {
            @Override
            public PooledDataSourceFactory getDataSourceFactory(PensieveConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(PensieveConfiguration configuration, Environment environment) throws Exception {
        DBI database = setupDatabase(configuration, environment);
        setupResources(environment, database);
        setupHealthChecks(environment);
    }

    private DBI setupDatabase(PensieveConfiguration configuration, Environment environment) {
        final DBIFactory factory = new DBIFactory();
        DataSourceFactory dataSourceFactory = configuration.getDataSourceFactory();
        return factory.build(environment, dataSourceFactory, POSTGRESQL);
    }

    private void setupResources(Environment environment, DBI database) {
        final ConnectionTestDAO connectionTestDAO = database.onDemand(ConnectionTestDAO.class);
        final PingResource ping = new PingResource(new AtomicLong(), connectionTestDAO);

        environment.jersey().register(ping);
    }

    private void setupHealthChecks(Environment environment) {
        final PingHealthCheck pingHealthCheck = new PingHealthCheck();

        environment.healthChecks().register("ping", pingHealthCheck);
    }

    public static void main(String[] args) throws Exception {
        new Pensieve().run(args);
    }
}

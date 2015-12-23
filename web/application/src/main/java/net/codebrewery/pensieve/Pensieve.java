package net.codebrewery.pensieve;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.codebrewery.pensieve.config.PensieveConfiguration;
import net.codebrewery.pensieve.config.PensieveModule;

import java.text.SimpleDateFormat;

import static net.codebrewery.pensieve.config.PensieveConfiguration.getDefaultDateFormat;

public class Pensieve extends Application<PensieveConfiguration>{

    @Override
    public void initialize(Bootstrap<PensieveConfiguration> bootstrap) {
        super.initialize(bootstrap);

//        customiseObjectMapper(bootstrap);

        bootstrap.addBundle(new MigrationsBundle<PensieveConfiguration>() {
            @Override
            public PooledDataSourceFactory getDataSourceFactory(PensieveConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

        bootstrap.addBundle(GuiceBundle.<PensieveConfiguration>newBuilder()
                .addModule(new PensieveModule())
                .enableAutoConfig("net.codebrewery.pensieve")
                .setConfigClass(PensieveConfiguration.class)
                .build(Stage.DEVELOPMENT));
    }

    private void customiseObjectMapper(Bootstrap<PensieveConfiguration> bootstrap) {
        ObjectMapper mapper = bootstrap.getObjectMapper();
        mapper.registerModule(new JodaModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setDateFormat(new SimpleDateFormat(getDefaultDateFormat()));
    }

    @Override
    public void run(PensieveConfiguration configuration, Environment environment) throws Exception {
    }

    public static void main(String[] args) throws Exception {
        new Pensieve().run(args);
    }
}

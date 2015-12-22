package net.codebrewery.pensieve.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import net.codebrewery.pensieve.database.ConnectionTestDAO;
import net.codebrewery.pensieve.database.MemoriesDAO;
import org.skife.jdbi.v2.DBI;

public class PensieveModule extends AbstractModule {

    public static final String POSTGRESQL = "postgresql";
    private DBI database;

    @Provides
    public DBI prepareDatabase(PensieveConfiguration configuration, Environment environment) {
        if(database == null) {
            final DBIFactory factory = new DBIFactory();
            DataSourceFactory dataSourceFactory = configuration.getDataSourceFactory();
            database = factory.build(environment, dataSourceFactory, POSTGRESQL);
        }
        return database;
    }

    @Provides
    @Singleton
    public ConnectionTestDAO createConnectionTestDAO(DBI database) {
        return database.onDemand(ConnectionTestDAO.class);
    }

    @Provides
    @Singleton
    public MemoriesDAO createMemoriesDAO(DBI database) {
        return database.onDemand(MemoriesDAO.class);
    }

    @Override
    protected void configure() {

    }
}

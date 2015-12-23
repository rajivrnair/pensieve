package net.codebrewery.pensieve.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PensieveConfiguration extends Configuration {
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    @Valid
    @NotNull
    @JsonProperty("dataSource")
    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    public static String getDefaultDateFormat() {
        return DEFAULT_DATE_FORMAT;
    }
}

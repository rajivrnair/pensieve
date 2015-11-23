package net.codebrewery.pensieve.database;

import org.skife.jdbi.v2.sqlobject.SqlQuery;

public interface ConnectionTestDAO {

    @SqlQuery("select 1=1")
    boolean ping();
}

package net.codebrewery.pensieve.database;

import net.codebrewery.pensieve.domain.Memory;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.util.UUID;

public interface MemoriesDAO {

    @SqlUpdate("insert into memories (id, title, content, tags, created_on) values (:id, :title, :content, :tags, now())")
    void create(@BindBean Memory memory);

    @SqlQuery("select row_to_json(t) from (" +
                "select id, title, content, tags, created_on as createdOn from memories where id = :id" +
            ") t")
    String readAsJSON(@Bind("id") UUID id);

    @SqlQuery("select array_to_json(array_agg(row_to_json(t))) from (" +
            "select id, title, content, tags, created_on as createdOn from memories) t")
    String readAllAsJSON();
}

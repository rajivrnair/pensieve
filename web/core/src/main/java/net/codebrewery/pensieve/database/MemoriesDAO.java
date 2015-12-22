package net.codebrewery.pensieve.database;

import net.codebrewery.pensieve.domain.Memory;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.util.List;
import java.util.UUID;

public interface MemoriesDAO {

    @SqlUpdate("insert into memories (id, title, content, tags) values (:id, :title, :content, :tags)")
    void create(@BindBean Memory memory);

    @SqlQuery("select row_to_json(memories) from memories where id = :id")
    String readAsJSON(@Bind("id") UUID id);

    @SqlQuery("select row_to_json(memories) from memories")
    List<String> readAllAsJSON();
}

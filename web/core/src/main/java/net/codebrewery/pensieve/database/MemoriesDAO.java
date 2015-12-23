package net.codebrewery.pensieve.database;

import net.codebrewery.pensieve.database.mappers.MemoryMapper;
import net.codebrewery.pensieve.domain.Memory;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;
import java.util.UUID;

@RegisterMapper(MemoryMapper.class)
public interface MemoriesDAO {

    @SqlUpdate("insert into memories (id, title, content, tags, created_on) values (:id, :title, :content, :tags, now())")
    void create(@BindBean Memory memory);

    @SqlQuery("select * from memories where id = :id")
    Memory read(@Bind("id") UUID id);

    @SqlQuery("select * from memories")
    List<Memory> readAll();
}

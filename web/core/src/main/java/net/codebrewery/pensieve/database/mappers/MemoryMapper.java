package net.codebrewery.pensieve.database.mappers;

import net.codebrewery.pensieve.domain.Memory;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MemoryMapper implements ResultSetMapper<Memory>{

    @Override
    public Memory map(int index, ResultSet r, StatementContext ctx) throws SQLException {

        UUID id = UUID.fromString(r.getString("id"));
        String title = r.getString("title");
        String content = r.getString("content");
        String tags = r.getString("tags");
        DateTime createdOn = new DateTime(r.getTimestamp("created_on").getTime());

        return new Memory(id, title, content, tags, createdOn);
    }
}

package net.codebrewery.pensieve.database;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import net.codebrewery.pensieve.domain.Memory;
import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MemoriesDAOTest {

    private static DBI dbi = new DBI("jdbc:postgresql://localhost:5432/memories", "pensieve", "pensieve");
    MemoriesDAO dao;
    private static Handle handle;
    private static Liquibase liquibase;
    private UUID id = randomUUID();

    @BeforeClass
    public static void init() throws Exception {
        handle = dbi.open();
        liquibase = new Liquibase("migrations.xml", new ClassLoaderResourceAccessor(), new JdbcConnection(handle.getConnection()));
        liquibase.update("");
    }

    @AfterClass
    public static void tearDown() {
        try {
            liquibase.dropAll();
        } catch (Exception e) {
            throw new RuntimeException("failed clearing up Liquibase object", e);
        }
        handle.close();
    }

    @Before
    public void setUp() {
        dao = dbi.onDemand(MemoriesDAO.class);
    }

    @Test
    @Ignore
    public void createAndRead() throws IOException {
        Memory memory = new Memory(id, "title - more important shit", "content with {code} true == TRUE {code}", "[a,b]");
        dao.create(memory);

        String asJSON = dao.readAsJSON(id);
        memory = getObjectMapper().readValue(asJSON, Memory.class);

        assertThat(memory.getId(), is(id));
        assertThat(memory.getTitle(), is("title - more important shit"));
        assertThat(memory.getContent(), is("content with {code} true == TRUE {code}"));
        assertThat(memory.getTags(), is("[a,b]"));
        // I know time should probably be frozen here - but don't need to at this point.
        assertThat(memory.getCreatedOn().toString("dd-MM-yyyy HH"), is(DateTime.now().toString("dd-MM-yyyy HH")));
    }

    @Test
    @Ignore
    public void readAll() throws IOException {
        UUID anId = randomUUID();
        Memory memory = new Memory(anId, "important", "content with {code} true == TRUE {code}", "[a,b]");
        dao.create(memory);
        UUID anotherId = randomUUID();
        memory = new Memory(anotherId, "more important", "content with {code} true != FALSE {code}", null);
        dao.create(memory);

        String asJSON = dao.readAllAsJSON();

        System.out.println("memories-asJSON: " + asJSON);

        List<Memory> memories = getObjectMapper().readValue(asJSON, new TypeReference<List<Memory>>(){});
        System.out.println("memories: " + memories);
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        return mapper;
    }
}
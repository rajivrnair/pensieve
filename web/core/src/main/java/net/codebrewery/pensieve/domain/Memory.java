package net.codebrewery.pensieve.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.util.UUID;

public class Memory {
    private UUID id;

    private String title;

    private String content;

    private String tags;

    @JsonProperty("createdon")
    private DateTime createdOn;

    // Required for JDBI
    @SuppressWarnings("unused")
    public Memory() {
    }

    public Memory(UUID id, String title, String content, String tags) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTags() {
        return tags;
    }

    public DateTime getCreatedOn() {
        return createdOn;
    }
}

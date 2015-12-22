package net.codebrewery.pensieve.domain;

import java.util.UUID;

public class Memory {
    private UUID id;

    private String title;

    private String content;

    private String tags;

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

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}

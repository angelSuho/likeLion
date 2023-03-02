package org.example;

public class SayingEntity {

    Long id;
    String saying;
    String author;

    public void setSaying(String saying) {
        this.saying = saying;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaying() {
        return saying;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public SayingEntity(Long id, String saying, String author) {
        this.id = id;
        this.saying = saying;
        this.author = author;
    }
}

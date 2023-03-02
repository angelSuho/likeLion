package org.example.entity;

public class SayingEntity {

    int id;
    String saying;
    String author;

    public SayingEntity() {
    }

    public void setSaying(String saying) {
        this.saying = saying;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public SayingEntity(int id, String saying, String author) {
        this.id = id;
        this.saying = saying;
        this.author = author;
    }
}

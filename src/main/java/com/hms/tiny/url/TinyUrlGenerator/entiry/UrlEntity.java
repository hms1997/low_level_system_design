package com.hms.tiny.url.TinyUrlGenerator.entiry;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "url_mapping")
@Data
public class UrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    @Column(name = "tiny_url")
    String tinyUrl;
    @Column(name = "long_url", nullable = false)
    String longUrl;

    public UrlEntity() {

    }

    public UrlEntity(int id, String sortUrl, String longUrl) {
        this.id = id;
        this.tinyUrl = sortUrl;
        this.longUrl = longUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTinyUrl() {
        return tinyUrl;
    }

    public void setTinyUrl(String tinyUrl) {
        this.tinyUrl = tinyUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}

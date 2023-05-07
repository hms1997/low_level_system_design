package com.hms.tiny.url.TinyUrlGenerator.utils;

public class UrlShortenRequest {
    private String longUrl;

    public UrlShortenRequest() {
    }

    public UrlShortenRequest(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}

package com.hms.tiny.url.TinyUrlGenerator.service;

import com.hms.tiny.url.TinyUrlGenerator.utils.UrlShortenRequest;
import org.springframework.stereotype.Service;

@Service
public interface UrlServiceProvider {
    public String createAndReturnTinyUrl(UrlShortenRequest urlShortenRequest);
    public String getOriginalUrl(String tinyUrl);
}

package com.hms.tiny.url.TinyUrlGenerator.controller;

import com.hms.tiny.url.TinyUrlGenerator.service.UrlServiceProvider;
import com.hms.tiny.url.TinyUrlGenerator.utils.UrlShortenRequest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class TinyUrlController {
    @Autowired
    private UrlServiceProvider urlServiceProvider;

    /*public TinyUrlController(UrlServiceProvider urlServiceProvider) {
        this.urlServiceProvider = urlServiceProvider;
    }
*/
    @PostMapping("/shorten")
    public ResponseEntity<String> createTinyUrl(@RequestBody UrlShortenRequest urlShortenRequest) {
        String tinyUrl = urlServiceProvider.createAndReturnTinyUrl(urlShortenRequest);
        return tinyUrl == null ? ResponseEntity.badRequest().body("invalid request body") : ResponseEntity.ok(tinyUrl);
    }

    @GetMapping("/{tinyUrl}")
    public ResponseEntity<String> getLongUrl(@PathVariable String tinyUrl) {
        String originalUrl = urlServiceProvider.getOriginalUrl(tinyUrl);
        if(originalUrl == null) {
            return ResponseEntity.badRequest().build();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(originalUrl));
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }
}

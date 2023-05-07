package com.hms.tiny.url.TinyUrlGenerator.service;

import com.hms.tiny.url.TinyUrlGenerator.cache.LRUCache;
import com.hms.tiny.url.TinyUrlGenerator.entiry.UrlEntity;
import com.hms.tiny.url.TinyUrlGenerator.repository.TinyUrlRepository;
import com.hms.tiny.url.TinyUrlGenerator.utils.UrlShortenRequest;
import org.springframework.stereotype.Service;

@Service
public class TinyUrlService implements UrlServiceProvider{


    private TinyUrlRepository repository;

    private LRUCache lruCache;


    public TinyUrlService(TinyUrlRepository repository, LRUCache lruCache) {
        this.repository = repository;
        this.lruCache = lruCache;
    }

    @Override
    public String createAndReturnTinyUrl(UrlShortenRequest urlShortenRequest) {
        String longUrl = urlShortenRequest.getLongUrl();

        UrlEntity urlEntity =  repository.findByLongUrl(longUrl);
        if(urlEntity != null) return urlEntity.getTinyUrl();

        urlEntity = new UrlEntity();
        urlEntity.setLongUrl(longUrl);
        repository.save(urlEntity);

        int COUNTER = urlEntity.getId();

        String tinyUrl = generateTinyUrl(COUNTER);
        urlEntity.setTinyUrl(tinyUrl);
        repository.save(urlEntity);

        lruCache.set(tinyUrl, longUrl);
        return  tinyUrl;
    }

    @Override
    public String getOriginalUrl(String tinyUrl) {
        if(lruCache.get(tinyUrl) != null) return lruCache.get(tinyUrl);

        UrlEntity urlEntity = repository.findByTinyUrl(tinyUrl);
        if(urlEntity == null) return null;
        return urlEntity.getLongUrl();
    }

    private String generateTinyUrl(long id) {
        return base10ToBase62(id);
    }

    private String generateBase62Hash(long counter) {
        String base62String = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXXYZ";
        StringBuilder hashString = new StringBuilder();

        while(counter > 0) {
            hashString.append(base62String.charAt((int) (counter % 62)));
            counter /= 62;
        }
        return hashString.toString();
    }

    public String base10ToBase62(long n) {
        String base62String = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXXYZ";
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            sb.insert(0, base62String.charAt((int) (n % 62)));
            n /= 62;
        }
        while (sb.length() != 7) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }
}

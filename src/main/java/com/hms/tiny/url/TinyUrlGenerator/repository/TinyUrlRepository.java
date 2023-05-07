package com.hms.tiny.url.TinyUrlGenerator.repository;

import com.hms.tiny.url.TinyUrlGenerator.entiry.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TinyUrlRepository extends JpaRepository<UrlEntity, Long> {

    UrlEntity findByTinyUrl(String tinyUrl);
    UrlEntity findByLongUrl(String longUrl);

}

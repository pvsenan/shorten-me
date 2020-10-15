package se.pscode.shortenme.controller;

import lombok.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.pscode.shortenme.modal.UrlData;
import se.pscode.shortenme.modal.UrlRequest;

import java.util.Date;

@RestController
@RequestMapping("/api/v1")
public class UrlController {
    @PostMapping(path = "/generate")
    public UrlData generateShortUrl(@NonNull @RequestBody UrlRequest requestBody){
        UrlData urlData = new UrlData();
        urlData.setUrlId("abcd");
        urlData.setOriginalUrl("http://www.google.com");
        urlData.setCreatedDate(new Date());
        return urlData;
    }
}

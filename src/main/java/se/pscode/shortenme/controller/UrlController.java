package se.pscode.shortenme.controller;

import lombok.NonNull;
import org.springframework.web.bind.annotation.*;
import se.pscode.shortenme.entity.UrlData;
import se.pscode.shortenme.modal.UrlRequest;
import se.pscode.shortenme.services.UrlShortenerService;

@RestController
@RequestMapping("/api/v1")
public class UrlController {
    private UrlShortenerService urlShortenerService;

    public UrlController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping(path = "/generate")
    public UrlData generateShortUrl(@NonNull @RequestBody UrlRequest requestBody) {
        return urlShortenerService.createShortenedUrl(requestBody);
    }

    @GetMapping(path = "/id/{id}")
    public UrlData getOriginalUrl(@PathVariable Integer id){
        return urlShortenerService.getOriginalUrl(id);
    }
}

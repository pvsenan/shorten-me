package se.pscode.shortenme.controller;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UrlData> generateShortUrl(@NonNull @RequestBody UrlRequest requestBody) {
        UrlData urlDataResponseEntity = urlShortenerService.createShortenedUrl(requestBody);
        return ResponseEntity.ok().body(urlDataResponseEntity);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<UrlData> getOriginalUrl(@PathVariable Integer id) {
        UrlData urlData = urlShortenerService.getOriginalUrl(id);
        return ResponseEntity.ok().body(urlData);
    }
}

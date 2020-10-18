package se.pscode.shortenme.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.pscode.shortenme.entity.UrlData;
import se.pscode.shortenme.modal.ErrorModal;
import se.pscode.shortenme.modal.UrlRequest;
import se.pscode.shortenme.services.UrlShortenerService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UrlController {
    private UrlShortenerService urlShortenerService;

    public UrlController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping(path = "/generate")
    public ResponseEntity<UrlData> generateShortUrl(@Valid @RequestBody UrlRequest requestBody) {
        UrlData urlDataResponseEntity = urlShortenerService.createShortenedUrl(requestBody);
        return ResponseEntity.ok().body(urlDataResponseEntity);
    }

    @GetMapping(path = "/urlId/{id}")
    public ResponseEntity<UrlData> getOriginalUrl(@PathVariable Integer id) {
        UrlData urlData = urlShortenerService.getOriginalUrl(id);
        return ResponseEntity.ok().body(urlData);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorModal> handleRunTimeException(RuntimeException runtimeException){
        ErrorModal errorModal = new ErrorModal(runtimeException.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorModal);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorModal> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException){
        ErrorModal errorModal = new ErrorModal(illegalArgumentException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModal);
    }

}

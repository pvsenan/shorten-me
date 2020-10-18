package se.pscode.shortenme.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.pscode.shortenme.entity.UrlData;
import se.pscode.shortenme.modal.UrlRequest;
import se.pscode.shortenme.repository.UrlDataRepository;

import java.util.Date;

@Service
@Slf4j
public class UrlShortenerService {
    private final UrlDataRepository urlDataRepository;

    public UrlShortenerService(UrlDataRepository urlDataRepository) {
        this.urlDataRepository = urlDataRepository;
    }

    public UrlData createShortenedUrl(UrlRequest urlRequest){
        try {
            UrlData urlData = new UrlData();
            urlData.setOriginalUrl(urlRequest.getOriginalUrl());
            urlData.setCreatedDate(new Date());
            return urlDataRepository.save(urlData);
        }catch (RuntimeException runtimeException){
            log.error("Unable to create shortened url for {}", urlRequest.getOriginalUrl());
            throw runtimeException;
        }
    }

    public UrlData getOriginalUrl(Integer id) {
        return urlDataRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Unable to find url with id " + id));
    }
}

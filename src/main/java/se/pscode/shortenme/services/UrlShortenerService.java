package se.pscode.shortenme.services;

import org.springframework.stereotype.Service;
import se.pscode.shortenme.entity.UrlData;
import se.pscode.shortenme.modal.UrlRequest;
import se.pscode.shortenme.repository.UrlDataRepository;

import java.util.Date;

@Service
public class UrlShortenerService {
    private final UrlDataRepository urlDataRepository;

    public UrlShortenerService(UrlDataRepository urlDataRepository) {
        this.urlDataRepository = urlDataRepository;
    }

    public UrlData createShortenedUrl(UrlRequest urlRequest){
        UrlData urlData = new UrlData();
        urlData.setOriginalUrl(urlRequest.getOriginalUrl());
        urlData.setCreatedDate(new Date());
        return urlDataRepository.save(urlData);
    }

    public UrlData getOriginalUrl(Integer id){
        return urlDataRepository.findById(id).orElse(null);
    }
}

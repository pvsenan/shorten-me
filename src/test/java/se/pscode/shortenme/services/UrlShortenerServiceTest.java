package se.pscode.shortenme.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.pscode.shortenme.entity.UrlData;
import se.pscode.shortenme.modal.UrlRequest;
import se.pscode.shortenme.repository.UrlDataRepository;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UrlShortenerServiceTest {

    @Mock
    UrlDataRepository urlDataRepository;

    @InjectMocks
    UrlShortenerService urlShortenerService;

    @Test
    void shouldCreateShortenedUrlAndSave() {
        UrlRequest request = new UrlRequest();
        request.setOriginalUrl("https://www.psco.de");

        UrlData mockedResult = new UrlData();
        mockedResult.setCreatedDate(new Date());
        mockedResult.setOriginalUrl("https://www.psco.de");
        mockedResult.setUrlId(1);
        when(urlDataRepository.save(any(UrlData.class))).thenReturn(mockedResult);

        UrlData response = urlShortenerService.createShortenedUrl(request);
        Assertions.assertEquals(1, response.getUrlId());
    }

    @Test
    void shouldThrowRuntimeExceptionIfNotAbleToSave() {
        UrlRequest request = new UrlRequest();
        request.setOriginalUrl("https://www.psco.de");

        when(urlDataRepository.save(any(UrlData.class)))
                .thenThrow(new RuntimeException("Unable to create shortened url for " + request.getOriginalUrl()));

        Assertions.assertThrows(RuntimeException.class, () -> urlShortenerService.createShortenedUrl(request));
    }

    @Test
    void shouldReturnUrlDataWhenFindingById() {
        UrlData mockedResult = new UrlData();
        mockedResult.setCreatedDate(new Date());
        mockedResult.setOriginalUrl("https://www.psco.de");
        mockedResult.setUrlId(1);
        when(urlDataRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(mockedResult));

        UrlData response = urlShortenerService.getOriginalUrl(1);
        Assertions.assertEquals(1, response.getUrlId());
    }

    @Test
    void shouldThrowRuntimeExceptionRequestedUrlNotFoundById() {
        when(urlDataRepository.findById(any(Integer.class)))
                .thenThrow(new RuntimeException("Unable to find url with id 1"));

        Assertions.assertThrows(RuntimeException.class, () -> urlShortenerService.getOriginalUrl(1));
    }
}
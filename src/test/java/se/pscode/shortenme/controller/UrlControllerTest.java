package se.pscode.shortenme.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import se.pscode.shortenme.modal.UrlRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class UrlControllerTest {

    @Autowired
    MockMvc mockMvc;

    private final String url = "https://www.psco.de";

    @Test
    void shouldGenerateShortenedUrlAndSave() throws Exception {
        UrlRequest urlRequest = new UrlRequest();
        urlRequest.setOriginalUrl(url);
        ObjectMapper mapper = new ObjectMapper();

        MvcResult result = mockMvc.perform(post("/api/v1/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(urlRequest)))
                .andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        Assertions.assertTrue(result.getResponse().getContentAsString().contains(url));
    }

    @Test
    void shouldReturnOriginalUrlWhenGetByUrlId() throws Exception {
        UrlRequest urlRequest = new UrlRequest();
        urlRequest.setOriginalUrl(url);
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(urlRequest)));

        MvcResult result = mockMvc.perform(get("/api/v1/urlId/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(HttpStatus.FOUND.value(), result.getResponse().getStatus());
        Assertions.assertEquals(url, result.getResponse().getHeader("Location"));
    }

    @Test
    void shouldReturnNotFoundIfTheIdDoesntExists() throws Exception {
        UrlRequest urlRequest = new UrlRequest();
        urlRequest.setOriginalUrl(url);

        MvcResult result = mockMvc.perform(get("/api/v1/urlId/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }
}
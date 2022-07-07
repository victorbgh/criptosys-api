package br.com.criptosys.service;

import br.com.criptosys.dto.BtcPrice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class DashboardService {

    @Value("${btc.ticker.url}")
    private String btcUrl;

    public BtcPrice getBtcPrice() throws URISyntaxException, JsonProcessingException {
        URI uri = new URI(btcUrl);
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue( result.getBody() , BtcPrice.class);
    }
}

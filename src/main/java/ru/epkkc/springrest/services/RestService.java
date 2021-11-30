package ru.epkkc.springrest.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.epkkc.springrest.model.User;

@Service
public class RestService {

    private final String URL = "http://91.241.64.178:7081/api/users";

    private final RestTemplate rt = new RestTemplate();

    public HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public ResponseEntity<User[]> getMethod(HttpHeaders headers) {
        HttpEntity<User[]> entity = new HttpEntity<>(headers);
        return rt.exchange(URL, HttpMethod.GET, entity, User[].class);
    }

    public ResponseEntity<String> postMethod(HttpHeaders headers, User user) {
        HttpEntity<User> requestBodyPost = new HttpEntity<>(user, headers);
        return rt.exchange(URL, HttpMethod.POST, requestBodyPost, String.class);
    }

    public ResponseEntity<String> putMethod(HttpHeaders headers, User user) {
        HttpEntity<User> requestBodyPut = new HttpEntity<>(user, headers);
        return rt.exchange(URL, HttpMethod.PUT, requestBodyPut, String.class);
    }

    public ResponseEntity<String> deleteMethod(HttpHeaders headers, long id) {
        HttpEntity<String> requestBodyDelete = new HttpEntity<>(headers);
        return rt.exchange(URL + "/" + id, HttpMethod.DELETE, requestBodyDelete, String.class);
    }
}

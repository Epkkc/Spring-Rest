package ru.epkkc.springrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import ru.epkkc.springrest.model.User;
import ru.epkkc.springrest.services.RestService;

@SpringBootApplication
public class SpringRestApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringRestApplication.class, args);
        RestService service = applicationContext.getBean(RestService.class);

        HttpHeaders httpHeaders = service.buildHeaders();
        ResponseEntity<User[]> response = service.getMethod(httpHeaders);

        // GET
        String sessionGet = response.getHeaders().get("Set-Cookie").get(0);

        // POST
        httpHeaders.add("Cookie", sessionGet);
        User userPost = new User(3L, "James", "Brown", (byte) 20);
        ResponseEntity<String> responsePost = service.postMethod(httpHeaders, userPost);

        // PUT
        User userPut = new User(3L, "Thomas", "Shelby", (byte) 20);
        ResponseEntity<String> responsePut = service.putMethod(httpHeaders, userPut);

        // DELETE
        ResponseEntity<String> responseDelete = service.deleteMethod(httpHeaders, 3L);

        // RESULT
        String firstPart = responsePost.getBody();
        String secondPart = responsePut.getBody();
        String thirdPart = responseDelete.getBody();
        System.out.println(firstPart + secondPart + thirdPart);
    }
}
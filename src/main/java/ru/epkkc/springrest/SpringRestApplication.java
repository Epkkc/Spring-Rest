package ru.epkkc.springrest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.epkkc.springrest.model.User;

@SpringBootApplication
public class SpringRestApplication {

    public static final String URL = "http://91.241.64.178:7081/api/users";

    public static void main(String[] args) {

		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);

		// HttpEntity<Employee[]>: To get result as Employee[].
		HttpEntity<User[]> entity = new HttpEntity<>(headers);

		// RestTemplate
		RestTemplate rt = new RestTemplate();

		// Send request with GET method, and Headers.
		ResponseEntity<User[]> response = rt.exchange(URL, //
				HttpMethod.GET, entity, User[].class);

		String sessionGet = response.getHeaders().get("Set-Cookie").get(0);

		// POST

		HttpHeaders headersCoockie = new HttpHeaders();
		headersCoockie.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headersCoockie.add("Cookie", sessionGet);
		headersCoockie.setContentType(MediaType.APPLICATION_JSON);

		User userPost = new User(3L, "James", "Brown", (byte) 20);

		HttpEntity<User> requestBodyPost = new HttpEntity<>(userPost, headersCoockie);
		ResponseEntity<String> responsePost = rt.exchange(URL, //
				HttpMethod.POST, requestBodyPost, String.class);

		// PUT

		User userPut = new User(3L, "Thomas", "Shelby", (byte) 20);
		HttpEntity<User> requestBodyPut = new HttpEntity<>(userPut, headersCoockie);
		ResponseEntity<String> responsePut = rt.exchange(URL, HttpMethod.PUT, requestBodyPut, String.class);

		// DELETE
		HttpEntity<String> requestBodyDelete = new HttpEntity<>(headersCoockie);
		ResponseEntity<String> responseDelete = rt.exchange(URL + "/3", HttpMethod.DELETE, requestBodyDelete, String.class);

		String firstPart = responsePost.getBody();
		String secondPart = responsePut.getBody();
		String thirdPart = responseDelete.getBody();

		System.out.println(firstPart+secondPart+thirdPart);
    }

}

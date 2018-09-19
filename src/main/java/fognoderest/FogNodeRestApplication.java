package fognoderest;

import fognoderest.entities.Node;
import fognoderest.rest.RegisterService;
import fognoderest.utils.JsonBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FogNodeRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FogNodeRestApplication.class, args);

		Node node = new Node(1,5,5,5,5,5);
		JsonBuilder jsonBuilder = new JsonBuilder();
		RegisterService registerService = new RegisterService();
		String payload = jsonBuilder.nodeToJson(node);
		String requestUrl="http://localhost:8080/registration";
		String res = registerService.sendPostRequestForRegistration(requestUrl, payload);
		System.out.println(res);
	}
}

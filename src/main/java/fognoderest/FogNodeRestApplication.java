package fognoderest;

import fognoderest.entities.Node;
import fognoderest.rest.RegisterService;
import fognoderest.utils.JsonBuilder;
import generator.FogNodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FogNodeRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FogNodeRestApplication.class, args);

		Integer id = 0;
		FogNodeGenerator fogNodeGenerator = new FogNodeGenerator();
		Node node = fogNodeGenerator.spawnFogNode(id);

		JsonBuilder jsonBuilder = new JsonBuilder();
		RegisterService registerService = new RegisterService();
		String payload = jsonBuilder.nodeToJson(node);
		String requestUrl="http://localhost:8080/registration";
		String res = registerService.sendPostRequestForRegistration(requestUrl, payload);
		System.out.println(res);
	}
}

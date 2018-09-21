package fognoderest;

import fognoderest.entities.FogNode;
import fognoderest.rest.RegisterService;
import fognoderest.utils.JsonBuilder;
import generator.FogNodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.management.MalformedObjectNameException;
import java.io.IOException;


@SpringBootApplication
public class FogNodeRestApplication {

	public static void main(String[] args) throws IOException, MalformedObjectNameException {
		SpringApplication.run(FogNodeRestApplication.class, args);

		Integer id = 0;
		FogNodeGenerator fogNodeGenerator = new FogNodeGenerator();
		FogNode fogNode = fogNodeGenerator.spawnFogNode(id);

		JsonBuilder jsonBuilder = new JsonBuilder();
		RegisterService registerService = new RegisterService();
		String payload = jsonBuilder.nodeToJson(fogNode);
		String requestUrl="http://localhost:8080/registration";
		FogNode updatedFogNode = registerService.sendPostRequestForRegistration(requestUrl, payload);

		System.out.println(payload);
	}
}

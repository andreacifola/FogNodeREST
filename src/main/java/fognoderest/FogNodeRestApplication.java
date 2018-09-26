package fognoderest;


import fognoderest.entities.FogNode;
import fognoderest.generator.FogNodeGenerator;
import fognoderest.handler.RegistrationHandler;
import fognoderest.utils.JsonBuilder;

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
		FogNode fogNode = fogNodeGenerator.spawnFogNode(id, 2);

		JsonBuilder jsonBuilder = new JsonBuilder();
		RegistrationHandler registrationHandler = new RegistrationHandler();
		String payload = jsonBuilder.nodeToJson(fogNode);
		String requestUrl="http://localhost:8080/registration";
		FogNode updatedFogNode = registrationHandler.sendPostRequestForRegistration(requestUrl, payload);
		fogNode.setId(updatedFogNode.getId());
		fogNode.setPort(updatedFogNode.getPort());
		System.out.println("FogNode: id = " + fogNode.getId() + "; type = " + fogNode.getType() + "; CPU = " +
				fogNode.getCpu() + "; RAM = " + fogNode.getRam() + "; battery = " + fogNode.getBattery() +
				"; storage = " + fogNode.getStorage() + "; port = " + updatedFogNode.getPort() +
				"; latitude = " + fogNode.getLatitude() + "longitude = " + fogNode.getLongitude());
	}
}

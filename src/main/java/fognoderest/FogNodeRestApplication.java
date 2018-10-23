package fognoderest;

import fognoderest.entities.FogNode;
import fognoderest.generator.FogNodeGenerator;
import fognoderest.handler.RegistrationHandler;
import fognoderest.utils.JsonBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.management.MalformedObjectNameException;
import java.io.IOException;


@SpringBootApplication
public class FogNodeRestApplication implements EnvironmentAware {

	private static Environment environment;

	@Override
	public void setEnvironment(final Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) throws IOException, MalformedObjectNameException, InterruptedException {
		SpringApplication.run(FogNodeRestApplication.class, args);

		String port = environment.getProperty("local.server.port");
		System.out.println("porta : "+port);

		FogNodeGenerator fogNodeGenerator = new FogNodeGenerator();
		FogNode fogNode = fogNodeGenerator.spawnFogNode(port,2);

		JsonBuilder jsonBuilder = new JsonBuilder();
		RegistrationHandler registrationHandler = new RegistrationHandler();
		String payload = jsonBuilder.nodeToJson(fogNode);
		String requestUrl="http://localhost:8080/registration";
		registrationHandler.sendPostRequestForRegistration(requestUrl, payload);
		System.out.println("FogNodeSide: id = " + fogNode.getId() + "; type = " + fogNode.getType() + "; CPU = " +
				fogNode.getCpu() + "; RAM = " + fogNode.getRam() + "; battery = " + fogNode.getBattery() +
				"; storage = " + fogNode.getStorage() + "; port = " + fogNode.getPort() +
				"; latitude = " + fogNode.getLatitude() + "; longitude = " + fogNode.getLongitude() +
				"; powered = " + fogNode.getPowered());
	}
}

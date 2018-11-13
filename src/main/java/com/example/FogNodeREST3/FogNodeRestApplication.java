package com.example.FogNodeREST3;

import com.example.FogNodeREST3.entities.FogNode;
import com.example.FogNodeREST3.generator.FogNodeGenerator;
import com.example.FogNodeREST3.handler.RegistrationHandler;
import com.example.FogNodeREST3.utils.JsonBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.management.MalformedObjectNameException;
import java.io.IOException;
import java.net.InetAddress;


@SpringBootApplication
public class FogNodeRestApplication implements EnvironmentAware {

	private static Environment environment;

	private static String mwIp = "a6ce72bb8e6e211e886ee02a7517efe7-507914080.us-east-2.elb.amazonaws.com";

	@Override
	public void setEnvironment(final Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) throws IOException, MalformedObjectNameException, InterruptedException {
		SpringApplication.run(FogNodeRestApplication.class, args);

		//String port = environment.getProperty("local.server.port");
        //todo porta container per accedere al nodo
		String port = "32010";
		System.out.println("porta : " + port);

		FogNodeGenerator fogNodeGenerator = new FogNodeGenerator();
		FogNode fogNode = fogNodeGenerator.spawnFogNode(port,2);


		//todo prova indirizzo
		//InetAddress IP=InetAddress.getLocalHost();

		//todo in port c'è l'ip interno del nodo + porta del container
		fogNode.setPort("172.20.47.247" + ":" + fogNode.getPort());
        System.out.println("*****" + fogNode.getPort());


		JsonBuilder jsonBuilder = new JsonBuilder();
		RegistrationHandler registrationHandler = new RegistrationHandler();
		String payload = jsonBuilder.nodeToJson(fogNode);

		//TODO indirizzo mw
		String requestUrl="http://localhost:8080/registration";
		//String requestUrl="http://" + mwIp + ":8080/registration";

		registrationHandler.sendPostRequestForRegistration(requestUrl, payload);
		System.out.println("FogNodeSide: id = " + fogNode.getId() + "; type = " + fogNode.getType() + "; CPU = " +
				fogNode.getCpu() + "; RAM = " + fogNode.getRam() + "; battery = " + fogNode.getBattery() +
				"; storage = " + fogNode.getStorage() + "; port = " + fogNode.getPort() +
				"; latitude = " + fogNode.getLatitude() + "; longitude = " + fogNode.getLongitude() +
				"; powered = " + fogNode.getPowered());
	}
}

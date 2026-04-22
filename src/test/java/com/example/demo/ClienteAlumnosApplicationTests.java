package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ClienteAlumnosApplicationTests {

	private static final String EXPECTED_SERVER_PORT = "8000";

	@Autowired
	private Environment environment;

	@Test
	void contextLoads() {
	}

	@Test
	void serverPortMatchesExpectedConfiguration() {
		String configuredPort = environment.getProperty("server.port");
		assertNotNull(configuredPort, "Expected 'server.port' to be configured");
		assertEquals(EXPECTED_SERVER_PORT, configuredPort);
	}

}

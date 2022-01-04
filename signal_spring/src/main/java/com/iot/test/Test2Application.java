package com.iot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Test2Application {

//	@Bean
//	public KurentoClient kurentoClient() {
//		return KurentoClient.create("wss://13.125.108.31:8433/kurento");
//	}

	public static void main(String[] args) {
		SpringApplication.run(Test2Application.class, args);
	}
		

//	@Override
//	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		registry.addHandler(groupCallHandler(), "/groupcall");
//	}
}

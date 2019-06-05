package com.ray.study.dockercompose.dcregisterservereureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DcRegisterServerEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DcRegisterServerEurekaApplication.class, args);
	}

}

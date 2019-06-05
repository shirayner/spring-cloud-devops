package com.ray.study.dockercompose.dcorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan("com.ray.study.dockercompose.dcmodel.entity.order")//扫描实体类
@EnableDiscoveryClient
@EnableFeignClients
public class DcOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DcOrderServiceApplication.class, args);
	}

}

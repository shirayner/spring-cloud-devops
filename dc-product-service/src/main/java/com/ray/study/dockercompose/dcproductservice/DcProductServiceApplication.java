package com.ray.study.dockercompose.dcproductservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EntityScan("com.ray.study.dockercompose.dcmodel.entity.product")//扫描实体类
@EnableDiscoveryClient
public class DcProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DcProductServiceApplication.class, args);
	}

}

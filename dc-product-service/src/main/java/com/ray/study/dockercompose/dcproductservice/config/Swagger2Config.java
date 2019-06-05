package com.ray.study.dockercompose.dcproductservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置类
 *
 * @author shira 2019/05/07 17:10
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket createRestApi() {
		//设置 Swagger 扫描的包
		String basePackage = "com.ray.study.dockercompose.dcproductservice.controller";

		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage(basePackage))
				.paths(PathSelectors.any())
				.build();
	}


	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("通过 Gradle+Docker 部署 Spring Cloud 项目")
				.description("原文地址：https://github.com/shirayner/java-knowledge-hierarchy")
				.termsOfServiceUrl("https://blog.csdn.net/qq_26981333/")
				.contact("shirayner")
				.version("v1.0")
				.build();
	}
}

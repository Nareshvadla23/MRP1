package com.member.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MemberRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberRegistrationApplication.class, args);
	}

}

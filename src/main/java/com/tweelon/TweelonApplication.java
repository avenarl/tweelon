package com.tweelon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.tweelon.model")
public class TweelonApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweelonApplication.class, args);
	}

}

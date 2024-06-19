package com.swd391.bachhoasi_worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BachhoasiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BachhoasiApplication.class, args);
	}

}

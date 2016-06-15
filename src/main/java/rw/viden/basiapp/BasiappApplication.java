package rw.viden.basiapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "rw.viden.basicapp.controllers")
public class BasiappApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasiappApplication.class, args);
	}
}

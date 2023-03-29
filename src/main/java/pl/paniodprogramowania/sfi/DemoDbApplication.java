package pl.paniodprogramowania.sfi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoDbApplication {

	public static void main(String[] args) {
		System.setProperty("spring.profiles.default", "local");
		// SEEK FIND INPUT =
		SpringApplication.run(DemoDbApplication.class, args);
	}
}

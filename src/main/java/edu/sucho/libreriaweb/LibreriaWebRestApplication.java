package edu.sucho.libreriaweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class LibreriaWebRestApplication {

/*	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}*/

	public static void main(String[] args) {
		SpringApplication.run(LibreriaWebRestApplication.class, args);
	}
}

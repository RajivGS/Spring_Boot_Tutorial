
//DAta Acess LAyer
package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			Student adma = new Student( "Adma", "adma@gmail.com", LocalDate.of(2000, Month.APRIL, 1));
			Student alex = new Student( "Alex", "alex@gmail.com", LocalDate.of(2004, Month.APRIL, 1));
			repository.saveAll(List.of(adma, alex));
		};
	}

}

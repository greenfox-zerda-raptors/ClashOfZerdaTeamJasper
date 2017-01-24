package com.greenfox.jasper;

import com.greenfox.jasper.domain.Kingdom;
import com.greenfox.jasper.domain.User;
import com.greenfox.jasper.repos.UserRepo;
import com.greenfox.jasper.services.MainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProofOfConceptApplication implements CommandLineRunner {

	@Autowired
	MainServices mainServices;

	public static void main(String[] args) {
		SpringApplication.run(ProofOfConceptApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mainServices.saveOneUser(new User("sanya", "romania"));
		mainServices.saveOneKingdom(new Kingdom((long)1));

	}
}

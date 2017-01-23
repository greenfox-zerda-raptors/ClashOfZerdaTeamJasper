package com.greenfox.jasper;

import com.greenfox.jasper.domain.User;
import com.greenfox.jasper.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProofOfConceptApplication implements CommandLineRunner {

	@Autowired
	UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(ProofOfConceptApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepo.save(new User("user"));
		userRepo.save(new User("user2"));
		userRepo.save(new User("user3"));
		userRepo.save(new User("user4"));
		userRepo.save(new User("user5"));
	}
}

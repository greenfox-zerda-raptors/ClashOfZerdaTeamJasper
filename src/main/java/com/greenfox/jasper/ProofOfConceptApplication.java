package com.greenfox.jasper;

import com.greenfox.jasper.domain.Building;
import com.greenfox.jasper.domain.Resource;
import com.greenfox.jasper.domain.Troop;
import com.greenfox.jasper.domain.User;
import com.greenfox.jasper.services.MainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProofOfConceptApplication implements CommandLineRunner {

	@Autowired
	MainServices mainServices;

	public static void main(String[] args) {
		SpringApplication.run(ProofOfConceptApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mainServices.saveOneUser(new User("sanya", "romania"));
		mainServices.saveOneResource(new Resource("food", mainServices.findOneUser(1)));
		mainServices.saveOneBuilding(new Building("mine", mainServices.findOneUser(1), mainServices.findOneResource("food")));
		mainServices.saveOneBuilding(new Building("farm", mainServices.findOneUser(1), mainServices.findOneResource("food")));
		mainServices.saveOneBuilding(new Building("barrack", mainServices.findOneUser(1), mainServices.findOneResource("food")));
		mainServices.saveOneTroop(new Troop(mainServices.findOneUser(1)));
	}
}

package com.greenfox.jasper;

import com.greenfox.jasper.domain.*;
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
		mainServices.saveOneUser(new User("romania", "sanya"));
		mainServices.saveOneUser(new User("hungary", "feri"));
		mainServices.saveOneUser(new User("deutchland", "hanzi"));
		mainServices.saveOneUser(new User("mexico", "jesus"));
		mainServices.saveOneResource(new Resource("food", mainServices.findKingdomByName("romania")));
		mainServices.saveOneBuilding(new Building("mine", mainServices.findKingdomByName("romania"), mainServices.findOneResource("food")));
		mainServices.saveOneBuilding(new Building("farm", mainServices.findKingdomByName("romania"), mainServices.findOneResource("food")));
		mainServices.saveOneBuilding(new Building("barrack", mainServices.findKingdomByName("romania"), mainServices.findOneResource("food")));
		mainServices.saveOneTroop(new Troop(mainServices.findKingdomByName("romania")));
		mainServices.saveOneTroop(new Troop(mainServices.findKingdomByName("romania")));
		mainServices.saveOneTroop(new Troop(mainServices.findKingdomByName("romania")));
		mainServices.saveOneTroop(new Troop(mainServices.findKingdomByName("hungary")));
		mainServices.saveOneTroop(new Troop(mainServices.findKingdomByName("hungary")));
		mainServices.saveOneTroop(new Troop(mainServices.findKingdomByName("mexico")));
		mainServices.saveOneTroop(new Troop(mainServices.findKingdomByName("deutchland")));
		mainServices.saveOneTroop(new Troop(mainServices.findKingdomByName("deutchland")));

	}
}

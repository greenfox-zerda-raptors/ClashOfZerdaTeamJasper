package com.greenfox.jasper;

import com.greenfox.jasper.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProofOfConceptApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProofOfConceptApplication.class, args);
    }

}

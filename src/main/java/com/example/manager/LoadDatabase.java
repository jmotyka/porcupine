package com.example.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PatientRepository repository) {

        return args -> {
            log.info("Pre-populating database with the following records:");
            log.info(
                "Pre-loading " + repository.save(
                new Patient(
                        "John Smith",
                        "johnsmith@gmail.com",
                        LocalDateTime.of(1900, 1, 1, 0, 0, 0),
                        LocalDateTime.of(2024, 1, 1, 12, 0, 0)
                    )
                )
            );
            log.info(
                "Pre-loading " + repository.save(
                new Patient(
                        "Joe Smith",
                        "joesmith@gmail.com",
                        LocalDateTime.of(1910, 1, 10, 0, 0, 0),
                        LocalDateTime.of(2024, 1, 1, 12, 0, 0)
                    )
                )
            );  
        };
    }
};

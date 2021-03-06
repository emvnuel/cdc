package io.github.emanuelcerqueira.cdc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(enableDefaultTransactions = false)
public class CdcApplication {

    public static void main(String[] args) {
        SpringApplication.run(CdcApplication.class, args);
    }

}

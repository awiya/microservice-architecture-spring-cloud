package com.idihia.customer;

import com.idihia.customer.model.Customer;
import com.idihia.customer.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration){
		return args -> {
			restConfiguration.exposeIdsFor(Customer.class);
			customerRepository.saveAll(
					List.of(
							Customer.builder().name("JAd").email("jad@gmail.com").build(),
							Customer.builder().name("judia").email("judia@gmail.com").build(),
							Customer.builder().name("fadza").email("fadza@gmail.com").build()
					)
			);
			customerRepository.findAll().forEach(System.out::println);
		};
	}

}

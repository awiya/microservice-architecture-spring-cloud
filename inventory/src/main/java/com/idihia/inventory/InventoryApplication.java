package com.idihia.inventory;

import com.idihia.inventory.model.Product;
import com.idihia.inventory.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration){
		return args -> {
			restConfiguration.exposeIdsFor(Product.class);
			productRepository.saveAll(
					List.of(
							Product.builder().name("MACBOOK").price(10000+Math.random()*9999).quantity((int) (3+Math.random()*10)).build(),
							Product.builder().name("iPhone").price(Math.random()*9999).quantity((int) (3+Math.random()*10)).build(),
							Product.builder().name("iPad").price(Math.random()*9999).quantity((int) (3+Math.random()*10)).build()
					)
			);
			productRepository.findAll().forEach(System.out::println);
		};
	}

}

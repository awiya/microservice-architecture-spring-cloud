package com.idihia.billing;

import com.idihia.billing.model.Bill;
import com.idihia.billing.model.ProductItem;
import com.idihia.billing.notmodel.Customer;
import com.idihia.billing.notmodel.Product;
import com.idihia.billing.repositories.BillRepository;
import com.idihia.billing.repositories.ProductItemRepository;
import com.idihia.billing.services.CustomerRestClient;
import com.idihia.billing.services.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class BillingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BillRepository billRepository,
							ProductItemRepository productItemRepository,
							CustomerRestClient customerRestClient,
							ProductRestClient productRestClient){
		return args -> {
			Collection<Product> products = productRestClient.allProducts().getContent();
//			Long customerId=1L;
			Customer customer = customerRestClient.findCustomerById(1L);
			if (customer == null) {
				throw new RuntimeException("Customer not found");
			}
			Bill bill = new Bill();
			bill.setBillDate(new Date());
			bill.setCustomerId(1L);
			Bill savedBill = billRepository.save(bill);
			products.forEach(product -> {
				ProductItem productItem = new ProductItem();
				productItem.setBill(savedBill);
				productItem.setQuantity((int) (1+Math.random()*10));
				productItem.setPrice(product.getPrice());
				productItem.setDiscount(Math.random());
				productItem.setProductId(product.getId());
				productItem.setName(product.getName());
				productItemRepository.save(productItem);
			});

		};
	}

}

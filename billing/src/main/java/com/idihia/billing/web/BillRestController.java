package com.idihia.billing.web;

import com.idihia.billing.model.Bill;
import com.idihia.billing.repositories.BillRepository;
import com.idihia.billing.repositories.ProductItemRepository;
import com.idihia.billing.services.CustomerRestClient;
import com.idihia.billing.services.ProductRestClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BillRestController {
    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClient customerRestClient;
    private ProductRestClient productRestClient;

    @GetMapping("/fullBill/{id}")
    public Bill bill(@PathVariable Long id){
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(pr->{
            pr.setProduct(productRestClient.findProductById(pr.getProductId()));
        });
        return bill;
    }
}

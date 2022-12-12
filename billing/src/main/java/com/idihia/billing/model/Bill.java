package com.idihia.billing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idihia.billing.notmodel.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billDate;
    private Long customerId;
    @OneToMany(mappedBy = "bill" ,fetch = FetchType.EAGER)
    private List<ProductItem> productItems;
    @Transient
    private Customer customer;
}

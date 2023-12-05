package com.buhl.hub.customer.domain;

import com.buhl.hub.customer.values.CustomerDTO;
import com.buhl.hub.shop.values.ShopDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Customer {
    @Id
    private long id;
    private String name;

    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CustomerDTO toDto() {
        return new CustomerDTO(name);
    }
}

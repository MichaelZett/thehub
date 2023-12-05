package com.buhl.hub.shop.domain;

import com.buhl.hub.shop.values.ShopDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Shop {
    @Id
    private long id;
    private String name;

    public Shop(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ShopDTO toDto() {
        return new ShopDTO(name);
    }
}

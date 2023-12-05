package com.buhl.hub.shop.application;

import com.buhl.hub.shop.domain.Shop;
import com.buhl.hub.shop.domain.ShopRepository;
import com.buhl.hub.shop.values.ShopDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;
    private final JdbcTemplate huboneJdbcTemplate;

    @Override
    public Collection<ShopDTO> getAll() {
        return StreamSupport.stream(shopRepository.findAll().spliterator(), false).map(Shop::toDto).toList();
    }

    @PostConstruct
    public void setup() {
        if (!shopRepository.existsById(42L)) {
            huboneJdbcTemplate.execute("INSERT INTO Shop(id,name) VALUES(42, 'The Shop')");
        }
    }
}

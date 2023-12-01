package com.buhl.hub.shop.application;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.buhl.hub.shop.values.ShopDTO;

@Service
public class ShopServiceImpl implements ShopService {

	@Override
	public Collection<ShopDTO> getAll() {
		return List.of(new ShopDTO("The Shop"));
	}

}

package com.buhl.hub.customer.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

//	@Query(value = "...")
	Optional<Customer> findOneByName(String name);
}
package com.canalplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.canalplus.domain.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    /* @Query("SELECT p FROM Product p WHERE " +
            "p.name LIKE CONCAT('%',:query, '%')" +
            "Or p.description LIKE CONCAT('%', :query, '%')") */
/*
    @Query("SELECT c FROM customer c WHERE c.email = ?1 and c.isActive = true")
    Customer findByEmail(String email);

    @Query("SELECT c FROM customer c WHERE c.phone = ?1 and c.isActive = true")
    Customer findByPhone(String email);
     */
}

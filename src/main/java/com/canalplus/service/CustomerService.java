package com.canalplus.service;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.canalplus.domain.Customer;

@Service
public interface CustomerService {

   public List<Customer> findAll();
   public Customer findById(Long id);
   public Optional<Customer> findByPhone(String phone);
   public Optional<Customer> findByIdOrPhone(Long id, String phone);
   public Customer save(Customer customer);
   public Customer update(Customer customer);
   public Customer delete(Long id);
}

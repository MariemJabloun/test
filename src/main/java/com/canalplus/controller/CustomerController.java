package com.canalplus.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.canalplus.domain.Customer;
import com.canalplus.domain.Subscription;
import com.canalplus.error.ExistException;
import com.canalplus.error.NotAllowedException;
import com.canalplus.error.NotFoundException;
import com.canalplus.error.NotNullException;
import com.canalplus.repository.CustomerRepository;
import com.canalplus.repository.SubscriptionRepository;
import com.canalplus.service.CustomerService;
import com.canalplus.service.SubscriptionService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {

  @Autowired
  CustomerService customerSvc;
  @Autowired
  CustomerRepository custRepo;
  @Autowired
  SubscriptionService subscriptionbSvc;
  @Autowired
  SubscriptionRepository subRepo;

  @Operation(summary = "Get all active customers")
  @GetMapping("/")
  public List<Customer> getCustomers() {
    // build Query programmatically using queries
    return customerSvc.findAll();

  }

  @Operation(summary = "Get Customer by Identifier")
  @GetMapping("/{id}")
  public Customer getCustomersById(@PathVariable(required = true) Long id) {
    return customerSvc.findById(id);

  }

  @Operation(summary = "Get active customer by Phone, where Phone is unique")
  @GetMapping(value = "", params = "phone")
  public Optional<Customer> getCustomersByPhone(@RequestParam(name = "phone", required = true) String phone) {

    return customerSvc.findByPhone(phone);

  }

  @Operation(summary = "Create a new customer, active by default")
  @PostMapping("/")
  public Customer createNewCustomer(@RequestBody Customer customer) {
    return customerSvc.save(customer);

  }

  @Operation(summary = "Update an exist active customer")
  @PutMapping("/")
  public Customer updateCustomer(@RequestBody Customer customer) throws Exception {

    return customerSvc.update(customer);
  }

  @Operation(summary = "Disactivate a customer and its related subscriptions")
  @DeleteMapping("/customers/{id}")
  public Customer deleteCustomer(@PathVariable Long id) {
    log.info(" Deleting  customer");
    // All Subscriptions should be disactivated
    return customerSvc.delete(id);

  }

  static Specification<Customer> withActive() {

    return (root, query, cb) -> cb.equal(root.get("active").as(Boolean.class), true);

  }

  static Specification<Customer> withPhone(String phone) {
    return (root, query, cb) -> phone == null ? null : cb.equal(root.get("phone").as(String.class), phone);
  }

  static Specification<Customer> withId(Long id) {
    return (root, query, cb) -> id == null ? null : cb.equal(root.get("id").as(Long.class), id);
  }

  static Specification<Subscription> withCustomerId(Long id) {
    return (root, query, cb) -> id == null ? null : cb.equal(root.get("customer").as(Long.class), id);
  }

  static Specification<Customer> withSubscriptions(Long id) {
    return (root, query, cb) -> id == null ? null : cb.equal(root.join("subscriptions").get("id").as(Long.class), id);
  }

}

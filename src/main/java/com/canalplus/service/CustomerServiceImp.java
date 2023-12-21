package com.canalplus.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.canalplus.domain.Customer;
import com.canalplus.domain.Subscription;
import com.canalplus.error.ExistException;
import com.canalplus.error.NotAllowedException;
import com.canalplus.error.NotFoundException;
import com.canalplus.error.NotNullException;
import com.canalplus.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImp implements CustomerService {

  @Autowired
  private CustomerRepository custRepo;
  @Autowired
  private SubscriptionService subscriptionSvc;

  @Override
  public List<Customer> findAll() {
    return custRepo.findAll(withActive());
  }

  @Override
  public Customer findById(Long id) {
    return custRepo.findOne(withId(id).and(withActive()))
        .orElseThrow(() -> new NotFoundException("Customer " + id + " Not Found"));
  }

  @Override
  public Optional<Customer> findByPhone(String phone) {
    return custRepo.findOne(withActive().and(withPhone(phone)));
  }

  @Override
  public Optional<Customer> findByIdOrPhone(Long id, String phone) {
    if (id == null && phone == null) {
      throw new NotNullException("To Search the right customer for the action, Either Id or Phone should be provided");
    }
    return custRepo.findOne(withPhone(phone).and(withActive()).and(withId(id)));
  }

  @Override
  public Customer save(Customer customer) {
    if (customer == null) {
      throw new NotNullException("Customer should not be null");
    }
    if (customer.getActive() != null && !customer.getActive()) {
      throw new NotAllowedException("To disactivate Customer, refer to the delete request ");
    }

    Optional<Customer> custo = this.findByIdOrPhone(customer.getId(), customer.getPhone());
    custo.ifPresent(item -> {
      throw new ExistException("Customer " + item.getId() + ", " + item.getFirstName() + " " + item.getLastName() + ", "
          + item.getPhone() + " already Exists");
    });
    return custRepo.save(customer);

  }

  @Override
  public Customer update(Customer customer) {

    if (customer == null) {
      throw new NotNullException("Customer should Not be Null");
    }
    if (!customer.getActive()) {
      throw new NotAllowedException("To disactivate Customer, refer to the delete request ");
    }

    Optional<Customer> cust = this.findByIdOrPhone(customer.getId(), customer.getPhone());
    if (!cust.isPresent()) {
      throw new NotFoundException(
          "Customer " + customer.getId() + " with phone number " + customer.getPhone() + " Not found");
    }
    if (cust.isPresent()) {

      customer.setId(cust.get().getId());

    }

    return custRepo.save(customer);
  }

  @Transactional
  public Customer delete(Long id) {

    if (id == null) {
      throw new NotAllowedException("Please provide the Customer ID");
    }

    List<Subscription> customerSubs = subscriptionSvc.findByCustomer(id)
    .stream().map( item -> {

      item.setActive(false);
      return subscriptionSvc.save(item);
    }).collect(Collectors.toList());

    Customer cust;
    if(customerSubs != null && !customerSubs.isEmpty()){
      cust = customerSubs.get(0).getCustomer();
      
    }else{

      cust = this.findById(id);
        
    }
    cust.setActive(false);
     return custRepo.save(cust);

    // Need to manage a transaction, first remove subscriptions then customer

    // We have unicity problem as phone is unique, whene we distactivate customer an
    // want to create another, it will generate a problem

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

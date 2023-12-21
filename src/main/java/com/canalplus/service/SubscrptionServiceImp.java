package com.canalplus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.canalplus.domain.Subscription;
import com.canalplus.error.NotAllowedException;
import com.canalplus.error.NotFoundException;
import com.canalplus.error.NotNullException;
import com.canalplus.repository.SubscriptionRepository;

@Service
public class SubscrptionServiceImp implements SubscriptionService {

  @Autowired
  SubscriptionRepository subRepo;

  @Override
  public List<Subscription> findAll() {
    return subRepo.findAll(withActive());
  }

  @Override
  public Optional<Subscription> findById(Long id) {
    return subRepo.findOne(withId(id).and(withActive()));
  }

  @Override
  public List<Subscription> findByCustomer(Long id) {
    return subRepo.findAll(withCustomerId(id).and(withActive()));
  }

  @Override
  public Subscription save(Subscription subscription) {
    return subRepo.save(subscription);
  }

  @Override
  public Subscription update(Subscription subscription) {

    if (subscription == null) {
      throw new NotNullException("Subscription should Not be Null");
    }
    if(subscription.getId() == null){
        throw new NotNullException("Subscription Id should Not be Null");
    }
    if (!subscription.getActive()) {
      throw new NotAllowedException("To disactivate Subscription, refer to the delete request ");
    }

    Optional<Subscription> sub = this.findById(subscription.getId());
    if(!sub.isPresent()){
       throw new NotFoundException("subscription "+ subscription.getId() +"  Not found");
    }

    return subRepo.save(subscription);
  }

  @Override
  public Subscription delete(Long id) {
    
    Optional<Subscription> sub = this.findById(id);
    if(!sub.isPresent()){
      throw new NotFoundException("Subcription Not Found");
    }
        
    sub.get().setActive(false);
    return subRepo.save(sub.get());
    
  }
  
  static Specification<Subscription> withActive() {

    return (root, query, cb) -> cb.equal(root.get("active").as(Boolean.class), true);

  }

  static Specification<Subscription> withName(String name) {
    return (root, query, cb) -> name == null ? null : cb.equal(root.get("name").as(String.class), name);
  }

  static Specification<Subscription> withId(Long id) {
    return (root, query, cb) -> id == null ? null : cb.equal(root.get("id").as(Long.class), id);
  }

  static Specification<Subscription> withCustomerId(Long id) {
    return (root, query, cb) -> id == null ? null : cb.equal(root.get("customer").as(Long.class), id);
  }








}

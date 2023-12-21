package com.canalplus.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.canalplus.domain.Subscription;
import com.canalplus.repository.CustomerRepository;
import com.canalplus.repository.SubscriptionRepository;
import com.canalplus.service.SubscriptionService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;






@RestController
@RequestMapping("/subscriptions")
@Slf4j
// canalplus.fr/v1/api/subscription
public class Subscribercontroller {

    @Autowired
    SubscriptionService subscriptionSvc;
    @Autowired 
    CustomerRepository custRepo;

   @Autowired
   SubscriptionRepository subRepo;


    @Operation(summary = "Get all active subscriptions")
    @GetMapping("/")
    public List<Subscription> getAllSubscriptions() {
        
         return subscriptionSvc.findAll();
    }  

    @Operation(summary = "Get active subscription by Identifier")
    @GetMapping("/{id}")
    public Optional<Subscription> getSubscriptionbyId(@PathVariable Long id) {
      log.info(" Find Subscription " + id);
      return subscriptionSvc.findById(id);
        //return subRepo.findOne(withId(id).and(withActive()));
    }  

    @Operation(summary = "Get all active subscriptions for a customer using its Identifier")
    @GetMapping("/customer/{id}")
    public List<Subscription> getSubscriptionbyCustomerId(@RequestParam(name = "Customer Id", required = true)  Long id) {
        log.info(" Find Subscriptions for Customer " + id);
        return subscriptionSvc.findByCustomer(id);
        //return subRepo.findAll(withCustomerId(id).and(withActive()));
    }  
     
    @Operation(summary = "Add subscription, duplication is allowed")
    @PostMapping("/")
    public Subscription addSubscriptions(@RequestBody Subscription subscription) {
      log.info(" Adding new Subscription");
        
      // We can force unicity constraint on subscription name 
      // In this method we will allow duplication
      return subscriptionSvc.save(subscription);
      //return subRepo.save(subscription);
        
      //  return subscription;
    }

  @Operation(summary = "Update an exist active customer")
  @PutMapping("/")
  public Subscription updateSubscriber(@RequestBody Subscription subscription) throws Exception {

    
      return subscriptionSvc.update(subscription);

    /*
    if (subscription == null) {
      throw new Exception("Subscription should not be null");
    }
    if(subscription.getId() == null){
        throw new Exception("Subscription Id should not be null");
    }
    if (!subscription.getActive()) {
      throw new NotAllowedException("To disactivate Subscription, refer to the delete request ");
    }


      Optional<Subscription> sub = subRepo.findOne(withId(subscription.getId()).and(withActive()));
      if(!sub.isPresent()){
        throw new NotFoundException("subscription "+ subscription.getId() +"  Not found");
      }



    return subRepo.save(subscription);  */

  }
    
  @Operation(summary = "Disactivate a subscription customer")
  @DeleteMapping("/{id}")
  public Subscription deleteSubscription(@PathVariable(required = true) Long id) {
    log.info(" Deleting Subscription " + id);
    return subscriptionSvc.delete(id);
    /*
    Optional<Subscription> sub = subRepo.findOne(withId(id).and(withActive()));
    if(!sub.isPresent()){
      throw new NotFoundException("Subcription not found");
    }
        
    sub.get().setActive(false);
    return subRepo.save(sub.get());
  */
        
     
  }


  
}

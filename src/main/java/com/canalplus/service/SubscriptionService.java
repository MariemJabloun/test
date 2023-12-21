package com.canalplus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.canalplus.domain.Subscription;

@Service
public interface SubscriptionService {

    public List<Subscription> findAll();
    public Optional<Subscription> findById(Long id);
    public List<Subscription> findByCustomer(Long id);
    public Subscription save(Subscription subscription);
    public Subscription update(Subscription subscription);
    public Subscription delete(Long id);


    
}

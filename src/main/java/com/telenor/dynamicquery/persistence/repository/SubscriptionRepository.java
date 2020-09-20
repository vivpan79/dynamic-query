package com.telenor.dynamicquery.persistence.repository;

import com.telenor.dynamicquery.persistence.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long>,
    JpaSpecificationExecutor<Subscription> {

}
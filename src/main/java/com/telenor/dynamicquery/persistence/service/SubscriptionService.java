package com.telenor.dynamicquery.persistence.service;

import com.telenor.dynamicquery.persistence.entity.Subscription;
import com.telenor.dynamicquery.persistence.repository.SubscriptionRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository repository;

    public List<Subscription> findAll(String minPrice, String maxPrice, String minGBLimit, String maxGBLimit,
        String city) {
        Subscription subscription = new Subscription();
        subscription.setStoreAddress(city);
        return findAllSubscription(minPrice, maxPrice, Long.parseLong(minGBLimit), Long.parseLong(maxGBLimit),
            subscription);
    }

    public List<Subscription> findAllSubscription(String minPrice, String maxPrice, Long minDataLimit,
        Long maxDataLimit, Subscription subscription) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id");
        return repository.findAll(getSubscriptionSpecification(minPrice, maxPrice, minDataLimit, maxDataLimit,
            Example.of(subscription, exampleMatcher)));
    }

    Specification<Subscription> getSubscriptionSpecification(String minPrice, String maxPrice,
        Long minDataLimit, Long maxDataLimit, Example<Subscription> example) {
        return (root, query, builder) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if (minPrice != null && !minPrice.isEmpty()) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("price"), new BigDecimal(minPrice)));
            }
            if (maxPrice != null && !maxPrice.isEmpty()) {
                predicates.add(builder.lessThanOrEqualTo(root.get("price"), new BigDecimal(maxPrice)));
            }
            if (minDataLimit != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dataLimitInGB"), minDataLimit));
            }
            if (maxDataLimit != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dataLimitInGB"), maxDataLimit));
            }
            predicates.add(QueryByExamplePredicateBuilder.getPredicate(root, builder, example));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
package com.telenor.dynamicquery.persistence.service;

import com.telenor.dynamicquery.persistence.entity.Phone;
import com.telenor.dynamicquery.persistence.repository.PhoneRepository;
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
public class PhoneService {

    @Autowired
    private PhoneRepository repository;

    public Phone save(Phone product) {
        return repository.save(product);
    }

    public List<Phone> findAll(BigDecimal minPrice, BigDecimal maxPrice, String color, String city) {
        Phone phone = new Phone();
        phone.setColor(color);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id");
        return repository.findAll(getPhoneSpecification(minPrice, maxPrice, city, Example.of(phone, exampleMatcher)));
    }

    public Specification<Phone> getPhoneSpecification(BigDecimal minPrice, BigDecimal maxPrice, String city,
        Example<Phone> example) {
        return (root, query, builder) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if (minPrice != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("price"), maxPrice));
            }
            if (city != null && !city.isEmpty()) {
                predicates.add(builder.like(root.get("storeAddress"), "%" + city + "%"));
            }
            predicates.add(QueryByExamplePredicateBuilder.getPredicate(root, builder, example));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}

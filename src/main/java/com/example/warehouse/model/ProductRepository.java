package com.example.warehouse.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByproductNumber(@Param("productNumber") String productNumber);

}

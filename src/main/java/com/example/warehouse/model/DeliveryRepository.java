package com.example.warehouse.model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DeliveryRepository extends CrudRepository<Delivery, Long> {

    List<Delivery> findBydeliveryNumber(@Param("deliveryNumber") String deliveryNumber);

}

package com.example.warehouse;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;

import com.example.warehouse.model.CategoryRepository;
import com.example.warehouse.model.Delivery;
import com.example.warehouse.model.DeliveryRepository;
import com.example.warehouse.model.Product;
import com.example.warehouse.model.ProductRepository;



@ExtendWith(SpringExtension.class)
@DataJpaTest
public class WarehouseRepositoriesTest {

	@Autowired
	private ProductRepository prepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@Autowired
	private DeliveryRepository drepository;
	
	// *Test Product repository*

	@Test
	public void findByproductNumberShouldReturnProduct() {
		List<Product> products = prepository.findByproductNumber("222222");
		assertThat(products).hasSize(1);
		assertThat(products.get(0).getProductName()).isEqualTo("High pressure hose(1.5m)");
	}
	
	
	// Testing product creation
	@Test
	public void createProduct() {
		Product product = new Product("512346", "Torque wrench", crepository.findByName("Bolting tool").get(0), 1.1, 30,
				1300);
		prepository.save(product);
		assertThat(product.getId()).isNotNull();
	}
	
	
	// Testing product deletion
	@Test
	public void deleteProduct() {
		List<Product> product = prepository.findByproductNumber("333333");
		prepository.delete(product.get(0));
		assertThat(prepository.findByproductNumber("333333")).isNullOrEmpty();
	}
	
	
	// *Test Delivery repository*
	
	@Test
	public void findBydeliveryNumberShouldReturnDelivery() {
		List<Delivery> deliveries = drepository.findBydeliveryNumber("271441");
		assertThat(deliveries).hasSize(1);
		assertThat(deliveries.get(0).getDate()).isEqualTo("05.10.2021");
	}
	
	
	// Testing delivery creation
	@Test
	public void createDelivery() {
		Delivery delivery = new Delivery("000000", "00.00.0000", "00:00", "Empty", "Delivered");
		drepository.save(delivery);
		assertThat(delivery.getDeliveryid()).isNotNull();
	}
	
	
	// Testing delivery deletion
	@Test
	public void deleteDelivery() {
		List<Delivery> delivery = drepository.findBydeliveryNumber("271441");
		drepository.delete(delivery.get(0));
		assertThat(drepository.findBydeliveryNumber("271441")).isNullOrEmpty();
	}

}

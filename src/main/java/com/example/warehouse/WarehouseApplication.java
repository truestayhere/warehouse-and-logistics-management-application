package com.example.warehouse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.warehouse.model.Category;
import com.example.warehouse.model.CategoryRepository;
import com.example.warehouse.model.Delivery;
import com.example.warehouse.model.DeliveryRepository;
import com.example.warehouse.model.Product;
import com.example.warehouse.model.ProductRepository;

@SpringBootApplication
public class WarehouseApplication {
	private static final Logger log = LoggerFactory.getLogger(WarehouseApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(DeliveryRepository drepository, ProductRepository prepository,
			CategoryRepository crepository) {
		return args -> {

			// Create new orders:
			log.info("save a couple of deliveries");

			Delivery delivery1 = new Delivery("211111", "30.11.2021", "12:00", 0, 0, 0, "Unfulfilled");
			Delivery delivery2 = new Delivery("311341", "14.12.2021", "16:30", 0, 0, 0, "Unfulfilled");
			Delivery delivery3 = new Delivery("271441", "05.10.2021", "14:00", 0, 0, 0, "Fulfilled");
			Delivery delivery4 = new Delivery("000000", "00.00.0000", "00:00", 0, 0, 0, "Delivered");

			drepository.save(delivery1);
			drepository.save(delivery2);
			drepository.save(delivery3);
			drepository.save(delivery4);
			
			
			// Create new categories
			log.info("save a couple of categories");
			crepository.save(new Category("High-pressure hose"));
			crepository.save(new Category("Bolting tool"));
			crepository.save(new Category("Power unit"));

			// Create new products:
			log.info("save a couple of products");

			Product product1 = new Product("111111", "Bolt tensioner", crepository.findByName("Bolting tool").get(0), 1.5, 100,
					1200);
			Product product2 = new Product("222222", "High pressure hose(1.5m)", crepository.findByName("High-pressure hose").get(0), 1,
					90, 300);
			Product product3 = new Product("333333", "High pressure hose(5m)", crepository.findByName("High-pressure hose").get(0), 2.5,
					90, 300);
			Product product4 = new Product("444444", "Power Unit(1500bar)", crepository.findByName("Power unit").get(0), 31, 4,
					7000);
			Product product5 = new Product("555555", "Hand pump(1500bar)", crepository.findByName("Power unit").get(0), 9, 6,
					2500);

			prepository.save(product1);
			prepository.save(product2);
			prepository.save(product3);
			prepository.save(product4);
			prepository.save(product5);

			log.info("fetch all products");
			for (Product product : prepository.findAll()) {
				log.info(product.toString());
			}

			log.info("fetch all deliveries");
			for (Delivery delivery : drepository.findAll()) {
				log.info(delivery.toString());
			}

		};

	}

}

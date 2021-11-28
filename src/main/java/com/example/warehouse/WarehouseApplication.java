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
import com.example.warehouse.model.User;
import com.example.warehouse.model.UserRepository;

@SpringBootApplication
public class WarehouseApplication {
	private static final Logger log = LoggerFactory.getLogger(WarehouseApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(DeliveryRepository drepository, ProductRepository prepository,
			CategoryRepository crepository, UserRepository urepository) {
		return args -> {

			// Create new orders:
			log.info("save a couple of deliveries");

			Delivery delivery1 = new Delivery("211111", "30.11.2021", "12:00", "Empty", "Unfulfilled");
			Delivery delivery2 = new Delivery("311341", "14.12.2021", "16:30", "Empty", "Unfulfilled");
			Delivery delivery3 = new Delivery("271441", "05.10.2021", "14:00", "Empty", "Fulfilled");

			drepository.save(delivery1);
			drepository.save(delivery2);
			drepository.save(delivery3);
			
			
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
			
			
			// Create users admin/admin user/user  
			// Passwords secured with BCrypt hashing
			
			User user1 = new User("user", "$2a$10$hPMQYM/.ED19d99LBLkmH.sFEEmle/bnBDb8ySwqSIj6j/vl.WUDG", "USER");
			User user2 = new User("admin", "$2a$10$aWHR7WdURs25/GxDITY/3OxURra0D6YBUVpMAtxlbaA8dp1Vrgd6O", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			

			log.info("fetch all products");
			for (Product product : prepository.findAll()) {
				log.info(product.toString());
			}

			log.info("fetch all deliveries");
			for (Delivery delivery : drepository.findAll()) {
				log.info(delivery.toString());
			}
			
			log.info("fetch all users");
			for (User user : urepository.findAll()) {
				log.info(user.toString());
			}

		};

	}

}

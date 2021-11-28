package com.example.warehouse;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.warehouse.web.WarehouseController;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class WarehouseApplicationTests {
	
	// Checking if controller loads

	@Autowired
	private WarehouseController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}

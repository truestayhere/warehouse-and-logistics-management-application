package com.example.warehouse.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.warehouse.model.CategoryRepository;
import com.example.warehouse.model.Delivery;
import com.example.warehouse.model.DeliveryRepository;
import com.example.warehouse.model.Product;
import com.example.warehouse.model.ProductRepository;

@Controller
public class WarehouseController {

	@Autowired
	private DeliveryRepository drepository;

	@Autowired
	private ProductRepository prepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	// Log in 
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	//Log out
	@RequestMapping(value= "/logout")
	public String logout() {
		return "logout";
	}

	// Default page

	@RequestMapping(value = "/")
	public String main() {
		return "default";
	}

	// Product Controller

	@RequestMapping(value = "/productlist")
	public String productList(Model model) {
		model.addAttribute("products", prepository.findAll());
		return "productlist";
	}

	@RequestMapping(value = "/add/product")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categories", crepository.findAll());
		return "addproduct";
	}

	@RequestMapping(value = "/save/product", method = RequestMethod.POST)
	public String saveProduct(Product product) {
		prepository.save(product);
		return "redirect:../productlist";
	}

	@RequestMapping(value = "/delete/product/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteProduct(@PathVariable("id") Long productId, Model model) {
		prepository.deleteById(productId);
		return "redirect:http://localhost:8080/productlist";
	}

	@RequestMapping(value = "/edit/product/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editProduct(@PathVariable("id") Long productId, Model model) {
		model.addAttribute("product", prepository.findById(productId));
		model.addAttribute("categories", crepository.findAll());
		return "editproduct";
	}

	@RequestMapping(value = "${spring.data.rest.basePath}/products", method = RequestMethod.GET)
	public @ResponseBody List<Product> productListRest() {
		return (List<Product>) prepository.findAll();
	}

	@RequestMapping(value = "${spring.data.rest.basePath}/product/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Product> findProductRest(@PathVariable("id") Long productId) {
		return prepository.findById(productId);
	}

	// Delivery Controller

	@RequestMapping(value = "/deliverylist")
	public String bookList(Model model) {
		model.addAttribute("deliveries", drepository.findAll());
		return "deliverylist";
	}

	@RequestMapping(value = "/add/delivery")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addDelivery(Model model) {
		Delivery delivery = new Delivery();
		delivery.setStatus("Unfulfilled");
		model.addAttribute("delivery", delivery);
		return "adddelivery";
	}

	@RequestMapping(value = "/save/delivery", method = RequestMethod.POST)
	public String saveDelivery(Delivery delivery) {
		drepository.save(delivery);
		return "redirect:../deliverylist";
	}

	@RequestMapping(value = "/delete/delivery/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteDelivery(@PathVariable("id") Long deliveryId, Model model) {
		drepository.deleteById(deliveryId);
		return "redirect:http://localhost:8080/deliverylist";
	}

	@RequestMapping(value = "/edit/delivery/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editDelivery(@PathVariable("id") Long deliveryId, Model model) {
		model.addAttribute("delivery", drepository.findById(deliveryId));
		return "editdelivery";
	}

	@RequestMapping(value = "${spring.data.rest.basePath}/deliveries", method = RequestMethod.GET)
	public @ResponseBody List<Delivery> deliveryListRest() {
		return (List<Delivery>) drepository.findAll();
	}

	@RequestMapping(value = "${spring.data.rest.basePath}/delivery/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Delivery> findDeliveryRest(@PathVariable("id") Long deliveryId) {
		return drepository.findById(deliveryId);
	}
	
	// Basket controller
	
	@RequestMapping(value = "/basket/delivery/{id}", method = RequestMethod.GET)
	public String basketList(@PathVariable("id") Long deliveryId, Model model) {
		model.addAttribute("delivery", drepository.findById(deliveryId));
		return "basket";
	}

}

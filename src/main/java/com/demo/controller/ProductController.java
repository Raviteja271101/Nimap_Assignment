package com.demo.controller;


import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.demo.entity.Product;
import com.demo.repository.ProductRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;



@RestController
@RequestMapping("/api")
public class ProductController {
	@Autowired
	private ProductRepository proRepo;
	
	//post -create new
	
	@PostMapping("/product")
	public Product createNewProduct(@RequestBody Product product) {
		return proRepo.save(product);
		
	}
	
	// get all
	@GetMapping("/product")
	public List<Product> getAllProduct(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
		
		Page<Product> pagePost=proRepo.findAll(PageRequest.of(page, size));
		List<Product>allPost=pagePost.getContent();
			return allPost;

		}
	
	
	 
	// get by id
	@GetMapping("/product/{p_id}")
	public Optional<Product> findById(@PathVariable long p_id){
		return proRepo.findById(p_id);
	}
	
	//put-update by id
	
		@PutMapping("/product/{p_id}")
		public String updateProductById(@PathVariable long p_id,@RequestBody Product product) {
			Optional <Product> pro=proRepo.findById(p_id);
			if(pro.isPresent()) {
				Product existPro=pro.get();
				existPro.setP_name(product.getP_name());
				existPro.setP_price(product.getP_price());
				existPro.setP_quantity(product.getP_quantity());
				proRepo.save(existPro);
				return "Product details against id "+ p_id+" updated";
			}else  {
				return "Product details does not exist for id "+ p_id+" updated";
			}
		}
		@DeleteMapping("/product/{p_id}")
		public String deleteProductById(@PathVariable long p_id) {
			proRepo.deleteById(p_id);
			return "Product deleted successfully";
		}
}

package com.demo.controller;



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

import com.demo.entity.Category;
import com.demo.repository.CategoryRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

@RestController
@RequestMapping("/api")
public class CategoryController {
	@Autowired
	 CategoryRepository cateRepo;
	
	//post -create new
	@PostMapping("/category")
	public String createNewCategory(@RequestBody Category category) {
		
		cateRepo.save(category);
		return "Category saved";
	}
	
	// get all
	@GetMapping("/category")
	public List<Category> getAllCategory(@RequestParam (defaultValue="0") int page,@RequestParam (defaultValue="5") int size){
		
		Page <Category >list=cateRepo.findAll(PageRequest.of(page, size));
		List<Category>allList=list.getContent();
	return allList;
	}
	
	// get by id 
	@GetMapping("/category/{id}")
	@JsonIgnore
		public Optional<Category> findById(@PathVariable long id){
		
			return cateRepo.findById(id);
		
	}
	//put-update by id
	@PutMapping("/category/{id}")
	public String updateCategoryById(@PathVariable long id,@RequestBody Category category) {
		Optional <Category> cat=cateRepo.findById(id);
		if(cat.isPresent()) {
			Category existCat=cat.get();
			existCat.setC_name(category.getC_name());
			cateRepo.save(existCat);
			return "Category details against id "+ id+" upadted";
		}else  {
			return "Category details does not exist for id "+ id+" upadted";
		}
	}
	@DeleteMapping("/category/{id}")
	public String deleteCategoryById(@PathVariable long id) {
		cateRepo.deleteById(id);
		return "Category deleted successfully";
	}
}

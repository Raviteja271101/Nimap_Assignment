package com.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="category")
public class Category {
@Id
@GeneratedValue(strategy =GenerationType.IDENTITY )
@Column(name="id")	
private long id;
@Column(name="c_name")
private String c_name;

@JsonIgnore
@OneToMany  (mappedBy="category", cascade = CascadeType.ALL) 
private List<Product> product;






public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getC_name() {
	return c_name;
}

public void setC_name(String c_name) {
	this.c_name = c_name;
}

public List<Product> getProduct() {
	return product;
}

public void setProduct(List<Product> product) {
	this.product = product;
}



public Category(List<Product> product, long id, String c_name) {
	super();
	this.product = product;
	this.id = id;
	this.c_name = c_name;
}

public Category() {
	super();
}

@Override
public String toString() {
	return "Category [ id=" + id + ", c_name=" + c_name + "]";
}


}

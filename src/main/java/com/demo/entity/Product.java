package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="product")

public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "p_id")
	private long p_id;
	@Column(name = "p_name")
	private String p_name;

	@Column(name = "p_price")
	private long p_price;

	@Column(name = "p_quantity")
	private long p_quantity;
		
	
	@ManyToOne (fetch =FetchType.EAGER) 
	@JoinColumn(name = "id")

		private Category category;


	
	public long getP_id() {
		return p_id;
	}

	public void setP_id(long p_id) {
		this.p_id = p_id;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public long getP_price() {
		return p_price;
	}

	public void setP_price(long p_price) {
		this.p_price = p_price;
	}

	public long getP_quantity() {
		return p_quantity;
	}

	public void setP_quantity(long p_quantity) {
		this.p_quantity = p_quantity;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}

	public Product() {
		super();
	}
	public Product(long p_id, String p_name, long p_price, long p_quantity, Category category) {
		super();
		this.p_id = p_id;
		this.p_name = p_name;
		this.p_price = p_price;
		this.p_quantity = p_quantity;
		this.category = category;
	}


	@Override
	public String toString() {
		return "Product [p_id=" + p_id + ", p_name=" + p_name + ", p_price=" + p_price + ", p_quantity=" + p_quantity
				+ ", category=" + category + "]";
	}
	
	
	
}

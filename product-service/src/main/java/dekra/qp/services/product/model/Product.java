package dekra.qp.services.product.model;

import dekra.qp.services.product.tax.TaxCalculator;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private Long inventoryId;
	@Column(nullable = false)
	private Long categoryId;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private double price;

	@Transient
	private Double priceAfterTax;

	@Column(nullable = false)
	private String description;

	public Product() {

	}
	
	public Product(Long inventoryId, Long categoryId, String name, double price, String description) {
		this.inventoryId = inventoryId;
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Double getPriceAfterTax() {
		return priceAfterTax;
	}

	public void setPriceAfterTax(double price) {
		this.priceAfterTax = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", inventoryId=" + inventoryId + ", categoryId=" + categoryId
				+ ", name=" + name + ", description=" + description + "]";
	}

}

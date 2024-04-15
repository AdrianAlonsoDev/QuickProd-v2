package dekra.qp.services.inventory.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductDTO implements Serializable {

	private Long id;
	private String name;
	private double price;
	private String description;

	public ProductDTO() {

	}

	public ProductDTO(String name, double price, String description) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", position=" + description + "]";
	}

}

package dekra.qp.services.inventory.model;

import dekra.qp.services.inventory.dto.CategoryDTO;
import dekra.qp.services.inventory.dto.ProductDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Inventory implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String address;
	@Transient
	private List<CategoryDTO> categories = new ArrayList<>();
	@Transient
	private List<ProductDTO> productDTOS = new ArrayList<>();

	public Inventory() {

	}
	
	public Inventory(String name, String address) {
		this.name = name;
		this.address = address;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDTO> categories) {
		this.categories = categories;
	}

	public List<ProductDTO> getProducts() {
		return productDTOS;
	}

	public void setProducts(List<ProductDTO> productDTOS) {
		this.productDTOS = productDTOS;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", name=" + name + ", address=" + address + "]";
	}

}

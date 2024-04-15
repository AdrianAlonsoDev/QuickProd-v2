package dekra.qp.services.category.model;

import dekra.qp.services.category.dto.ProductDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category implements Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private Long inventoryId;
	@Column(nullable = false)
	private String name;
	@Transient
	private List<ProductDTO> productDTOS = new ArrayList<>();

	public Category() {
		
	}

	public Category(Long inventoryId, String name) {
		super();
		this.inventoryId = inventoryId;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductDTO> getProducts() {
		return productDTOS;
	}

	public void setProducts(List<ProductDTO> productDTOS) {
		this.productDTOS = productDTOS;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", inventoryId=" + inventoryId + ", name=" + name + "]";
	}

}

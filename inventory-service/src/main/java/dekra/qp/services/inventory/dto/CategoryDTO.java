package dekra.qp.services.inventory.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryDTO implements Serializable {

	private Long id;
	private String name;
	private List<ProductDTO> productDTOS = new ArrayList<>();

	public CategoryDTO() {
		
	}

	public CategoryDTO(String name) {
		super();
		this.name = name;
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

	public List<ProductDTO> getProducts() {
		return productDTOS;
	}

	public void setProducts(List<ProductDTO> productDTOS) {
		this.productDTOS = productDTOS;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

}

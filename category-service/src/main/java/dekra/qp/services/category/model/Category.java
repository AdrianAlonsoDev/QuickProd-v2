package dekra.qp.services.category.model;

import dekra.qp.services.category.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
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
	private List<ProductDTO> products = new ArrayList<>();

	public Category(Long inventoryId, String name) {
		this.inventoryId = inventoryId;
		this.name = name;
	}
}

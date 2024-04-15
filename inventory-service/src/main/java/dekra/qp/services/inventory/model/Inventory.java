package dekra.qp.services.inventory.model;

import dekra.qp.services.inventory.dto.CategoryDTO;
import dekra.qp.services.inventory.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
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
	private List<ProductDTO> products = new ArrayList<>();
	
	public Inventory(String name, String address) {
		this.name = name;
		this.address = address;
	}
}

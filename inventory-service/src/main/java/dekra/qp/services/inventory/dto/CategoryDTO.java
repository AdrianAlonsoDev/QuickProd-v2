package dekra.qp.services.inventory.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CategoryDTO implements Serializable {

	private Long id;
	private String name;
	private List<ProductDTO> productDTOS = new ArrayList<>();

	public CategoryDTO(String name) {
		super();
		this.name = name;
	}
}

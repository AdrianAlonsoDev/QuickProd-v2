package dekra.qp.services.category.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ProductDTO implements Serializable {

	private Long id;
	private String name;
	private double price;
	private String description;
	
	public ProductDTO(String name, double price, String description) {
		this.name = name;
		this.price = price;
		this.description = description;
	}


}

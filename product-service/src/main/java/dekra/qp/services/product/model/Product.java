package dekra.qp.services.product.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
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
	@Transient							// This field is not stored in the database
	private Double priceAfterTax;		// calculated on the fly TODO FIX THIS
	@Column(nullable = false)
	private String description;
	
	public Product(Long inventoryId, Long categoryId, String name, double price, String description) {
		this.inventoryId = inventoryId;
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public void setPriceAfterTax(double price) {
		this.priceAfterTax = price;
	}

}

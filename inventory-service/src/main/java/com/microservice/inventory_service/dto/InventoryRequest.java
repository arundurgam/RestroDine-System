package com.microservice.inventory_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequest {

	@NotBlank(message = "itemName must not be blank")
	private String itemName;

	@NotNull(message = "quantity must be provided")
	@Positive(message = "quantity must be positive")
	private Integer quantity;

}

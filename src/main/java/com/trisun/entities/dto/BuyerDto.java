package com.trisun.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BuyerDto {
	
	private Integer id;
	private String email;
	private String fullName;
	private String token;
}

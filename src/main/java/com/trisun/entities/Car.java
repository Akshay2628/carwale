package com.trisun.entities;

import com.trisun.core.FuelType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table( name = "cars")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private String name;
	@NotNull
	private String colour;
	@NotNull
	private Double price;
	@NotNull
	private String model;
	@NotNull
	private String mileage;
	@NotNull
	@Enumerated(EnumType.STRING)
	private FuelType fuelType;
	@NotNull
	private Integer year;
	
	private Integer seatingCapacity;
	private Integer noOfDoors;
	private String length;
	private String width;
	private String height;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn (name = "brandId")
	Brand brand;
	

}

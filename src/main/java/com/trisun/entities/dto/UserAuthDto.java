package com.trisun.entities.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAuthDto {
//    @NotNull
	private String fullName;

	@NotNull
	private String email;
	@NotNull
    private String password;

	private String userRole;
}

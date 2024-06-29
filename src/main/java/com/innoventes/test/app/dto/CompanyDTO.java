package com.innoventes.test.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

	private Long id;

	@NotBlank(message = "Company name is mandatory")
	@Size(min = 5, message = "Company name must be at least 5 characters long")
	private String companyName;

	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	private String email;

	@PositiveOrZero(message = "Strength should be a positive number or zero")
	private Integer strength;

	private String webSiteURL;

	@Pattern(regexp = "^[A-Za-z]{2}\\d{2}[ENen]$", message = "Company code must be 5 characters long, with first 2 characters as letters, next 2 characters as digits, and the last character as 'E' or 'N'")
	private String companyCode;
}

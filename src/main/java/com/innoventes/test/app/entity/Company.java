package com.innoventes.test.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "company", uniqueConstraints = @UniqueConstraint(columnNames = "company_code"))
@Entity
public class Company extends BaseEntity {

	@Id
	@SequenceGenerator(sequenceName = "company_seq", allocationSize = 1, name = "company_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
	private Long id;

	@Column(name = "company_name")
	@NotBlank(message = "Company name is mandatory")
	@Size(min = 5, message = "Company name must be at least 5 characters long")
	private String companyName;

	@Column(name = "email")
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	private String email;

	@Column(name = "strength")
	@PositiveOrZero(message = "Strength should be a positive number or zero")
	private Integer strength;

	@Column(name = "website_url")
	private String webSiteURL;

	@Column(name = "company_code", unique = true)
	@Pattern(regexp = "^[A-Za-z]{2}\\d{2}[ENen]$", message = "Company code must be 5 characters long, with first 2 characters as letters, next 2 characters as digits, and the last character as 'E' or 'N'")
	private String companyCode;
}

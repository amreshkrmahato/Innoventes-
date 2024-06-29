package com.innoventes.test.app.controller;

import com.innoventes.test.app.dto.CompanyDTO;
import com.innoventes.test.app.entity.Company;
import com.innoventes.test.app.exception.ValidationException;
import com.innoventes.test.app.mapper.CompanyMapper;
import com.innoventes.test.app.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

	@Autowired
	private CompanyMapper companyMapper;

	@Autowired
	private CompanyService companyService;

	@GetMapping("/{id}")
	public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long id) {
		Company company = companyService.findById(id);
		if (company == null) {
			return ResponseEntity.notFound().build();
		}
		CompanyDTO companyDTO = companyMapper.getCompanyDTO(company);
		return ResponseEntity.ok(companyDTO);
	}

	@GetMapping
	public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
		List<Company> companies = companyService.getAllCompanies();
		List<CompanyDTO> companyDTOs = companies.stream()
				.map(companyMapper::getCompanyDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(companyDTOs);
	}

	@PostMapping
	public ResponseEntity<CompanyDTO> addCompany(@RequestBody @Valid CompanyDTO companyDTO) throws ValidationException {
		Company company = companyMapper.getCompany(companyDTO);
		Company newCompany = companyService.addCompany(company);
		CompanyDTO newCompanyDTO = companyMapper.getCompanyDTO(newCompany);

		URI location = URI.create("/api/v1/companies/" + newCompany.getId());
		return ResponseEntity.created(location).body(newCompanyDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long id, @RequestBody @Valid CompanyDTO companyDTO) throws ValidationException {
		Company company = companyMapper.getCompany(companyDTO);
		Company updatedCompany = companyService.updateCompany(id, company);
		CompanyDTO updatedCompanyDTO = companyMapper.getCompanyDTO(updatedCompany);
		return ResponseEntity.ok(updatedCompanyDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
		companyService.deleteCompany(id);
		return ResponseEntity.noContent().build();
	}
}

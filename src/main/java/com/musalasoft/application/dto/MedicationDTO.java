package com.musalasoft.application.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicationDTO {

	@Length(max = 100)
	private String serialNumber;
	
	@Pattern(regexp = "^[A-Za-z0-9_-]*$")
	private String name;
	
	private double weight;
	
	@Pattern(regexp = "^[A-Z0-9_]*$")
	private String code;
	
	private MultipartFile image;
}

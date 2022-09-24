package com.musalasoft.application.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MedicationItems {

	private String name;
	private double weight;
	private String code;
	private String image;
	
}

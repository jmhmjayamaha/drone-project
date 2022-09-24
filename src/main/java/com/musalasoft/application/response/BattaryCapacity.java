package com.musalasoft.application.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class BattaryCapacity {

	private String serialNumber;
	private String battaryCapacity;
	
}

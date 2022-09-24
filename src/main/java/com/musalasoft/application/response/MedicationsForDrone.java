package com.musalasoft.application.response;

import java.util.List;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MedicationsForDrone {

	private String droneSerialNumber;
	private List<MedicationItems> medications;
}

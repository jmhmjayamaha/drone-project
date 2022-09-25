package com.musalasoft.application.service;

import static org.mockito.BDDMockito.given;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;


import com.musalasoft.application.model.Drone;
import com.musalasoft.application.model.DroneModel;
import com.musalasoft.application.model.DroneState;
import com.musalasoft.application.model.Medication;
import com.musalasoft.application.repository.DroneRepository;

@ExtendWith(MockitoExtension.class)
public class DroneServiceTests {

	@Mock
	private DroneRepository droneRepository;
	
	@InjectMocks
	private DroneServiceImpl droneService;
	
	private Drone drone;
	
	@BeforeEach
	public void setUp() {
		drone = new Drone();
		Drone drone1 = new Drone();
		Set<Medication> set = new HashSet<Medication>();
		
		drone1.setId(1);
		drone1.setSerialNumber("sdbddetijkigk124kkd");
		drone1.setModel(DroneModel.HEAVYWEIGHT.toString());
		drone1.setWeightLimit(400.0);
		drone1.setBatteryCapacity(86.0);
		drone1.setState(DroneState.IDLE.toString());
		drone1.setMedication(set);
	}
	
	@Test
	public void givenSerialNumber_and_getDroneDetails_Test() {
		given(droneRepository.getBySerialNumber(drone.getSerialNumber()))
		.willReturn(drone);
		
		Drone expected = droneService.getDroneDetails(drone.getSerialNumber());
		
		assertThat(expected).isNotNull();
	}
}

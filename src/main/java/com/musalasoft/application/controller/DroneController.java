package com.musalasoft.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.musalasoft.application.model.Drone;
import com.musalasoft.application.response.BattaryCapacity;
import com.musalasoft.application.response.MedicationsForDrone;
import com.musalasoft.application.service.DroneService;

@RestController
@RequestMapping("/api/v1/drone")
public class DroneController {

	@Autowired
	private DroneService droneService;
	
	@GetMapping(value="/all")
	public List<Drone> getAllDrones() {
		return droneService.getAllDrones();
	}
	
	@PostMapping(value="/register")
	@ResponseStatus(value=HttpStatus.CREATED)
	public void registerDrone(@RequestBody Drone drone) {
		droneService.registerDrone(drone);
	}
	
	@GetMapping(value="/{serialNumber}")
	public Drone findBySerialNumber(@PathVariable("serialNumber") String serialNumber) {
		System.out.println(serialNumber);
		return droneService.getDroneDetails(serialNumber);
	}
	
	@GetMapping(value="/battary-capacity/{serial-number}")
	public BattaryCapacity getBattaryCapacity(@PathVariable("serial-number") String serialNumber) {
		return droneService.droneBatteryCapacity(serialNumber);
	}
	
	@GetMapping(value="/avaiable-for-load")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Drone> getAvaialbeDrones() {
		return droneService.getAvailableDrones();
	}
	
	@GetMapping(value="/{serial-number}/medicationItems")
	public MedicationsForDrone getMedicationForADrone(@PathVariable("serial-number")String serialNumber) {
		return droneService.getMedicationItemForADrone(serialNumber);
	}
}

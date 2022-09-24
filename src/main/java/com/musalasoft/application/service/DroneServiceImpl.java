package com.musalasoft.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musalasoft.application.model.Drone;
import com.musalasoft.application.model.Medication;
import com.musalasoft.application.repository.DroneRepository;
import com.musalasoft.application.response.BattaryCapacity;

@Service
public class DroneServiceImpl implements DroneService {

	@Autowired
	private DroneRepository droneRepository;

	@Override
	public void registerDrone(Drone drone) {
		if(drone != null) {
			droneRepository.save(drone);
		}
	}

	@Override
	public Drone getDroneDetails(String serialNumber) {
		return droneRepository.getBySerialNumber(serialNumber);
	}

	@Override
	public List<Drone> availableDrones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medication> meditionItemforDrone(String serialNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMedicationItemsForDrone(String serialNumber, Medication medication) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Drone> getAllDrones() {
		
		return (List<Drone>) droneRepository.findAll();
	}

	@Override
	public BattaryCapacity droneBatteryCapacity(String serialNumber) {
		double battaryLevel = droneBatteryLevel(serialNumber);
		
		BattaryCapacity battaryCapacity = BattaryCapacity.builder().serialNumber(serialNumber).battaryCapacity(battaryLevel+"%").build();

		return battaryCapacity;
	}
	
	
	public double droneBatteryLevel(String serialNumber) {
		Drone drone = droneRepository.getBySerialNumber(serialNumber);
		if(drone != null) {
			return drone.getBatteryCapacity();
		}
		return 0;
	}
}

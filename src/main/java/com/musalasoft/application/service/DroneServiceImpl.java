package com.musalasoft.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musalasoft.application.model.Drone;
import com.musalasoft.application.model.Medication;
import com.musalasoft.application.repository.DroneRepository;
import com.musalasoft.application.response.BattaryCapacity;
import com.musalasoft.application.response.MedicationItems;
import com.musalasoft.application.response.MedicationsForDrone;

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
	public List<Drone> getAvailableDrones() {
		
		return droneRepository.findAvailableDrones();
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

	@Override
	public MedicationsForDrone getMedicationItemForADrone(String serialNumber) {
		Drone drone = droneRepository.getBySerialNumber(serialNumber);
		
		List<MedicationItems> itemList = new ArrayList<MedicationItems>();
		
		drone.getMedication().stream().forEach(item -> {
			itemList.add(MedicationItems.builder()
					.name(item.getName())
					.code(item.getCode())
					.weight(item.getWeight())
					.image(item.getImageLocation())
					.build());
		});
		
		return MedicationsForDrone.builder().droneSerialNumber(serialNumber).medications(itemList).build();
	}
}

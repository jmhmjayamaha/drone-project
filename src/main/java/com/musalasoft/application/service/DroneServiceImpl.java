package com.musalasoft.application.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musalasoft.application.dto.MedicationDTO;
import com.musalasoft.application.exception.CustomException;
import com.musalasoft.application.exception.ResourceNotFoundException;
import com.musalasoft.application.model.Drone;
import com.musalasoft.application.model.DroneState;
import com.musalasoft.application.model.Medication;
import com.musalasoft.application.repository.DroneRepository;
import com.musalasoft.application.repository.MedicationRepository;
import com.musalasoft.application.response.BattaryCapacity;
import com.musalasoft.application.response.MedicationItems;
import com.musalasoft.application.response.MedicationsForDrone;

@Service
public class DroneServiceImpl implements DroneService {
	
	private static final Logger log = LoggerFactory.getLogger(DroneServiceImpl.class);

	@Autowired
	private DroneRepository droneRepository;
	
	@Autowired
	private MedicationRepository medicationRepository;
	
	@Autowired
	private ImageService imageService;

	@Override
	public void registerDrone(Drone drone) {
	
			if(drone != null) {
				droneRepository.save(drone);
			} else {
				throw new ResourceNotFoundException("Passing drone object is null");
			}
		 
	}

	@Override
	public Drone getDroneDetails(String serialNumber) {
		Drone drone = droneRepository.getBySerialNumber(serialNumber);
		if(drone == null) {
			throw new ResourceNotFoundException("serial number : " + serialNumber + "Not Found");
		}
		return drone;
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
	public void addMedicationItemsForDrone(MedicationDTO medicationDto) throws IOException {
		Drone drone = droneRepository.getBySerialNumber(medicationDto.getSerialNumber());
		double totalWeight = 0.0;
		if(drone != null) {
			if((drone.getState().equals(DroneState.LOADING.toString()) || drone.getState().equals(DroneState.IDLE.toString())) 
					&& drone.getBatteryCapacity() < 25) {
				throw new CustomException("Battary is below 25%");
			}
			
			for(Medication items : drone.getMedication()) {
				totalWeight += items.getWeight();
			}
			totalWeight += medicationDto.getWeight();
			
			log.info("total weight :  " + totalWeight);
			
			if(totalWeight > drone.getWeightLimit()) {
				throw new CustomException("Maximum weight exceed the limit " + drone.getWeightLimit());
			}
			
			imageService.saveFile(medicationDto.getImage(), getFilePath("src/main/resources/images", 
					medicationDto.getName()));
			
			Medication medication = new Medication();
			medication.setDrone(drone);
			medication.setName(medicationDto.getName());
			medication.setWeight(medicationDto.getWeight());
			medication.setCode(medicationDto.getCode());
			medication.setImageLocation("src/main/resources/images/" + medicationDto.getName() + 
					"/" + medicationDto.getImage().getOriginalFilename());
			
			log.info("image is saved to : " + medication.getImageLocation());
			
			medicationRepository.save(medication);
			drone.setState(DroneState.LOADING.toString());
			droneRepository.save(drone);
		} else {
			throw new ResourceNotFoundException("serial number : " + medicationDto.getSerialNumber() +
					"Not Found");
		}
	}

	private Path getFilePath(String basePath, String medicationName){
        Path path= Paths.get(basePath);
        return path.resolve(String.valueOf(medicationName));
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
		} else {
			throw new ResourceNotFoundException("serial number : " + serialNumber + " Not Found");
		}
	}

	@Override
	public MedicationsForDrone getMedicationItemForADrone(String serialNumber) {
		Drone drone = droneRepository.getBySerialNumber(serialNumber);
		
		List<MedicationItems> itemList = new ArrayList<MedicationItems>();
		
		if(drone == null) {
			throw new ResourceNotFoundException("serial number : " + serialNumber + " Not Found");
		}
		
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

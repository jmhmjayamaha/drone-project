package com.musalasoft.application.service;

import java.io.IOException;
import java.util.List;

import com.musalasoft.application.dto.MedicationDTO;
import com.musalasoft.application.model.Drone;
import com.musalasoft.application.model.Medication;
import com.musalasoft.application.response.BattaryCapacity;
import com.musalasoft.application.response.MedicationsForDrone;

public interface DroneService {

	public void registerDrone(Drone drone);
	public Drone getDroneDetails(String serialNumber);
	public BattaryCapacity droneBatteryCapacity(String serialNumber);
	public List<Drone> getAvailableDrones();
	public List<Drone> getAllDrones();
	public List<Medication> meditionItemforDrone(String serialNumber);
	public void addMedicationItemsForDrone(MedicationDTO medication) throws IOException;
	public MedicationsForDrone getMedicationItemForADrone(String serialNumber);
}

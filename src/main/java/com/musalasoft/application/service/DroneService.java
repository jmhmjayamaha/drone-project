package com.musalasoft.application.service;

import java.util.List;

import com.musalasoft.application.model.Drone;
import com.musalasoft.application.model.Medication;
import com.musalasoft.application.response.BattaryCapacity;

public interface DroneService {

	public void registerDrone(Drone drone);
	public Drone getDroneDetails(String serialNumber);
	public BattaryCapacity droneBatteryCapacity(String serialNumber);
	public List<Drone> availableDrones();
	public List<Drone> getAllDrones();
	public List<Medication> meditionItemforDrone(String serialNumber);
	public void addMedicationItemsForDrone(String serialNumber, Medication medication);
}

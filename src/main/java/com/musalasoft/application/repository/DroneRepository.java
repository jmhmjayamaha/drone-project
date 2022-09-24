package com.musalasoft.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.musalasoft.application.model.Drone;

public interface DroneRepository extends CrudRepository<Drone, Integer> {

	public Drone getBySerialNumber(String serialNumber);
	
	@Query("SELECT d FROM Drone d WHERE d.state='IDLE'")
	public List<Drone> findAvailableDrones();
	
}

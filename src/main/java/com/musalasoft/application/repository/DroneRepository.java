package com.musalasoft.application.repository;

import org.springframework.data.repository.CrudRepository;

import com.musalasoft.application.model.Drone;

public interface DroneRepository extends CrudRepository<Drone, Integer> {

	public Drone getBySerialNumber(String serialNumber);
	
}

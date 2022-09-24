package com.musalasoft.application.repository;

import org.springframework.data.repository.CrudRepository;

import com.musalasoft.application.model.Medication;

public interface MedicationRepository extends CrudRepository<Medication, Integer>{

}

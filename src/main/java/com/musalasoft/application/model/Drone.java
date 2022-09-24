package com.musalasoft.application.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="drone_details")
@Setter
@Getter
public class Drone {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(unique = true)
	private String serialNumber;
	private String model;
	private double weightLimit;
	private double batteryCapacity;
	private String state;
	
	  @OneToMany(fetch = FetchType.LAZY, mappedBy = "drone")
	  private Set<Medication> medication;
	 
}

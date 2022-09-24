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
import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.Length;

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
	@Length(max = 100)
	private String serialNumber;
	
	private String model;
	
	@Max(500)
	private double weightLimit;
	
	private double batteryCapacity;
	
	private String state;
	
	  @OneToMany(fetch = FetchType.LAZY, mappedBy = "drone")
	  private Set<Medication> medication;
	 
}

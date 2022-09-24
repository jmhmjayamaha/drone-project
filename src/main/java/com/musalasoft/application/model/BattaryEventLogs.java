package com.musalasoft.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="battary_event_logs")
@Getter
@Setter
public class BattaryEventLogs {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String serialNumber;
	
	private String updatedTime;
	
	private double battaryLevel;

}

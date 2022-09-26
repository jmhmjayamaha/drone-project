package com.musalasoft.application.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.musalasoft.application.model.BattaryEventLogs;
import com.musalasoft.application.model.Drone;
import com.musalasoft.application.repository.BattaryEventLogRepository;
import com.musalasoft.application.repository.DroneRepository;

@Service
public class BattaryEventSchedulerImpl implements BattaryEventScheduler {
	
	private static final Logger log = LoggerFactory.getLogger(BattaryEventLogs.class);

	@Autowired
	private BattaryEventLogRepository eventLogRepo;
	
	@Autowired
	private DroneRepository droneRepo;
	
	@Override
	@Scheduled(fixedRate = 50000)
	public void eventLogsScheduler() {
		List<Drone> droneList = (List<Drone>) droneRepo.findAll();
		droneList.stream().forEach(drone -> {
			BattaryEventLogs eventLogs = new BattaryEventLogs();
			eventLogs.setSerialNumber(drone.getSerialNumber());	
			eventLogs.setBattaryLevel(drone.getBatteryCapacity());
			eventLogs.setUpdatedTime(new Date().toString());
					
			log.info("event logs: serialNumber="+ drone.getSerialNumber() + " battaryCapacity=" 
					+ drone.getBatteryCapacity() + " date: " + new Date().toString());
			eventLogRepo.save(eventLogs);

		});
	}

	@Override
	public List<BattaryEventLogs> getBattaryEventLogs() {
		
		return (List<BattaryEventLogs>) eventLogRepo.findAll();
	}
	
}

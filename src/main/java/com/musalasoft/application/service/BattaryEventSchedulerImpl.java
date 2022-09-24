package com.musalasoft.application.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.musalasoft.application.model.BattaryEventLogs;
import com.musalasoft.application.model.Drone;
import com.musalasoft.application.repository.BattaryEventLogRepository;
import com.musalasoft.application.repository.DroneRepository;

@Service
public class BattaryEventSchedulerImpl implements BattaryEventScheduler {

	@Autowired
	private BattaryEventLogRepository eventLogRepo;
	
	@Autowired
	private DroneRepository droneRepo;
	
	@Override
	@Scheduled(fixedRate = 5000)
	public void eventLogsScheduler() {
		List<Drone> droneList = (List<Drone>) droneRepo.findAll();
		droneList.stream().forEach(drone -> {
			BattaryEventLogs eventLogs = new BattaryEventLogs();
			eventLogs.setSerialNumber(drone.getSerialNumber());	
			eventLogs.setBattaryLevel(drone.getBatteryCapacity());
			eventLogs.setUpdatedTime(new Date().toString());
					
			eventLogRepo.save(eventLogs);

		});
	}

	@Override
	public List<BattaryEventLogs> getBattaryEventLogs() {
		
		return (List<BattaryEventLogs>) eventLogRepo.findAll();
	}
	
}

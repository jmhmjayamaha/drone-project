package com.musalasoft.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musalasoft.application.model.BattaryEventLogs;
import com.musalasoft.application.service.BattaryEventScheduler;

@RestController
@RequestMapping("/api/v1/audit")
public class AuditController {
	
	@Autowired
	private BattaryEventScheduler battaryEventScheduler;
	
	@GetMapping(value="/battary")
	public List<BattaryEventLogs> getBattaryEventLogs() {
		return battaryEventScheduler.getBattaryEventLogs();
		
	}
}

package com.musalasoft.application.service;

import java.util.List;

import com.musalasoft.application.model.BattaryEventLogs;

public interface BattaryEventScheduler {

	public void eventLogsScheduler();
	
	public List<BattaryEventLogs> getBattaryEventLogs();
	
}

package com.musalasoft.application.repository;

import org.springframework.data.repository.CrudRepository;

import com.musalasoft.application.model.BattaryEventLogs;

public interface BattaryEventLogRepository extends CrudRepository<BattaryEventLogs, Integer>{

}

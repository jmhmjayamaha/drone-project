package com.musalasoft.application.model;

public enum DroneModel {
	LIGHTWEIGHT("Lightweight"),
	MIDDLEWEIGHT("Middleweight"),
	CRUISERWEIGHT("Cruiseweight"),
	HEAVYWEIGHT("Heavyweight");
	
	private String modelAsString;
	
	private DroneModel(String modelAsString) {
		this.modelAsString = modelAsString;
	}
	
	@Override
	public String toString() {
		return this.modelAsString;
	}
}

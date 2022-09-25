package com.musalasoft.application.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.musalasoft.application.model.Drone;
import com.musalasoft.application.model.DroneModel;
import com.musalasoft.application.model.DroneState;
import com.musalasoft.application.model.Medication;
import com.musalasoft.application.service.DroneService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value=DroneController.class)
public class DroneControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DroneService droneService;
	
	private String droneList = "[{\"id\":1,\"serialNumber\":\"asdfddec1235\",\"model\":\"Lightweight\",\"weightLimit\":400.0,\"batteryCapacity\""
			+ ":90.0,\"state\":\"LOADING\",\"medication\":["
			+ "{\"id\":1,\"name\":\"suger1 tester\",\"weight\":100.0,\"code\":\"3r4s\",\"imageLocation\":\"http://locations\",\"drone\":1}]},"
			+ "{\"id\":3,\"serialNumber\":\"asdfddec1sdf235\",\"model\":\"Lightweight\",\"weightLimit\":400.0,\"batteryCapacity\":24.0,\"state\""
			+ ":\"IDLE\",\"medication\":[]}]";
	
	
	@Test
	public void getAllDronesTest() throws Exception {
		Mockito.when(droneService.getAllDrones()).thenReturn(createDroneList());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/drone/all");
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
		String expected = "[{\"id\":1,\"serialNumber\":\"sdbddetijkigk124kkd\",\"model\":\"Heavyweight\",\"weightLimit\":400.0,\"batteryCapacity\":80.0,\"state\":\"IDLE\",\"medication\":[]},"
				+ "{\"id\":2,\"serialNumber\":\"sdfsdfsdfdsfs23434\",\"model\":\"Lightweight\",\"weightLimit\":450.0,\"batteryCapacity\":77.0,\"state\":\"IDLE\",\"medication\":[]}]";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	private List<Drone> createDroneList() {
		List<Drone> list = new ArrayList<Drone>();
		Set<Medication> set = new HashSet<Medication>();
		
		Drone drone1 = new Drone();
		drone1.setId(1);
		drone1.setSerialNumber("sdbddetijkigk124kkd");
		drone1.setModel(DroneModel.HEAVYWEIGHT.toString());
		drone1.setWeightLimit(400.0);
		drone1.setBatteryCapacity(80.0);
		drone1.setState(DroneState.IDLE.toString());
		drone1.setMedication(set);
		
		Drone drone2 = new Drone();
		drone2.setId(2);
		drone2.setSerialNumber("sdfsdfsdfdsfs23434");
		drone2.setModel(DroneModel.LIGHTWEIGHT.toString());
		drone2.setWeightLimit(450.0);
		drone2.setBatteryCapacity(77.0);
		drone2.setState(DroneState.IDLE.toString());
		drone2.setMedication(set);
		
		list.add(drone1);
		list.add(drone2);
		return list;
	}
}

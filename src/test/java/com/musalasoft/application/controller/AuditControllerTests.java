package com.musalasoft.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.musalasoft.application.model.BattaryEventLogs;
import com.musalasoft.application.service.BattaryEventScheduler;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value=AuditController.class)
public class AuditControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BattaryEventScheduler battaryEventScheduler;
	
	@Test
	public void getBattaryEventLogsTest() throws Exception {
		Mockito.when(battaryEventScheduler.getBattaryEventLogs()).thenReturn(createBattaryEventLogs());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/audit/battary")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "[{\"id\": 1,\"serialNumber\": \"sdfsdfsrer343\",\"updatedTime\": \"Sun Sep 25 22:47:56 IST 2022\",\"battaryLevel\": 30.0},{\"id\": 2,\"serialNumber\":"
				+ " \"ewerwrtrtwwrer4\",\"updatedTime\":"
				+ " \"Sun Sep 25 22:33:56 IST 2022\",\"battaryLevel\": 35.0}]";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	private List<BattaryEventLogs> createBattaryEventLogs() {
		List<BattaryEventLogs> logs = new ArrayList<BattaryEventLogs>();
		
		BattaryEventLogs log = new BattaryEventLogs();
		log.setId(1);
		log.setSerialNumber("sdfsdfsrer343");
		log.setUpdatedTime("Sun Sep 25 22:47:56 IST 2022");
		log.setBattaryLevel(30.0);
		
		BattaryEventLogs log1 = new BattaryEventLogs();
		log1.setId(2);
		log1.setSerialNumber("ewerwrtrtwwrer4");
		log1.setUpdatedTime("Sun Sep 25 22:33:56 IST 2022");
		log1.setBattaryLevel(35.0);
		
		logs.add(log);
		logs.add(log1);
		
		return logs;
	}
}

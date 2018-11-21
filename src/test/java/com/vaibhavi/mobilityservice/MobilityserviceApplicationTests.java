package com.vaibhavi.mobilityservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaibhavi.mobilityservice.repository.RoomRepository;
import com.vaibhavi.mobilityservice.service.MobilityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MobilityserviceApplicationTests {

	@Test
	public void contextLoads() {
	}

}

package com.vaibhavi.mobilityservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaibhavi.mobilityservice.entiry.Room;
import com.vaibhavi.mobilityservice.exception.MoveIsForbidden;
import com.vaibhavi.mobilityservice.exception.MoveIsUnauthorized;
import com.vaibhavi.mobilityservice.service.MobilityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MobilityControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    MobilityService mobilityServiceMock;

    @Test
    public void test_moveCharacter() throws Exception {
        when(mobilityServiceMock.moveCharacter(5, 7))
                .thenReturn("Moved");
        mockMvc.perform(MockMvcRequestBuilders.post("/move/{characterId}/to/{nextRoom}", 5,7))
                .andExpect(status().isOk());
        verify(mobilityServiceMock, times(1)).moveCharacter(5,7);
        verifyNoMoreInteractions(mobilityServiceMock);
    }

    @Test
    public void test_moveCharacterMoveIsUnauthorized() throws Exception {
        when(mobilityServiceMock.moveCharacter(2, 7))
                .thenThrow(MoveIsUnauthorized.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/move/{characterId}/to/{nextRoom}", 2,7))
                .andExpect(status().isUnauthorized());
        verify(mobilityServiceMock, times(1)).moveCharacter(2,7);
        verifyNoMoreInteractions(mobilityServiceMock);
    }
}

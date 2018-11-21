package com.vaibhavi.mobilityservice.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaibhavi.mobilityservice.entiry.Room;
import com.vaibhavi.mobilityservice.exception.MoveIsUnauthorized;
import com.vaibhavi.mobilityservice.exception.RoomNotFound;
import com.vaibhavi.mobilityservice.service.MobilityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MobilityRepositoryTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Mock
    RoomRepository roomRepositoryMock;

    @InjectMocks
    MobilityService mobilityService;

    @Test(expected= RoomNotFound.class)
    public void test_moveCharacterRoomNotfound() throws Exception {
        when(mobilityService.isMoveValid(2, 7))
                .thenReturn(false);
        when(roomRepositoryMock.findById(2))
                .thenReturn(Optional.of(new Room()));
        mockMvc.perform(MockMvcRequestBuilders.post("/move/{characterId}/to/{nextRoom}", 5,7))
                .andExpect(status().isNotFound());
        verify(roomRepositoryMock, times(1)).findById(2);
        verifyNoMoreInteractions(roomRepositoryMock);
    }

    @Test
    public void test_moveCharacterMoveIsForbidden() throws Exception {
        int[] arr = new int[]{1,2,2};
        Room testRoom = new Room(1, "some sample description",arr);
        Optional<Room> testRoomOptional = Optional.of(testRoom);
        when(roomRepositoryMock.findById(2)).thenReturn(testRoomOptional);
        //when(mobilityService.isMoveValid(2, 7)).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/move/{characterId}/to/{nextRoom}", 5,7))
                .andExpect(status().isForbidden());
        verifyNoMoreInteractions(roomRepositoryMock);
    }
}

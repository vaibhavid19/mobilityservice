package com.vaibhavi.mobilityservice.service;

import com.vaibhavi.mobilityservice.entiry.Room;
import com.vaibhavi.mobilityservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class MobilityService {
    @Autowired
    RoomRepository roomRepository;

    public int[] genRandomNumBetween(int num1, int num2) {
        int range = (num2 - num1) + 1;
        int[] roomExits = new int[3];
        for (int i=0; i<3; i++) {
            roomExits[i] = (int)(Math.random() * range) + num1;
        }
        return roomExits;
    }

    public void moveCharacter(int characterId, int nextRoom) throws Exception {
        final String uri = "http://localhost:8080/character/get/" + characterId;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        Optional<Room> roomOptional = this.roomRepository.findById(nextRoom);
        if(!roomOptional.isPresent()) {
            throw new Exception("No such room exists");
        }
        if (isMoveValid()) {
            //Cha
        }
    }

    public boolean isMoveValid() {
        return true;
    }

    public Room saveRoom(Room room) {
        return this.roomRepository.save(room);
    }
}

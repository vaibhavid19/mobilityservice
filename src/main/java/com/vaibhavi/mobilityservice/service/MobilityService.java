package com.vaibhavi.mobilityservice.service;

import com.vaibhavi.mobilityservice.entiry.Room;
import com.vaibhavi.mobilityservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.Optional;
import java.util.stream.IntStream;
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

    public String moveCharacter(int characterId, int nextRoom) throws Exception {
        final String uri = "http://localhost:8080/character/get/" + characterId;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        Optional<Room> roomOptional = this.roomRepository.findById(nextRoom);
        if(!roomOptional.isPresent()) {
            throw new Exception("No such room exists");
        }
        if (isMoveValid(Integer.valueOf(result))) {
            return "Moved";
        }
        return "Unable to move";
    }

    public boolean isMoveValid(int currentLocation) throws Exception{
        Optional<Room> currentRoomOptional = this.roomRepository.findById(currentLocation);
        if(!currentRoomOptional.isPresent()) {
            throw new Exception("No such room exists!");
        }
        System.out.println("ROOM IS : " + currentLocation);
        System.out.println("EXITS ARE : " + currentRoomOptional.get().getExits());

        System.out.println("CHECK RESOLVES : ");

        if (IntStream.of(currentRoomOptional.get().getExits()).anyMatch(i -> i == currentLocation)) {
            return true;
        }
        return false;
    }

    public Room saveRoom(Room room) {
        return this.roomRepository.save(room);
    }

    public Room getRoom(int roomId) throws Exception {
        Optional<Room> roomOptional = this.roomRepository.findById(roomId);
        if(!roomOptional.isPresent()) {
            throw new Exception("No such room exists!");
        }
        return roomOptional.get();
    }
}

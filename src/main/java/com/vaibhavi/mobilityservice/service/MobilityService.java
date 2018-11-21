package com.vaibhavi.mobilityservice.service;

import com.vaibhavi.mobilityservice.entiry.Room;
import com.vaibhavi.mobilityservice.exception.MoveIsForbidden;
import com.vaibhavi.mobilityservice.exception.MoveIsUnauthorized;
import com.vaibhavi.mobilityservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
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
        final String uri1 = "http://localhost:8080/character/get/location/" + characterId;
        RestTemplate restTemplate1 = new RestTemplate();
        String result = restTemplate1.getForObject(uri1, String.class);

        final String uri2 = "http://localhost:8080/character/get/hitpoints/" + characterId;
        RestTemplate restTemplate2 = new RestTemplate();
        String result2 = restTemplate2.getForObject(uri2, String.class);
        if (Integer.parseInt(result2)<1) {
            throw new MoveIsUnauthorized();
        }
        Optional<Room> roomOptional = this.roomRepository.findById(nextRoom);
        if(!roomOptional.isPresent()) {
            throw new Exception("No such room exists");
        }
        if (isMoveValid(Integer.valueOf(result), nextRoom)) {
            //http://localhost:8080/character/update/location/9/10
            //http://localhost:8080/character/update/location/5/7
            String uri3 = "http://localhost:8080/character/update/location/" + characterId +"/" + nextRoom;
            System.out.println("UPDATING AT" + uri3);
            RestTemplate restTemplate3 = new RestTemplate();
            String result3 = restTemplate3.getForObject(uri3, String.class);

            if (result3=="" || result3==null) {
                throw new MoveIsForbidden();
            }
            return "Moved";
        }
        return "Unable to move!";
    }

    public boolean isMoveValid(int currentLocation, int nextRoom) throws Exception{
        Optional<Room> currentRoomOptional = this.roomRepository.findById(currentLocation);
        if(!currentRoomOptional.isPresent()) {
            throw new Exception("No such room exists!");
        }
        Room theRoom = currentRoomOptional.get();
        System.out.println("ROOM IS : " + currentLocation);
        int myArr[] = theRoom.getExits();
        for (int i=0; i<myArr.length; i++) {
            int roomNumber = myArr[i];
            String roomNumberStr = String.valueOf(roomNumber);
            String nextRoomForCharacter = String.valueOf(nextRoom);
            System.out.println("Array elements are format 1 : " + Integer.valueOf(myArr[i]));
            System.out.println("TYPE 1  : " + roomNumberStr instanceof String);
            System.out.println("TYPE 2  : " + nextRoomForCharacter instanceof String);
            System.out.println("CHECK 1   : " + nextRoomForCharacter.equalsIgnoreCase(roomNumberStr));
            System.out.println("CHECK 2   : " + nextRoomForCharacter.contentEquals(roomNumberStr));
            System.out.println("Array elements are : " + myArr[i]);
            System.out.println("TO MOVE TO ? : " + nextRoomForCharacter + " " + nextRoom);
            if (nextRoomForCharacter.equalsIgnoreCase(roomNumberStr)){
                return true;
            }
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

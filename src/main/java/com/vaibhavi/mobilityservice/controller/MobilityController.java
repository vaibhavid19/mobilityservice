package com.vaibhavi.mobilityservice.controller;

import com.vaibhavi.mobilityservice.entiry.Room;
import com.vaibhavi.mobilityservice.repository.RoomRepository;
import com.vaibhavi.mobilityservice.service.MobilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MobilityController {

    @Autowired
    MobilityService mobilityService;

    @PostMapping("/room/add")
    public Room addRoom(@RequestBody Room room) {
        room.setExits(mobilityService.genRandomNumBetween(8,18));
        return this.mobilityService.saveRoom(room);
    }

    @GetMapping("/room/get/{roomId}")
    public Room getRoomDetails(@PathVariable int roomId) throws Exception{
        return this.mobilityService.getRoom(roomId);
    }
//
//    Scenario: Legal move
//    Given /move/1/to/4
//    When character 1 is in room 3 which has exits to 4, 5 and 7.
//    Then character 1's location is updated to 4 and a 200 is returned
    @PostMapping("/move/{characterId}/to/{nextRoom}")
    public String moveCharacter(@PathVariable int characterId, @PathVariable int nextRoom) throws Exception{
        return mobilityService.moveCharacter(characterId, nextRoom);
    }

//    Scenario: Illegal move
//    Given /move/1/to/6
//    When character 1 is in room 3 which has exits to 4, 5 and 7.
//    Then character 1's location is not changed and a 403 is returned.
//
//    Scenario: Illegal move, the character is dead.
//    Given /move/1/to/5
//    When character 1 is in room 3 which has exits to 4, 5 and 7, but the character's hitPoints is less than 1.
//    Then character 1's location is not changed and a 401 is returned.


}

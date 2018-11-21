package com.vaibhavi.mobilityservice.repository;

import com.vaibhavi.mobilityservice.entiry.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Integer> {

    //@Query("from Item i where i.id=:id AND i.name=:name")
    //public Optional<Item> findByItemNameAndId(@Param("id") int id, @Param("name") String name);
    public Room save(Room room);

}

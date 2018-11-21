package com.vaibhavi.mobilityservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND,reason="Room with given ID does not exist. ")
public class RoomNotFound extends RuntimeException {
}

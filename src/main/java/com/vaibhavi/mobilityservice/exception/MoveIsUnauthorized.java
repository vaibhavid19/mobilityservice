package com.vaibhavi.mobilityservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNAUTHORIZED,reason="The character has hitPoints less than 1. Location remains unchanged. ")
public class MoveIsUnauthorized extends RuntimeException{
}

package com.vaibhavi.mobilityservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN,reason="The character can not allowed to move to specified room. No exits from current location available. ")
public class MoveIsForbidden extends RuntimeException{
}

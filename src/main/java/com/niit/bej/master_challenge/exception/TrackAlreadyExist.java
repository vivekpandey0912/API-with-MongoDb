package com.niit.bej.master_challenge.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Track is already Exist")
public class TrackAlreadyExist extends Exception {
}

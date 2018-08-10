package com.springboot.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springboot.rest.model.Message;
import com.springboot.rest.model.NodeJsMessage;

@RestController
@RequestMapping("/ws")
public class BaseController {

	@GetMapping(path = "/message", produces=MediaType.APPLICATION_JSON_VALUE)
//    public Message defaultMessage() {
	public ResponseEntity<Object> defaultMessage(){
		RestTemplate restTemplate = new RestTemplate();
		String message = restTemplate.getForObject("http://localhost:3000/test.ws",String.class);
//      return new Message("[name="+message.getName()+"],[version="+message.getVersion()+"]");
//      return new Message(message);
		return new ResponseEntity<Object>(message, HttpStatus.OK);
    }
	
}

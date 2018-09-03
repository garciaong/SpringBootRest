package com.springboot.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springboot.rest.model.Message;
import com.springboot.rest.model.NodeJsMessage;

@RestController
@RequestMapping("/ws")
public class BaseController {

	private static final Logger log = LoggerFactory.getLogger(BaseController.class);
			
	@GetMapping(path = "/message", produces=MediaType.APPLICATION_JSON_VALUE)
//    public Message defaultMessage() {
	public ResponseEntity<Object> defaultMessage(){
		log.info("Invoking Node JS web service...");
		RestTemplate restTemplate = new RestTemplate();
		String message = restTemplate.getForObject("http://localhost:3000/test.ws",String.class);
//      return new Message("[name="+message.getName()+"],[version="+message.getVersion()+"]");
//      return new Message(message);
		log.info("Response message from web service call : "+message);
		return new ResponseEntity<Object>(message, HttpStatus.OK);
    }
	
	@PostMapping(path = "/request/wtc", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> requestWTC(@RequestBody String inputJson){
		log.info("Invoking WTC web service...");
		log.info("Request message from client call : "+inputJson);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.set("REMOTE_USER", "UamRMUsLntmjDZFq4jdg");      

		HttpEntity<String> request = new HttpEntity<String>(inputJson, headers);
		String message = restTemplate.postForObject("http://172.29.124.1:7011/wtcws/api/wtc/quote", request, String.class);
		log.info("Response message from web service call : "+message);
		return new ResponseEntity<Object>(message, HttpStatus.OK);
	}
}

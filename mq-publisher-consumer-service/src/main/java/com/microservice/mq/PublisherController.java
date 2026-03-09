package com.microservice.mq;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publish")
public class PublisherController {
	private final MessagePublisher publisher;

	public PublisherController(MessagePublisher publisher) {
		this.publisher = publisher;
	}

	@PostMapping
	public ResponseEntity<String> publishMessage(@RequestBody Map<String, Object> payload) {
		try {
			String message = payload.toString();
			publisher.sendMessage(message);
			return ResponseEntity.ok("Message published successfully!");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Message publishing failed");
		}
	}
}

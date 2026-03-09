package com.microservice.mq;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consume")
public class ConsumerController {
	private final MessageConsumer consumer;

	public ConsumerController(MessageConsumer consumer) {
		this.consumer = consumer;
	}

	@GetMapping
	public ResponseEntity<String> consumeMessage() {
		String message = consumer.consume();
		if (message != null) {
			return ResponseEntity.ok("Consumed message: " + message);
		} else {
			return ResponseEntity.ok("No messages available in queue");
		}
	}
}

package com.microservice.mq;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ConsumerController is REST controller that exposes
 * an endpoint for consuming messages from the in-memory queue.
 *
 * Responsibilities:
 * - Handle GET request at /consume
 * - Delegate message retrieval to the MessageConsumer
 * - Return appropriate HTTP responses based on queue state
 */
@RestController
@RequestMapping("/consume")
public class ConsumerController {
	// service which handles message consumption--dependency
	private final MessageConsumer consumer;
	// ensuring the consumer service is available
	public ConsumerController(MessageConsumer consumer) {
		this.consumer = consumer;
	}

	 /**
     * REST endpoint to consume a message.
     * - Calls the MessageConsumer to poll the next message
     * - Returns the consumed message if available
     * - Returns a "No messages available" response if queue is empty
     */
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

package com.microservice.mq;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * PublisherController is a REST controller that exposes
 * an endpoint for publishing messages to the in-memory queue.
 *
 * Responsibilities:
 * - Accept JSON payloads via POST requests
 * - Extract message content from the payload
 * - Delegate publishing to the MessagePublisher service
 * - Return appropriate HTTP responses (success or error)
 */
@RestController
@RequestMapping("/publish")
public class PublisherController {
	// service which is handling queue operations
	private final MessagePublisher publisher;

	// ensuring the publisher service is available using constructor
	public PublisherController(MessagePublisher publisher) {
		this.publisher = publisher;
	}

	/**
     * REST endpoint to publish a message, accepts a JSON payload, converts it to a string,
     * and sends it to the MessagePublisher also handled unexpected errors
     */
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

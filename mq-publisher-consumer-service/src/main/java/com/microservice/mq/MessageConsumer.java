package com.microservice.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * MessageConsumer is component that retrieves (consumes) messages from the in-memory queue
 * managed by the MessagePublisher.
 *
 * Responsibilities:
 * - Poll messages from the queue
 * - Log consumed messages for traceability
 * - Return the consumed message to the caller
 */
@Component
public class MessageConsumer {
	// tracking consumption operations---logger
	private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);
	// adding dependency of publisher service that manages the queue.
	private final MessagePublisher publisher;

	public MessageConsumer(MessagePublisher publisher) {
		this.publisher = publisher;
	}

	/**
     * consuming a message from the queue.
     * - Retrieves the next message using publisher.pollMessage()
     * - Logs the consumed message if available
     * - Returns consumed message, or null if queue is empty
     */
	public String consume() {
		String message = publisher.pollMessage();
		if (message != null) {
			log.info("consume: {}", message);
			return message;
		}
		return null;
	}
}

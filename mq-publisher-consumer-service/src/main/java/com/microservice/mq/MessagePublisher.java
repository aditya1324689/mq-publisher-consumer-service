package com.microservice.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * MessagePublisher performing following responsibilities:
 * - Accept messages and add them to the queue
 * - Provide methods to poll (consume) messages
 * - Check if the queue has pending messages
 */
@Service
public class MessagePublisher {
	// logger for tracking publish operations
	private static final Logger log = LoggerFactory.getLogger(MessagePublisher.class);
	// in-memory queue to hold messages
	private final Queue<String> queue = new ConcurrentLinkedQueue<>();

	// publishes a message by adding it to the queue
	public void sendMessage(String message) {
		queue.add(message);
		log.info("publish: {}", message);
	}

	// retrieves and removes the next message from the queue and if queue is empty then returns NULL
	public String pollMessage() {
		return queue.poll();
	}

	// checking whether there are any messages waiting in the queue
	public boolean hasMessages() {
		return !queue.isEmpty();
	}
}

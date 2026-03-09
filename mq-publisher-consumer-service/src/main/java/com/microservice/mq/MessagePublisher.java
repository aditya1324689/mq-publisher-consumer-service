package com.microservice.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class MessagePublisher {
	private static final Logger log = LoggerFactory.getLogger(MessagePublisher.class);
	private final Queue<String> queue = new ConcurrentLinkedQueue<>();

	public void sendMessage(String message) {
		queue.add(message);
		log.info("publish: {}", message);
	}

	public String pollMessage() {
		return queue.poll();
	}

	public boolean hasMessages() {
		return !queue.isEmpty();
	}
}

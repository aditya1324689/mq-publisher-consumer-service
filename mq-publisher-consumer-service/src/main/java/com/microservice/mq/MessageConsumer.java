package com.microservice.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
	private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);
	private final MessagePublisher publisher;

	public MessageConsumer(MessagePublisher publisher) {
		this.publisher = publisher;
	}

	public String consume() {
		String message = publisher.pollMessage();
		if (message != null) {
			log.info("consume: {}", message);
			return message;
		}
		return null;
	}
}

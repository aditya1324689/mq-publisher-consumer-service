package com.microservice.mq;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


/**
 * These tests validates /consume endpoint:
 * - When a message is available, the controller should return it.
 * - When no message is available, the controller should respond accordingly.
 */
class ConsumerControllerTest {

	@Mock
	private MessageConsumer consumer;

	@InjectMocks
	private ConsumerController controller;

	public ConsumerControllerTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	// ensuring, when a message is available, ConsumerController returns the correct response and calls consume() exactly once.
	void testConsumeMessageAvailable() {
		when(consumer.consume()).thenReturn("{message=Hello MQ}");
		ResponseEntity<String> response = controller.consumeMessage();
		assertEquals("Consumed message: {message=Hello MQ}", response.getBody());
		verify(consumer, times(1)).consume();
	}

	@Test
	// ensuring, when no message is available, ConsumerController returns the correct "No messages available in queue" response
	// and calls consume() exactly once.
	void testConsumeMessageEmpty() {
		when(consumer.consume()).thenReturn(null);
		ResponseEntity<String> response = controller.consumeMessage();
		assertEquals("No messages available in queue", response.getBody());
		verify(consumer, times(1)).consume();
	}
}

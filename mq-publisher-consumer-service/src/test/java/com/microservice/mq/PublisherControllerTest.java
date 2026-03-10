package com.microservice.mq;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * These tests validates /publish endpoint:
 * - When publishing succeeds, the controller should return a success response.
 * - When publishing fails (exception thrown), the controller should return a failure response.
 */
class PublisherControllerTest {

	@Mock
	private MessagePublisher publisher;

	@InjectMocks
	private PublisherController controller;

	public PublisherControllerTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	// Ensures, when publishing succeeds, the controller returns "Message published successfully!"
    // and calls publisher.sendMessage() exactly once.
	void testPublishMessageSuccess() {
		Map<String, Object> payload = Map.of("message", "Hello MQ");
		doNothing().when(publisher).sendMessage(payload.toString());
		ResponseEntity<String> response = controller.publishMessage(payload);
		assertEquals("Message published successfully!", response.getBody());
		verify(publisher, times(1)).sendMessage(payload.toString());
	}

	@Test
	// Ensures, when publishing fails (exception thrown), the controller returns "Message publishing failed"
    // and still calls publisher.sendMessage() exactly once.
	void testPublishMessageFailure() {
		Map<String, Object> payload = Map.of("message", "Fail MQ");
		doThrow(new RuntimeException("Simulated failure")).when(publisher).sendMessage(payload.toString());
		ResponseEntity<String> response = controller.publishMessage(payload);
		assertEquals("Message publishing failed", response.getBody());
		verify(publisher, times(1)).sendMessage(payload.toString());
	}
}

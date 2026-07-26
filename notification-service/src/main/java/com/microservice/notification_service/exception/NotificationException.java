package com.microservice.notification_service.exception;

/**
 * Generic domain exception for notification errors.
 */
public class NotificationException extends RuntimeException {

	public NotificationException(String message) {
		super(message);
	}

}

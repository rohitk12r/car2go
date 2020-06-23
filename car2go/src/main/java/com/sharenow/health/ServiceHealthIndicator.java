package com.sharenow.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ServiceHealthIndicator  implements HealthIndicator {
	private final String message_key = "API Car and Polygon Service";

	@Override
	public Health health() {
		return Health.up().withDetail(message_key, "Available").build();
	}
}

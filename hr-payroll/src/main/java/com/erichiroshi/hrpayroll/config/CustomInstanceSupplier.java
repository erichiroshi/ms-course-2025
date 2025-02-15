package com.erichiroshi.hrpayroll.config;

import java.util.List;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;

import reactor.core.publisher.Flux;

public class CustomInstanceSupplier implements ServiceInstanceListSupplier {

	private final String serviceId;

	public CustomInstanceSupplier(String serviceId) {
		this.serviceId = serviceId;
	}

	@Override
	public String getServiceId() {
		return serviceId;
	}

	@Override
	public Flux<List<ServiceInstance>> get() {
		List<ServiceInstance> instances = List.of(
				new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 8001, false),
				new DefaultServiceInstance(serviceId + "2", serviceId, "localhost", 8002, false)
				);
		return Flux.just(instances);
	}
}

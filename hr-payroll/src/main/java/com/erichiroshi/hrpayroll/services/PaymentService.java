package com.erichiroshi.hrpayroll.services;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.erichiroshi.hrpayroll.entities.Payment;
import com.erichiroshi.hrpayroll.entities.Worker;
import com.erichiroshi.hrpayroll.feignclient.WorkerFeignClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@Service
public class PaymentService {

	private WorkerFeignClient workerFeignClient;

	public PaymentService(WorkerFeignClient workerFeignClient) {
		this.workerFeignClient = workerFeignClient;
	}

	@CircuitBreaker(name = "hr-worker", fallbackMethod = "paymentAlternative")
	@TimeLimiter(name = "hr-worker")
	public CompletableFuture<Payment> getPayment(long workerId, int days) {
		return CompletableFuture.supplyAsync(() -> {
			Worker worker = workerFeignClient.findById(workerId).getBody();
			return new Payment(worker.getName(), worker.getDailyIncome(), days);
		});
	}

	public CompletableFuture<Payment> paymentAlternative(long workerId, int days, Throwable throwable) {
		return CompletableFuture.supplyAsync(() -> {
			System.out.println("Timeout ou falha detectada: " + throwable.getMessage());
			Payment payment = new Payment("Default Worker", 100.0, days);
			return payment;
		});
	}

}

package com.erichiroshi.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.erichiroshi.hrpayroll.entities.Payment;
import com.erichiroshi.hrpayroll.entities.Worker;

@Service
public class PaymentService {

	private RestTemplate restTemplate;

	@Value("${hr-worker.host}")
	private String workerHost;

	public PaymentService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Payment getPayment(long workerId, int days) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", "" + workerId);

		Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables);
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}
}

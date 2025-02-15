package com.erichiroshi.hrpayroll.services;

import org.springframework.stereotype.Service;

import com.erichiroshi.hrpayroll.entities.Payment;
import com.erichiroshi.hrpayroll.entities.Worker;
import com.erichiroshi.hrpayroll.feignclient.WorkerFeignClient;

@Service
public class PaymentService {

	private WorkerFeignClient workerFeignClient;

	public PaymentService(WorkerFeignClient workerFeignClient) {
		this.workerFeignClient = workerFeignClient;
	}

	public Payment getPayment(long workerId, int days) {
		Worker worker = workerFeignClient.findById(workerId).getBody();
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}
}

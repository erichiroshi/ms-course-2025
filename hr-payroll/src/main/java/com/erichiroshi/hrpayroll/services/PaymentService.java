package com.erichiroshi.hrpayroll.services;

import org.springframework.stereotype.Service;

import com.erichiroshi.hrpayroll.entities.Payment;

@Service
public class PaymentService {

//	@Autowired
//	private WorkerFeignClient workerFeignClient;

	public Payment getPayment(long workerId, int days) {
//		Worker worker = workerFeignClient.findById(workerId).getBody();
//		return new Payment(worker.getName(), worker.getDailyIncome(), days);
		return new Payment("Bob", 200.0, days);
	}
}

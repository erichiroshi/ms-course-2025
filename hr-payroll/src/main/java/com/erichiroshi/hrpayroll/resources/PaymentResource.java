package com.erichiroshi.hrpayroll.resources;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erichiroshi.hrpayroll.entities.Payment;
import com.erichiroshi.hrpayroll.services.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentResource {

	private PaymentService paymentService;

	public PaymentResource(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@GetMapping("/{workeyId}/days/{days}")
	public CompletableFuture<ResponseEntity<Payment>> getPayment(@PathVariable Long workeyId, @PathVariable Integer days) {
		CompletableFuture<Payment> payment = paymentService.getPayment(workeyId, days);
		return payment.thenApply(ResponseEntity::ok);
	}

}

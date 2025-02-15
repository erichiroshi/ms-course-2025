package com.erichiroshi.hrpayroll.resources;

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

//	@HystrixCommand(fallbackMethod = "getPaymentAlternative")
	@GetMapping("/{workeyId}/days/{days}")
	public ResponseEntity<Payment> getPayment(@PathVariable Long workeyId, @PathVariable Integer days) {
		Payment payment = paymentService.getPayment(workeyId, days);
		return ResponseEntity.ok(payment);
	}

//	public ResponseEntity<Payment> getPaymentAlternative(Long workeyId, Integer days) {
//		Payment payment = new Payment("Brann", 400.0, days);
//		return ResponseEntity.ok(payment);
//	}

}

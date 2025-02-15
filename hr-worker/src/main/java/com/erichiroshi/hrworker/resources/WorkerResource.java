package com.erichiroshi.hrworker.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erichiroshi.hrworker.entities.Worker;
import com.erichiroshi.hrworker.repositories.WorkerRepository;

@RestController
@RequestMapping("/workers")
public class WorkerResource {

	private WorkerRepository repository;

	public WorkerResource(WorkerRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public ResponseEntity<List<Worker>> listAll() {
		List<Worker> workers = repository.findAll();
		return ResponseEntity.ok(workers);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Worker> findById(@PathVariable Long id) {
		Worker worker = repository.findById(id).orElseThrow();
		return ResponseEntity.ok(worker);
	}

}
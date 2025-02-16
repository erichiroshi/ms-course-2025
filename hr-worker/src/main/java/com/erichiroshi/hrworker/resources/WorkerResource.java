package com.erichiroshi.hrworker.resources;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erichiroshi.hrworker.entities.Worker;
import com.erichiroshi.hrworker.repositories.WorkerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/workers")
public class WorkerResource {

	private Environment environment;

	private WorkerRepository repository;

	public WorkerResource(WorkerRepository repository, Environment environment) {
		this.repository = repository;
		this.environment = environment;
	}

	@GetMapping
	public ResponseEntity<List<Worker>> listAll() {
		List<Worker> workers = repository.findAll();
		return ResponseEntity.ok(workers);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Worker> findById(@PathVariable Long id) {

		Worker worker = repository.findById(id).orElseThrow();
		log.info("PORT = " + environment.getProperty("local.server.port"));
		return ResponseEntity.ok(worker);
	}

}
package org.agm.sbtest.api.tasks;

import java.util.Optional;

import org.agm.sbtest.entities.Task;
import org.agm.sbtest.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskRepository repository;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Task>> findAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> findOne(@PathVariable Long taskId) {

		Optional<Task> taskOp = repository.findById(taskId);
		ResponseEntity<Task> response = taskOp.map(task -> ResponseEntity.ok(task))
				.orElseGet(() -> ResponseEntity.notFound().build());

		return response;
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> create(@RequestBody TaskRequest request) {
		log.info("Create task received: {}", request);
		Task task = repository.save(request.createTask());

		log.info("Task created: {}", task);
		return ResponseEntity.ok(task);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> update(@PathVariable Long taskId, @RequestBody TaskRequest request) {
		log.info("Update task received: {}, {}", taskId, request);

		Optional<Task> taskOp = repository.findById(taskId);
		ResponseEntity<Task> response = taskOp.map(task -> {
			task = request.updateTask(task);
			task = repository.save(task);
			log.info("Task updated: {}", task);
			return ResponseEntity.ok(task);
		}).orElseGet(() -> ResponseEntity.notFound().build());

		return response;
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{taskId}")
	public ResponseEntity<?> deleteOne(@PathVariable Long taskId) {
		log.info("Delete task received: {}", taskId);

		Optional<Task> taskOp = repository.findById(taskId);
		ResponseEntity<?> response = taskOp.map(task -> {
			repository.delete(task);
			log.info("Task deleted: {}", task);
			return ResponseEntity.noContent().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());

		return response;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/{taskId}/finish", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> finish(@PathVariable Long taskId) {
		log.info("Finish task received: {}", taskId);

		Optional<Task> taskOp = repository.findById(taskId);
		ResponseEntity<Task> response = taskOp.map(task -> {
			task.setFinished(true);
			task = repository.save(task);
			log.info("Task finished: {}", task);
			return ResponseEntity.ok(task);
		}).orElseGet(() -> ResponseEntity.notFound().build());

		return response;
	}
}

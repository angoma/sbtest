package org.agm.sbtest.api.tasks;

import org.agm.sbtest.entities.Task;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskRequest {

	private String name;
	private String content;

	public Task createTask() {
		Task result = new Task();
		result.setName(name);
		result.setContent(content);
		result.setFinished(false);

		return result;
	}

	public Task updateTask(Task task) {
		task.setName(name);
		task.setContent(content);

		return task;
	}
}

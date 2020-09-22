package org.agm.sbtest.api.tasks;

import java.time.LocalDateTime;

import org.agm.sbtest.entities.Task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class TaskRequest {

    private String name;
    private String content;

    public Task createTask() {
        Task result = new Task();
        result.setName(name);
        result.setContent(content);
        result.setFinished(false);
        result.setCreationDate(LocalDateTime.now());

        return result;
    }

    public Task updateTask(Task task) {
        task.setName(name);
        task.setContent(content);

        return task;
    }
}

package org.agm.sbtest.repositories;

import org.agm.sbtest.entities.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
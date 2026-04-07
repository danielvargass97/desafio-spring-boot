package com.nuevospa.tasks.application.port.in;

import com.nuevospa.tasks.domain.model.Task;
import java.util.List;

public interface GetAllTasksUseCase {
    List<Task> getAll();
}
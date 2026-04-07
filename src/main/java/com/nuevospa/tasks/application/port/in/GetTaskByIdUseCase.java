package com.nuevospa.tasks.application.port.in;

import com.nuevospa.tasks.domain.model.Task;
import java.util.UUID;

public interface GetTaskByIdUseCase {
    Task getById(UUID id);
}
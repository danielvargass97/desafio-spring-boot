package com.nuevospa.tasks.application.port.out;

import com.nuevospa.tasks.domain.model.TaskStatus;
import java.util.Optional;
import java.util.UUID;

public interface TaskStatusRepositoryPort {
    Optional<TaskStatus> findById(UUID id);
}
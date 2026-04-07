package com.nuevospa.tasks.application.port.out;

import com.nuevospa.tasks.domain.model.Task;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepositoryPort {
    List<Task> findAll();
    Optional<Task> findById(UUID id);
    Task save(Task task);
    void deleteById(UUID id);
    boolean existsById(UUID id);
}
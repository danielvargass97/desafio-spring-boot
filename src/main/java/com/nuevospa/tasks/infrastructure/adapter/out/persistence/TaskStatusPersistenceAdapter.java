package com.nuevospa.tasks.infrastructure.adapter.out.persistence;

import com.nuevospa.tasks.application.port.out.TaskStatusRepositoryPort;
import com.nuevospa.tasks.domain.model.TaskStatus;
import com.nuevospa.tasks.infrastructure.entity.TaskStatusEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TaskStatusPersistenceAdapter implements TaskStatusRepositoryPort {

    private final TaskStatusJpaRepository taskStatusJpaRepository;

    @Override
    public Optional<TaskStatus> findById(UUID id) {
        return taskStatusJpaRepository.findById(id)
                .map(this::toModel);
    }

    private TaskStatus toModel(TaskStatusEntity entity) {
        return new TaskStatus(entity.getId(), entity.getName());
    }
}
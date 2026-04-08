package com.nuevospa.tasks.infrastructure.adapter.out.persistence;

import com.nuevospa.tasks.application.port.out.TaskRepositoryPort;
import com.nuevospa.tasks.domain.model.Task;
import com.nuevospa.tasks.infrastructure.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskPersistenceAdapter implements TaskRepositoryPort {

    private final TaskJpaRepository taskJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final TaskStatusJpaRepository taskStatusJpaRepository;

    @Override
    public List<Task> findAll() {
        return taskJpaRepository.findAll().stream()
                .map(this::toModel)
                .toList();
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return taskJpaRepository.findById(id)
                .map(this::toModel);
    }

    @Override
    public Task save(Task task) {
        TaskEntity entity = toEntity(task);
        return toModel(taskJpaRepository.save(entity));
    }

    @Override
    public void deleteById(UUID id) {
        taskJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return taskJpaRepository.existsById(id);
    }

    private Task toModel(TaskEntity entity) {
        return new Task(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus().getName(),
                entity.getAssignedUser().getUsername(),
                entity.getCreatedAt()
        );
    }

    private TaskEntity toEntity(Task task) {
        TaskEntity entity = new TaskEntity();
        entity.setId(task.getId());
        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());
        entity.setCreatedAt(task.getCreatedAt());
        return entity;
    }
}
package com.nuevospa.tasks.application.usecase;

import com.nuevospa.tasks.application.port.in.CreateTaskUseCase;
import com.nuevospa.tasks.application.port.out.TaskRepositoryPort;
import com.nuevospa.tasks.application.port.out.TaskStatusRepositoryPort;
import com.nuevospa.tasks.application.port.out.UserRepositoryPort;
import com.nuevospa.tasks.domain.exception.TaskNotFoundException;
import com.nuevospa.tasks.domain.model.Task;
import com.nuevospa.tasks.domain.model.TaskStatus;
import com.nuevospa.tasks.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateTaskUseCaseImpl implements CreateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;
    private final TaskStatusRepositoryPort taskStatusRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Task create(String title, String description, UUID statusId, UUID assignedUserId) {
        TaskStatus status = taskStatusRepositoryPort.findById(statusId)
                .orElseThrow(() -> new TaskNotFoundException("Status not found: " + statusId));

        User user = userRepositoryPort.findByUUID(assignedUserId)
                .orElseThrow(() -> new TaskNotFoundException("User not found: " + assignedUserId));

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status.getName());
        task.setAssignedUser(user.getUsername());
        task.setCreatedAt(LocalDateTime.now());

        return taskRepositoryPort.save(task);
    }
}
package com.nuevospa.tasks.application.usecase;

import com.nuevospa.tasks.application.port.in.UpdateTaskUseCase;
import com.nuevospa.tasks.application.port.out.TaskRepositoryPort;
import com.nuevospa.tasks.application.port.out.TaskStatusRepositoryPort;
import com.nuevospa.tasks.application.port.out.UserRepositoryPort;
import com.nuevospa.tasks.domain.exception.TaskNotFoundException;
import com.nuevospa.tasks.domain.model.Task;
import com.nuevospa.tasks.domain.model.TaskStatus;
import com.nuevospa.tasks.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateTaskUseCaseImpl implements UpdateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;
    private final TaskStatusRepositoryPort taskStatusRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Task update(UUID id, String title, String description, UUID statusId, UUID assignedUserId) {
        Task task = taskRepositoryPort.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id.toString()));

        TaskStatus status = taskStatusRepositoryPort.findById(statusId)
                .orElseThrow(() -> new TaskNotFoundException("Status not found: " + statusId));

        User user = userRepositoryPort.findByUsername(assignedUserId.toString())
                .orElseThrow(() -> new TaskNotFoundException("User not found: " + assignedUserId));

        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status.getName());
        task.setAssignedUser(user.getUsername());

        return taskRepositoryPort.save(task);
    }
}
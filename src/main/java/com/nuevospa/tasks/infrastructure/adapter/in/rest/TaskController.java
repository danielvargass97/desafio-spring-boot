package com.nuevospa.tasks.infrastructure.adapter.in.rest;

import com.nuevospa.tasks.application.port.in.*;
import com.nuevospa.tasks.domain.model.Task;
import com.nuevospa.tasks.infrastructure.adapter.in.rest.api.TasksApi;
import com.nuevospa.tasks.infrastructure.adapter.in.rest.model.TaskRequest;
import com.nuevospa.tasks.infrastructure.adapter.in.rest.model.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TaskController implements TasksApi {

    private final GetAllTasksUseCase getAllTasksUseCase;
    private final GetTaskByIdUseCase getTaskByIdUseCase;
    private final CreateTaskUseCase createTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;

    @Override
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> tasks = getAllTasksUseCase.getAll()
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @Override
    public ResponseEntity<TaskResponse> getTaskById(UUID id) {
        return ResponseEntity.ok(toResponse(getTaskByIdUseCase.getById(id)));
    }

    @Override
    public ResponseEntity<TaskResponse> createTask(TaskRequest taskRequest) {
        Task task = createTaskUseCase.create(
                taskRequest.getTitle(),
                taskRequest.getDescription(),
                taskRequest.getStatusId(),
                taskRequest.getAssignedUserId()
        );
        return ResponseEntity.status(201).body(toResponse(task));
    }

    @Override
    public ResponseEntity<TaskResponse> updateTask(UUID id, TaskRequest taskRequest) {
        Task task = updateTaskUseCase.update(
                id,
                taskRequest.getTitle(),
                taskRequest.getDescription(),
                taskRequest.getStatusId(),
                taskRequest.getAssignedUserId()
        );
        return ResponseEntity.ok(toResponse(task));
    }

    @Override
    public ResponseEntity<Void> deleteTask(UUID id) {
        deleteTaskUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    private TaskResponse toResponse(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());
        response.setAssignedUser(task.getAssignedUser());
        if (task.getCreatedAt() != null) {
            response.setCreatedAt(task.getCreatedAt().atOffset(java.time.ZoneOffset.UTC));
        }
        return response;
    }
}
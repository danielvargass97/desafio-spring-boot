package com.nuevospa.tasks.application.usecase;

import com.nuevospa.tasks.application.port.in.GetTaskByIdUseCase;
import com.nuevospa.tasks.application.port.out.TaskRepositoryPort;
import com.nuevospa.tasks.domain.exception.TaskNotFoundException;
import com.nuevospa.tasks.domain.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetTaskByIdUseCaseImpl implements GetTaskByIdUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    @Override
    public Task getById(UUID id) {
        return taskRepositoryPort.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id.toString()));
    }
}
package com.nuevospa.tasks.application.usecase;

import com.nuevospa.tasks.application.port.in.DeleteTaskUseCase;
import com.nuevospa.tasks.application.port.out.TaskRepositoryPort;
import com.nuevospa.tasks.domain.exception.TaskNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteTaskUseCaseImpl implements DeleteTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    @Override
    public void delete(UUID id) {
        if (!taskRepositoryPort.existsById(id)) {
            throw new TaskNotFoundException(id.toString());
        }
        taskRepositoryPort.deleteById(id);
    }
}
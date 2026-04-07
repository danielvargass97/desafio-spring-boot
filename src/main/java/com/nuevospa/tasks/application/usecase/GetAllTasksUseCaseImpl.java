package com.nuevospa.tasks.application.usecase;

import com.nuevospa.tasks.application.port.in.GetAllTasksUseCase;
import com.nuevospa.tasks.application.port.out.TaskRepositoryPort;
import com.nuevospa.tasks.domain.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllTasksUseCaseImpl implements GetAllTasksUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    @Override
    public List<Task> getAll() {
        return taskRepositoryPort.findAll();
    }
}
package com.nuevospa.tasks.application.usecase;

import com.nuevospa.tasks.application.port.out.TaskRepositoryPort;
import com.nuevospa.tasks.domain.model.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllTasksUseCaseImplTest {

    @Mock
    private TaskRepositoryPort taskRepositoryPort;

    @InjectMocks
    private GetAllTasksUseCaseImpl getAllTasksUseCase;

    @Test
    void shouldReturnAllTasks() {
        Task task1 = new Task(UUID.randomUUID(), "Task 1", "Desc 1", "PENDING", "admin", LocalDateTime.now());
        Task task2 = new Task(UUID.randomUUID(), "Task 2", "Desc 2", "IN_PROGRESS", "john.doe", LocalDateTime.now());

        when(taskRepositoryPort.findAll()).thenReturn(List.of(task1, task2));

        List<Task> result = getAllTasksUseCase.getAll();

        assertEquals(2, result.size());
        verify(taskRepositoryPort).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoTasks() {
        when(taskRepositoryPort.findAll()).thenReturn(List.of());

        List<Task> result = getAllTasksUseCase.getAll();

        assertTrue(result.isEmpty());
    }
}
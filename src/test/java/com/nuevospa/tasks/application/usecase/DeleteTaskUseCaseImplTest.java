package com.nuevospa.tasks.application.usecase;

import com.nuevospa.tasks.application.port.out.TaskRepositoryPort;
import com.nuevospa.tasks.domain.exception.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteTaskUseCaseImplTest {

    @Mock
    private TaskRepositoryPort taskRepositoryPort;

    @InjectMocks
    private DeleteTaskUseCaseImpl deleteTaskUseCase;

    @Test
    void shouldDeleteTaskWhenExists() {
        UUID id = UUID.randomUUID();
        when(taskRepositoryPort.existsById(id)).thenReturn(true);

        deleteTaskUseCase.delete(id);

        verify(taskRepositoryPort).deleteById(id);
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {
        UUID id = UUID.randomUUID();
        when(taskRepositoryPort.existsById(id)).thenReturn(false);

        assertThrows(TaskNotFoundException.class, () ->
                deleteTaskUseCase.delete(id));

        verify(taskRepositoryPort, never()).deleteById(id);
    }
}
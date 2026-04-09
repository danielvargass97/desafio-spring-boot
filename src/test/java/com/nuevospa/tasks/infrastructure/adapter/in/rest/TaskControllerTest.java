package com.nuevospa.tasks.infrastructure.adapter.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuevospa.tasks.application.port.in.*;
import com.nuevospa.tasks.domain.exception.TaskNotFoundException;
import com.nuevospa.tasks.domain.model.Task;
import com.nuevospa.tasks.infrastructure.adapter.in.rest.model.TaskRequest;
import com.nuevospa.tasks.infrastructure.config.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GetAllTasksUseCase getAllTasksUseCase;

    @MockBean
    private GetTaskByIdUseCase getTaskByIdUseCase;

    @MockBean
    private CreateTaskUseCase createTaskUseCase;

    @MockBean
    private UpdateTaskUseCase updateTaskUseCase;

    @MockBean
    private DeleteTaskUseCase deleteTaskUseCase;

    @MockBean
    private JwtService jwtService;

    @Test
    @WithMockUser
    void shouldReturnAllTasks() throws Exception {
        Task task = new Task(UUID.randomUUID(), "Task 1", "Desc 1", "PENDING", "admin", LocalDateTime.now());
        when(getAllTasksUseCase.getAll()).thenReturn(List.of(task));

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Task 1"));
    }

    @Test
    @WithMockUser
    void shouldReturnTaskById() throws Exception {
        UUID id = UUID.randomUUID();
        Task task = new Task(id, "Task 1", "Desc 1", "PENDING", "admin", LocalDateTime.now());
        when(getTaskByIdUseCase.getById(id)).thenReturn(task);

        mockMvc.perform(get("/api/tasks/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Task 1"));
    }

    @Test
    @WithMockUser
    void shouldReturn404WhenTaskNotFound() throws Exception {
        UUID id = UUID.randomUUID();
        when(getTaskByIdUseCase.getById(id)).thenThrow(new TaskNotFoundException(id.toString()));

        mockMvc.perform(get("/api/tasks/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void shouldCreateTask() throws Exception {
        UUID statusId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Task task = new Task(UUID.randomUUID(), "New Task", "Desc", "PENDING", "admin", LocalDateTime.now());

        when(createTaskUseCase.create(any(), any(), any(), any())).thenReturn(task);

        TaskRequest request = new TaskRequest();
        request.setTitle("New Task");
        request.setDescription("Desc");
        request.setStatusId(statusId);
        request.setAssignedUserId(userId);

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Task"));
    }

    @Test
    @WithMockUser
    void shouldDeleteTask() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/api/tasks/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturn401WhenNotAuthenticated() throws Exception {
        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isUnauthorized());
    }
}
package com.nuevospa.tasks.application.port.in;

import com.nuevospa.tasks.domain.model.Task;
import java.util.UUID;

public interface UpdateTaskUseCase {
    Task update(UUID id, String title, String description, UUID statusId, UUID assignedUserId);
}
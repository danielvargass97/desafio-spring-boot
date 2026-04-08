package com.nuevospa.tasks.infrastructure.adapter.out.persistence;

import com.nuevospa.tasks.infrastructure.entity.TaskStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskStatusJpaRepository extends JpaRepository<TaskStatusEntity, UUID> {
}
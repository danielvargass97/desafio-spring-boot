package com.nuevospa.tasks.infrastructure.adapter.out.persistence;

import com.nuevospa.tasks.infrastructure.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskJpaRepository extends JpaRepository<TaskEntity, UUID> {
}
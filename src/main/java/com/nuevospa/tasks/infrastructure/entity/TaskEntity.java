package com.nuevospa.tasks.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tareas")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private TaskStatusEntity status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity assignedUser;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
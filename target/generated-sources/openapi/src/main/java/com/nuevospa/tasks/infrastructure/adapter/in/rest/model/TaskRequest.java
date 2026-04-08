package com.nuevospa.tasks.infrastructure.adapter.in.rest.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * TaskRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-04-08T16:22:02.855085-05:00[America/Bogota]", comments = "Generator version: 7.4.0")
public class TaskRequest {

  private String title;

  private String description;

  private UUID statusId;

  private UUID assignedUserId;

  public TaskRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TaskRequest(String title, UUID statusId, UUID assignedUserId) {
    this.title = title;
    this.statusId = statusId;
    this.assignedUserId = assignedUserId;
  }

  public TaskRequest title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  @NotNull @Size(min = 3, max = 100) 
  @Schema(name = "title", example = "Implement login", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public TaskRequest description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  @Size(max = 500) 
  @Schema(name = "description", example = "Implement JWT authentication", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TaskRequest statusId(UUID statusId) {
    this.statusId = statusId;
    return this;
  }

  /**
   * Get statusId
   * @return statusId
  */
  @NotNull @Valid 
  @Schema(name = "statusId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("statusId")
  public UUID getStatusId() {
    return statusId;
  }

  public void setStatusId(UUID statusId) {
    this.statusId = statusId;
  }

  public TaskRequest assignedUserId(UUID assignedUserId) {
    this.assignedUserId = assignedUserId;
    return this;
  }

  /**
   * Get assignedUserId
   * @return assignedUserId
  */
  @NotNull @Valid 
  @Schema(name = "assignedUserId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("assignedUserId")
  public UUID getAssignedUserId() {
    return assignedUserId;
  }

  public void setAssignedUserId(UUID assignedUserId) {
    this.assignedUserId = assignedUserId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaskRequest taskRequest = (TaskRequest) o;
    return Objects.equals(this.title, taskRequest.title) &&
        Objects.equals(this.description, taskRequest.description) &&
        Objects.equals(this.statusId, taskRequest.statusId) &&
        Objects.equals(this.assignedUserId, taskRequest.assignedUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, description, statusId, assignedUserId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaskRequest {\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    statusId: ").append(toIndentedString(statusId)).append("\n");
    sb.append("    assignedUserId: ").append(toIndentedString(assignedUserId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


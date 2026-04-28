package com.intv.test.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class Todo {

        @NotBlank
        private Long id;
        @NotBlank
        private String userId;
        @NotBlank
        private String title;
        private String description;
        @NotBlank
        private String status;
        @NotBlank
        private Instant createdAt;
        @NotBlank
        private Instant updatedAt;

        protected void onCreate(){
            createdAt = Instant.now();
        }

        protected void onUpdate(){
            updatedAt = Instant.now();
        }

        public Todo(){}

    public Todo(Long id, String userId, String title, String description, String status, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}

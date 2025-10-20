package main.grp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class Task {
    private Integer id;

    @NotBlank(message = "not filled in...")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    private boolean completed;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    public Task() {
    }

    public Task(Integer id, String title, boolean completed, LocalDate createdAt) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
package com.example.todomvvm.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "task")
public class TaskEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String description;
    private int taskUserId;
    private String title;
    private int priority;
    @ColumnInfo(name="updated_at")
    private Date updatedAt;



    public int getTaskUserId() {
        return taskUserId;
    }

    public void setTaskUserId(int taskUserId) {
        this.taskUserId = taskUserId;
    }

    @Ignore
    public TaskEntry(String description, String title, int priority, Date updatedAt, int taskUserId) {

        this.description = description;
        this.title = title;
        this.priority = priority;
        this.updatedAt = updatedAt;
        this.taskUserId=taskUserId;
    }

    public TaskEntry(int id, String description, String title, int priority, Date updatedAt, int taskUserId) {

        this.id = id;
        this.description = description;
        this.title = title;
        this.priority = priority;
        this.updatedAt = updatedAt;
        this.taskUserId=taskUserId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}

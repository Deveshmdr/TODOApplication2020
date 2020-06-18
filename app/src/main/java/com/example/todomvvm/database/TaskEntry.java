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
    private int priority;
    @ColumnInfo(name="updated_at")
    private Date updatedAt;
    private String time;
    private String title;
    @Ignore
    //this constructor is called when id is already created for e.g while inserting new data into table.
    public TaskEntry(String description, int priority, Date updatedAt,String time,String title) {
        this.description = description;
        this.priority = priority;
        this.updatedAt = updatedAt;
        this.time = time;
        this.title = title;
    }
    //<-- this contructor is called when id is already created for e.g while updating,etc.-->
    public TaskEntry(int id, String description, int priority, Date updatedAt,String time,String title) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.updatedAt = updatedAt;
        this.time = time;
        this.title = title;
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
    public String getTime() {  return time;}

    public void setTime(String time) {this.time = time;
    }
    public String getTitle() {  return title;}

    public void setTitle(String title) {
        this.title = title;
    }
}

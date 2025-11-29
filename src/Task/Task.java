package task;

import java.time.LocalDateTime;

public class Task {
    
    //Fields
    private int id;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(int id, String description) {

        this.id = id;
        this.description = description;
        this.status = TaskStatus.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

    }

    public Task() {

        

    }

    public int getId() {

        return this.id;

    }

    public void setInProgress() {

        this.status = TaskStatus.IN_PROGRESS;
        this.updatedAt = LocalDateTime.now();

    }

    public void setDone() {

        this.status = TaskStatus.DONE;
        this.updatedAt = LocalDateTime.now();

    }

}
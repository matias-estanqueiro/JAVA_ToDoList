public class Task {
    private String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return  this.completed;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setStatus(boolean newStatus) {
        this.completed = newStatus;
    }
}


package todo_list_app;

import java.time.LocalDate;
//Abstract variable type class
public class Task {

    private static int taskId = 0;
    private int currentTaskId;
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean status;

    public boolean isComplete() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
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
    public int getCurrentTaskId(){
        return currentTaskId;
    }

    public Task(String title, String description, LocalDate dueDate){
        currentTaskId = taskId++;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = false;
    }

    public Task(String title, LocalDate dueDate){
        this(title,"No Description",dueDate);
    }

    public void markAsComplete(){
        this.status = true;
    }

    public void editTitle(String title){
        this.title = title;
    }

    public void editDescription(String description){
        this.description = description;
    }

    public void editDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }
}

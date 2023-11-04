package todo_list_app;

import java.time.LocalDate;
//Abstract variable type class
public class Task {

    public static int taskId = 0;
    private int currentTaskId;
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean status;

    private int priority = 0;
    public boolean isComplete() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getStatus(){
        String status = "Ongoing";
        if(isComplete()){status = "Completed";}

        return status;
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

    public int getPriority(){ return priority; }

    public void setPriority(int priority){ this.priority = priority; }

    public Task(String title, String description, LocalDate dueDate, int priority){
        currentTaskId = taskId++;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = false;
        this.priority = priority;
    }

    public Task(String title, LocalDate dueDate, int priority){
        this(title,"No Description",dueDate,priority);
    }

    public void markAsComplete(){
        this.status = true;
    }

    @Override
    public String toString(){
        String priorityString = null;
        if(this.priority == 1){
            priorityString = "High";
        }else if(this.priority == 2){
            priorityString = "Medium";
        }else if(this.priority == 3){
            priorityString = "Low";
        }
        String displayData = "\nTitle: "+getTitle();
        displayData += "\nDescription: "+getDescription();
        displayData += "\nDue Date: "+getDueDate();
        displayData += "\nPriority Level: "+priorityString;
        displayData += "\nTask status: "+getStatus();

        return displayData;
    }
}

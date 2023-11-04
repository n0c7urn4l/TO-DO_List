package todo_list_app;

import java.time.LocalDate;

/**
 * This Task class represents an individual task in a to-do list. It defines the properties and methods
 * that tasks should have, such as title, description, due date, status, and priority.
 */
public class Task {

    public static int taskId = 0;  // Static variable to assign a unique ID to each task
    private int currentTaskId;  // The current task's unique ID
    private String title;  // The title of the task
    private String description;   // The description or details of the task
    private LocalDate dueDate;  // The due date of the task
    private boolean status;  // The status of the task (ongoing or completed)

    private int priority = 0;  // The priority level of the task (1--> high 2-->normal 3-->low)

    /**
     * Checks if the task is complete.
     *
     * @return true if the task is complete, false if it's ongoing.
     */
    public boolean isComplete() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Gets the status of the task (ongoing or completed) as a string.
     *
     * @return "Ongoing" if the task is ongoing, "Completed" if it's completed.
     */
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

    /**
     * Constructor for creating a task with title, description, due date, and priority.
     *
     * @param title       The title of the task.
     * @param description The description of the task.
     * @param dueDate     The due date of the task.
     * @param priority    The priority level of the task.
     */
    public Task(String title, String description, LocalDate dueDate, int priority){
        currentTaskId = taskId++;  // Assign a unique ID to the task
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = false;  // Initially, the task is marked as ongoing
        this.priority = priority;
    }

    /**
     * Constructor for creating a task with title, due date, and priority. It sets the description to a default value("No Description").
     *
     * @param title    The title of the task.
     * @param dueDate  The due date of the task.
     * @param priority The priority level of the task.
     */
    public Task(String title, LocalDate dueDate, int priority){
        this(title,"No Description",dueDate,priority);
    }

    public void markAsComplete(){
        this.status = true;
    }

    /**
     * Generates a string representation of the task, including title, description, due date, priority, and status.
     *
     * @return A string with task information.
     */
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

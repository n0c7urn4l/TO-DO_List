/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package todo_list_app;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

/**
 * This class represents the main application for the to-do list. It allows users to interact with
 * a task-list and add, remove, complete, and edit tasks, and display task information.
 *
 * @author shanu
 */
public class ToDo_List_App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new ToDo_List_App().displayMainMenu();

    }

    private TaskList taskList = new TaskList();

    /**
     * Menu options Collection --> Display options and return the user input
     */
    private String displayMenuOptions(int menuId){
        Scanner sc = new Scanner(System.in);
        String option = null;
        switch (menuId){
            case 1:
                //main menu options
                System.out.println("#---------------------------------------------------------------#");
                System.out.println("A:ADD-Task  B:Remove-All  C:Sort-List  D:Completed-Tasks  E:Show-Tasks-Due-Today  F:Exit");
                System.out.println("Select a task-number or an option:");
                option = sc.nextLine();
                break;
            case 2:
                //task menu options
                System.out.println("#------------------------------------------------------#");
                System.out.println("A:Mark-as-Completed  B:Remove-task  C:Edit-Task  D:Back");
                System.out.println("Select an option:");
                option = sc.nextLine();
                break;
            case 3:
                //sorting options
                System.out.println("#----------------------------------------#");
                System.out.println("A:By-DueDate  B:By-Title  C:By-Description  D:By-Added-order  E:By-Priority");
                System.out.println("Select sorting option:");
                option = sc.nextLine();
                break;
            case 4:
                //go back option
                System.out.println("#--------#");
                System.out.println("A:Go-Back");
                System.out.println("Select an option:");
                option = sc.nextLine();
                break;
            case 5:
                //task edit options
                System.out.println("#---------------------------------------#");
                System.out.println("A:Title  B:Description  C:Due Date  D:Priority");
                System.out.println("Select sorting option:");
                option = sc.nextLine();
                break;
            case 6:
                //options for due today tasks list
                System.out.println("#--------#");
                System.out.println("A:Go-Back");
                System.out.println("Select a task-number or an option:");
                option = sc.nextLine();
                break;
            default:
                System.out.println("Wrong menu Id");
                break;
        }
        return option;
    }

    /**
     * Create Task object and Node object
     * Add task object to the Node Object
     * Add taskNode to the TaskList
     */
    private void addTask(){

        Scanner sc = new Scanner(System.in);
        Task task = null;
        System.out.println("\n---Enter Task Data---");
        System.out.println("Title: ");
        String title = sc.nextLine();
        System.out.println("Do you want to add a description?(y/n)");
        String option1 = sc.nextLine();
        if(Objects.equals(option1, "y")){
            System.out.println("Description: ");
            String description = sc.nextLine();
            System.out.println("Due Date: ");
            LocalDate dueDate = getDate();
            int priority = getPriority();
            task = new Task(title,description,dueDate,priority);
            Node newNode = new Node(task);
            taskList.addNode(newNode);
            System.out.println("-Task added-");
        }else if(Objects.equals(option1,"n")){
            System.out.println("Due Date: ");
            LocalDate dueDate = getDate();
            int priority = getPriority();
            task = new Task(title,dueDate,priority);
            Node newNode = new Node(task);
            taskList.addNode(newNode);
            System.out.println("-Task added-");
        }else{
            System.out.println("_Wrong input try again_");
            addTask();
        }
        System.out.println("Want to add more?(yes->y/no->enter any other character): ");
        String option2 = sc.nextLine();
        if(Objects.equals(option2,"y")){
            addTask();
        }else{
            displayMainMenu();
        }

    }

    /**
     * Fetch and return priority level from user
     */
    private int getPriority(){
        Scanner sc = new Scanner(System.in);
        System.out.println("#--------------------#");
        System.out.println("Select priority level:");
        System.out.println("    1:High  2:Normal  3:Low");
        String option = sc.nextLine();
        int priority = 0;
        if(option.equals("1")){
            priority = 1;
        }else if(option.equals("2")){
            priority = 2;
        }else if(option.equals("3")){
            priority = 3;
        }else{
            System.out.println("Wrong input try again!");
            priority = getPriority();
        }
        return priority;
    }

    /**
     * Fetch and return LocalDate from the user
     */
    private LocalDate getDate(){
        Scanner sc = new Scanner(System.in);
        int year,month,day;
        System.out.println("Date input:");
        System.out.println("    *Enter year:");
        year = sc.nextInt();
        sc.nextLine();
        System.out.println("    *Enter month:");
        month = sc.nextInt();
        sc.nextLine();
        System.out.println("    *Enter day:");
        day = sc.nextInt();
        sc.nextLine();
        LocalDate fetchedDate = LocalDate.of(year,month,day);
        if(fetchedDate.isBefore(LocalDate.now())){
            System.out.println("Date has passed. Try upcoming date!");
            fetchedDate = getDate();
        }
        return fetchedDate;
    }

    /**
     * Displays the main menu options and handles user interactions with the task-list.
     */
    public void displayMainMenu(){
        taskList.displayList(1);  // Display the list of tasks
        System.out.println("### MAIN MENU ###\n");
        String option = displayMenuOptions(1);  // Display main menu options and get user input
        if(option.matches("[A-Z]+")){
            if(option.matches("[ABCDEF]")){
                switch(option){
                    case "A":
                        addTask();  // Add a new task
                        break;
                    case "B":
                        taskList = new TaskList();  // Remove all tasks
                        Task.taskId = 0;
                        System.out.println("List cleared");
                        displayMainMenu();
                        break;
                    case "C":
                        String sortOption = displayMenuOptions(3);  // Display sorting options and get user input
                        if(sortOption.matches("[ABCDE]")){
                            taskList.selectionSort(sortOption);  // Sort the task list
                            displayMainMenu();
                        }else{
                            System.out.println("Wrong sort option try again");
                            displayMainMenu();
                        }
                        break;
                    case "D":
                        taskList.showCompletedTasks();  // Display completed tasks
                        if(displayMenuOptions(4).equals("A")){
                            displayMainMenu();
                        }else{
                            System.out.println("Wrong input Try again!");
                            displayMainMenu();
                        }
                        break;
                    case "E":
                        if(taskList.isEmpty()){
                            displayMainMenu();
                        }else{
                            displayTaskDueTodayMenu();  // Display tasks due today
                        }

                        break;
                    case "F":
                        System.exit(0);  // Exit the application
                        break;
                    default:
                        System.out.println("Wrong main menu option");
                        break;
                }
            }else{
                System.out.println("Wrong main menu input try again");
                displayMainMenu();
            }
        }else if(option.matches("\\d+")){
            int taskNum = Integer.parseInt(option);
            if(!(taskNum>taskList.nodeArrCount)&&(taskNum != 0)){
                Node taskNode = taskList.nodeArr[taskNum-1];
                taskMenu(taskNode);  // Display the task menu
            }else{
                System.out.println("Wrong input try again");
                displayMainMenu();
            }
        }else{
            System.out.println("Wrong input try again");
            displayMainMenu();
        }

    }

    /**
     * Displays the menu for tasks due today and handles user interactions with these tasks.
     */
    public void displayTaskDueTodayMenu(){
        String option = null;
        taskList.displayList(2);  // Display tasks due today
        if(taskList.nodeArrCount != 0){
            option = displayMenuOptions(6);  // Display due today task menu options and get user input
            if(option.equals("A")){
                displayMainMenu();
            }else if(option.matches("\\d+")){
                int taskNum = Integer.parseInt(option);
                if(!(taskNum>taskList.nodeArrCount)&&(taskNum != 0)){
                    Node taskNode = taskList.nodeArr[taskNum-1];
                    taskMenu(taskNode);  // Display the task menu
                }else{
                    System.out.println("Wrong input try again");
                    displayTaskDueTodayMenu();
                }
            }else{
                System.out.println("Wrong input try again");
                displayTaskDueTodayMenu();
            }
        }else {
            System.out.println("           NO TASKS DUE");
            System.out.println("              TODAY ;)\n");
            option = displayMenuOptions(4);  // Display "Go Back" option and get user input
            if(option.equals("A")){
                displayMainMenu();
            }else{
                System.out.println("Wrong option try again!");
                displayTaskDueTodayMenu();
            }

        }

    }

    /**
     * Displays the task menu for a specific task and handles user interactions with that task.
     *
     * @param currentTaskNode The node representing the current task.
     */
    public void taskMenu(Node currentTaskNode){
        System.out.println("### TASK MENU ###");
        currentTaskNode.display(0);
        String option = displayMenuOptions(2);  // Display task menu options and get user input
        switch(option){
            case "A":
                taskList.markAsComplete(currentTaskNode.getTask());  // Mark the task as completed
                System.out.println("Task mark as completed");
                taskList.selectionSort("A");
                displayMainMenu();
                break;
            case "B":
                taskList.remove(currentTaskNode.getTask());  // Remove the task
                System.out.println("Task removed from the list");
                taskList.selectionSort("A");
                displayMainMenu();
                break;
            case "C":
                taskEditMenu(currentTaskNode);  // Edit the task details
                break;
            case "D":
                displayMainMenu();
                break;
            default:
                System.out.println("Task menu wrong input");
                break;
        }

    }

    /**
     * Displays the menu for editing task details and handles user interactions for editing tasks.
     *
     * @param currentTaskNode The node representing the current task.
     */
    private void taskEditMenu(Node currentTaskNode){
        Scanner sc = new Scanner(System.in);
        System.out.println("Edit By:");
        String option = displayMenuOptions(5);  // Display task edit options and get user input
        if(option.matches("[ABCD]")){
            switch(option){
                case "A":
                    System.out.println("Enter new title:");
                    String title = sc.nextLine();
                    currentTaskNode.getTask().setTitle(title);
                    System.out.println("Edit successful");
                    displayMainMenu();
                    break;
                case "B":
                    System.out.println("Enter new description:");
                    String description = sc.nextLine();
                    currentTaskNode.getTask().setDescription(description);
                    System.out.println("Edit successful");
                    displayMainMenu();
                    break;
                case "C":
                    System.out.println("Enter new due date:");
                    LocalDate newDate = getDate();
                    currentTaskNode.getTask().setDueDate(newDate);
                    System.out.println("Edit successful");
                    displayMainMenu();
                    break;
                case "D":
                    currentTaskNode.getTask().setPriority(getPriority());
                    System.out.println("Edit successful");
                    displayMainMenu();
                    break;
                default:
                    System.out.println("Wrong task edit option");
                    break;
            }
        }else{
            System.out.println("Wrong edit option try again");
            displayMainMenu();
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package todo_list_app;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Objects;
import java.util.Scanner;

/**
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

    private String displayMenuOptions(int menuId){
        /*
            Display menu options
         */
        Scanner sc = new Scanner(System.in);
        String option = null;
        switch (menuId){
            case 1:
                //main menu options
                System.out.println("#---------------------------------------------------------------#");
                System.out.println("A:ADD-Task  B:Remove-All  C:Sort-List  D:Completed-Tasks  E:Exit");
                System.out.println("Select a task-number or option:");
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
            default:
                System.out.println("Wrong menu Id");
                break;
        }
        return option;
    }

    private void addTask(){
        /*
            Add tasks to the TaskList in due date order
        */
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
            task = new Task(title,description,dueDate);
//            System.out.println("task = new Task(title,description,dueDate);");
            Node newNode = new Node(task);
            taskList.addNode(newNode);
            System.out.println("-Task added-");
        }else if(Objects.equals(option1,"n")){
            System.out.println("Due Date: ");
            LocalDate dueDate = getDate();
            task = new Task(title,dueDate);
            Node newNode = new Node(task);
//            System.out.println("Node newNode = new Node(task);");
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

    private LocalDate getDate(){
        /*
            Fetch and return LocalDate from user
         */
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

    public void displayMainMenu(){
        /*
                MAIN MENU for TODO List App
         */
        System.out.println("### MAIN MENU ###\n");
        taskList.displayList();
        String option = displayMenuOptions(1);
        if(option.matches("[A-Z]+")){
            if(option.matches("[ABCDE]")){
                switch(option){
                    case "A":
                        addTask();
                        break;
                    case "B":
                        taskList = new TaskList();
                        Task.taskId = 0;
                        System.out.println("List cleared");
                        displayMainMenu();
                        break;
                    case "C":
                        String sortOption = displayMenuOptions(3);
                        if(sortOption.matches("[ABCDE]")){
                            taskList.selectionSort(sortOption);
                            displayMainMenu();
                        }else{
                            System.out.println("Wrong sort option try again");
                            displayMainMenu();
                        }
                        break;
                    case "D":
                        taskList.showCompletedTasks();
                        if(displayMenuOptions(4).equals("A")){
                            displayMainMenu();
                        }else{
                            displayMainMenu();
                        }
                        break;
                    case "E":
                        System.exit(0);
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
                taskMenu(taskNode);
            }else{
                System.out.println("Wrong input try again");
                displayMainMenu();
            }
        }else{
            System.out.println("Wrong input try again");
            displayMainMenu();
        }

    }

    public void taskMenu(Node currentTaskNode){

        /*
                TASK MENU for the TODO List app
         */

        System.out.println("### TASK MENU ###");
        currentTaskNode.display(0);
        String option = displayMenuOptions(2);
        switch(option){
            case "A":
                taskList.markAsComplete(currentTaskNode.getTask());
                System.out.println("Task mark as completed");
                displayMainMenu();
                break;
            case "B":
                taskList.remove(currentTaskNode.getTask());
                System.out.println("Task removed from the list");
                displayMainMenu();
                break;
            case "C":
                taskEditMenu(currentTaskNode);
                break;
            case "D":
                displayMainMenu();
                break;
            default:
                System.out.println("Task menu wrong input");
                break;
        }

    }

    private void taskEditMenu(Node currentTaskNode){

        /*
                Used to Edit task details
         */

        Scanner sc = new Scanner(System.in);
        System.out.println("Edit By:");
        String option = displayMenuOptions(5);
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
                    System.out.println("Not done yet");
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package todo_list_app;
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

        // TODO code application logic here
    }

    private TaskList taskList;

    private void menuOptions(int menuId){

        switch (menuId){
            case 1:
                System.out.println("#---------------------------------------------------------------#");
                System.out.println("A:ADD-Task  B:Remove-All  C:Sort-List  D:Completed-Tasks  E:Exit");
                System.out.println("Select a task/option: ");
                break;
            case 2:
                System.out.println("#------------------------------------------------------#");
                System.out.println("A:Mark-as-Completed  B:Remove-task  C:Edit-Task  D:Back");
                System.out.println("Select an option: ");
                break;
            case 3:
                System.out.println("#-----------------------#");
                System.out.println("A:By-DueDate  B:By-Title");
                System.out.println("Select sorting option: ");
                break;
            case 4:
                System.out.println("#--------#");
                System.out.println("A:Go-Back");
                System.out.println("Select an option: ");
            default:
                System.out.println("Wrong menu Id");
                break;
        }
    }

    private void menuManager(){

    }

    private void displayList(){



    }
    
}

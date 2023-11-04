package todo_list_app;

/**
 * This class represents a node for the TaskList linked list. It contains task data and references to the next
 * and previous nodes.
 */
public class Node {

    private Task taskData;  // Task data stored in this node
    public Node next;  // Reference to the next node in the linked list
    public Node previous;  // Reference to the previous node in the linked list

    /**
     * Gets the task data stored in this node.
     *
     * @return The task data.
     */
    public Task getTask(){
        return taskData;
    }

    /**
     * Sets the task data for this node.
     *
     * @param taskData The task data to set.
     */
    public void setTask(Task taskData){this.taskData = taskData;}

    /**
     * Constructor for creating a new node with the given task data. Initializes next and previous references to null.
     *
     * @param taskData The task data to be stored in the node.
     */
    public Node(Task taskData){
        this.taskData = taskData;
        next = null;
        previous = null;
    }

    /**
     * Displays the task information within the node along with the task number.
     *
     * @param count The task number to display.
     */
    public void display(int count){
        System.out.println("-----------------------------------------------------------");
        System.out.println("Task-Number: "+count);
        System.out.println(taskData.toString());
        System.out.println("-----------------------------------------------------------\n");
    }

}

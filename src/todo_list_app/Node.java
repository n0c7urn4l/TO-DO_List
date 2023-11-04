package todo_list_app;

public class Node {

    private Task taskData;
    public Node next;
    public Node previous;

    public Task getTask(){
        return taskData;
    }
    public void setTask(Task taskData){this.taskData = taskData;}

    public Node(Task taskData){
        this.taskData = taskData;
        next = null;
        previous = null;
    }

    public void display(int count){
        System.out.println("-----------------------------------------------------------");
        System.out.println("Task-Number: "+count);
        System.out.println(taskData.toString());
        System.out.println("-----------------------------------------------------------\n");
    }

}

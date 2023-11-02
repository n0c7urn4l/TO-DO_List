package todo_list_app;

public class Node {

    private Task taskData;
    public Node next;
    public Node previous;

    public Task getTask(){
        return taskData;
    }

    public Node(Task taskData){
        this.taskData = taskData;
        next = null;
        previous = null;
    }

    public void display(int count){
        System.out.println("-----------------------------------------------------------");
        System.out.println(count);
        System.out.println("Title: "+taskData.getTitle());
        System.out.println("Description: "+taskData.getDescription());
        System.out.println(("Due Date: "+taskData.getDueDate()));
        System.out.println("-----------------------------------------------------------");
    }

}

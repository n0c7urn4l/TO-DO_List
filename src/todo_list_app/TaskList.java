package todo_list_app;

import java.time.LocalDate;

//Implementing using a ordered linked list which can traverse both sides
public class TaskList {

    private Node firstNode;
    private Node lastNode;
    private int nodeCount = 0;

    public TaskList(){
        firstNode = null;
        lastNode = null;
    }

    public void addNode(Node node){
        boolean flag = true;
        //Node prevNode = null;
        Node currentNode = firstNode;
        if(nodeCount == 0){
            firstNode = node;
            lastNode = node;
            nodeCount++;
        }else{

            //DO THIS AGAIN
            /*
            for (int i = 1;i<=nodeCount;i++){
                if((currentNode.next != null)&&(currentNode.getTask().getDueDate().isBefore(node.getTask().getDueDate()))&&(currentNode.getTask().getCurrentTaskId()<node.getTask().getCurrentTaskId())){
                    currentNode = currentNode.next;
                }else{
                    break;
                }
            }

            if(currentNode.next == null){
                node.previous = currentNode;
                currentNode.next = node;
                nodeCount++;
            }else{
                node.previous = currentNode;
                node.next = currentNode.next;
                currentNode.next.previous = node;
                nodeCount++;
            }
            */
        }
    }

    public void delete(Task task){
        Node currentNode = firstNode;
        boolean flag = true;
        int count = 0;
        if(nodeCount != 0){
            while(flag){

                if(currentNode.getTask().equals(task)){
                    if(currentNode.next == null){
                        currentNode.previous.next = null;
                        nodeCount--;
                    }else{
                        currentNode.next.previous = currentNode.previous;
                        currentNode.previous.next = currentNode.next;
                        nodeCount--;
                    }
                }else if(count == nodeCount){
                    System.out.println("### Task not found ###");
                    flag = false;
                }
                count++;
            }

        }else{
            System.out.println("### List is empty ###");
        }
    }

}

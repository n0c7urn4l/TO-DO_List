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

    private void addFront(Node node){
        node.next = firstNode;
        firstNode.previous = node;
        firstNode = node;
        nodeCount++;

    }

    private void addLast(Node node){
        lastNode.next = node;
        node.previous = lastNode;
        lastNode = node;
        nodeCount++;
    }

    private void addLeft(Node currentNode,Node node){
        Node tempNode = currentNode.previous;
        currentNode.previous = node;
        node.next = currentNode;
        node.previous = tempNode;
        tempNode.next = node;
        nodeCount++;
    }

//    private void addRight(Node currentNode,Node node){
//        Node tempNode = currentNode.next;
//        currentNode.next = node;
//        node.previous = currentNode;
//        node.next = tempNode;
//        tempNode.previous = node;
//    }

    public void addNode(Node node){
        Node currentNode = firstNode;

        if(nodeCount == 0){
            firstNode = node;
            lastNode = node;
            nodeCount++;
        }else if(nodeCount == 1){

            if(node.getTask().getDueDate().isBefore(currentNode.getTask().getDueDate())){
                addFront(node);
            }else{
                addLast(node);
            }

        }else{

            for(int i=1;i<=nodeCount;i++){

                if((node.getTask().getDueDate().equals(currentNode.getTask().getDueDate()))&&(currentNode.next == null)){
                    addLast(node);
                }else if((node.getTask().getDueDate().isBefore(currentNode.getTask().getDueDate()))){
                    if(currentNode == firstNode){
                        addFront(node);
                    }else{
                        addLeft(currentNode,node);
                    }
                }else{
                    currentNode = currentNode.next;
                }

            }

        }
    }

    public void remove(Task task){
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

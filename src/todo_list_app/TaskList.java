package todo_list_app;

import java.time.LocalDate;

//Implementing using an ordered linked list which can traverse both sides
public class TaskList {

    private Node firstNode;
    private Node lastNode;
    private int nodeCount = 0; // keep track of total nodes created in the list

    //To temporary store nodes when displaying tasks(in displayList method) to fetch them when user requests using index;
    public Node[] nodeArr;
    public int nodeArrCount = 0;

    private final int maxCompletedTasks = 100;

    public int completedTaskCount = 0; //keep track of the number of completed tasks int the completedTaskArr[]


    //To store completed Task Nodes
    public Task[] completedTaskArr = new Task[maxCompletedTasks]; // Array to store completed tasks list reset when task count reached 100


    public TaskList(){
        firstNode = null;
        lastNode = null;
    }

    public boolean isEmpty(){
        return nodeCount == 0;
    } // check whether the list is empty or not



    public void addNode(Node node){
        /*
            Add new node to linked list
         */

        if(nodeCount == 0){
            firstNode = node;
            lastNode = node;
            nodeCount++;
        }else{
            lastNode.next = node;
            node.previous = lastNode;
            lastNode = node;
            nodeCount++;
        }

    }

    private void removeFirstNode(){
        //remove first node of the linked list
        firstNode.next.previous = null;
        firstNode = firstNode.next;
        nodeCount--;
    }

    private void removeLastNode(){
        //remove last node of the linked list
        lastNode.previous.next = null;
        lastNode = lastNode.previous;
        nodeCount--;
    }
    public void remove(Task task){

        /*
                Remove Tasks by searching the task from the list
         */

        Node currentNode = firstNode;
        boolean flag = true;
        int count = 0;
        if(nodeCount>1){
            while(flag){

                if(currentNode.getTask().equals(task)){
                    if(currentNode.next == null){
                        removeLastNode();
                        flag = false;
                    }else if(currentNode.previous == null){
                        removeFirstNode();
                        flag = false;
                    }else{
                        currentNode.next.previous = currentNode.previous;
                        currentNode.previous.next = currentNode.next;
                        nodeCount--;
                        flag = false;
                    }
                }else if(count == nodeCount){
                    System.out.println("### Task not found ###\n");
                    flag = false;
                }
                currentNode = currentNode.next;
                count++;
            }

        }else if(nodeCount == 1){
            firstNode = null;
            lastNode = null;
            nodeCount--;
        }else{
            System.out.println("### List is empty ###\n");
        }
    }

    public void displayList(int listType){

        /*
                Display Task List
                listType == 1 --> display whole task list
                listType == 2 --> display only the tasks due current day
         */
        nodeArrCount = 0;
        if(isEmpty()){
            System.out.println("           TASK LIST EMPTY");
            System.out.println("             ADD TASKS ;)");
        }else if(listType == 1){
            System.out.println("---ALL TASKS---\n");
            nodeArr = new Node[100];
            Node currentNode = firstNode;
            LocalDate currentDateRecord = null;
            for (int i =1;i<=nodeCount;i++){

                if(!currentNode.getTask().getDueDate().equals(currentDateRecord)){
                    currentDateRecord = currentNode.getTask().getDueDate();
                    if(currentDateRecord.equals(LocalDate.now())){
                        System.out.println("***DUE TODAY***");
                    }else{
                        System.out.println("***DUE "+currentDateRecord+"***");
                    }
                }

                currentNode.display(i);
                nodeArrCount++;
                nodeArr[i-1] = currentNode;
                currentNode = currentNode.next;
            }
        }else if(listType == 2){
            selectionSort("A");
            nodeArr = new Node[100];
            Node currentNode = firstNode;
            LocalDate currentDateRecord = LocalDate.now();
            System.out.println("---TASKS DUE TODAY---\n");
            for (int i =1;i<=nodeCount;i++){
                if(currentNode.getTask().getDueDate().equals(currentDateRecord)){
                    currentNode.display(i);
                    nodeArrCount++;
                    nodeArr[i-1] = currentNode;
                }
                currentNode = currentNode.next;
            }

        }

    }

    public void selectionSort(String sortType){
        /*
            SORTING ALGORITHM For the linked list
            sortType = B --> sort by title
            sortType = A --> sort by DueDate
            sortType = C --> sort by description
            sortType = D --> sort by task added order
            sortType = E --> sort by task priority
         */
        Node currentNode = firstNode;
        while(currentNode != null){
            Node minNode = currentNode;
            Node tempNode = currentNode.next;
            while(tempNode != null) {

                if (sortType.equals("B")) {
                    if (tempNode.getTask().getTitle().compareTo(minNode.getTask().getTitle()) < 0) {
                        minNode = tempNode;
                    }
                } else if (sortType.equals("A")) {
                    if (tempNode.getTask().getDueDate().isBefore(minNode.getTask().getDueDate())) {
                        minNode = tempNode;
                    }
                } else if (sortType.equals("C")) {
                    if ((tempNode.getTask().getDescription().compareTo(minNode.getTask().getDescription()) < 0)) {
                        minNode = tempNode;
                    }
                } else if (sortType.equals("D")) {
                    if (tempNode.getTask().getCurrentTaskId() < minNode.getTask().getCurrentTaskId()) {
                        minNode = tempNode;
                    }
                }else if (sortType.equals("E")){
                    if(tempNode.getTask().getPriority()<minNode.getTask().getPriority()){
                        minNode = tempNode;
                        break;
                    }
                }else{
                    System.out.println("Wrong sort input");
                    break;
                }
                tempNode = tempNode.next;
            }

            Task swapTask = currentNode.getTask();
            currentNode.setTask(minNode.getTask());
            minNode.setTask(swapTask);

            currentNode = currentNode.next;

        }

    }

    public void markAsComplete(Task completedTask){
        if(completedTaskCount == maxCompletedTasks){
            //reset the completed task array if array gets full
            completedTaskArr = new Task[maxCompletedTasks];
        }
        completedTaskArr[completedTaskCount] = completedTask;
        completedTask.markAsComplete();
        remove(completedTask);
        completedTaskCount++;
    }
    public void showCompletedTasks(){
        if(completedTaskCount ==0){
            System.out.println("        No Completed Tasks");
            System.out.println("            to show :(");
        }else{
            for(int i = 0;i<completedTaskCount;i++){
                System.out.println("-----------------------------------------------------------");
                System.out.println(i+1);
                System.out.println(completedTaskArr[i].toString());
                System.out.println("-----------------------------------------------------------\n");

            }
        }

    }



}

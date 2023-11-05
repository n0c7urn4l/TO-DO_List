package todo_list_app;

import java.time.LocalDate;

/**
 * T.G.D.S.Chathuranga  index: AS2021367
 * This TaskList class represents a list of tasks using a linked list data structure.
 * It provides methods for adding, removing, displaying, and sorting tasks, as well
 * as marking tasks as completed and displaying completed tasks.
 */
public class TaskList {

    private Node firstNode; //Head node(first node) of the linked list
    private Node lastNode;  //Tail node(last node) of the linked list
    private int nodeCount = 0; // keep track of total nodes created in the list

    public Node[] nodeArr;  //To temporary store nodes when displaying tasks(in displayList method) to fetch them when user requests using index;
    public int nodeArrCount = 0; //keep track of the number of nodes in the nodeArr[]

    private final int maxCompletedTasks = 100;  //Maximum size of the completedTaskArr[]

    public int completedTaskCount = 0; //keep track of the number of completed tasks int the completedTaskArr[]


    //To store completed Task Nodes
    public Task[] completedTaskArr = new Task[maxCompletedTasks]; // Array to store completed tasks list reset when task count reached 100


    public TaskList(){
        firstNode = null;
        lastNode = null;
    }

    /**
     * Check whether the task list is empty or not.
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty(){
        return nodeCount == 0;
    }



    /**
     * Add new node to the linked list
     */
    public void addNode(Node node){
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

    /**
     * remove first node of the linked list
     */
    private void removeFirstNode(){
        firstNode.next.previous = null;
        firstNode = firstNode.next;
        nodeCount--;
    }


    /**
        remove last node of the linked list
     */
    private void removeLastNode(){
        lastNode.previous.next = null;
        lastNode = lastNode.previous;
        nodeCount--;
    }

    /**
     * Remove Tasks by searching the task from the list
     */
    public void remove(Task task){
        Node currentNode = firstNode;
        boolean flag = true;
        int count = 0;
        if(nodeCount>1){
            while(flag){

                if(currentNode.getTask().equals(task)){
                    if(currentNode.next == null){ // when node to be removed is the last node
                        removeLastNode();
                        flag = false;
                    }else if(currentNode.previous == null){ // when node to be removed is the first node
                        removeFirstNode();
                        flag = false;
                    }else{ // when node to be removed is between two nodes
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

    /**
     * Display the task list based on the specified listType.
     *
     * @param listType 1 for displaying the whole list, 2 for displaying tasks due today.
     */
    public void displayList(int listType){
        nodeArrCount = 0;
        if(isEmpty()){ // when task-list is empty display "empty task list" message
            System.out.println("           TASK LIST EMPTY");
            System.out.println("             ADD TASKS ;)");
        }else if(listType == 1){  //display whole list
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
        }else if(listType == 2){ // display tasks due today
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

    /**
     * Sort the linked list based on the specified sortType.
     *
     * @param sortType Sorting criteria ("A" for due date, "B" for title, "C" for description, "D" for task added order, "E" for priority)
     */
    public void selectionSort(String sortType){
        Node currentNode = firstNode;
        while(currentNode != null){
            Node minNode = currentNode;
            Node tempNode = currentNode.next;
            while(tempNode != null) {

                if (sortType.equals("B")) { //sort by title
                    if (tempNode.getTask().getTitle().compareTo(minNode.getTask().getTitle()) < 0) {
                        minNode = tempNode;
                    }
                } else if (sortType.equals("A")) {  //sort by due date
                    if (tempNode.getTask().getDueDate().isBefore(minNode.getTask().getDueDate())) {
                        minNode = tempNode;
                    }
                } else if (sortType.equals("C")) {  //sort by description
                    if ((tempNode.getTask().getDescription().compareTo(minNode.getTask().getDescription()) < 0)) {
                        minNode = tempNode;
                    }
                } else if (sortType.equals("D")) {  //sort by task added order
                    if (tempNode.getTask().getCurrentTaskId() < minNode.getTask().getCurrentTaskId()) {
                        minNode = tempNode;
                    }
                }else if (sortType.equals("E")){  //sort by priority
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

    /**
     * Mark a task as completed, remove it from the list, and store it in the completed tasks array.
     *
     * @param completedTask The task to mark as completed.
     */
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

    /**
     * Display the list of completed tasks.
     */
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
